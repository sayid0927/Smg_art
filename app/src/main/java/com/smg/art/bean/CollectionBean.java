package com.smg.art.bean;

import java.util.List;

/**
 * Created by Mervin on 2018/7/30 0030.
 */

public class CollectionBean {

    /**
     * status : 1
     * msg : OK
     * data : [{"id":1,"goodsId":3,"memberId":78,"actionName":"丰子恺《人物》","pictureUrl":"/static/uploads/image/6634126a-6446-4238-a9c5-c851038c95d2.jpg,/static/uploads/image/9839676b-a183-4373-b0b1-feb1c5d2ab77.jpg,/static/uploads/image/e19cb1ec-dc9c-47bf-a3d5-c1f838473da2.jpg,/static/uploads/image/8dc94fd4-d44f-4733-9835-8e6238626630.jpg,/static/uploads/image/13226838-4189-485f-a852-51a6c62f327e.jpg","startPrice":100,"nowprice":100,"startTime":"2018-07-26 00:00:00","endTime":"2018-08-31 00:00:00","createTime":"2018-07-27 16:45:46","status":6},{"id":3,"goodsId":4,"memberId":78,"actionName":"刘旦宅《人物》","pictureUrl":"/static/uploads/image/663cde30-6490-49a2-81fe-93f4024ed345.jpg,/static/uploads/image/2b4d103e-ce01-46e8-9324-8604bd93b700.jpg,/static/uploads/image/fa83a298-949f-4d22-a701-1cfc80281a03.jpg,/static/uploads/image/0e682285-7118-4013-9d09-796b1f42418e.jpg,/static/uploads/image/ac1f7894-9453-4204-9764-de81fe8cae90.jpg","startPrice":100,"nowprice":300,"startTime":"2018-07-26 00:00:00","endTime":"2018-08-31 00:00:00","createTime":"2018-07-27 17:17:09","status":3}]
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
         * id : 1
         * goodsId : 3
         * memberId : 78
         * actionName : 丰子恺《人物》
         * pictureUrl : /static/uploads/image/6634126a-6446-4238-a9c5-c851038c95d2.jpg,/static/uploads/image/9839676b-a183-4373-b0b1-feb1c5d2ab77.jpg,/static/uploads/image/e19cb1ec-dc9c-47bf-a3d5-c1f838473da2.jpg,/static/uploads/image/8dc94fd4-d44f-4733-9835-8e6238626630.jpg,/static/uploads/image/13226838-4189-485f-a852-51a6c62f327e.jpg
         * startPrice : 100
         * nowprice : 100
         * startTime : 2018-07-26 00:00:00
         * endTime : 2018-08-31 00:00:00
         * createTime : 2018-07-27 16:45:46
         * status : 6
         */

        private int id;
        private int goodsId;
        private int memberId;
        private String actionName;
        private String pictureUrl;
        private int startPrice;
        private int nowprice;
        private long startTime;
        private long endTime;
        private String createTime;
        private int status;
        private long sysDate;

        public long getSysDate() {
            return sysDate;
        }

        public void setSysDate(long sysDate) {
            this.sysDate = sysDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public int getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(int startPrice) {
            this.startPrice = startPrice;
        }

        public int getNowprice() {
            return nowprice;
        }

        public void setNowprice(int nowprice) {
            this.nowprice = nowprice;
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
    }
}
