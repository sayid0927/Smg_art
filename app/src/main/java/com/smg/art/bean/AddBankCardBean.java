package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class AddBankCardBean {

    /**
     * status : 103
     * msg : 银行卡号错误
     * data : null
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
