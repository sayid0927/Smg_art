package com.smg.art.bean;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class CheckBankCardBean {

    /**
     * status : 1
     * msg : OK
     * data : {"id":12,"memberId":78,"bank":"建设银行","openBankName":"上海","bankUserName":"抵抗","cardNo":"6217002710000684874","bankType":"CCB","mobile":"18807490910","addTime":"2018-08-02 17:49:46","receiptName":null}
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
         * id : 12
         * memberId : 78
         * bank : 建设银行
         * openBankName : 上海
         * bankUserName : 抵抗
         * cardNo : 6217002710000684874
         * bankType : CCB
         * mobile : 18807490910
         * addTime : 2018-08-02 17:49:46
         * receiptName : null
         */

        private int id;
        private int memberId;
        private String bank;
        private String openBankName;
        private String bankUserName;
        private String cardNo;
        private String bankType;
        private String mobile;
        private String addTime;
        private String receiptName;

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

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getOpenBankName() {
            return openBankName;
        }

        public void setOpenBankName(String openBankName) {
            this.openBankName = openBankName;
        }

        public String getBankUserName() {
            return bankUserName;
        }

        public void setBankUserName(String bankUserName) {
            this.bankUserName = bankUserName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getReceiptName() {
            return receiptName;
        }

        public void setReceiptName(String receiptName) {
            this.receiptName = receiptName;
        }
    }
}
