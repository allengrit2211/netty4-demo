package com.allen.batchquery;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: TestRequest
 * @description:
 * @author: Allen
 * @create: 2019-06-23 12:16
 **/
public class TestRequest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ProductServiceImpl productService = new ProductServiceImpl();
        productService.initializer();
        int threadNum = 350000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);
        for (int i = 0; i < threadNum; i++) {
            final String productId = String.valueOf((i + 1));
            Thread thread = new Thread(() -> {
                try{
                    cyclicBarrier.await();
                    Product product1 = productService.queryProductDetail(productId);
                    System.out.println(Thread.currentThread().getName()+"查询结束，结果是:"+product1);

                }catch (Exception e){
                    e.printStackTrace();
                }

            });
            thread.setName("price-thread-" + productId);
            thread.start();

        }


    }

}
