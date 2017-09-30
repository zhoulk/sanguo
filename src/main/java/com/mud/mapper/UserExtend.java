package com.mud.mapper;

/**
 * Created by leeesven on 2017/9/26.
 */
public class UserExtend {

    // '用户编号'
    private String userId;
    // '扩展属性'
    private String prop;
    // '值'
    private String val;

    public String getUserId() {
        return userId;
    }

    public String getProp() {
        return prop;
    }

    public String getVal() {
        return val;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
