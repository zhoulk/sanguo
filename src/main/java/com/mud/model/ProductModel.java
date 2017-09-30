package com.mud.model;

import com.mud.mapper.Product;
import com.mud.mapper.UserBag;

/**
 * Created by leeesven on 2017/9/25.
 */
public class ProductModel {

    //'物品Id'
    private String productId;
    //'描述'
    private String describ;

    // '物品数量'
    private int num;

    // 合成数量
    private int intoNum;

    public ProductModel(Product product){
        this.productId = product.getProductId();
        this.describ = product.getDescrib();
    }

    public ProductModel(Product product, UserBag userBag){
        this.productId = product.getProductId();
        this.describ = product.getDescrib();

        this.num = userBag.getNum();
    }

    public String getProductId() {
        return productId;
    }

    public String getDescrib() {
        return describ;
    }

    public int getNum() {
        return num;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIntoNum() {
        return intoNum;
    }

    public void setIntoNum(int intoNum) {
        this.intoNum = intoNum;
    }
}
