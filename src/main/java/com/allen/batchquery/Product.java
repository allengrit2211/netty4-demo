package com.allen.batchquery;

/**
 * @ClassName: Product
 * @description:
 * @author: Allen
 * @create: 2019-06-23 11:54
 **/
public class Product {


    private String productId;

    private String name;

    public static Product getProduct(String productId,String name) {
        Product product = new Product();
        product.productId = productId;
        product.name = name;
        return product;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("productId=%s, name=%s",productId,name);
    }
}
