package com.zggk.newiroad.kdxf;

/**
 * Created by Administrator on 2018/5/22.
 */
public class VideoVo2 {

    /**
     * code : success
     * data : {"needLogin":1,"redirectUrl":"http://mjw666.com/api/touchGold/index","status":0,"url":"http://mjkf.oss-cn-beijing.aliyuncs.com/mjw-images/upload/201806/410JhJs.png"}
     * message : 请求成功
     * status : 1
     */

    private String code;
    private DataBean data;
    private String message;
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * needLogin : 1
         * redirectUrl : http://mjw666.com/api/touchGold/index
         * status : 0
         * url : http://mjkf.oss-cn-beijing.aliyuncs.com/mjw-images/upload/201806/410JhJs.png
         */

        private int needLogin;
        private String redirectUrl;
        private int status;
        private String url;

        public int getNeedLogin() {
            return needLogin;
        }

        public void setNeedLogin(int needLogin) {
            this.needLogin = needLogin;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
