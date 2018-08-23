package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/21.
 */

public class SearchAreaBean {


    /**
     * status : 1
     * msg : 获取成功
     * data : [{"provinceId":1,"countyId":0,"provinceName":"安徽"},
     * {"provinceId":2,"countyId":0,"provinceName":"福建"},{"provinceId":3,"countyId":0,"provinceName":"广东"},{"provinceId":4,"countyId":0,"provinceName":"广西"},{"provinceId":5,"countyId":0,"provinceName":"贵州"},{"provinceId":6,"countyId":0,"provinceName":"甘肃"},{"provinceId":7,"countyId":0,"provinceName":"海南"},{"provinceId":8,"countyId":0,"provinceName":"河南"},{"provinceId":9,"countyId":0,"provinceName":"黑龙江"},{"provinceId":10,"countyId":0,"provinceName":"湖北"},{"provinceId":11,"countyId":0,"provinceName":"湖南"},{"provinceId":12,"countyId":0,"provinceName":"河北"},{"provinceId":13,"countyId":0,"provinceName":"江苏"},{"provinceId":14,"countyId":0,"provinceName":"江西"},{"provinceId":15,"countyId":0,"provinceName":"吉林"},{"provinceId":16,"countyId":0,"provinceName":"辽宁"},{"provinceId":17,"countyId":0,"provinceName":"宁夏"},{"provinceId":18,"countyId":0,"provinceName":"内蒙古"},{"provinceId":19,"countyId":0,"provinceName":"青海"},{"provinceId":20,"countyId":0,"provinceName":"山东"},{"provinceId":21,"countyId":0,"provinceName":"山西"},{"provinceId":22,"countyId":0,"provinceName":"陕西"},{"provinceId":23,"countyId":0,"provinceName":"四川"},{"provinceId":24,"countyId":0,"provinceName":"新疆"},{"provinceId":25,"countyId":0,"provinceName":"西藏"},{"provinceId":26,"countyId":0,"provinceName":"云南"},{"provinceId":27,"countyId":0,"provinceName":"浙江"},{"provinceId":28,"countyId":0,"provinceName":"其他"},{"provinceId":29,"countyId":0,"provinceName":"海外"},{"provinceId":30,"provinceName":"香港"}]
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
         * provinceId : 1
         * countyId : 0
         * provinceName : 安徽
         */

        private int provinceId;
        private int countyId;
        private String provinceName;
        private  int  cityId;
        private  String cityName;
        private  String countyName;


        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCountyId() {
            return countyId;
        }

        public void setCountyId(int countyId) {
            this.countyId = countyId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }
    }
}
