package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/3 0003.
 */

public class PersonalCenterBean {

    /**
     * msg : 查询成功
     * data : {"memberId":78,"memberName":"18129852972","mobilePhone":"18129852972","memberNo":"15324220720","headImg":"/static/uploads/userHeadImg/15324220720.png","password":"FA1E717BD97D2902EB40B4A123108C66","isReal":1,"inviterMemberId":0,"contactMemberId":0,"cashTotal":87676,"energyTotal":87676,"moneyTotal":50944,"freeTotal":0,"storageTotal":0,"memberAddress":"OX294a8e5f4a2c4c53bc1b6a1b9835554c","createTime":"2018-07-24 16:47:52","locked":1,"token":"+xGmIKcMhuG8n27EZGPX0ad4zGNQjLesC+4CACdACo5Q4yXcUFAW50HxD0Mvl23cCicJVdizW+yJAGarVnIJKw=="}
     * status : 1
     */

    private String msg;
    private DataBean data;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * memberId : 78
         * memberName : 18129852972
         * mobilePhone : 18129852972
         * memberNo : 15324220720
         * headImg : /static/uploads/userHeadImg/15324220720.png
         * password : FA1E717BD97D2902EB40B4A123108C66
         * isReal : 1
         * inviterMemberId : 0
         * contactMemberId : 0
         * cashTotal : 87676.0
         * energyTotal : 87676.0
         * moneyTotal : 50944.0
         * freeTotal : 0.0
         * storageTotal : 0.0
         * memberAddress : OX294a8e5f4a2c4c53bc1b6a1b9835554c
         * createTime : 2018-07-24 16:47:52
         * locked : 1
         * token : +xGmIKcMhuG8n27EZGPX0ad4zGNQjLesC+4CACdACo5Q4yXcUFAW50HxD0Mvl23cCicJVdizW+yJAGarVnIJKw==
         */

        private int memberId;
        private String memberName;
        private String mobilePhone;
        private String memberNo;
        private String headImg;
        private String password;
        private int isReal;
        private int inviterMemberId;
        private int contactMemberId;
        private double cashTotal;
        private double energyTotal;
        private double moneyTotal;
        private double freeTotal;
        private double storageTotal;
        private String memberAddress;
        private String createTime;
        private int locked;
        private String token;

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(String memberNo) {
            this.memberNo = memberNo;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIsReal() {
            return isReal;
        }

        public void setIsReal(int isReal) {
            this.isReal = isReal;
        }

        public int getInviterMemberId() {
            return inviterMemberId;
        }

        public void setInviterMemberId(int inviterMemberId) {
            this.inviterMemberId = inviterMemberId;
        }

        public int getContactMemberId() {
            return contactMemberId;
        }

        public void setContactMemberId(int contactMemberId) {
            this.contactMemberId = contactMemberId;
        }

        public double getCashTotal() {
            return cashTotal;
        }

        public void setCashTotal(double cashTotal) {
            this.cashTotal = cashTotal;
        }

        public double getEnergyTotal() {
            return energyTotal;
        }

        public void setEnergyTotal(double energyTotal) {
            this.energyTotal = energyTotal;
        }

        public double getMoneyTotal() {
            return moneyTotal;
        }

        public void setMoneyTotal(double moneyTotal) {
            this.moneyTotal = moneyTotal;
        }

        public double getFreeTotal() {
            return freeTotal;
        }

        public void setFreeTotal(double freeTotal) {
            this.freeTotal = freeTotal;
        }

        public double getStorageTotal() {
            return storageTotal;
        }

        public void setStorageTotal(double storageTotal) {
            this.storageTotal = storageTotal;
        }

        public String getMemberAddress() {
            return memberAddress;
        }

        public void setMemberAddress(String memberAddress) {
            this.memberAddress = memberAddress;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getLocked() {
            return locked;
        }

        public void setLocked(int locked) {
            this.locked = locked;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
