package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 2017/9/22.
 */
public class Product {

    //'物品Id'
    private String productId;
    //'描述'
    private String describ;
    //'更新时间'
    private Date updateTime;
    //'创建时间'
    private Date createTime;

    public String getProductId() {
        return productId;
    }

    public String getDescrib() {
        return describ;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
