package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/1.
 */

public class SearchMemberBean {


    /**
     * status : 1
     * msg : OK
     * data : [{"memberId":79,"memberName":"15118183011","mobilePhone":"15118183011","headImg":"/static/uploads/image/defaultHeadImg.png"},{"memberId":80,"memberName":"系统","mobilePhone":"15118183022","headImg":"/static/uploads/image/defaultHeadImg.png"},{"memberId":81,"memberName":"订单","mobilePhone":"15118183033","headImg":"/static/uploads/image/defaultHeadImg.png"}]
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
         * memberId : 79
         * memberName : 15118183011
         * mobilePhone : 15118183011
         * headImg : /static/uploads/image/defaultHeadImg.png
         */

        private int memberId;
        private String memberName;
        private String mobilePhone;
        private String headImg;

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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }
}
