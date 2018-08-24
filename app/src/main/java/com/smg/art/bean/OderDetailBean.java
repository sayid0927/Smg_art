package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/8 0008.
 */

public class OderDetailBean {

    /**
     * status : 1
     * msg : OK
     * data : {"id":277,"sellerMemberId":95,"addressId":null,"sellerMemberName":null,"goodsId":62,"bidNo":"BM15343048202","actionName":"杨小石 《熊出没》9060cm","startPrice":200,"stepSize":100,"nowprice":300,"buyerMemberId":95,"bidCount":1,"startTime":1534348800000,"endTime":1534392600000,"stepTime":null,"deferredTime":null,"status":8,"orderStatus":null,"feeStatus":1,"feeAmount":200,"returnFrontMoneyStatus":1,"frontMoneyAmount":200,"deliveryStatus":1,"approvalStatus":1,"approvalReason":null,"applyStatus":1,"applyReason":"","frontMoneyStatus":1,"pictureUrl":"/static/uploads/image/fa454c96-277f-43e8-99c9-f0329906914e.jpg,/static/uploads/image/9f609250-d243-4e9e-9210-b94ebc164b19.jpg,/static/uploads/image/b167c839-61aa-4a11-bc7e-a88dcaf0274d.jpg,/static/uploads/image/16f77fd3-c1c0-4a1b-888b-00cde55c2c17.jpg,/static/uploads/image/66d982a2-bfe5-4219-ac24-ccd7c2acf30e.jpg","auctionDesc":"<p><img alt=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t24229/271/341145286/2555800/709de47/5b2c9964Nfc18ec7e.jpg\"/><img alt=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t20641/198/786136079/279425/86dd9266/5b1746feN6b5a49d4.jpg\"/><img alt=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t8623/10/1995910707/514154/1ca84d6/59c2297eN2718f014.jpg\"/><\/p>","certificateUrl":"/static/uploads/image/2646b483-82aa-4444-95f6-e54998501210.jpg","complainId":null,"complainStatus":1,"complain":null,"complainResult":null,"complainTime":null,"complainImageUrl":null,"createTime":"2018-08-15 11:49:07","returnFrontMoneyTime":"2018-08-17 23:59:59","descn":null,"startCreateDate":null,"endCreateDate":null,"sysDate":1534903074376,"buyerEnsureAmount":100,"categoryId":null,"depositStatus":null,"deliveryTime":1534401498000,"finishTime":null,"paybiddingStatus":0,"shipStatus":0,"receiveStatus":0,"paybiddingFinalTime":null,"shipFinalTime":null,"receiveFinalTime":null,"receiveNoticeStatus":0,"paybiddingMoney":null,"biddingMoneyReturnStatus":0,"shipPictureUrl":null,"logisticsNum":null,"sendOutTime":null,"paymentTime":null,"receiveTime":null}
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
         * id : 277
         * sellerMemberId : 95
         * addressId : null
         * sellerMemberName : null
         * goodsId : 62
         * bidNo : BM15343048202
         * actionName : 杨小石 《熊出没》9060cm
         * startPrice : 200.0
         * stepSize : 100.0
         * nowprice : 300.0
         * buyerMemberId : 95
         * bidCount : 1
         * startTime : 1534348800000
         * endTime : 1534392600000
         * stepTime : null
         * deferredTime : null
         * status : 8
         * orderStatus : null
         * feeStatus : 1
         * feeAmount : 200.0
         * returnFrontMoneyStatus : 1
         * frontMoneyAmount : 200.0
         * deliveryStatus : 1
         * approvalStatus : 1
         * approvalReason : null
         * applyStatus : 1
         * applyReason : 
         * frontMoneyStatus : 1
         * pictureUrl : /static/uploads/image/fa454c96-277f-43e8-99c9-f0329906914e.jpg,/static/uploads/image/9f609250-d243-4e9e-9210-b94ebc164b19.jpg,/static/uploads/image/b167c839-61aa-4a11-bc7e-a88dcaf0274d.jpg,/static/uploads/image/16f77fd3-c1c0-4a1b-888b-00cde55c2c17.jpg,/static/uploads/image/66d982a2-bfe5-4219-ac24-ccd7c2acf30e.jpg
         * auctionDesc : <p><img alt="" src="https://img10.360buyimg.com/imgzone/jfs/t24229/271/341145286/2555800/709de47/5b2c9964Nfc18ec7e.jpg"/><img alt="" src="https://img10.360buyimg.com/imgzone/jfs/t20641/198/786136079/279425/86dd9266/5b1746feN6b5a49d4.jpg"/><img alt="" src="https://img10.360buyimg.com/imgzone/jfs/t8623/10/1995910707/514154/1ca84d6/59c2297eN2718f014.jpg"/></p>
         * certificateUrl : /static/uploads/image/2646b483-82aa-4444-95f6-e54998501210.jpg
         * complainId : null
         * complainStatus : 1
         * complain : null
         * complainResult : null
         * complainTime : null
         * complainImageUrl : null
         * createTime : 2018-08-15 11:49:07
         * returnFrontMoneyTime : 2018-08-17 23:59:59
         * descn : null
         * startCreateDate : null
         * endCreateDate : null
         * sysDate : 1534903074376
         * buyerEnsureAmount : 100.0
         * categoryId : null
         * depositStatus : null
         * deliveryTime : 1534401498000
         * finishTime : null
         * paybiddingStatus : 0
         * shipStatus : 0
         * receiveStatus : 0
         * paybiddingFinalTime : null
         * shipFinalTime : null
         * receiveFinalTime : null
         * receiveNoticeStatus : 0
         * paybiddingMoney : null
         * biddingMoneyReturnStatus : 0
         * shipPictureUrl : null
         * logisticsNum : null
         * sendOutTime : null
         * paymentTime : null
         * receiveTime : null
         */

        private int id;
        private int sellerMemberId;
        private String addressId;
        private String sellerMemberName;
        private int goodsId;
        private String bidNo;
        private String actionName;
        private double startPrice;
        private double stepSize;
        private double nowprice;
        private String buyerMemberId;
        private int bidCount;
        private long startTime;
        private long endTime;
        private String stepTime;
        private String deferredTime;
        private String status;
        private String orderStatus;
        private int feeStatus;
        private double feeAmount;
        private int returnFrontMoneyStatus;
        private double frontMoneyAmount;
        private int deliveryStatus;
        private int approvalStatus;
        private String approvalReason;
        private int applyStatus;
        private String applyReason;
        private int frontMoneyStatus;
        private String pictureUrl;
        private String auctionDesc;
        private String certificateUrl;
        private String complainId;
        private int complainStatus;
        private String complain;
        private String complainResult;
        private String complainTime;
        private String complainImageUrl;
        private String createTime;
        private String returnFrontMoneyTime;
        private String descn;
        private String startCreateDate;
        private String endCreateDate;
        private Long sysDate;
        private double buyerEnsureAmount;
        private String categoryId;
        private String depositStatus;
        private long deliveryTime;
        private String finishTime;
        private int paybiddingStatus;
        private int shipStatus;
        private int receiveStatus;
        private long paybiddingFinalTime;
        private long shipFinalTime;
        private long receiveFinalTime;
        private int receiveNoticeStatus;
        private String paybiddingMoney;
        private int biddingMoneyReturnStatus;
        private String shipPictureUrl;
        private String logisticsNum;
        private String sendOutTime;
        private String paymentTime;
        private String receiveTime;
        private String memberNowPrice;

        public String getMemberNowPrice() {
            return memberNowPrice;
        }

        public void setMemberNowPrice(String memberNowPrice) {
            this.memberNowPrice = memberNowPrice;
        }

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

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
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

        public String getBuyerMemberId() {
            return buyerMemberId;
        }

        public void setBuyerMemberId(String buyerMemberId) {
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

        public String getStepTime() {
            return stepTime;
        }

        public void setStepTime(String stepTime) {
            this.stepTime = stepTime;
        }

        public String getDeferredTime() {
            return deferredTime;
        }

        public void setDeferredTime(String deferredTime) {
            this.deferredTime = deferredTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
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

        public int getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(int deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getApprovalReason() {
            return approvalReason;
        }

        public void setApprovalReason(String approvalReason) {
            this.approvalReason = approvalReason;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getApplyReason() {
            return applyReason;
        }

        public void setApplyReason(String applyReason) {
            this.applyReason = applyReason;
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

        public String getComplainId() {
            return complainId;
        }

        public void setComplainId(String complainId) {
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

        public String getComplainTime() {
            return complainTime;
        }

        public void setComplainTime(String complainTime) {
            this.complainTime = complainTime;
        }

        public String getComplainImageUrl() {
            return complainImageUrl;
        }

        public void setComplainImageUrl(String complainImageUrl) {
            this.complainImageUrl = complainImageUrl;
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

        public Long getSysDate() {
            return sysDate;
        }

        public void setSysDate(Long sysDate) {
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

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public int getPaybiddingStatus() {
            return paybiddingStatus;
        }

        public void setPaybiddingStatus(int paybiddingStatus) {
            this.paybiddingStatus = paybiddingStatus;
        }

        public int getShipStatus() {
            return shipStatus;
        }

        public void setShipStatus(int shipStatus) {
            this.shipStatus = shipStatus;
        }

        public int getReceiveStatus() {
            return receiveStatus;
        }

        public void setReceiveStatus(int receiveStatus) {
            this.receiveStatus = receiveStatus;
        }

        public long getPaybiddingFinalTime() {
            return paybiddingFinalTime;
        }

        public void setPaybiddingFinalTime(long paybiddingFinalTime) {
            this.paybiddingFinalTime = paybiddingFinalTime;
        }

        public long getShipFinalTime() {
            return shipFinalTime;
        }

        public void setShipFinalTime(long shipFinalTime) {
            this.shipFinalTime = shipFinalTime;
        }

        public long getReceiveFinalTime() {
            return receiveFinalTime;
        }

        public void setReceiveFinalTime(long receiveFinalTime) {
            this.receiveFinalTime = receiveFinalTime;
        }

        public int getReceiveNoticeStatus() {
            return receiveNoticeStatus;
        }

        public void setReceiveNoticeStatus(int receiveNoticeStatus) {
            this.receiveNoticeStatus = receiveNoticeStatus;
        }

        public String getPaybiddingMoney() {
            return paybiddingMoney;
        }

        public void setPaybiddingMoney(String paybiddingMoney) {
            this.paybiddingMoney = paybiddingMoney;
        }

        public int getBiddingMoneyReturnStatus() {
            return biddingMoneyReturnStatus;
        }

        public void setBiddingMoneyReturnStatus(int biddingMoneyReturnStatus) {
            this.biddingMoneyReturnStatus = biddingMoneyReturnStatus;
        }

        public String getShipPictureUrl() {
            return shipPictureUrl;
        }

        public void setShipPictureUrl(String shipPictureUrl) {
            this.shipPictureUrl = shipPictureUrl;
        }

        public String getLogisticsNum() {
            return logisticsNum;
        }

        public void setLogisticsNum(String logisticsNum) {
            this.logisticsNum = logisticsNum;
        }

        public String getSendOutTime() {
            return sendOutTime;
        }

        public void setSendOutTime(String sendOutTime) {
            this.sendOutTime = sendOutTime;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }
    }
}
