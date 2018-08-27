package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/20.
 */

public class AddressListBean {


    /**
     * status : 1
     * msg : 获取成功
     * data : [{"id":5,"memberId":98,"deliveryName":"22","deliveryPhone":"2424","provinceId":1,"cityId":1,"countyId":null,"provinceName":null,"cityName":"北京","countyName":null,"adress":"2452","postcode":null,"defaultFlag":0,"createTime":1534823514000}]
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
         * id : 5
         * memberId : 98
         * deliveryName : 22
         * deliveryPhone : 2424
         * provinceId : 1
         * cityId : 1
         * countyId : null
         * provinceName : null
         * cityName : 北京
         * countyName : null
         * adress : 2452
         * postcode : null
         * defaultFlag : 0
         * createTime : 1534823514000
         */

        private int id;
        private int memberId;
        private String deliveryName;
        private String deliveryPhone;
        private int provinceId;
        private int cityId;
        private int countyId;
        private String provinceName;
        private String cityName;
        private String countyName;
        private String adress;
        private Object postcode;
        private int defaultFlag;
        private long createTime;

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

        public String getDeliveryName() {
            return deliveryName;
        }

        public void setDeliveryName(String deliveryName) {
            this.deliveryName = deliveryName;
        }

        public String getDeliveryPhone() {
            return deliveryPhone;
        }

        public void setDeliveryPhone(String deliveryPhone) {
            this.deliveryPhone = deliveryPhone;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
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

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCountyName() {
            return countyName;
        }

        public void setCountyName(String countyName) {
            this.countyName = countyName;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public Object getPostcode() {
            return postcode;
        }

        public void setPostcode(Object postcode) {
            this.postcode = postcode;
        }

        public int getDefaultFlag() {
            return defaultFlag;
        }

        public void setDefaultFlag(int defaultFlag) {
            this.defaultFlag = defaultFlag;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
