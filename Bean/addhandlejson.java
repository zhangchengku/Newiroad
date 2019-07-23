package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-22.
 */

public class addhandlejson {
    private String BHGUID;
    private String QDZH;
    private String JLDW;
    private String SGDW;
    private String SGFZR;
    private String BZ;
    private List<PICBean> PIC;
    private List<CZFABean> CZFA;

    public String getBHGUID() {
        return BHGUID;
    }

    public void setBHGUID(String BHGUID) {
        this.BHGUID = BHGUID;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
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

    public String getSGFZR() {
        return SGFZR;
    }

    public void setSGFZR(String SGFZR) {
        this.SGFZR = SGFZR;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public List<CZFABean> getCZFA() {
        return CZFA;
    }

    public void setCZFA(List<CZFABean> CZFA) {
        this.CZFA = CZFA;
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
        private String WJLX;

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

        public String getWJLX() {
            return WJLX;
        }

        public void setWJLX(String WJLX) {
            this.WJLX = WJLX;
        }
    }
    public static class CZFABean {
        /**
         * PicFileName : 1491897074640.jpg
         * PicFileType : jpg
         * PicDataBlob :
         * WJLX : 0
         */

        private String GCXMID;
        private String JSGS;
        private String HD;

        public String getJSGS() {
            return JSGS;
        }

        public void setJSGS(String JSGS) {
            this.JSGS = JSGS;
        }

        public String getHD() {
            return HD;
        }

        public void setHD(String HD) {
            this.HD = HD;
        }

        public String getGCXMID() {
            return GCXMID;
        }

        public void setGCXMID(String GCXMID) {
            this.GCXMID = GCXMID;
        }
    }
}
