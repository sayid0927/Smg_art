package com.smg.art.bean;

/**
 * Created by Lenovo on 2018/7/30.
 */

public class AuctionBuyerDepositBean {


    /**
     * status : 1002
     * msg : 用户未未通过实名认证
     * data : null
     */

    private int status;
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
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
