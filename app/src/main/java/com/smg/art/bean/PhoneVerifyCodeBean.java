package com.smg.art.bean;

/**
 * Created by Lenovo on 2018/7/24.
 */

public class PhoneVerifyCodeBean {


    /**
     * status : 1
     * msg : 手机验证码发送成功,3分钟内有效
     * data : {"verifyCode":"448062"}
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
         * verifyCode : 448062
         */

        private String verifyCode;

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }
    }
}
