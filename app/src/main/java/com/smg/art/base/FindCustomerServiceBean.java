package com.smg.art.base;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/7.
 */

public class FindCustomerServiceBean {


    /**
     * status : 1
     * msg : 查询客服信息成功
     * data : [{"memberId":78,"memberName":"萌萌","mobilePhone":"18807490910","memberNo":"15324220720","headImg":"/static/uploads/userHeadImg/15324220720.jpg","isReal":1,"createTime":1532422072000,"locked":1},{"memberId":33,"memberName":"18278969173","mobilePhone":"18278969173","memberNo":"15301573726","headImg":"/static/uploads/image/defaultHeadImg.png","isReal":0,"createTime":1530157372000,"locked":1}]
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
         * memberId : 78
         * memberName : 萌萌
         * mobilePhone : 18807490910
         * memberNo : 15324220720
         * headImg : /static/uploads/userHeadImg/15324220720.jpg
         * isReal : 1
         * createTime : 1532422072000
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
