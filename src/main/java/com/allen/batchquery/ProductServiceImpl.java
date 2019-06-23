package com.allen.batchquery;

import java.util.*;
import java.util.concurrent.*;

/**
 * @ClassName:
 * @description: 批量查询列子
 * @author: Allen
 * @create: 2019-06-23 09:55
 **/
public class ProductServiceImpl {

    /***
     * 接收请求放入队列
     */
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue();

    /***
     * 数据库数据(临时)
     */
    public static Map<String, Product> productMap = new ConcurrentHashMap<>();


    static {
        for (int i = 0; i < 1000; i++) {
            Product product = Product.getProduct(String.valueOf((i + 1)), UUID.randomUUID().toString());
            productMap.put(product.getProductId(),product);
        }
    }


    /****
     * 查询商品明细
     * 调用远程的商品信息查询接口(每一次请求，都调用一次,没有利用到后端批量性能提升)
     * @param productId
     * @return
     */
    public Product queryProductDetail(String productId) throws ExecutionException, InterruptedException {
        //目标： 因为后端 redis  数据库 微服务 都支持批量
        boolean flag = true;
        //动态配置，是否使用批量查询功能
        if (flag) {
            Request request = new Request();
            request.productId = productId;
            //异步变成 获取异步执行结果的工具
            CompletableFuture<Product> result = new CompletableFuture<>();
            request.result = result;
            queue.add(request);
            //剩下的事情 ，等待请求结束（这个请求不再是同步请求，交给计划任务异步处理）
            return result.get();
        } else {//走普通查询方法
            return productMap.get(productId);
        }

    }

    /***
     * 返回 商品列表
     * @param productIds
     * @return
     */
    public List<Product> queryBatchProductDetail(Set<String> productIds) {
        List<Product> products = new ArrayList<>();
        for(String productId:productIds){
            products.add(productMap.get(productId));
        }
        return products;
    }


    //启动时开启一个计划任务去执行
//    @PostConstruct
    public void initializer() {//吃实话对象后，执行一次

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //每隔10秒去获取数据
        scheduledThreadPool.scheduleAtFixedRate(() -> {

            System.out.println("scheduled exec");

            if (queue.size() == 0) {
                return;
            }
            //发起批量查询,组装productIds请求参数，并取出所有请求Request
            Set<String> productIds = new HashSet<>();
            List<Request> requests = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                Request request = queue.poll();
                requests.add(request);
                productIds.add(request.productId);
            }


            //根据productIds参数获取请求结果
            Map<String, Product> resultMap = new HashMap<>();
            List<Product> responseResult = queryBatchProductDetail(productIds);
            System.out.println("此次请求数量:" + queue.size() + ", 批量查询的结果是:" + responseResult);
            if (responseResult != null && responseResult.size() > 0) {
                responseResult.forEach(product -> {
                    resultMap.put(product.getProductId(), product);
                });
            }

            //为每次请求返回对应结果
            for (Request request : requests) {
                Product result = resultMap.get(request.productId);
                // 通过每一个用户请求的线程(线程A -- 发送 -- 线程B) 结果
                request.result.complete(result);
            }

            //业务 TODO 去除这段时间积累的请求，生成批量接口调用
        }, 0, 10, TimeUnit.MILLISECONDS);

    }


    class Request {
        private String productId;
        private CompletableFuture<Product> result;
    }


}
