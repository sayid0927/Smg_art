package com.smg.art.bean;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public class LoginBean {


    /**
     * status : 1
     * msg : 登录成功
     * data : {"memberNo":"15324886309","headImg":"/static/uploads/image/defaultHeadImg.png","JSESSIONID_SHIRO":"1a13a563-486f-4354-8539-d3d3b2fe701f","JSESSIONID":"1a13a563-486f-4354-8539-d3d3b2fe701f","memberId":79,"RCToken":"b9n9IEfFQuM5HNaqJLXWGK1337bdbXhGRi763MOs+4QH2GNeODKQH4RuiaK/5KCqVnw6JxqSbKQ="}
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
         * memberNo : 15324886309
         * headImg : /static/uploads/image/defaultHeadImg.png
         * JSESSIONID_SHIRO : 1a13a563-486f-4354-8539-d3d3b2fe701f
         * JSESSIONID : 1a13a563-486f-4354-8539-d3d3b2fe701f
         * memberId : 79
         * RCToken : b9n9IEfFQuM5HNaqJLXWGK1337bdbXhGRi763MOs+4QH2GNeODKQH4RuiaK/5KCqVnw6JxqSbKQ=
         */

        private String memberNo;
        private String headImg;
        private String JSESSIONID_SHIRO;
        private String JSESSIONID;
        private int memberId;
        private String RCToken;

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

        public String getJSESSIONID_SHIRO() {
            return JSESSIONID_SHIRO;
        }

        public void setJSESSIONID_SHIRO(String JSESSIONID_SHIRO) {
            this.JSESSIONID_SHIRO = JSESSIONID_SHIRO;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getRCToken() {
            return RCToken;
        }

        public void setRCToken(String RCToken) {
            this.RCToken = RCToken;
        }
    }
}
