package com.smg.art.base;

import java.util.List;

/**
 * Created by Lenovo on 2018/7/30.
 */

public class HomePageImgBean {


    /**
     * status : 1
     * msg : 获取列表成功
     * data : {"upperList":[{"id":1,"imgPath":"/static/uploads/image/722ddf43-0e34-4ef7-923a-af8e9191acc0.png","releaseStatus":"1","displayPlate":1,"remark":null,"url":null}],"categoryList":[{"id":1,"parentId":null,"parentCategoryName":null,"categoryName":"书画","ico":"/static/uploads/image/ff36f215-1666-48ad-a667-3f0cda6bf4cd.png","showStatus":1,"remark":null},{"id":2,"parentId":null,"parentCategoryName":null,"categoryName":"油画","ico":"/static/uploads/image/5151cc49-6904-4bad-8e3c-2474f308587d.png","showStatus":1,"remark":null},{"id":3,"parentId":null,"parentCategoryName":null,"categoryName":"花鸟画","ico":"/static/uploads/image/652a94da-7528-49be-9b84-cec3d95cae80.png","showStatus":1,"remark":null},{"id":4,"parentId":null,"parentCategoryName":null,"categoryName":"山水画","ico":"/static/uploads/image/fd337914-9ab8-4a19-b7df-2d2c2ad897ef.png","showStatus":1,"remark":null},{"id":5,"parentId":null,"parentCategoryName":null,"categoryName":"人物画","ico":"/static/uploads/image/71b65dad-6dbb-4fdf-af61-963daa9e4e0b.png","showStatus":1,"remark":null},{"id":6,"parentId":null,"parentCategoryName":null,"categoryName":"钱币画","ico":"/static/uploads/image/bc1a479d-8c03-4764-ad53-ac86a4de04e5.png","showStatus":1,"remark":null},{"id":7,"parentId":null,"parentCategoryName":null,"categoryName":"玉器","ico":"/static/uploads/image/3235eba2-3a45-410e-8a55-12e2ec3e18f5.png","showStatus":1,"remark":null},{"id":8,"parentId":null,"parentCategoryName":null,"categoryName":"民间精品","ico":"/static/uploads/image/a6368ed7-78d0-4051-b4bb-054a28fa33e9.png","showStatus":1,"remark":null},{"id":9,"parentId":null,"parentCategoryName":null,"categoryName":"古家具","ico":"/static/uploads/image/8a6019e9-20f8-45c4-b6d7-298857d73961.png","showStatus":1,"remark":null}],"underList":[{"id":2,"imgPath":"/static/uploads/image/ecb067d5-55ec-437d-b278-830089e973fc.png","releaseStatus":"1","displayPlate":2,"remark":null,"url":null}]}
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
        private List<UpperListBean> upperList;
        private List<CategoryListBean> categoryList;
        private List<UnderListBean> underList;

        public List<UpperListBean> getUpperList() {
            return upperList;
        }

        public void setUpperList(List<UpperListBean> upperList) {
            this.upperList = upperList;
        }

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public List<UnderListBean> getUnderList() {
            return underList;
        }

        public void setUnderList(List<UnderListBean> underList) {
            this.underList = underList;
        }

        public static class UpperListBean {
            /**
             * id : 1
             * imgPath : /static/uploads/image/722ddf43-0e34-4ef7-923a-af8e9191acc0.png
             * releaseStatus : 1
             * displayPlate : 1
             * remark : null
             * url : null
             */

            private int id;
            private String imgPath;
            private String releaseStatus;
            private int displayPlate;
            private Object remark;
            private Object url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getReleaseStatus() {
                return releaseStatus;
            }

            public void setReleaseStatus(String releaseStatus) {
                this.releaseStatus = releaseStatus;
            }

            public int getDisplayPlate() {
                return displayPlate;
            }

            public void setDisplayPlate(int displayPlate) {
                this.displayPlate = displayPlate;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }
        }

        public static class CategoryListBean {
            /**
             * id : 1
             * parentId : null
             * parentCategoryName : null
             * categoryName : 书画
             * ico : /static/uploads/image/ff36f215-1666-48ad-a667-3f0cda6bf4cd.png
             * showStatus : 1
             * remark : null
             */

            private int id;
            private Object parentId;
            private Object parentCategoryName;
            private String categoryName;
            private String ico;
            private int showStatus;
            private Object remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getParentCategoryName() {
                return parentCategoryName;
            }

            public void setParentCategoryName(Object parentCategoryName) {
                this.parentCategoryName = parentCategoryName;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getIco() {
                return ico;
            }

            public void setIco(String ico) {
                this.ico = ico;
            }

            public int getShowStatus() {
                return showStatus;
            }

            public void setShowStatus(int showStatus) {
                this.showStatus = showStatus;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }

        public static class UnderListBean {
            /**
             * id : 2
             * imgPath : /static/uploads/image/ecb067d5-55ec-437d-b278-830089e973fc.png
             * releaseStatus : 1
             * displayPlate : 2
             * remark : null
             * url : null
             */

            private int id;
            private String imgPath;
            private String releaseStatus;
            private int displayPlate;
            private Object remark;
            private Object url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getReleaseStatus() {
                return releaseStatus;
            }

            public void setReleaseStatus(String releaseStatus) {
                this.releaseStatus = releaseStatus;
            }

            public int getDisplayPlate() {
                return displayPlate;
            }

            public void setDisplayPlate(int displayPlate) {
                this.displayPlate = displayPlate;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }
        }
    }
}
