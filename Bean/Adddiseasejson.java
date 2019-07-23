package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-17.
 */

public class Adddiseasejson {

    /**
     * BHMC : a18d2cc8-9029-4874-b4a6-3c6e767f879f
     * CREATOR : 马睿
     * CZFA : [{"GCXMID":"6e5ad833-bff8-466c-a8ec-4c4bf6bee2dd","JSGS":"2"}]
     * CZFAMC : 修复
     * DCLX : 1
     * DCR : 马睿
     * DCSJ : 2019-03-18 09:47:21
     * FXDW : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
     * GYDW : 5c16c3d8-13c5-40ac-95b7-1c9496ff208a
     * LXCODE : d545ce99-3d61-4a08-b83d-98f775630ad1
     * PIC : [{"PicDataBlob":"/9j/4AA","PicFileName":"1552873665459.jpg","PicFileType":"jpg"}]
     * QDZH : 82.83
     * SFJL : 0
     * SHBW : 路面
     * WZFX : 上行—右
     */

    private String BHMC;
    private String CREATOR;
    private String CZFAMC;
    private String DCLX;
    private String DCR;
    private String DCSJ;
    private String FXDW;
    private String GYDW;
    private String LXCODE;
    private String QDZH;
    private String SFJL;
    private String SHBW;
    private String WZFX;
    private List<CZFABean> CZFA;
    private List<PICBean> PIC;

    public String getBHMC() {
        return BHMC;
    }

    public void setBHMC(String BHMC) {
        this.BHMC = BHMC;
    }

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getCZFAMC() {
        return CZFAMC;
    }

    public void setCZFAMC(String CZFAMC) {
        this.CZFAMC = CZFAMC;
    }

    public String getDCLX() {
        return DCLX;
    }

    public void setDCLX(String DCLX) {
        this.DCLX = DCLX;
    }

    public String getDCR() {
        return DCR;
    }

    public void setDCR(String DCR) {
        this.DCR = DCR;
    }

    public String getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(String DCSJ) {
        this.DCSJ = DCSJ;
    }

    public String getFXDW() {
        return FXDW;
    }

    public void setFXDW(String FXDW) {
        this.FXDW = FXDW;
    }

    public String getGYDW() {
        return GYDW;
    }

    public void setGYDW(String GYDW) {
        this.GYDW = GYDW;
    }

    public String getLXCODE() {
        return LXCODE;
    }

    public void setLXCODE(String LXCODE) {
        this.LXCODE = LXCODE;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getSFJL() {
        return SFJL;
    }

    public void setSFJL(String SFJL) {
        this.SFJL = SFJL;
    }

    public String getSHBW() {
        return SHBW;
    }

    public void setSHBW(String SHBW) {
        this.SHBW = SHBW;
    }

    public String getWZFX() {
        return WZFX;
    }

    public void setWZFX(String WZFX) {
        this.WZFX = WZFX;
    }

    public List<CZFABean> getCZFA() {
        return CZFA;
    }

    public void setCZFA(List<CZFABean> CZFA) {
        this.CZFA = CZFA;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public static class CZFABean {
        /**
         * GCXMID : 6e5ad833-bff8-466c-a8ec-4c4bf6bee2dd
         * JSGS : 2
         */

        private String GCXMID;
        private String JSGS;

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
    }

    public static class PICBean {
        /**
         * PicDataBlob : /9j/4AA
         * PicFileName : 1552873665459.jpg
         * PicFileType : jpg
         */

        private String PicDataBlob;
        private String PicFileName;
        private String PicFileType;

        public String getPicDataBlob() {
            return PicDataBlob;
        }

        public void setPicDataBlob(String PicDataBlob) {
            this.PicDataBlob = PicDataBlob;
        }

        public String getPicFileName() {
            return PicFileName;
        }

        public void setPicFileName(String PicFileName) {
            this.PicFileName = PicFileName;
        }

        public String getPicFileType() {
            return PicFileType;
        }

        public void setPicFileType(String PicFileType) {
            this.PicFileType = PicFileType;
        }
    }

}
