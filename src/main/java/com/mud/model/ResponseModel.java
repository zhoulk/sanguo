package com.mud.model;

import com.mud.property.ResponseCode;

/**
 * Created by leeesven on 17/8/21.
 */
public class ResponseModel {

    private ResponseCode code;
    private String msg;
    private Object data;

    public ResponseCode getCode() {
        if (code == null)
            code = ResponseCode.SYS_SUCCESS;
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getMsg() {
        if (msg == null)
            if (code != null){
                msg = code.getDesc();
            }else {
                msg = "";
            }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
