package com.smg.art.bean;

import java.util.List;

/**
 * Created by Mervin on 2018/5/5 0005.
 */

public class Feedback {

    /**
     * error_code : 0
     * data : [{"id":"1","t_value":"产品建议"},{"id":"2","t_value":"程序错误"},{"id":"3","t_value":"违规投诉"},{"id":"4","t_value":"测试反馈"}]
     */

    private int error_code;
    private List<DataBean> data;
    private String error_msg;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * t_value : 产品建议
         */

        private String id;
        private String t_value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getT_value() {
            return t_value;
        }

        public void setT_value(String t_value) {
            this.t_value = t_value;
        }
    }
}
