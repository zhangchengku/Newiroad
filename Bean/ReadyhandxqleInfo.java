package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-22.
 */

public class ReadyhandxqleInfo {


    /**
     * SGZTP :
     * MSG : SUCCESS
     * SGQTP : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553243499877761_790a1346-1664-4da5-af59-ac42307dfb17.jpg
     * SGHTP : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553243511524209_790a1346-1664-4da5-af59-ac42307dfb17.jpg
     * STATE : 1
     * CJTP : [{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553242875928542_790a1346-1664-4da5-af59-ac42307dfb17.jpg"}]
     * BHDATA : [{"BHGUID":"790a1346-1664-4da5-af59-ac42307dfb17","QDZH":"25.026","CZFAMC":"补植","SGMX":null,"YGSL":null,"TJQDZH":null,"BH":null,"WZFX":"上行右","BHLX":"乔灌木枯株","JLDW":"d4078045-645d-4802-bc74-b0ce703ff9cc","DCR":"成坤","LXMC":"G201","DCSJ":"2019-03-22T16:20:00","SHBW":"绿化","DCLX":"养护巡查"}]
     * CZFADATA : [{"GCXM":"清除死树","GCXMID":"6b0af1e4-da02-4422-af65-235e0c5d7daa","JSGS":"1+2","SL":"3","DW":"棵","HD":null}]
     */

    private String SGZTP;
    private String MSG;
    private String SGQTP;
    private String SGHTP;
    private String STATE;
    private List<CJTPBean> CJTP;
    private List<BHDATABean> BHDATA;
    private List<CZFADATABean> CZFADATA;

    public String getSGZTP() {
        return SGZTP;
    }

    public void setSGZTP(String SGZTP) {
        this.SGZTP = SGZTP;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getSGQTP() {
        return SGQTP;
    }

    public void setSGQTP(String SGQTP) {
        this.SGQTP = SGQTP;
    }

    public String getSGHTP() {
        return SGHTP;
    }

    public void setSGHTP(String SGHTP) {
        this.SGHTP = SGHTP;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<CJTPBean> getCJTP() {
        return CJTP;
    }

    public void setCJTP(List<CJTPBean> CJTP) {
        this.CJTP = CJTP;
    }

    public List<BHDATABean> getBHDATA() {
        return BHDATA;
    }

    public void setBHDATA(List<BHDATABean> BHDATA) {
        this.BHDATA = BHDATA;
    }

    public List<CZFADATABean> getCZFADATA() {
        return CZFADATA;
    }

    public void setCZFADATA(List<CZFADATABean> CZFADATA) {
        this.CZFADATA = CZFADATA;
    }

    public static class CJTPBean {
        /**
         * FILEPATH : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553242875928542_790a1346-1664-4da5-af59-ac42307dfb17.jpg
         */

        private String FILEPATH;

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }
    }

    public static class BHDATABean {
        /**
         * BHGUID : 790a1346-1664-4da5-af59-ac42307dfb17
         * QDZH : 25.026
         * CZFAMC : 补植
         * SGMX : null
         * YGSL : null
         * TJQDZH : null
         * BH : null
         * WZFX : 上行右
         * BHLX : 乔灌木枯株
         * JLDW : d4078045-645d-4802-bc74-b0ce703ff9cc
         * DCR : 成坤
         * LXMC : G201
         * DCSJ : 2019-03-22T16:20:00
         * SHBW : 绿化
         * DCLX : 养护巡查
         */

        private String BHGUID;
        private String QDZH;
        private String CZFAMC;
        private Object SGMX;
        private Object YGSL;
        private Object TJQDZH;
        private Object BH;
        private String WZFX;
        private String BHLX;
        private String JLDW;
        private String DCR;
        private String LXMC;
        private String DCSJ;
        private String SHBW;
        private String DCLX;
        private String BZ;

        public String getBZ() {
            return BZ;
        }

        public void setBZ(String BZ) {
            this.BZ = BZ;
        }

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

        public String getCZFAMC() {
            return CZFAMC;
        }

        public void setCZFAMC(String CZFAMC) {
            this.CZFAMC = CZFAMC;
        }

        public Object getSGMX() {
            return SGMX;
        }

        public void setSGMX(Object SGMX) {
            this.SGMX = SGMX;
        }

        public Object getYGSL() {
            return YGSL;
        }

        public void setYGSL(Object YGSL) {
            this.YGSL = YGSL;
        }

        public Object getTJQDZH() {
            return TJQDZH;
        }

        public void setTJQDZH(Object TJQDZH) {
            this.TJQDZH = TJQDZH;
        }

        public Object getBH() {
            return BH;
        }

        public void setBH(Object BH) {
            this.BH = BH;
        }

        public String getWZFX() {
            return WZFX;
        }

        public void setWZFX(String WZFX) {
            this.WZFX = WZFX;
        }

        public String getBHLX() {
            return BHLX;
        }

        public void setBHLX(String BHLX) {
            this.BHLX = BHLX;
        }

        public String getJLDW() {
            return JLDW;
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }

        public String getDCR() {
            return DCR;
        }

        public void setDCR(String DCR) {
            this.DCR = DCR;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public String getDCSJ() {
            return DCSJ;
        }

        public void setDCSJ(String DCSJ) {
            this.DCSJ = DCSJ;
        }

        public String getSHBW() {
            return SHBW;
        }

        public void setSHBW(String SHBW) {
            this.SHBW = SHBW;
        }

        public String getDCLX() {
            return DCLX;
        }

        public void setDCLX(String DCLX) {
            this.DCLX = DCLX;
        }
    }

    public static class CZFADATABean {
        /**
         * GCXM : 清除死树
         * GCXMID : 6b0af1e4-da02-4422-af65-235e0c5d7daa
         * JSGS : 1+2
         * SL : 3
         * DW : 棵
         * HD : null
         */

        private String GCXM;
        private String GCXMID;
        private String JSGS;
        private String SL;
        private String DW;
        private String HD;

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

        public String getHD() {
            return HD;
        }

        public void setHD(String HD) {
            this.HD = HD;
        }
    }
}
