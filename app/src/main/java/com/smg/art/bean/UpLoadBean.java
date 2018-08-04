package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/1 0001.
 */

public class UpLoadBean {

    /**
     * msg : 上传成功
     * headImg : /static/uploads/userHeadImg/15303450529.jpg
     * status : 1
     */

    private String msg;
    private String headImg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
