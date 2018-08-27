package com.smg.art.bean;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class ServiceBean {

    /**
     * status : 1
     * msg : 获取平台热线电话成功
     * data : {"hotline":"4006008079"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hotline : 4006008079
         */

        private String hotline;

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }
    }
}
