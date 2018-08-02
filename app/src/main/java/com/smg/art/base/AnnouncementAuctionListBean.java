package com.smg.art.base;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/2.
 */

public class AnnouncementAuctionListBean {


    /**
     * status : 1
     * msg : 获取列表成功
     * data : {"total":1,"rows":[{"id":4,"sellerMemberId":null,"sellerMemberName":"zp_test","goodsId":null,"bidNo":"BM201807260004","actionName":"瓷中皇族 臻品收藏 《绶带鸟》小美人瓶 一代宗师 省工艺美术大师 张震作品","startPrice":null,"stepSize":null,"nowprice":null,"buyerMemberId":null,"bidCount":null,"startTime":"2018-07-26 00:00:00","endTime":"2019-07-31 00:00:00","stepTime":null,"deferredTime":null,"status":4,"feeStatus":null,"feeAmount":null,"returnFrontMoneyStatus":null,"frontMoneyAmount":null,"deliveryStatus":null,"approvalStatus":null,"applyStatus":null,"frontMoneyStatus":null,"pictureUrl":"/static/uploads/image/50da66ae-5189-45e4-8556-7e3870a370d7.jpg","auctionDesc":null,"certificateUrl":null,"complainFlag":null,"complainStatus":null,"complain":null,"complainResult":null,"createTime":null,"returnFrontMoneyTime":null,"descn":null,"startCreateDate":null,"endCreateDate":null,"sysDate":null,"buyerEnsureAmount":null,"categoryId":null}]}
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
         * total : 1
         * rows : [{"id":4,"sellerMemberId":null,"sellerMemberName":"zp_test","goodsId":null,"bidNo":"BM201807260004","actionName":"瓷中皇族 臻品收藏 《绶带鸟》小美人瓶 一代宗师 省工艺美术大师 张震作品","startPrice":null,"stepSize":null,"nowprice":null,"buyerMemberId":null,"bidCount":null,"startTime":"2018-07-26 00:00:00","endTime":"2019-07-31 00:00:00","stepTime":null,"deferredTime":null,"status":4,"feeStatus":null,"feeAmount":null,"returnFrontMoneyStatus":null,"frontMoneyAmount":null,"deliveryStatus":null,"approvalStatus":null,"applyStatus":null,"frontMoneyStatus":null,"pictureUrl":"/static/uploads/image/50da66ae-5189-45e4-8556-7e3870a370d7.jpg","auctionDesc":null,"certificateUrl":null,"complainFlag":null,"complainStatus":null,"complain":null,"complainResult":null,"createTime":null,"returnFrontMoneyTime":null,"descn":null,"startCreateDate":null,"endCreateDate":null,"sysDate":null,"buyerEnsureAmount":null,"categoryId":null}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 4
             * sellerMemberId : null
             * sellerMemberName : zp_test
             * goodsId : null
             * bidNo : BM201807260004
             * actionName : 瓷中皇族 臻品收藏 《绶带鸟》小美人瓶 一代宗师 省工艺美术大师 张震作品
             * startPrice : null
             * stepSize : null
             * nowprice : null
             * buyerMemberId : null
             * bidCount : null
             * startTime : 2018-07-26 00:00:00
             * endTime : 2019-07-31 00:00:00
             * stepTime : null
             * deferredTime : null
             * status : 4
             * feeStatus : null
             * feeAmount : null
             * returnFrontMoneyStatus : null
             * frontMoneyAmount : null
             * deliveryStatus : null
             * approvalStatus : null
             * applyStatus : null
             * frontMoneyStatus : null
             * pictureUrl : /static/uploads/image/50da66ae-5189-45e4-8556-7e3870a370d7.jpg
             * auctionDesc : null
             * certificateUrl : null
             * complainFlag : null
             * complainStatus : null
             * complain : null
             * complainResult : null
             * createTime : null
             * returnFrontMoneyTime : null
             * descn : null
             * startCreateDate : null
             * endCreateDate : null
             * sysDate : null
             * buyerEnsureAmount : null
             * categoryId : null
             */

            private int id;
            private Object sellerMemberId;
            private String sellerMemberName;
            private Object goodsId;
            private String bidNo;
            private String actionName;
            private Object startPrice;
            private Object stepSize;
            private Object nowprice;
            private Object buyerMemberId;
            private Object bidCount;
            private String startTime;
            private String endTime;
            private Object stepTime;
            private Object deferredTime;
            private int status;
            private Object feeStatus;
            private Object feeAmount;
            private Object returnFrontMoneyStatus;
            private Object frontMoneyAmount;
            private Object deliveryStatus;
            private Object approvalStatus;
            private Object applyStatus;
            private Object frontMoneyStatus;
            private String pictureUrl;
            private Object auctionDesc;
            private Object certificateUrl;
            private Object complainFlag;
            private Object complainStatus;
            private Object complain;
            private Object complainResult;
            private Object createTime;
            private Object returnFrontMoneyTime;
            private Object descn;
            private Object startCreateDate;
            private Object endCreateDate;
            private Object sysDate;
            private Object buyerEnsureAmount;
            private Object categoryId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getSellerMemberId() {
                return sellerMemberId;
            }

            public void setSellerMemberId(Object sellerMemberId) {
                this.sellerMemberId = sellerMemberId;
            }

            public String getSellerMemberName() {
                return sellerMemberName;
            }

            public void setSellerMemberName(String sellerMemberName) {
                this.sellerMemberName = sellerMemberName;
            }

            public Object getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(Object goodsId) {
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

            public Object getStartPrice() {
                return startPrice;
            }

            public void setStartPrice(Object startPrice) {
                this.startPrice = startPrice;
            }

            public Object getStepSize() {
                return stepSize;
            }

            public void setStepSize(Object stepSize) {
                this.stepSize = stepSize;
            }

            public Object getNowprice() {
                return nowprice;
            }

            public void setNowprice(Object nowprice) {
                this.nowprice = nowprice;
            }

            public Object getBuyerMemberId() {
                return buyerMemberId;
            }

            public void setBuyerMemberId(Object buyerMemberId) {
                this.buyerMemberId = buyerMemberId;
            }

            public Object getBidCount() {
                return bidCount;
            }

            public void setBidCount(Object bidCount) {
                this.bidCount = bidCount;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public Object getStepTime() {
                return stepTime;
            }

            public void setStepTime(Object stepTime) {
                this.stepTime = stepTime;
            }

            public Object getDeferredTime() {
                return deferredTime;
            }

            public void setDeferredTime(Object deferredTime) {
                this.deferredTime = deferredTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getFeeStatus() {
                return feeStatus;
            }

            public void setFeeStatus(Object feeStatus) {
                this.feeStatus = feeStatus;
            }

            public Object getFeeAmount() {
                return feeAmount;
            }

            public void setFeeAmount(Object feeAmount) {
                this.feeAmount = feeAmount;
            }

            public Object getReturnFrontMoneyStatus() {
                return returnFrontMoneyStatus;
            }

            public void setReturnFrontMoneyStatus(Object returnFrontMoneyStatus) {
                this.returnFrontMoneyStatus = returnFrontMoneyStatus;
            }

            public Object getFrontMoneyAmount() {
                return frontMoneyAmount;
            }

            public void setFrontMoneyAmount(Object frontMoneyAmount) {
                this.frontMoneyAmount = frontMoneyAmount;
            }

            public Object getDeliveryStatus() {
                return deliveryStatus;
            }

            public void setDeliveryStatus(Object deliveryStatus) {
                this.deliveryStatus = deliveryStatus;
            }

            public Object getApprovalStatus() {
                return approvalStatus;
            }

            public void setApprovalStatus(Object approvalStatus) {
                this.approvalStatus = approvalStatus;
            }

            public Object getApplyStatus() {
                return applyStatus;
            }

            public void setApplyStatus(Object applyStatus) {
                this.applyStatus = applyStatus;
            }

            public Object getFrontMoneyStatus() {
                return frontMoneyStatus;
            }

            public void setFrontMoneyStatus(Object frontMoneyStatus) {
                this.frontMoneyStatus = frontMoneyStatus;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public Object getAuctionDesc() {
                return auctionDesc;
            }

            public void setAuctionDesc(Object auctionDesc) {
                this.auctionDesc = auctionDesc;
            }

            public Object getCertificateUrl() {
                return certificateUrl;
            }

            public void setCertificateUrl(Object certificateUrl) {
                this.certificateUrl = certificateUrl;
            }

            public Object getComplainFlag() {
                return complainFlag;
            }

            public void setComplainFlag(Object complainFlag) {
                this.complainFlag = complainFlag;
            }

            public Object getComplainStatus() {
                return complainStatus;
            }

            public void setComplainStatus(Object complainStatus) {
                this.complainStatus = complainStatus;
            }

            public Object getComplain() {
                return complain;
            }

            public void setComplain(Object complain) {
                this.complain = complain;
            }

            public Object getComplainResult() {
                return complainResult;
            }

            public void setComplainResult(Object complainResult) {
                this.complainResult = complainResult;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getReturnFrontMoneyTime() {
                return returnFrontMoneyTime;
            }

            public void setReturnFrontMoneyTime(Object returnFrontMoneyTime) {
                this.returnFrontMoneyTime = returnFrontMoneyTime;
            }

            public Object getDescn() {
                return descn;
            }

            public void setDescn(Object descn) {
                this.descn = descn;
            }

            public Object getStartCreateDate() {
                return startCreateDate;
            }

            public void setStartCreateDate(Object startCreateDate) {
                this.startCreateDate = startCreateDate;
            }

            public Object getEndCreateDate() {
                return endCreateDate;
            }

            public void setEndCreateDate(Object endCreateDate) {
                this.endCreateDate = endCreateDate;
            }

            public Object getSysDate() {
                return sysDate;
            }

            public void setSysDate(Object sysDate) {
                this.sysDate = sysDate;
            }

            public Object getBuyerEnsureAmount() {
                return buyerEnsureAmount;
            }

            public void setBuyerEnsureAmount(Object buyerEnsureAmount) {
                this.buyerEnsureAmount = buyerEnsureAmount;
            }

            public Object getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Object categoryId) {
                this.categoryId = categoryId;
            }
        }
    }
}
