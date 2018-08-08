package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/7/25.
 */

public class SystemMessageBean {


    /**
     * status : 1
     * msg : 获取消息列表成功
     * data : {"total":3,"rows":[{"id":4,"userId":1,"newsTitle":"高投资高风险的项目","summary":"高投资高风险的项目","detailContent":"高投资高风险的项目","publishTime":"2018-06-27 19:03:21","addTime":"2018-06-27 19:03:21","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null},{"id":3,"userId":1,"newsTitle":"618大优惠购买1000币送500币","summary":"618大优惠购买100币送50","detailContent":"<p>618大优惠购买1000币送500币<\/p>","publishTime":"2018-06-25 19:11:22","addTime":"2018-06-25 19:11:22","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null},{"id":2,"userId":1,"newsTitle":"618大优惠购买100币送50币","summary":"618大优惠购买100币送5","detailContent":"<p>618大优惠购买100币送50币<\/p><p>618大优惠购买100币送50币<\/p>","publishTime":"2018-06-25 19:03:21","addTime":"2018-06-25 19:03:21","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null}]}
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
         * total : 3
         * rows : [{"id":4,"userId":1,"newsTitle":"高投资高风险的项目","summary":"高投资高风险的项目","detailContent":"高投资高风险的项目","publishTime":"2018-06-27 19:03:21","addTime":"2018-06-27 19:03:21","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null},{"id":3,"userId":1,"newsTitle":"618大优惠购买1000币送500币","summary":"618大优惠购买100币送50","detailContent":"<p>618大优惠购买1000币送500币<\/p>","publishTime":"2018-06-25 19:11:22","addTime":"2018-06-25 19:11:22","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null},{"id":2,"userId":1,"newsTitle":"618大优惠购买100币送50币","summary":"618大优惠购买100币送5","detailContent":"<p>618大优惠购买100币送50币<\/p><p>618大优惠购买100币送50币<\/p>","publishTime":"2018-06-25 19:03:21","addTime":"2018-06-25 19:03:21","remark":null,"releaseStatus":"1","readStatus":"0","memberId":null,"typeName":null,"username":"admin","greaterThanPublishTime":null,"lessThanPublishTime":null}]
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
             * userId : 1
             * newsTitle : 高投资高风险的项目
             * summary : 高投资高风险的项目
             * detailContent : 高投资高风险的项目
             * publishTime : 2018-06-27 19:03:21
             * addTime : 2018-06-27 19:03:21
             * remark : null
             * releaseStatus : 1
             * readStatus : 0
             * memberId : null
             * typeName : null
             * username : admin
             * greaterThanPublishTime : null
             * lessThanPublishTime : null
             */

            private int id;
            private int userId;
            private String newsTitle;
            private String summary;
            private String detailContent;
            private String publishTime;
            private String addTime;
            private Object remark;
            private String releaseStatus;
            private String readStatus;
            private Object memberId;
            private Object typeName;
            private String username;
            private Object greaterThanPublishTime;
            private Object lessThanPublishTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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

            public String getDetailContent() {
                return detailContent;
            }

            public void setDetailContent(String detailContent) {
                this.detailContent = detailContent;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getReleaseStatus() {
                return releaseStatus;
            }

            public void setReleaseStatus(String releaseStatus) {
                this.releaseStatus = releaseStatus;
            }

            public String getReadStatus() {
                return readStatus;
            }

            public void setReadStatus(String readStatus) {
                this.readStatus = readStatus;
            }

            public Object getMemberId() {
                return memberId;
            }

            public void setMemberId(Object memberId) {
                this.memberId = memberId;
            }

            public Object getTypeName() {
                return typeName;
            }

            public void setTypeName(Object typeName) {
                this.typeName = typeName;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getGreaterThanPublishTime() {
                return greaterThanPublishTime;
            }

            public void setGreaterThanPublishTime(Object greaterThanPublishTime) {
                this.greaterThanPublishTime = greaterThanPublishTime;
            }

            public Object getLessThanPublishTime() {
                return lessThanPublishTime;
            }

            public void setLessThanPublishTime(Object lessThanPublishTime) {
                this.lessThanPublishTime = lessThanPublishTime;
            }
        }
    }
}
