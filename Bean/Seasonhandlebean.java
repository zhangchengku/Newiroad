package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-28.
 */

public class Seasonhandlebean {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * PIC : [{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1539324988472_f581030f-ed11-4088-b92d-e19b67b1c7e2.jpg"},{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1539324988489_f581030f-ed11-4088-b92d-e19b67b1c7e2.jpg"},{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1539324988534_f581030f-ed11-4088-b92d-e19b67b1c7e2.jpg"}]
     * JJXYH : [{"GUID_OBJ":"f581030f-ed11-4088-b92d-e19b67b1c7e2","WZFX":"下行","LXMC":"G303 集阿线","QDZH":"22.22","ZDZH":"3.2","SGDWMC":"吉林省交通厅","SGLXMC":"刷白","SGLXDW":null,"SGLXSL":null,"RWYQ":"332","SGSM":"1111"}]
     */

    private String MSG;
    private String STATE;
    private List<PICBean> PIC;
    private List<JJXYHBean> JJXYH;

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

    public List<PICBean> getPIC() {
        return PIC;
    }

    public void setPIC(List<PICBean> PIC) {
        this.PIC = PIC;
    }

    public List<JJXYHBean> getJJXYH() {
        return JJXYH;
    }

    public void setJJXYH(List<JJXYHBean> JJXYH) {
        this.JJXYH = JJXYH;
    }

    public static class PICBean {
        /**
         * FILEPATH : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1539324988472_f581030f-ed11-4088-b92d-e19b67b1c7e2.jpg
         */

        private String FILEPATH;

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }
    }

    public static class JJXYHBean {
        /**
         * GUID_OBJ : f581030f-ed11-4088-b92d-e19b67b1c7e2
         * WZFX : 下行
         * LXMC : G303 集阿线
         * QDZH : 22.22
         * ZDZH : 3.2
         * SGDWMC : 吉林省交通厅
         * SGLXMC : 刷白
         * SGLXDW : null
         * SGLXSL : null
         * RWYQ : 332
         * SGSM : 1111
         */

        private String GUID_OBJ;
        private String WZFX;
        private String LXMC;
        private String QDZH;
        private String ZDZH;
        private String SGDWMC;
        private String SGLXMC;
        private String SGLXDW;
        private String SGLXSL;
        private String RWYQ;
        private String SGSM;

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

        public String getWZFX() {
            return WZFX;
        }

        public void setWZFX(String WZFX) {
            this.WZFX = WZFX;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

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

        public String getSGDWMC() {
            return SGDWMC;
        }

        public void setSGDWMC(String SGDWMC) {
            this.SGDWMC = SGDWMC;
        }

        public String getSGLXMC() {
            return SGLXMC;
        }

        public void setSGLXMC(String SGLXMC) {
            this.SGLXMC = SGLXMC;
        }
 public String getRWYQ() {
            return RWYQ;
        }

        public void setRWYQ(String RWYQ) {
            this.RWYQ = RWYQ;
        }

        public String getSGSM() {
            return SGSM;
        }

        public void setSGSM(String SGSM) {
            this.SGSM = SGSM;
        }
    }
}
