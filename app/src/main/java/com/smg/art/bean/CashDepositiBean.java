package com.smg.art.bean;

import java.util.List;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public class CashDepositiBean {

    /**
     * status : 1
     * msg : OK
     * data : [{"id":8,"auctionId":1,"bidNo":"BM201807260001","actionName":"丰子恺《人物》","goodsId":23,"memberId":78,"amount":600,"createTime":"2018-07-30 10:53:55","status":0,"remark":null},{"id":9,"auctionId":2,"bidNo":"BM201807260002","actionName":"刘旦宅《人物》","goodsId":13,"memberId":78,"amount":600,"createTime":"2018-07-30 11:13:53","status":0,"remark":null},{"id":10,"auctionId":3,"bidNo":"BM201707260003","actionName":"冰芝玉翡翠珠宝 貔貅挂件 40.77g","goodsId":13,"memberId":78,"amount":600,"createTime":"2018-07-30 11:29:50","status":0,"remark":null}]
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
         * id : 8
         * auctionId : 1
         * bidNo : BM201807260001
         * actionName : 丰子恺《人物》
         * goodsId : 23
         * memberId : 78
         * amount : 600
         * createTime : 2018-07-30 10:53:55
         * status : 0
         * remark : null
         */

        private int id;
        private int auctionId;
        private String bidNo;
        private String actionName;
        private int goodsId;
        private int memberId;
        private double amount;
        private String createTime;
        private int status;
        private String remark;
        private int mainStatus;

        public int getMainStatus() {
            return mainStatus;
        }

        public void setMainStatus(int mainStatus) {
            this.mainStatus = mainStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAuctionId() {
            return auctionId;
        }

        public void setAuctionId(int auctionId) {
            this.auctionId = auctionId;
        }

        public String getBidNo() {
            return bidNo;
        }

        public void setBidNo(String bidNo) {
            this.bidNo = bidNo;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
