package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-20.
 */

public class repairPFjson {

    private List<PFDXXBean> PFDXX;
    private List<XCBHBean> XCBH;
    private List<BHZTBean> BHZT;

    public List<BHZTBean> getBHZT() {
        return BHZT;
    }

    public void setBHZT(List<BHZTBean> BHZT) {
        this.BHZT = BHZT;
    }
    public static class BHZTBean {
        private String BHZT;
        private String BHID;
        private String CZR;

        public String getBHZT() {
            return BHZT;
        }

        public void setBHZT(String BHZT) {
            this.BHZT = BHZT;
        }

        public String getBHID() {
            return BHID;
        }

        public void setBHID(String BHID) {
            this.BHID = BHID;
        }

        public String getCZR() {
            return CZR;
        }

        public void setCZR(String CZR) {
            this.CZR = CZR;
        }
    }
    public List<PFDXXBean> getPFDXX() {
        return PFDXX;
    }

    public void setPFDXX(List<PFDXXBean> PFDXX) {
        this.PFDXX = PFDXX;
    }

    public List<XCBHBean> getXCBH() {
        return XCBH;
    }

    public void setXCBH(List<XCBHBean> XCBH) {
        this.XCBH = XCBH;
    }

    public static class PFDXXBean {
        /**
         * CREATOR : 马睿
         * GZZLBH : 20190320170455581_55582
         * JLDW : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
         * PFR : 马睿
         * RWXDSJ : 2019/03/20 17:06:32
         * SGDW : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
         * YQWCSJ : 2019-03-2017:06:32
         */

        private String CREATOR;
        private String GZZLBH;
        private String JLDW;
        private String PFR;
        private String RWXDSJ;
        private String SGDW;
        private String YQWCSJ;

        public String getCREATOR() {
            return CREATOR;
        }

        public void setCREATOR(String CREATOR) {
            this.CREATOR = CREATOR;
        }

        public String getGZZLBH() {
            return GZZLBH;
        }

        public void setGZZLBH(String GZZLBH) {
            this.GZZLBH = GZZLBH;
        }

        public String getJLDW() {
            return JLDW;
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }

        public String getPFR() {
            return PFR;
        }

        public void setPFR(String PFR) {
            this.PFR = PFR;
        }

        public String getRWXDSJ() {
            return RWXDSJ;
        }

        public void setRWXDSJ(String RWXDSJ) {
            this.RWXDSJ = RWXDSJ;
        }

        public String getSGDW() {
            return SGDW;
        }

        public void setSGDW(String SGDW) {
            this.SGDW = SGDW;
        }

        public String getYQWCSJ() {
            return YQWCSJ;
        }

        public void setYQWCSJ(String YQWCSJ) {
            this.YQWCSJ = YQWCSJ;
        }
    }

    public static class XCBHBean {
        /**
         * GUID_OBJ : 6a144885-1b3e-4879-a5ae-62be0a480c21
         */
        private String GUID_OBJ;
        private String JLDW;
        private String SGDW;
        private String PFR;
        private String CREATOR;
        private String QDZHPF;
        private String CZFAMC;
        private List<CZFABean> CZFA;



        public List<CZFABean> getCZFA() {
            return CZFA;
        }

        public void setCZFA(List<CZFABean> CZFA) {
            this.CZFA = CZFA;
        }

        public String getJLDW() {
            return JLDW;
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }

        public String getSGDW() {
            return SGDW;
        }

        public void setSGDW(String SGDW) {
            this.SGDW = SGDW;
        }

        public String getPFR() {
            return PFR;
        }

        public void setPFR(String PFR) {
            this.PFR = PFR;
        }

        public String getCREATOR() {
            return CREATOR;
        }

        public void setCREATOR(String CREATOR) {
            this.CREATOR = CREATOR;
        }

        public String getQDZHPF() {
            return QDZHPF;
        }

        public void setQDZHPF(String QDZHPF) {
            this.QDZHPF = QDZHPF;
        }

        public String getCZFAMC() {
            return CZFAMC;
        }

        public void setCZFAMC(String CZFAMC) {
            this.CZFAMC = CZFAMC;
        }


        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }
    }
    public static class CZFABean {
        /**
         * GCXM : 浆砌构造物维修
         * GCXMID : 36bb1d49-7742-48d7-99cf-b334416b1ecc
         * JSGS : 2+1
         * SL : 3
         * DW : 平方米
         * HD : null
         */

        private String GCXM;
        private String GCXMID;
        private String JSGS;
        private String SL;
        private String DW;
        private Object HD;

        public String getGCXM() {
            return GCXM;
        }

        public void setGCXM(String GCXM) {
            this.GCXM = GCXM;
        }

        public String getGCXMID() {
            return GCXMID;
        }

        public void setGCXMID(String GCXMID) {
            this.GCXMID = GCXMID;
        }

        public String getJSGS() {
            return JSGS;
        }

        public void setJSGS(String JSGS) {
            this.JSGS = JSGS;
        }

        public String getSL() {
            return SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }

        public String getDW() {
            return DW;
        }

        public void setDW(String DW) {
            this.DW = DW;
        }

        public Object getHD() {
            return HD;
        }

        public void setHD(Object HD) {
            this.HD = HD;
        }
    }
}

