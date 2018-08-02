package com.smg.art.bean;

import java.util.List;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class BalanceOfPayBean {


    /**
     * status : 1
     * msg : OK
     * data : [{"id":2,"memberId":78,"serialno":2,"amount":50,"type":2,"createTime":"2018-07-26 09:44:07","operName":"huanglf","descn":"支出"},{"id":7,"memberId":78,"serialno":153291923451239,"amount":100,"type":2,"createTime":"2018-07-30 10:53:55","operName":"78","descn":"支出"},{"id":16,"memberId":78,"serialno":153294017242953,"amount":700,"type":2,"createTime":"2018-07-30 16:42:52","operName":"78","descn":"支出"},{"id":26,"memberId":78,"serialno":153311173626408,"amount":100,"type":2,"createTime":"2018-08-01 16:22:16","operName":"78","descn":"支出"},{"id":27,"memberId":78,"serialno":153311178192154,"amount":100,"type":2,"createTime":"2018-08-01 16:23:02","operName":"78","descn":"支出"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * memberId : 78
         * serialno : 2
         * amount : 50.0
         * type : 2
         * createTime : 2018-07-26 09:44:07
         * operName : huanglf
         * descn : 支出
         */

        private int id;
        private int memberId;
        private String serialno;
        private double amount;
        private int type;
        private String createTime;
        private String operName;
        private String descn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getSerialno() {
            return serialno;
        }

        public void setSerialno(String serialno) {
            this.serialno = serialno;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOperName() {
            return operName;
        }

        public void setOperName(String operName) {
            this.operName = operName;
        }

        public String getDescn() {
            return descn;
        }

        public void setDescn(String descn) {
            this.descn = descn;
        }
    }
}
