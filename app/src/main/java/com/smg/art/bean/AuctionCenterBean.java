package com.smg.art.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Lenovo on 2018/8/6.
 */

public class AuctionCenterBean {


    /**
     * status : 1
     * msg : 获取消息列表成功
     * data : {"list":[{"id":19,"member":{"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"},"auctionId":3,"memberId":78,"amount":200,"bided":6130,"createTime":1533371570000,"sysDate":1533544221516}],"maxMoney":{"id":19,"member":{"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"},"auctionId":3,"memberId":78,"amount":200,"bided":6130,"createTime":1533371570000}}
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
         * list : [{"id":19,"member":{"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"},"auctionId":3,"memberId":78,"amount":200,"bided":6130,"createTime":1533371570000,"sysDate":1533544221516}]
         * maxMoney : {"id":19,"member":{"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"},"auctionId":3,"memberId":78,"amount":200,"bided":6130,"createTime":1533371570000}
         */

        private MaxMoneyBean maxMoney;
        private List<ListBean> list;

        public MaxMoneyBean getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(MaxMoneyBean maxMoney) {
            this.maxMoney = maxMoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MaxMoneyBean {
            /**
             * id : 19
             * member : {"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"}
             * auctionId : 3
             * memberId : 78
             * amount : 200.0
             * bided : 6130.0
             * createTime : 1533371570000
             */

            private int id;
            private MemberBean member;
            private int auctionId;
            private int memberId;
            private double amount;
            private double bided;
            private long createTime;
            private  int stepSize;
            private  int startPrice;

            public int getStartPrice() {
                return startPrice;
            }

            public void setStartPrice(int startPrice) {
                this.startPrice = startPrice;
            }

            public int getStepSize() {
                return stepSize;
            }

            public void setStepSize(int stepSize) {
                this.stepSize = stepSize;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public int getAuctionId() {
                return auctionId;
            }

            public void setAuctionId(int auctionId) {
                this.auctionId = auctionId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getBided() {
                return bided;
            }

            public void setBided(double bided) {
                this.bided = bided;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public static class MemberBean {
                /**
                 * memberName : 萌萌
                 * headImg : /static/uploads/userHeadImg/15324220720.png
                 */

                private String memberName;
                private String headImg;

                public String getMemberName() {
                    return memberName;
                }

                public void setMemberName(String memberName) {
                    this.memberName = memberName;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }
            }
        }

        public static class ListBean implements MultiItemEntity {
            /**
             * id : 19
             * member : {"memberName":"萌萌","headImg":"/static/uploads/userHeadImg/15324220720.png"}
             * auctionId : 3
             * memberId : 78
             * amount : 200.0
             * bided : 6130.0
             * createTime : 1533371570000
             * sysDate : 1533544221516
             */
            public static final int LEFT = 1;
            public static final int RIGHT = 2;
            private int id;
            private MemberBeanX member;
            private int auctionId;
            private int memberId;
            private double amount;
            private double bided;
            private long createTime;
            private long sysDate;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public MemberBeanX getMember() {
                return member;
            }

            public void setMember(MemberBeanX member) {
                this.member = member;
            }

            public int getAuctionId() {
                return auctionId;
            }

            public void setAuctionId(int auctionId) {
                this.auctionId = auctionId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getBided() {
                return bided;
            }

            public void setBided(double bided) {
                this.bided = bided;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getSysDate() {
                return sysDate;
            }

            public void setSysDate(long sysDate) {
                this.sysDate = sysDate;
            }

            public void setItemType(int type) {
                this.type = type;
            }

            @Override
            public int getItemType() {
                return type;
            }

            public static class MemberBeanX {
                /**
                 * memberName : 萌萌
                 * headImg : /static/uploads/userHeadImg/15324220720.png
                 */

                private String memberName;
                private String headImg;

                public String getMemberName() {
                    return memberName;
                }

                public void setMemberName(String memberName) {
                    this.memberName = memberName;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }
            }
        }
    }
}
