package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class OrderMessageBean {

    /**
     * status : 1
     * msg : 获取消息列表成功
     * data : {"total":23,"rows":[{"id":299,"memberId":78,"auctionId":273,"newsTitle":"进入竞价环节","summary":"进入竞价环节","addTime":"2018-08-15 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15342114595】有亲笔授权3.49米巨幅 国家一级美术师 贾光明《 旭日东升映山河》,已经开始竞价了！"},{"id":289,"memberId":78,"auctionId":273,"newsTitle":"系统扣除保证金","summary":"系统扣除保证金","addTime":"2018-08-14 17:55:09","remark":"保证金支付订单","readStatus":"0","detailContent":"系统扣除保证金"},{"id":256,"memberId":78,"auctionId":234,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-14 01:00:04","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335185042】著名牡丹画家 张志文《牡丹四条屏》,已经交易完成买卖双方的保证金已经退还！"},{"id":215,"memberId":78,"auctionId":242,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-14 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15336061232】一粒珠 陈腐多年底槽清 水色一流 实力派韩江,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":212,"memberId":78,"auctionId":15,"newsTitle":"系统扣除保证金","summary":"系统扣除保证金","addTime":"2018-08-13 16:26:12","remark":"保证金支付订单","readStatus":"0","detailContent":"系统扣除保证金"},{"id":197,"memberId":78,"auctionId":262,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-13 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15337976509】郑泽鹏情人节之夜拍卖，手慢无！,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":188,"memberId":78,"auctionId":23,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-12 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【PM201808020003】橄榄核手串 精雕大日如来 陈国栋刻,已经交易完成买卖双方的保证金已经退还！"},{"id":189,"memberId":78,"auctionId":23,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-12 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【PM201808020003】橄榄核手串 精雕大日如来 陈国栋刻,已经交易完成买卖双方的保证金已经退还！"},{"id":181,"memberId":78,"auctionId":240,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-11 01:00:02","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335264097】9.9新未使用PRADA普拉达2ML303男款黑色牛皮饰旅行者造型长款双全拉链钱包,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":184,"memberId":78,"auctionId":241,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-11 01:00:02","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335271357】9新BVLGARI（宝格丽）玫瑰金单钻女士戒指,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"}]}
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
         * total : 23
         * rows : [{"id":299,"memberId":78,"auctionId":273,"newsTitle":"进入竞价环节","summary":"进入竞价环节","addTime":"2018-08-15 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15342114595】有亲笔授权3.49米巨幅 国家一级美术师 贾光明《 旭日东升映山河》,已经开始竞价了！"},{"id":289,"memberId":78,"auctionId":273,"newsTitle":"系统扣除保证金","summary":"系统扣除保证金","addTime":"2018-08-14 17:55:09","remark":"保证金支付订单","readStatus":"0","detailContent":"系统扣除保证金"},{"id":256,"memberId":78,"auctionId":234,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-14 01:00:04","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335185042】著名牡丹画家 张志文《牡丹四条屏》,已经交易完成买卖双方的保证金已经退还！"},{"id":215,"memberId":78,"auctionId":242,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-14 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15336061232】一粒珠 陈腐多年底槽清 水色一流 实力派韩江,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":212,"memberId":78,"auctionId":15,"newsTitle":"系统扣除保证金","summary":"系统扣除保证金","addTime":"2018-08-13 16:26:12","remark":"保证金支付订单","readStatus":"0","detailContent":"系统扣除保证金"},{"id":197,"memberId":78,"auctionId":262,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-13 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15337976509】郑泽鹏情人节之夜拍卖，手慢无！,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":188,"memberId":78,"auctionId":23,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-12 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【PM201808020003】橄榄核手串 精雕大日如来 陈国栋刻,已经交易完成买卖双方的保证金已经退还！"},{"id":189,"memberId":78,"auctionId":23,"newsTitle":"交易完成,保证金已经退还","summary":"交易完成,保证金已经退还","addTime":"2018-08-12 01:00:01","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【PM201808020003】橄榄核手串 精雕大日如来 陈国栋刻,已经交易完成买卖双方的保证金已经退还！"},{"id":181,"memberId":78,"auctionId":240,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-11 01:00:02","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335264097】9.9新未使用PRADA普拉达2ML303男款黑色牛皮饰旅行者造型长款双全拉链钱包,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"},{"id":184,"memberId":78,"auctionId":241,"newsTitle":"竞价环节结束","summary":"竞价环节结束","addTime":"2018-08-11 01:00:02","remark":"后台定时器发出","readStatus":"0","detailContent":"拍卖品编号【BM15335271357】9新BVLGARI（宝格丽）玫瑰金单钻女士戒指,已经停止竞价了！未中标者的竞价金额及保证金已经全部退还！该拍卖品自动进入线下交割环节"}]
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
             * id : 299
             * memberId : 78
             * auctionId : 273
             * newsTitle : 进入竞价环节
             * summary : 进入竞价环节
             * addTime : 2018-08-15 01:00:01
             * remark : 后台定时器发出
             * readStatus : 0
             * detailContent : 拍卖品编号【BM15342114595】有亲笔授权3.49米巨幅 国家一级美术师 贾光明《 旭日东升映山河》,已经开始竞价了！
             */

            private int id;
            private int memberId;
            private int auctionId;
            private String newsTitle;
            private String summary;
            private String addTime;
            private String remark;
            private String readStatus;
            private String detailContent;

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

            public int getAuctionId() {
                return auctionId;
            }

            public void setAuctionId(int auctionId) {
                this.auctionId = auctionId;
            }

            public String getNewsTitle() {
                return newsTitle;
            }

            public void setNewsTitle(String newsTitle) {
                this.newsTitle = newsTitle;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReadStatus() {
                return readStatus;
            }

            public void setReadStatus(String readStatus) {
                this.readStatus = readStatus;
            }

            public String getDetailContent() {
                return detailContent;
            }

            public void setDetailContent(String detailContent) {
                this.detailContent = detailContent;
            }
        }
    }
}
