package com.credithc.baseapp.bean.resp;

import com.google.gson.JsonArray;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyong
 * @date 2019/10/22 16:29
 * @description
 */
public class BannerBean implements Serializable {

    /**
     * header : {"code":"0000","msg":"成功"}
     * body : {"shoppingCartUrl":"http://118.26.173.57:9000/shoppingcar","sysBanner":[{"id":3,"title":"分期大礼包","imgPath":"https://www.henghuirong.com/op_upload/app/20190729145505bayueapp3.jpg","sort":3,"route":["WebView",{"url":"http://10.150.30.58/act/act01"}]},{"id":12,"title":"banner","imgPath":"http://10.160.13.128/fs-api/downloadFile.hc?fsId=603FF5A0D9C942B1B26CD58C087808C3","sort":5,"route":["WebView",{"url":"https://www.baidu.com/"}]},{"id":17,"title":"迪奥999","imgPath":"http://10.160.13.128/fs-api/downloadFile.hc?fsId=390808ACA95848C7BA04E9333A16F780","sort":14,"route":["WebView",{"url":"https://h5.alwaysego.com/products/productsDetail?sku=4318398"}]}],"intervalTime":"5"}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean implements Serializable{
        /**
         * code : 0000
         * msg : 成功
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class BodyBean implements Serializable{
        /**
         * shoppingCartUrl : http://118.26.173.57:9000/shoppingcar
         * sysBanner : [{"id":3,"title":"分期大礼包","imgPath":"https://www.henghuirong.com/op_upload/app/20190729145505bayueapp3.jpg","sort":3,"route":["WebView",{"url":"http://10.150.30.58/act/act01"}]},{"id":12,"title":"banner","imgPath":"http://10.160.13.128/fs-api/downloadFile.hc?fsId=603FF5A0D9C942B1B26CD58C087808C3","sort":5,"route":["WebView",{"url":"https://www.baidu.com/"}]},{"id":17,"title":"迪奥999","imgPath":"http://10.160.13.128/fs-api/downloadFile.hc?fsId=390808ACA95848C7BA04E9333A16F780","sort":14,"route":["WebView",{"url":"https://h5.alwaysego.com/products/productsDetail?sku=4318398"}]}]
         * intervalTime : 5
         */

        private String shoppingCartUrl;
        private String intervalTime;
        private List<SysBannerBean> sysBanner;

        public String getShoppingCartUrl() {
            return shoppingCartUrl;
        }

        public void setShoppingCartUrl(String shoppingCartUrl) {
            this.shoppingCartUrl = shoppingCartUrl;
        }

        public String getIntervalTime() {
            return intervalTime;
        }

        public void setIntervalTime(String intervalTime) {
            this.intervalTime = intervalTime;
        }

        public List<SysBannerBean> getSysBanner() {
            return sysBanner;
        }

        public void setSysBanner(List<SysBannerBean> sysBanner) {
            this.sysBanner = sysBanner;
        }

        public static class SysBannerBean implements Serializable{
            /**
             * id : 3
             * title : 分期大礼包
             * imgPath : https://www.henghuirong.com/op_upload/app/20190729145505bayueapp3.jpg
             * sort : 3
             * route : ["WebView",{"url":"http://10.150.30.58/act/act01"}]
             */

            private int id;
            private String title;
            private String imgPath;
            private int sort;
            private JsonArray route;

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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public JsonArray getRoute() {
                return route;
            }

            public void setRoute(JsonArray route) {
                this.route = route;
            }
        }
    }
}
