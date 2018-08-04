package com.smg.art.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/3.
 */

public class HotWordsListBean {


    /**
     * status : 1
     * msg : 获取列表成功
     * data : {"recentlyWords":[{"id":39,"memberId":62,"word":"Saugus","status":null,"createTime":1533267949000},{"id":38,"memberId":62,"word":"aa","status":null,"createTime":1533267936000},{"id":37,"memberId":62,"word":"淘宝","status":null,"createTime":1533267548000}],"hotWords":[{"id":null,"memberId":null,"word":"淘宝","status":null,"createTime":null},{"id":null,"memberId":null,"word":"aa","status":null,"createTime":null},{"id":null,"memberId":null,"word":"Saugus","status":null,"createTime":null},{"id":null,"memberId":null,"word":"111","status":null,"createTime":null},{"id":null,"memberId":null,"word":"dd","status":null,"createTime":null},{"id":null,"memberId":null,"word":"bb","status":null,"createTime":null},{"id":null,"memberId":null,"word":"Do","status":null,"createTime":null}]}
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
        private List<RecentlyWordsBean> recentlyWords;
        private List<HotWordsBean> hotWords;

        public List<RecentlyWordsBean> getRecentlyWords() {
            return recentlyWords;
        }

        public void setRecentlyWords(List<RecentlyWordsBean> recentlyWords) {
            this.recentlyWords = recentlyWords;
        }

        public List<HotWordsBean> getHotWords() {
            return hotWords;
        }

        public void setHotWords(List<HotWordsBean> hotWords) {
            this.hotWords = hotWords;
        }

        public static class RecentlyWordsBean {
            /**
             * id : 39
             * memberId : 62
             * word : Saugus
             * status : null
             * createTime : 1533267949000
             */

            private int id;
            private int memberId;
            private String word;
            private Object status;
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

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        public static class HotWordsBean {
            /**
             * id : null
             * memberId : null
             * word : 淘宝
             * status : null
             * createTime : null
             */

            private int id;
            private int memberId;
            private String word;
            private Object status;
            private Object createTime;

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

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }
        }
    }
}
