package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public class CurrencyExchangeRateBean {

    /**
     * status : 1
     * msg : OK
     * data : {"currency":"6.8","rmbAmount":"1095.59","amount":"7450.00"}
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
         * currency : 6.8
         * rmbAmount : 1095.59
         * amount : 7450.00
         */

        private String currency;
        private String rmbAmount;
        private String amount;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getRmbAmount() {
            return rmbAmount;
        }

        public void setRmbAmount(String rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
