package com.credithc.baseapp.bean;

import com.google.gson.JsonArray;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: bannerBean
 * @author: zhengxf
 * @created: 2019/8/28.
 */

public class HomeBannerListBean implements Serializable {
    /**
     * sysBanner : [{"id":2,"title":"商城欢乐购","imgPath":"https://www.henghuirong.com/op_upload/app/20190729145505bayueapp3.jpg","url":"javascript:;","sort":2}]
     * intervalTime : 5
     */

    private String intervalTime;
    private String shoppingCartUrl;
    private List<SysBannerBean> sysBanner;

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getShoppingCartUrl() {
        return shoppingCartUrl;
    }

    public void setShoppingCartUrl(String shoppingCartUrl) {
        this.shoppingCartUrl = shoppingCartUrl;
    }

    public List<SysBannerBean> getSysBanner() {
        return sysBanner;
    }

    public void setSysBanner(List<SysBannerBean> sysBanner) {
        this.sysBanner = sysBanner;
    }

    public static class SysBannerBean implements Serializable {
        /**
         * id : 2
         * title : 商城欢乐购
         * imgPath : https://www.henghuirong.com/op_upload/app/20190729145505bayueapp3.jpg
         * url : javascript:;
         * sort : 2
         */

        private int id;
        private String title; // 活动主题
        private String imgPath;  // 图片存储路径
        private JsonArray route;  // 跳转地址
        private int sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public JsonArray getRoute() {
            return route;
        }

        public void setRoute(JsonArray route) {
            this.route = route;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
