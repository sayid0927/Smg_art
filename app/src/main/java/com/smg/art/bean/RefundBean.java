package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class RefundBean {

    /**
     * status : 1
     * msg : OK
     * data : {"id":23,"sellerMemberId":78,"sellerMemberName":null,"goodsId":15,"bidNo":"PM201808020003","actionName":"橄榄核手串 精雕大日如来 陈国栋刻","startPrice":1110,"stepSize":10,"nowprice":2000,"buyerMemberId":78,"bidCount":0,"startTime":1533312000000,"endTime":1535990400000,"stepTime":2,"deferredTime":2,"status":6,"feeStatus":1,"feeAmount":50,"returnFrontMoneyStatus":0,"frontMoneyAmount":50,"deliveryStatus":null,"approvalStatus":1,"applyStatus":1,"frontMoneyStatus":1,"pictureUrl":"44236b8b0d2b.jpg","auctionDesc":"\"/><\/a><\/p><p><br/><\/p>","certificateUrl":"6df5d64c7e86.jpg","complainId":1,"complainStatus":1,"complain":"投诉内容222","complainResult":null,"complainTime":1532680894000,"createTime":"2018-08-02 17:27:06","returnFrontMoneyTime":"2018-08-11 23:59:59","descn":null,"startCreateDate":null,"endCreateDate":null,"sysDate":null,"buyerEnsureAmount":50,"categoryId":null,"depositStatus":null,"deliveryTime":1533366201000,"finishTime":1533452601000}
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
         * id : 23
         * sellerMemberId : 78
         * sellerMemberName : null
         * goodsId : 15
         * bidNo : PM201808020003
         * actionName : 橄榄核手串 精雕大日如来 陈国栋刻
         * startPrice : 1110.0
         * stepSize : 10.0
         * nowprice : 2000.0
         * buyerMemberId : 78
         * bidCount : 0
         * startTime : 1533312000000
         * endTime : 1535990400000
         * stepTime : 2
         * deferredTime : 2
         * status : 6
         * feeStatus : 1
         * feeAmount : 50.0
         * returnFrontMoneyStatus : 0
         * frontMoneyAmount : 50.0
         * deliveryStatus : null
         * approvalStatus : 1
         * applyStatus : 1
         * frontMoneyStatus : 1
         * pictureUrl : 44236b8b0d2b.jpg
         * auctionDesc : "/></a></p><p><br/></p>
         * certificateUrl : 6df5d64c7e86.jpg
         * complainId : 1
         * complainStatus : 1
         * complain : 投诉内容222
         * complainResult : null
         * complainTime : 1532680894000
         * createTime : 2018-08-02 17:27:06
         * returnFrontMoneyTime : 2018-08-11 23:59:59
         * descn : null
         * startCreateDate : null
         * endCreateDate : null
         * sysDate : null
         * buyerEnsureAmount : 50.0
         * categoryId : null
         * depositStatus : null
         * deliveryTime : 1533366201000
         * finishTime : 1533452601000
         */

        private int id;
        private int sellerMemberId;
        private String sellerMemberName;
        private int goodsId;
        private String bidNo;
        private String actionName;
        private double startPrice;
        private double stepSize;
        private double nowprice;
        private int buyerMemberId;
        private int bidCount;
        private long startTime;
        private long endTime;
        private int stepTime;
        private int deferredTime;
        private int status;
        private int feeStatus;
        private double feeAmount;
        private int returnFrontMoneyStatus;
        private double frontMoneyAmount;
        private String deliveryStatus;
        private int approvalStatus;
        private int applyStatus;
        private int frontMoneyStatus;
        private String pictureUrl;
        private String auctionDesc;
        private String certificateUrl;
        private int complainId;
        private int complainStatus;
        private String complain;
        private String complainResult;
        private long complainTime;
        private String createTime;
        private String returnFrontMoneyTime;
        private String descn;
        private String startCreateDate;
        private String endCreateDate;
        private String sysDate;
        private double buyerEnsureAmount;
        private String categoryId;
        private String depositStatus;
        private long deliveryTime;
        private long finishTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSellerMemberId() {
            return sellerMemberId;
        }

        public void setSellerMemberId(int sellerMemberId) {
            this.sellerMemberId = sellerMemberId;
        }

        public String getSellerMemberName() {
            return sellerMemberName;
        }

        public void setSellerMemberName(String sellerMemberName) {
            this.sellerMemberName = sellerMemberName;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public double getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(double startPrice) {
            this.startPrice = startPrice;
        }

        public double getStepSize() {
            return stepSize;
        }

        public void setStepSize(double stepSize) {
            this.stepSize = stepSize;
        }

        public double getNowprice() {
            return nowprice;
        }

        public void setNowprice(double nowprice) {
            this.nowprice = nowprice;
        }

        public int getBuyerMemberId() {
            return buyerMemberId;
        }

        public void setBuyerMemberId(int buyerMemberId) {
            this.buyerMemberId = buyerMemberId;
        }

        public int getBidCount() {
            return bidCount;
        }

        public void setBidCount(int bidCount) {
            this.bidCount = bidCount;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getStepTime() {
            return stepTime;
        }

        public void setStepTime(int stepTime) {
            this.stepTime = stepTime;
        }

        public int getDeferredTime() {
            return deferredTime;
        }

        public void setDeferredTime(int deferredTime) {
            this.deferredTime = deferredTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getFeeStatus() {
            return feeStatus;
        }

        public void setFeeStatus(int feeStatus) {
            this.feeStatus = feeStatus;
        }

        public double getFeeAmount() {
            return feeAmount;
        }

        public void setFeeAmount(double feeAmount) {
            this.feeAmount = feeAmount;
        }

        public int getReturnFrontMoneyStatus() {
            return returnFrontMoneyStatus;
        }

        public void setReturnFrontMoneyStatus(int returnFrontMoneyStatus) {
            this.returnFrontMoneyStatus = returnFrontMoneyStatus;
        }

        public double getFrontMoneyAmount() {
            return frontMoneyAmount;
        }

        public void setFrontMoneyAmount(double frontMoneyAmount) {
            this.frontMoneyAmount = frontMoneyAmount;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public int getFrontMoneyStatus() {
            return frontMoneyStatus;
        }

        public void setFrontMoneyStatus(int frontMoneyStatus) {
            this.frontMoneyStatus = frontMoneyStatus;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getAuctionDesc() {
            return auctionDesc;
        }

        public void setAuctionDesc(String auctionDesc) {
            this.auctionDesc = auctionDesc;
        }

        public String getCertificateUrl() {
            return certificateUrl;
        }

        public void setCertificateUrl(String certificateUrl) {
            this.certificateUrl = certificateUrl;
        }

        public int getComplainId() {
            return complainId;
        }

        public void setComplainId(int complainId) {
            this.complainId = complainId;
        }

        public int getComplainStatus() {
            return complainStatus;
        }

        public void setComplainStatus(int complainStatus) {
            this.complainStatus = complainStatus;
        }

        public String getComplain() {
            return complain;
        }

        public void setComplain(String complain) {
            this.complain = complain;
        }

        public String getComplainResult() {
            return complainResult;
        }

        public void setComplainResult(String complainResult) {
            this.complainResult = complainResult;
        }

        public long getComplainTime() {
            return complainTime;
        }

        public void setComplainTime(long complainTime) {
            this.complainTime = complainTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReturnFrontMoneyTime() {
            return returnFrontMoneyTime;
        }

        public void setReturnFrontMoneyTime(String returnFrontMoneyTime) {
            this.returnFrontMoneyTime = returnFrontMoneyTime;
        }

        public String getDescn() {
            return descn;
        }

        public void setDescn(String descn) {
            this.descn = descn;
        }

        public String getStartCreateDate() {
            return startCreateDate;
        }

        public void setStartCreateDate(String startCreateDate) {
            this.startCreateDate = startCreateDate;
        }

        public String getEndCreateDate() {
            return endCreateDate;
        }

        public void setEndCreateDate(String endCreateDate) {
            this.endCreateDate = endCreateDate;
        }

        public String getSysDate() {
            return sysDate;
        }

        public void setSysDate(String sysDate) {
            this.sysDate = sysDate;
        }

        public double getBuyerEnsureAmount() {
            return buyerEnsureAmount;
        }

        public void setBuyerEnsureAmount(double buyerEnsureAmount) {
            this.buyerEnsureAmount = buyerEnsureAmount;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getDepositStatus() {
            return depositStatus;
        }

        public void setDepositStatus(String depositStatus) {
            this.depositStatus = depositStatus;
        }

        public long getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(long deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public long getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(long finishTime) {
            this.finishTime = finishTime;
        }
    }
}
