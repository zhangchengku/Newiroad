package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-26.
 */

public class seasonbean {

    /**
     * MSG : SUCCESS
     * YYSGLX : [{"SGLX":"26280ba1-d11f-4d9f-922b-ac8e81dece89","SGLXMC":"裂缝灌缝","JLDW":"米"},{"SGLX":"e05a81de-d0cf-4304-99fc-21bd6845314c","SGLXMC":"清理越冬垃圾","JLDW":"米"},{"SGLX":"53ee9620-61e3-4c77-96f7-d96a13a93b4f","SGLXMC":"路树刷白","JLDW":"米"},{"SGLX":"955ab546-73df-458b-8a19-6dae7e2eb06c","SGLXMC":"标牌刷新","JLDW":"个"}]
     * STATE : 1
     * GYDW : [{"GYDWID":"427b6c94-ab0e-4191-be92-ab1cc10f95fd","GYDWMC":"二道江道班"},{"GYDWID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","GYDWMC":"路祥养护公司"},{"GYDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a","GYDWMC":"金厂道班"},{"GYDWID":"eef55158-0cf1-45fc-a7f9-e05e23f2fc79","GYDWMC":"五道江道班"},{"GYDWID":"2e041626-38c6-46cb-8173-b094704de01b","GYDWMC":"九公里道班"},{"GYDWID":"aa6815aa-c139-4b27-be53-09bb725d97bc","GYDWMC":"通梅道班"},{"GYDWID":"c7e454c2-02bc-49f4-85a1-0eb356ff8d05","GYDWMC":"鸭园道班"},{"GYDWID":"4253c068-6f80-4827-92c7-ad6dc683d774","GYDWMC":"桦树道班"},{"GYDWID":"a6e8b3fd-3ff4-4f07-8d08-744ba1d0aaed","GYDWMC":"路桥养护公司"},{"GYDWID":"332408b2-8b8f-4034-84b5-76d61598a5b2","GYDWMC":"官道岭道班"},{"GYDWID":"d4078045-645d-4802-bc74-b0ce703ff9cc","GYDWMC":"通化市郊公路管理段"}]
     * YYLXDATA : [{"LXID":"92095fb4-7a27-4d86-a277-1d5b1aced9e9","LXMC":"G201"},{"LXID":"d545ce99-3d61-4a08-b83d-98f775630ad1","LXMC":"G303"},{"LXID":"8713f148-1c32-49f4-b659-3a0995913e1f","LXMC":"S205"},{"LXID":"635801e9-9dca-4e6d-9c3f-be46b9ececb5","LXMC":"S721"},{"LXID":"5e0e784a-6039-42db-916b-588fc5480138","LXMC":"S722"},{"LXID":"15fff2a7-a71c-4b71-b0e9-4e19fcfd9f84","LXMC":"S723"},{"LXID":"8e2f283b-8e75-4680-a05e-8984b2612678","LXMC":"S724"},{"LXID":"14f52399-b8ad-420b-972c-80e86f753bee","LXMC":"S725"},{"LXID":"6fde4bc0-b4cc-4faf-8442-7bcf022f8a36","LXMC":"S726"},{"LXID":"892bec02-57e8-41e7-a2c3-f72221da9585","LXMC":"S727"},{"LXID":"7d20abc3-569a-4d24-b154-03bb56a1bfd3","LXMC":"S728"},{"LXID":"5afbb782-74d9-4ae2-a169-337f47fe3411","LXMC":"S740"}]
     */

    private String MSG;
    private String STATE;
    private List<YYSGLXBean> YYSGLX;
    private List<GYDWBean> GYDW;
    private List<YYLXDATABean> YYLXDATA;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<YYSGLXBean> getYYSGLX() {
        return YYSGLX;
    }

    public void setYYSGLX(List<YYSGLXBean> YYSGLX) {
        this.YYSGLX = YYSGLX;
    }

    public List<GYDWBean> getGYDW() {
        return GYDW;
    }

    public void setGYDW(List<GYDWBean> GYDW) {
        this.GYDW = GYDW;
    }

    public List<YYLXDATABean> getYYLXDATA() {
        return YYLXDATA;
    }

    public void setYYLXDATA(List<YYLXDATABean> YYLXDATA) {
        this.YYLXDATA = YYLXDATA;
    }

    public static class YYSGLXBean {
        /**
         * SGLX : 26280ba1-d11f-4d9f-922b-ac8e81dece89
         * SGLXMC : 裂缝灌缝
         * JLDW : 米
         */

        private String SGLX;
        private String SGLXMC;
        private String JLDW;

        public String getSGLX() {
            return SGLX;
        }

        public void setSGLX(String SGLX) {
            this.SGLX = SGLX;
        }

        public String getSGLXMC() {
            return SGLXMC;
        }

        public void setSGLXMC(String SGLXMC) {
            this.SGLXMC = SGLXMC;
        }

        public String getJLDW() {
            return JLDW;
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }
    }

    public static class GYDWBean {
        /**
         * GYDWID : 427b6c94-ab0e-4191-be92-ab1cc10f95fd
         * GYDWMC : 二道江道班
         */

        private String GYDWID;
        private String GYDWMC;

        public String getGYDWID() {
            return GYDWID;
        }

        public void setGYDWID(String GYDWID) {
            this.GYDWID = GYDWID;
        }

        public String getGYDWMC() {
            return GYDWMC;
        }

        public void setGYDWMC(String GYDWMC) {
            this.GYDWMC = GYDWMC;
        }
    }

    public static class YYLXDATABean {
        /**
         * LXID : 92095fb4-7a27-4d86-a277-1d5b1aced9e9
         * LXMC : G201
         */

        private String LXID;
        private String LXMC;
        private String QDZH;
        private String ZDZH;

        public String getQDZH() {
            return QDZH;
        }

        public void setQDZH(String QDZH) {
            this.QDZH = QDZH;
        }

        public String getZDZH() {
            return ZDZH;
        }

        public void setZDZH(String ZDZH) {
            this.ZDZH = ZDZH;
        }

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }
    }
}
