package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-28.
 */

public class Seasonhandlejson {
    private String GUID_OBJ;
    private String SGLXDW;
    private String SGLXSL;
    private String SGSM;
    private String STATE;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    private List<PICBean> PIC;

    public String getSGLXDW() {
        return SGLXDW;
    }

    public void setSGLXDW(String SGLXDW) {
        this.SGLXDW = SGLXDW;
    }

    public String getSGLXSL() {
        return SGLXSL;
    }

    public void setSGLXSL(String SGLXSL) {
        this.SGLXSL = SGLXSL;
    }

    public String getGUID_OBJ() {
        return GUID_OBJ;
    }

    public void setGUID_OBJ(String GUID_OBJ) {
        this.GUID_OBJ = GUID_OBJ;
    }

    public String getSGSM() {
        return SGSM;
    }

    public void setSGSM(String SGSM) {
        this.SGSM = SGSM;
    }

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
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

        public String getWJLX() {
            return WJLX;
        }

        public void setWJLX(String WJLX) {
            this.WJLX = WJLX;
        }

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
