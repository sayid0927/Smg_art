package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class WalletBalanceBean {

    /**
     * status : 1
     * msg : OK
     * data : 0.00
     */

    private int status;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
