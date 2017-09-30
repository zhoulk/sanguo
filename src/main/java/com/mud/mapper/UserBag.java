package com.mud.mapper;

import java.util.Date;

/**
 * Created by leeesven on 2017/9/25.
 */
public class UserBag {

    // '用户编号'
    private String userId;
    // '物品编号'
    private String productId;
    // '物品数量'
    private int num;
    // '更新时间'
    private Date updateTime;
    // '创建时间'
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public int getNum() {
        return num;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}


