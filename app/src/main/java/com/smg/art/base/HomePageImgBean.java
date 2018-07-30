package com.smg.art.base;

import java.util.List;

/**
 * Created by Lenovo on 2018/7/30.
 */

public class HomePageImgBean {


    /**
     * status : 1
     * msg : 获取消息列表成功
     * data : [{"id":1,"imgPath":"/static/uploads/image/722ddf43-0e34-4ef7-923a-af8e9191acc0.png","releaseStatus":"1","displayPlate":1,"remark":null,"url":null}]
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
         * imgPath : /static/uploads/image/722ddf43-0e34-4ef7-923a-af8e9191acc0.png
         * releaseStatus : 1
         * displayPlate : 1
         * remark : null
         * url : null
         */

        private int id;
        private String imgPath;
        private String releaseStatus;
        private int displayPlate;
        private Object remark;
        private Object url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getReleaseStatus() {
            return releaseStatus;
        }

        public void setReleaseStatus(String releaseStatus) {
            this.releaseStatus = releaseStatus;
        }

        public int getDisplayPlate() {
            return displayPlate;
        }

        public void setDisplayPlate(int displayPlate) {
            this.displayPlate = displayPlate;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }
}
