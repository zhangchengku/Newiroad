package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-14.
 */

public class AddLogjson {
    private List<XCRZBean> XCRZ;

    public List<XCRZBean> getXCRZ() {
        return XCRZ;
    }

    public void setXCRZ(List<XCRZBean> XCRZ) {
        this.XCRZ = XCRZ;
    }

    public static class XCRZBean {
        private String GYDW;
        private String XCLD;
        private String LDMC;
        private String XCSJ;
        private String XCR;
        private String CREATOR;
        private String XCXZ;
        private String CLJL;
        private String XCNR;
        private String TQ;
        private List<PICBean> PICRZ;

        public String getTQ() {
            return TQ;
        }

        public void setTQ(String TQ) {
            this.TQ = TQ;
        }

        public String getGYDW() {
            return GYDW;
        }

        public void setGYDW(String GYDW) {
            this.GYDW = GYDW;
        }

        public String getXCLD() {
            return XCLD;
        }

        public void setXCLD(String XCLD) {
            this.XCLD = XCLD;
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

        public String getXCR() {
            return XCR;
        }

        public void setXCR(String XCR) {
            this.XCR = XCR;
        }

        public String getCREATOR() {
            return CREATOR;
        }

        public void setCREATOR(String CREATOR) {
            this.CREATOR = CREATOR;
        }

        public String getXCXZ() {
            return XCXZ;
        }

        public void setXCXZ(String XCXZ) {
            this.XCXZ = XCXZ;
        }

        public String getCLJL() {
            return CLJL;
        }

        public void setCLJL(String CLJL) {
            this.CLJL = CLJL;
        }

        public String getXCNR() {
            return XCNR;
        }

        public void setXCNR(String XCNR) {
            this.XCNR = XCNR;
        }

        public List<PICBean> getPICRZ() {
            return PICRZ;
        }

        public void setPICRZ(List<PICBean> PICRZ) {
            this.PICRZ = PICRZ;
        }


    }
    public static class PICBean {
        /**
         * PicFileName : 1491897074640.jpg
         * PicFileType : jpg
         * PicDataBlob :
         * WJLX : 0
         */

        private String PicFileName;
        private String PicFileType;
        private String PicDataBlob;

        public String getPicFileName() {
            return PicFileName;
        }

        public void setPicFileName(String picFileName) {
            PicFileName = picFileName;
        }

        public String getPicFileType() {
            return PicFileType;
        }

        public void setPicFileType(String picFileType) {
            PicFileType = picFileType;
        }

        public String getPicDataBlob() {
            return PicDataBlob;
        }

        public void setPicDataBlob(String picDataBlob) {
            PicDataBlob = picDataBlob;
        }
    }

}
