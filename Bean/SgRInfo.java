package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-21.
 */

public class SgRInfo {


    /**
     * MSG : SUCCESS
     * YBBHLXDATA : [{"BHLX":"边沟阻塞","BHLXID":"cefeb7bd-1a45-442a-993d-9cb64b2f8455"},{"BHLX":null,"BHLXID":"5c627e60-5230-4b75-b6f8-fa44eea6038b"},{"BHLX":"沉陷","BHLXID":"aed50ea9-189b-4e41-9d50-d27d15e207ff"},{"BHLX":"路面接缝","BHLXID":"46a15357-98f5-4182-a601-a21cded0781e"},{"BHLX":"纵缝","BHLXID":"e4f10f9d-7e11-4a2c-8482-13dc58b8b48f"},{"BHLX":null,"BHLXID":"20f9cf4f-24c8-4134-b529-68b4b47fa182"},{"BHLX":"路树遮挡公路牌、里程桩","BHLXID":"34684318-395e-4378-9866-fe13d9e9ae6c"},{"BHLX":"波浪涌包","BHLXID":"e81f3f82-1b0f-4d7d-9175-6a9124b7fac1"},{"BHLX":"严重边坡护坡塌陷","BHLXID":"6c5a3179-ac0b-406c-a48d-910ac4ca3ef4"},{"BHLX":"土方垃圾","BHLXID":"94bfab37-3d23-456e-8dc8-e859db3abdb4"},{"BHLX":"重度车辙","BHLXID":"4332b79b-c903-421f-abf0-21252642995a"},{"BHLX":"路树歪倒","BHLXID":"22113e3b-9f30-4279-af00-23cc3c0f61c7"},{"BHLX":"百米桩缺失","BHLXID":"92992192-7aa4-4cff-9336-581b1478ee21"},{"BHLX":"路缘石缺损","BHLXID":"fb39e670-9de6-44fa-832d-0abe163954cd"},{"BHLX":"土路肩阻水","BHLXID":"3d67c317-1652-4fab-97f6-ccaec2c3f2db"},{"BHLX":"坑槽","BHLXID":"d3e6d4dd-a602-4763-aaf7-b345eaf86c0d"},{"BHLX":"横缝","BHLXID":"8c128be5-b200-4fe9-abee-7c143170c164"},{"BHLX":"乔灌木枯株","BHLXID":"31528c7f-1708-40ec-8d52-d546a7f9e7da"},{"BHLX":"坑洞","BHLXID":"e5e068a6-b59e-4f7e-90d8-f3797466a8ae"},{"BHLX":"挡土墙破损","BHLXID":"023a2774-1235-40f4-8f21-f1c66ea624f8"},{"BHLX":"路肩边沟不洁","BHLXID":"33f82bb2-7685-4597-94dc-825496d02a9b"}]
     * LXDATA : [{"LXMC":"G201 鹤大线","LXID":"92095fb4-7a27-4d86-a277-1d5b1aced9e9"},{"LXMC":"G303 集阿线","LXID":"d545ce99-3d61-4a08-b83d-98f775630ad1"}]
     * BHLXDATA : [{"BHLX":"乔灌木枯株","BHLXID":"31528c7f-1708-40ec-8d52-d546a7f9e7da"},{"BHLX":"路肩高草","BHLXID":"820f36da-e366-4289-a166-91b9cc7a93b8"}]
     * STATE : 1
     * YBLXDATA : [{"LXMC":"G201 鹤大线","LXID":"92095fb4-7a27-4d86-a277-1d5b1aced9e9"},{"LXMC":"G303 集阿线","LXID":"d545ce99-3d61-4a08-b83d-98f775630ad1"},{"LXMC":"S205 石通线","LXID":"8713f148-1c32-49f4-b659-3a0995913e1f"}]
     */

    private String MSG;
    private String STATE;
    private List<YBBHLXDATABean> YBBHLXDATA;
    private List<LXDATABean> LXDATA;
    private List<BHLXDATABean> BHLXDATA;
    private List<YBLXDATABean> YBLXDATA;

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

    public List<YBBHLXDATABean> getYBBHLXDATA() {
        return YBBHLXDATA;
    }

    public void setYBBHLXDATA(List<YBBHLXDATABean> YBBHLXDATA) {
        this.YBBHLXDATA = YBBHLXDATA;
    }

    public List<LXDATABean> getLXDATA() {
        return LXDATA;
    }

    public void setLXDATA(List<LXDATABean> LXDATA) {
        this.LXDATA = LXDATA;
    }

    public List<BHLXDATABean> getBHLXDATA() {
        return BHLXDATA;
    }

    public void setBHLXDATA(List<BHLXDATABean> BHLXDATA) {
        this.BHLXDATA = BHLXDATA;
    }

    public List<YBLXDATABean> getYBLXDATA() {
        return YBLXDATA;
    }

    public void setYBLXDATA(List<YBLXDATABean> YBLXDATA) {
        this.YBLXDATA = YBLXDATA;
    }

    public static class YBBHLXDATABean {
        /**
         * BHLX : 边沟阻塞
         * BHLXID : cefeb7bd-1a45-442a-993d-9cb64b2f8455
         */

        private String BHLX;
        private String BHLXID;

        public String getBHLX() {
            return BHLX;
        }

        public void setBHLX(String BHLX) {
            this.BHLX = BHLX;
        }

        public String getBHLXID() {
            return BHLXID;
        }

        public void setBHLXID(String BHLXID) {
            this.BHLXID = BHLXID;
        }
    }

    public static class LXDATABean {
        /**
         * LXMC : G201 鹤大线
         * LXID : 92095fb4-7a27-4d86-a277-1d5b1aced9e9
         */

        private String LXMC;
        private String LXID;

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }
    }

    public static class BHLXDATABean {
        /**
         * BHLX : 乔灌木枯株
         * BHLXID : 31528c7f-1708-40ec-8d52-d546a7f9e7da
         */

        private String BHLX;
        private String BHLXID;

        public String getBHLX() {
            return BHLX;
        }

        public void setBHLX(String BHLX) {
            this.BHLX = BHLX;
        }

        public String getBHLXID() {
            return BHLXID;
        }

        public void setBHLXID(String BHLXID) {
            this.BHLXID = BHLXID;
        }
    }

    public static class YBLXDATABean {
        /**
         * LXMC : G201 鹤大线
         * LXID : 92095fb4-7a27-4d86-a277-1d5b1aced9e9
         */

        private String LXMC;
        private String LXID;

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }
    }
}
