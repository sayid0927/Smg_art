package com.smg.art.bean;

import java.util.List;

/**
 * Created by lurs on 2018/8/23 0023.
 */

public class ServiceBean {

    /**
     * status : 1
     * msg : 查询客服信息成功
     * data : [{"memberId":95,"memberName":"简单","mobilePhone":"13692202853","memberNo":"15343017131","headImg":"/static/uploads/userHeadImg/15343017131.jpg","isReal":1,"createTime":1534301713000,"locked":1}]
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
         * memberId : 95
         * memberName : 简单
         * mobilePhone : 13692202853
         * memberNo : 15343017131
         * headImg : /static/uploads/userHeadImg/15343017131.jpg
         * isReal : 1
         * createTime : 1534301713000
         * locked : 1
         */

        private int memberId;
        private String memberName;
        private String mobilePhone;
        private String memberNo;
        private String headImg;
        private int isReal;
        private long createTime;
        private int locked;

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

        public int getIsReal() {
            return isReal;
        }

        public void setIsReal(int isReal) {
            this.isReal = isReal;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getLocked() {
            return locked;
        }

        public void setLocked(int locked) {
            this.locked = locked;
        }
    }
}
