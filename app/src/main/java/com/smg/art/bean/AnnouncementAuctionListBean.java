package com.smg.art.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

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

        public static class RowsBean   implements MultiItemEntity {
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

            public static final int  GOODS= 1;
            public static final int AUCTION = 2;

            private int id;
            private String sellerMemberId;
            private String sellerMemberName;
            private String goodsId;
            private String bidNo;
            private String actionName;
            private String startPrice;
            private String stepSize;
            private String nowprice;
            private String buyerMemberId;
            private String bidCount;
            private long startTime;
            private long endTime;
            private String stepTime;
            private String deferredTime;
            private int status;
            private String feeStatus;
            private String feeAmount;
            private String returnFrontMoneyStatus;
            private String frontMoneyAmount;
            private String deliveryStatus;
            private String approvalStatus;
            private String applyStatus;
            private String frontMoneyStatus;
            private String pictureUrl;
            private String auctionDesc;
            private String certificateUrl;
            private String complainStatus;
            private String complain;
            private String complainResult;
            private String createTime;
            private String returnFrontMoneyTime;
            private String descn;
            private String startCreateDate;
            private String endCreateDate;
            private long sysDate;
            private String buyerEnsureAmount;
            private String categoryId;
            private String depositStatus;
            private String deliveryTime;
            private String finishTime;
            private  int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSellerMemberId() {
                return sellerMemberId;
            }

            public void setSellerMemberId(String sellerMemberId) {
                this.sellerMemberId = sellerMemberId;
            }

            public String getSellerMemberName() {
                return sellerMemberName;
            }

            public void setSellerMemberName(String sellerMemberName) {
                this.sellerMemberName = sellerMemberName;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
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

            public String getStartPrice() {
                return startPrice;
            }

            public void setStartPrice(String startPrice) {
                this.startPrice = startPrice;
            }

            public String getStepSize() {
                return stepSize;
            }

            public void setStepSize(String stepSize) {
                this.stepSize = stepSize;
            }

            public String getNowprice() {
                return nowprice;
            }

            public void setNowprice(String nowprice) {
                this.nowprice = nowprice;
            }

            public String getBuyerMemberId() {
                return buyerMemberId;
            }

            public void setBuyerMemberId(String buyerMemberId) {
                this.buyerMemberId = buyerMemberId;
            }

            public String getBidCount() {
                return bidCount;
            }

            public void setBidCount(String bidCount) {
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getFeeStatus() {
                return feeStatus;
            }

            public void setFeeStatus(String feeStatus) {
                this.feeStatus = feeStatus;
            }

            public String getFeeAmount() {
                return feeAmount;
            }

            public void setFeeAmount(String feeAmount) {
                this.feeAmount = feeAmount;
            }

            public String getReturnFrontMoneyStatus() {
                return returnFrontMoneyStatus;
            }

            public void setReturnFrontMoneyStatus(String returnFrontMoneyStatus) {
                this.returnFrontMoneyStatus = returnFrontMoneyStatus;
            }

            public String getFrontMoneyAmount() {
                return frontMoneyAmount;
            }

            public void setFrontMoneyAmount(String frontMoneyAmount) {
                this.frontMoneyAmount = frontMoneyAmount;
            }

            public String getDeliveryStatus() {
                return deliveryStatus;
            }

            public void setDeliveryStatus(String deliveryStatus) {
                this.deliveryStatus = deliveryStatus;
            }

            public String getApprovalStatus() {
                return approvalStatus;
            }

            public void setApprovalStatus(String approvalStatus) {
                this.approvalStatus = approvalStatus;
            }

            public String getApplyStatus() {
                return applyStatus;
            }

            public void setApplyStatus(String applyStatus) {
                this.applyStatus = applyStatus;
            }

            public String getFrontMoneyStatus() {
                return frontMoneyStatus;
            }

            public void setFrontMoneyStatus(String frontMoneyStatus) {
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

            public String getComplainStatus() {
                return complainStatus;
            }

            public void setComplainStatus(String complainStatus) {
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

            public long getSysDate() {
                return sysDate;
            }

            public void setSysDate(long sysDate) {
                this.sysDate = sysDate;
            }

            public String getBuyerEnsureAmount() {
                return buyerEnsureAmount;
            }

            public void setBuyerEnsureAmount(String buyerEnsureAmount) {
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

            public String getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(String deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public String getFinishTime() {
                return finishTime;
            }

            public void setFinishTime(String finishTime) {
                this.finishTime = finishTime;
            }
            @Override
            public int getItemType() {
                return type;
            }
            public void setType(int type) {
                this.type = type;
            }

        }
    }
}
