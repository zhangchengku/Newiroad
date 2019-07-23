package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-12.
 */

public class LookIntbean {

    /**
     * XcrzMx : [{"XCR":"马睿","LDMC":"G303 81.588-95.042","XCSJ":"2019-03-08T16:27:18","TQ":"晴","XCNR":null,"XCQKJL":null,"XCXZ":"1","CLYQ":null,"CLJL":null,"CZWT":null}]
     * PIC : [{"FILEPATH":"http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1552033645759_2776d7a6-f005-4fed-8f7a-5925d4170208.jpg"}]
     * state : 1
     */

    private String state;
    private List<XcrzMxBean> XcrzMx;
    private List<PICBean> PIC;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<XcrzMxBean> getXcrzMx() {
        return XcrzMx;
    }

    public void setXcrzMx(List<XcrzMxBean> XcrzMx) {
        this.XcrzMx = XcrzMx;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public static class XcrzMxBean {
        /**
         * XCR : 马睿
         * LDMC : G303 81.588-95.042
         * XCSJ : 2019-03-08T16:27:18
         * TQ : 晴
         * XCNR : null
         * XCQKJL : null
         * XCXZ : 1
         * CLYQ : null
         * CLJL : null
         * CZWT : null
         */

        private String XCR;
        private String LDMC;
        private String XCSJ;
        private String TQ;
        private String XCNR;
        private String XCQKJL;
        private String XCXZ;
        private String CLYQ;
        private String CLJL;
        private String CZWT;
        private String DCLX;

        public String getDCLX() {
            return DCLX;
        }

        public void setDCLX(String DCLX) {
            this.DCLX = DCLX;
        }

        public String getXCR() {
            return XCR;
        }

        public void setXCR(String XCR) {
            this.XCR = XCR;
        }

        public String getLDMC() {
            return LDMC;
        }

        public void setLDMC(String LDMC) {
            this.LDMC = LDMC;
        }

        public String getXCSJ() {
            return XCSJ;
        }

        public void setXCSJ(String XCSJ) {
            this.XCSJ = XCSJ;
        }

        public String getTQ() {
            return TQ;
        }

        public void setTQ(String TQ) {
            this.TQ = TQ;
        }

        public String getXCNR() {
            return XCNR;
        }

        public void setXCNR(String XCNR) {
            this.XCNR = XCNR;
        }

        public String getXCQKJL() {
            return XCQKJL;
        }

        public void setXCQKJL(String XCQKJL) {
            this.XCQKJL = XCQKJL;
        }

        public String getXCXZ() {
            return XCXZ;
        }

        public void setXCXZ(String XCXZ) {
            this.XCXZ = XCXZ;
        }

        public String getCLYQ() {
            return CLYQ;
        }

        public void setCLYQ(String CLYQ) {
            this.CLYQ = CLYQ;
        }

        public String getCLJL() {
            return CLJL;
        }

        public void setCLJL(String CLJL) {
            this.CLJL = CLJL;
        }

        public String getCZWT() {
            return CZWT;
        }

        public void setCZWT(String CZWT) {
            this.CZWT = CZWT;
        }
    }

    public static class PICBean {
        /**
         * FILEPATH : http://106.37.229.146:4141/Files/YHXC/TEMP/PIC/1552033645759_2776d7a6-f005-4fed-8f7a-5925d4170208.jpg
         */

        private String FILEPATH;

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }
    }
}
