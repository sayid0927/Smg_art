package com.smg.art.bean;

/**
 * Created by Lenovo on 2018/8/16.
 */

public class RongImStateBean {

    String msg;
    int state;

    public RongImStateBean(String msg, int state) {
        this.msg = msg;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
