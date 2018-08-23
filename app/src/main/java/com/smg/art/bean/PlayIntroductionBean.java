package com.smg.art.bean;

/**
 * Created by Lenovo on 2018/8/21.
 */

public class PlayIntroductionBean {


    /**
     * status : 1
     * msg : 获取竞拍支付说明成功
     * data : {"payIntroduction":"1.若竞拍不成功，保证金将全额退还;2.若竞拍成功，保证金如果小于成交价，将转为支付的一部分，请在72小时内支付尾款，逾期未付款将扣除保证金;"}
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
         * payIntroduction : 1.若竞拍不成功，保证金将全额退还;2.若竞拍成功，保证金如果小于成交价，将转为支付的一部分，请在72小时内支付尾款，逾期未付款将扣除保证金;
         */

        private String payIntroduction;

        public String getPayIntroduction() {
            return payIntroduction;
        }

        public void setPayIntroduction(String payIntroduction) {
            this.payIntroduction = payIntroduction;
        }
    }
}
