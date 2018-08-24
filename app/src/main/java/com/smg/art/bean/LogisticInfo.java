package com.smg.art.bean;

import java.util.List;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class LogisticInfo {

    /**
     * status : 1
     * msg : OK
     * data : {"id":6,"auctionid":311,"code":"3101938073167","name":"韵达快运","tel":"310193","status":1,"createtime":1535004279000,"address":{"id":45,"memberId":105,"deliveryName":"记好了","deliveryPhone":"13580746888","provinceId":3,"cityId":52,"countyId":21,"provinceName":"广东","cityName":"东莞","countyName":"东城区","adress":"另一个劲","postcode":null,"defaultFlag":1,"createTime":1534996992000},"adList":[{"id":15,"logisticsid":6,"date":1534852111000,"info":"【广东深圳公司宝安区华丰分部】已签收，签收人是【已签收】","sort":0},{"id":16,"logisticsid":6,"date":1534818081000,"info":"【广东深圳公司宝安区华丰分部】已签收，签收人是【签收】","sort":1},{"id":17,"logisticsid":6,"date":1534814056000,"info":"【广东深圳公司宝安区华丰分部】正在进行【分发】扫描","sort":2},{"id":18,"logisticsid":6,"date":1534807081000,"info":"【广东深圳公司宝安区创业分拨分部】正在进行【分发】扫描","sort":3},{"id":19,"logisticsid":6,"date":1534796401000,"info":"【广东深圳公司】正在进行【分发】扫描","sort":4},{"id":20,"logisticsid":6,"date":1534795979000,"info":"【广东深圳公司】正在进行【卸车】扫描","sort":5},{"id":21,"logisticsid":6,"date":1534782743000,"info":"【广东广州分拨中心】发往【广东深圳公司】","sort":6},{"id":22,"logisticsid":6,"date":1534782682000,"info":"【广东广州分拨中心】正在进行【称重】扫描","sort":7},{"id":23,"logisticsid":6,"date":1534760492000,"info":"【广州国际电子商务公司】已收件","sort":8}]}
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
         * id : 6
         * auctionid : 311
         * code : 3101938073167
         * name : 韵达快运
         * tel : 310193
         * status : 1
         * createtime : 1535004279000
         * address : {"id":45,"memberId":105,"deliveryName":"记好了","deliveryPhone":"13580746888","provinceId":3,"cityId":52,"countyId":21,"provinceName":"广东","cityName":"东莞","countyName":"东城区","adress":"另一个劲","postcode":null,"defaultFlag":1,"createTime":1534996992000}
         * adList : [{"id":15,"logisticsid":6,"date":1534852111000,"info":"【广东深圳公司宝安区华丰分部】已签收，签收人是【已签收】","sort":0},{"id":16,"logisticsid":6,"date":1534818081000,"info":"【广东深圳公司宝安区华丰分部】已签收，签收人是【签收】","sort":1},{"id":17,"logisticsid":6,"date":1534814056000,"info":"【广东深圳公司宝安区华丰分部】正在进行【分发】扫描","sort":2},{"id":18,"logisticsid":6,"date":1534807081000,"info":"【广东深圳公司宝安区创业分拨分部】正在进行【分发】扫描","sort":3},{"id":19,"logisticsid":6,"date":1534796401000,"info":"【广东深圳公司】正在进行【分发】扫描","sort":4},{"id":20,"logisticsid":6,"date":1534795979000,"info":"【广东深圳公司】正在进行【卸车】扫描","sort":5},{"id":21,"logisticsid":6,"date":1534782743000,"info":"【广东广州分拨中心】发往【广东深圳公司】","sort":6},{"id":22,"logisticsid":6,"date":1534782682000,"info":"【广东广州分拨中心】正在进行【称重】扫描","sort":7},{"id":23,"logisticsid":6,"date":1534760492000,"info":"【广州国际电子商务公司】已收件","sort":8}]
         */

        private int id;
        private int auctionid;
        private String code;
        private String name;
        private String tel;
        private int status;
        private long createtime;
        private AddressBean address;
        private String pictureUrl;
        private List<AdListBean> adList;

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAuctionid() {
            return auctionid;
        }

        public void setAuctionid(int auctionid) {
            this.auctionid = auctionid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public List<AdListBean> getAdList() {
            return adList;
        }

        public void setAdList(List<AdListBean> adList) {
            this.adList = adList;
        }

        public static class AddressBean {
            /**
             * id : 45
             * memberId : 105
             * deliveryName : 记好了
             * deliveryPhone : 13580746888
             * provinceId : 3
             * cityId : 52
             * countyId : 21
             * provinceName : 广东
             * cityName : 东莞
             * countyName : 东城区
             * adress : 另一个劲
             * postcode : null
             * defaultFlag : 1
             * createTime : 1534996992000
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

        public static class AdListBean {
            /**
             * id : 15
             * logisticsid : 6
             * date : 1534852111000
             * info : 【广东深圳公司宝安区华丰分部】已签收，签收人是【已签收】
             * sort : 0
             */

            private int id;
            private int logisticsid;
            private long date;
            private String info;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLogisticsid() {
                return logisticsid;
            }

            public void setLogisticsid(int logisticsid) {
                this.logisticsid = logisticsid;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
