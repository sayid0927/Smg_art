package com.smg.art.bean;

/**
 * Created by Mervin on 2018/7/25 0025.
 */

public class LoginBean {


    /**
     * status : 1
     * msg : 登录成功
     * data : {"memberNo":"15303450529","JSESSIONID_SHIRO":"90c90f9d-3585-411d-9336-70f9159a6053","JSESSIONID":"90c90f9d-3585-411d-9336-70f9159a6053","memberId":62,"RCToken":"WSLHy2CezIYsklI9Ax5KXq1337bdbXhGRi763MOs+4RbTn9GvosS7TE7CTadSlk/zR0F3LIhjc8="}
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
         * memberNo : 15303450529
         * JSESSIONID_SHIRO : 90c90f9d-3585-411d-9336-70f9159a6053
         * JSESSIONID : 90c90f9d-3585-411d-9336-70f9159a6053
         * memberId : 62
         * RCToken : WSLHy2CezIYsklI9Ax5KXq1337bdbXhGRi763MOs+4RbTn9GvosS7TE7CTadSlk/zR0F3LIhjc8=
         */

        private String memberNo;
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
