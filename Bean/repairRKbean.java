package com.zggk.newiroad.Bean;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-19.
 */

public class repairRKbean {

    /**
     * GYDWXLK : [{"GYDWMC":"金厂道班","GYDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a"}]
     * LXDATA : [{"LXMC":"G303 集阿线","LXID":"d545ce99-3d61-4a08-b83d-98f775630ad1"}]
     * state : 1
     * BHLXDATA : [{"BHLX":"沉陷","BHLXID":"aed50ea9-189b-4e41-9d50-d27d15e207ff"},{"BHLX":"边沟阻塞","BHLXID":"cefeb7bd-1a45-442a-993d-9cb64b2f8455"},{"BHLX":"路树遮挡公路牌、里程桩","BHLXID":"34684318-395e-4378-9866-fe13d9e9ae6c"},{"BHLX":"纵缝","BHLXID":"e4f10f9d-7e11-4a2c-8482-13dc58b8b48f"},{"BHLX":"波浪涌包","BHLXID":"e81f3f82-1b0f-4d7d-9175-6a9124b7fac1"},{"BHLX":"严重边坡护坡塌陷","BHLXID":"6c5a3179-ac0b-406c-a48d-910ac4ca3ef4"},{"BHLX":"边沟过道涵缺损","BHLXID":"f8fc3ecc-93ef-49a8-9733-7a7c4227544a"},{"BHLX":"路缘石缺损","BHLXID":"fb39e670-9de6-44fa-832d-0abe163954cd"},{"BHLX":"重度龟裂","BHLXID":"a84949d1-23ce-4618-9e34-50ee3aa8f088"},{"BHLX":"坑槽","BHLXID":"d3e6d4dd-a602-4763-aaf7-b345eaf86c0d"},{"BHLX":"坑洞","BHLXID":"e5e068a6-b59e-4f7e-90d8-f3797466a8ae"},{"BHLX":"乔灌木枯株","BHLXID":"31528c7f-1708-40ec-8d52-d546a7f9e7da"},{"BHLX":"横缝","BHLXID":"8c128be5-b200-4fe9-abee-7c143170c164"},{"BHLX":"唧浆","BHLXID":"eacd7162-0b68-45a2-9ff5-4a958431df36"},{"BHLX":"里程碑缺失","BHLXID":"8507dcc5-891d-40a3-a652-4f1b6588e5b8"},{"BHLX":"路肩高草","BHLXID":"820f36da-e366-4289-a166-91b9cc7a93b8"},{"BHLX":"板角断裂","BHLXID":"dc6b9f23-50a9-4b96-925f-9b09e8acb640"},{"BHLX":"泛油","BHLXID":"a18d2cc8-9029-4874-b4a6-3c6e767f879f"}]
     * GYDW : [{"GYDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a","GYDWMC":"金厂道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]}]
     * RWDH : 20190319232226176_26177
     */
    private String GYDWXLK;
//    private ArrayList<LXDATABean> LXDATA;
    private String state;
//    private ArrayList<BHLXDATABean> BHLXDATA;
//    private ArrayList<GYDWBean> GYDW;
    private String RWDH;

    public String getGYDWXLK() {
        return GYDWXLK;
    }

    public void setGYDWXLK(String GYDWXLK) {
        this.GYDWXLK = GYDWXLK;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRWDH() {
        return RWDH;
    }

    public void setRWDH(String RWDH) {
        this.RWDH = RWDH;
    }
    //    public ArrayList<GYDWXLKBean> getGYDWXLK() {
//        return GYDWXLK;
//    }
//
//    public void setGYDWXLK(ArrayList<GYDWXLKBean> GYDWXLK) {
//        this.GYDWXLK = GYDWXLK;
//    }
//
//    public ArrayList<LXDATABean> getLXDATA() {
//        return LXDATA;
//    }
//
//    public void setLXDATA(ArrayList<LXDATABean> LXDATA) {
//        this.LXDATA = LXDATA;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public ArrayList<BHLXDATABean> getBHLXDATA() {
//        return BHLXDATA;
//    }
//
//    public void setBHLXDATA(ArrayList<BHLXDATABean> BHLXDATA) {
//        this.BHLXDATA = BHLXDATA;
//    }
//
//    public ArrayList<GYDWBean> getGYDW() {
//        return GYDW;
//    }
//
//    public void setGYDW(ArrayList<GYDWBean> GYDW) {
//        this.GYDW = GYDW;
//    }
//
//    public String getRWDH() {
//        return RWDH;
//    }
//
//    public void setRWDH(String RWDH) {
//        this.RWDH = RWDH;
//    }
//
//    public static class GYDWBean {
//        private String GYDWID;
//        private String GYDWMC;
//        private String PARENTID;
//        private  ArrayList<Object> children;
//
//        public String getGYDWID() {
//            return GYDWID;
//        }
//
//        public void setGYDWID(String GYDWID) {
//            this.GYDWID = GYDWID;
//        }
//
//        public String getGYDWMC() {
//            return GYDWMC;
//        }
//
//        public void setGYDWMC(String GYDWMC) {
//            this.GYDWMC = GYDWMC;
//        }
//
//        public String getPARENTID() {
//            return PARENTID;
//        }
//
//        public void setPARENTID(String PARENTID) {
//            this.PARENTID = PARENTID;
//        }
//
//        public ArrayList<Object> getChildren() {
//            return children;
//        }
//
//        public void setChildren(ArrayList<Object> children) {
//            this.children = children;
//        }
//    }
//    public static class BHLXDATABean {
//        private String BHLX;
//        private String BHLXID;
//
//        public String getBHLX() {
//            return BHLX;
//        }
//
//        public void setBHLX(String BHLX) {
//            this.BHLX = BHLX;
//        }
//
//        public String getBHLXID() {
//            return BHLXID;
//        }
//
//        public void setBHLXID(String BHLXID) {
//            this.BHLXID = BHLXID;
//        }
//    }
//    public static class LXDATABean {
//        private String LXMC;
//        private String LXID;
//
//        public String getLXMC() {
//            return LXMC;
//        }
//
//        public void setLXMC(String LXMC) {
//            this.LXMC = LXMC;
//        }
//
//        public String getLXID() {
//            return LXID;
//        }
//
//        public void setLXID(String LXID) {
//            this.LXID = LXID;
//        }
//    }
//    public static class GYDWXLKBean {
//        private String GYDWMC;
//        private String GYDWID;
//
//        public String getGYDWMC() {
//            return GYDWMC;
//        }
//
//        public void setGYDWMC(String GYDWMC) {
//            this.GYDWMC = GYDWMC;
//        }
//
//        public String getGYDWID() {
//            return GYDWID;
//        }
//
//        public void setGYDWID(String GYDWID) {
//            this.GYDWID = GYDWID;
//        }
//    }

}
