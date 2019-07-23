package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-26.
 */

public class CheckxqBean {

    /**
     * SGZTP : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553563619746232_601f9e80-69f2-4ee2-8b34-7ca4df96d2b7.jpg
     * MSG : SUCCESS
     * PFCZFA : [{"GCXM":"补植云杉","GCXMID":"73192c2b-7e91-41f5-9495-7d6fc9d67c01","JSGS":"2","SL":"2","DW":"棵","HD":null},{"GCXM":"补植榆树","GCXMID":"e2f90435-9464-4312-b427-4542a047e7ab","JSGS":"2","SL":"2","DW":"棵","HD":null},{"GCXM":"补植柳树","GCXMID":"28a4de15-af00-46d5-8b5a-20398e76bdd2","JSGS":"2","SL":"2","DW":"棵","HD":null},{"GCXM":"补植杨树","GCXMID":"91c80c67-4a12-4c65-bb07-bfaad9b4bab1","JSGS":"2","SL":"2","DW":"棵","HD":null},{"GCXM":"补植樟子松","GCXMID":"8b8f9fe9-4412-48cd-95a3-cdd8124293a7","JSGS":"2","SL":"2","DW":"棵","HD":null}]
     * SGCZFA : [{"GCXM":"补植云杉","GCXMID":"73192c2b-7e91-41f5-9495-7d6fc9d67c01","JSGS":"3","SL":"3","DW":"棵","HD":null},{"GCXM":"补植榆树","GCXMID":"e2f90435-9464-4312-b427-4542a047e7ab","JSGS":"3","SL":"3","DW":"棵","HD":null},{"GCXM":"补植柳树","GCXMID":"28a4de15-af00-46d5-8b5a-20398e76bdd2","JSGS":"3","SL":"3","DW":"棵","HD":null},{"GCXM":"补植杨树","GCXMID":"91c80c67-4a12-4c65-bb07-bfaad9b4bab1","JSGS":"3","SL":"3","DW":"棵","HD":null},{"GCXM":"补植樟子松","GCXMID":"8b8f9fe9-4412-48cd-95a3-cdd8124293a7","JSGS":"3","SL":"3","DW":"棵","HD":null}]
     * STATE : 1
     * CJTP : [{"FILEPATH":"http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553563498097675_601f9e80-69f2-4ee2-8b34-7ca4df96d2b7.jpg"}]
     * CJCZFA : [{"GCXM":"补植云杉","GCXMID":"73192c2b-7e91-41f5-9495-7d6fc9d67c01","JSGS":"1","SL":"1","DW":"棵","HD":null},{"GCXM":"补植榆树","GCXMID":"e2f90435-9464-4312-b427-4542a047e7ab","JSGS":"1","SL":"1","DW":"棵","HD":null},{"GCXM":"补植柳树","GCXMID":"28a4de15-af00-46d5-8b5a-20398e76bdd2","JSGS":"1","SL":"1","DW":"棵","HD":null},{"GCXM":"补植杨树","GCXMID":"91c80c67-4a12-4c65-bb07-bfaad9b4bab1","JSGS":"1","SL":"1","DW":"棵","HD":null},{"GCXM":"补植樟子松","GCXMID":"8b8f9fe9-4412-48cd-95a3-cdd8124293a7","JSGS":"1","SL":"1","DW":"棵","HD":null}]
     * SGQTP : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553563615250445_601f9e80-69f2-4ee2-8b34-7ca4df96d2b7.jpg
     * BHDATA : [{"QDZH":"1022.123","GYDW":"通化市郊公路管理段成坤","QDZHPF":"1022.123","JLDW":"通化市郊公路管理段成坤","QDZHWX":"1022.123","SGDW":"通化市郊公路管理段成坤","SGMX":null,"BZ":null,"CJBZ":null,"WZFX":"上行右","BHLX":"乔灌木枯株","CZFAMC":"补植","YSBZ":null,"DCR":"成坤","SHBW":"绿化","LXMC":"G201"}]
     * SGHTP : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553563624694646_601f9e80-69f2-4ee2-8b34-7ca4df96d2b7.jpg
     */

    private String SGZTP;
    private String MSG;
    private String STATE;
    private String SGQTP;
    private String SGHTP;
    private List<PFCZFABean> PFCZFA;
    private List<SGCZFABean> SGCZFA;
    private List<CJTPBean> CJTP;
    private List<CJCZFABean> CJCZFA;
    private List<BHDATABean> BHDATA;
    private List<YSCZFABean> YSCZFA;

    public List<YSCZFABean> getYSCZFA() {
        return YSCZFA;
    }

    public void setYSCZFA(List<YSCZFABean> YSCZFA) {
        this.YSCZFA = YSCZFA;
    }

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

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
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

    public List<PFCZFABean> getPFCZFA() {
        return PFCZFA;
    }

    public void setPFCZFA(List<PFCZFABean> PFCZFA) {
        this.PFCZFA = PFCZFA;
    }

    public List<SGCZFABean> getSGCZFA() {
        return SGCZFA;
    }

    public void setSGCZFA(List<SGCZFABean> SGCZFA) {
        this.SGCZFA = SGCZFA;
    }

    public List<CJTPBean> getCJTP() {
        return CJTP;
    }

    public void setCJTP(List<CJTPBean> CJTP) {
        this.CJTP = CJTP;
    }

    public List<CJCZFABean> getCJCZFA() {
        return CJCZFA;
    }

    public void setCJCZFA(List<CJCZFABean> CJCZFA) {
        this.CJCZFA = CJCZFA;
    }

    public List<BHDATABean> getBHDATA() {
        return BHDATA;
    }

    public void setBHDATA(List<BHDATABean> BHDATA) {
        this.BHDATA = BHDATA;
    }

    public static class PFCZFABean {
        /**
         * GCXM : 补植云杉
         * GCXMID : 73192c2b-7e91-41f5-9495-7d6fc9d67c01
         * JSGS : 2
         * SL : 2
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

    public static class SGCZFABean {
        /**
         * GCXM : 补植云杉
         * GCXMID : 73192c2b-7e91-41f5-9495-7d6fc9d67c01
         * JSGS : 3
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

        public String getHD() {
            return HD;
        }

        public void setHD(String HD) {
            this.HD = HD;
        }

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


    }

    public static class CJTPBean {
        /**
         * FILEPATH : http://106.37.229.146:5001/Files/YHXC/TEMP/PIC/1553563498097675_601f9e80-69f2-4ee2-8b34-7ca4df96d2b7.jpg
         */

        private String FILEPATH;

        public String getFILEPATH() {
            return FILEPATH;
        }

        public void setFILEPATH(String FILEPATH) {
            this.FILEPATH = FILEPATH;
        }
    }

    public static class CJCZFABean {
        /**
         * GCXM : 补植云杉
         * GCXMID : 73192c2b-7e91-41f5-9495-7d6fc9d67c01
         * JSGS : 1
         * SL : 1
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
    }public static class YSCZFABean {
        /**
         * GCXM : 补植云杉
         * GCXMID : 73192c2b-7e91-41f5-9495-7d6fc9d67c01
         * JSGS : 1
         * SL : 1
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

    public static class BHDATABean {
        /**
         * QDZH : 1022.123
         * GYDW : 通化市郊公路管理段成坤
         * QDZHPF : 1022.123
         * JLDW : 通化市郊公路管理段成坤
         * QDZHWX : 1022.123
         * SGDW : 通化市郊公路管理段成坤
         * SGMX : null
         * BZ : null
         * CJBZ : null
         * WZFX : 上行右
         * BHLX : 乔灌木枯株
         * CZFAMC : 补植
         * YSBZ : null
         * DCR : 成坤
         * SHBW : 绿化
         * LXMC : G201
         */

        private String QDZH;
        private String GYDW;
        private String QDZHPF;
        private String JLDW;
        private String QDZHWX;
        private String SGDW;
        private String SGMX;
        private String BZ;
        private String CJBZ;
        private String WZFX;
        private String BHLX;
        private String CZFAMC;
        private String YSBZ;
        private String DCR;
        private String SHBW;
        private String LXMC;

        public String getQDZH() {
            return QDZH;
        }

        public void setQDZH(String QDZH) {
            this.QDZH = QDZH;
        }

        public String getGYDW() {
            return GYDW;
        }

        public void setGYDW(String GYDW) {
            this.GYDW = GYDW;
        }

        public String getQDZHPF() {
            return QDZHPF;
        }

        public void setQDZHPF(String QDZHPF) {
            this.QDZHPF = QDZHPF;
        }

        public String getJLDW() {
            return JLDW;
        }

        public void setJLDW(String JLDW) {
            this.JLDW = JLDW;
        }

        public String getQDZHWX() {
            return QDZHWX;
        }

        public void setQDZHWX(String QDZHWX) {
            this.QDZHWX = QDZHWX;
        }

        public String getSGDW() {
            return SGDW;
        }

        public void setSGDW(String SGDW) {
            this.SGDW = SGDW;
        }

        public String getSGMX() {
            return SGMX;
        }

        public void setSGMX(String SGMX) {
            this.SGMX = SGMX;
        }

        public String getBZ() {
            return BZ;
        }

        public void setBZ(String BZ) {
            this.BZ = BZ;
        }

        public String getCJBZ() {
            return CJBZ;
        }

        public void setCJBZ(String CJBZ) {
            this.CJBZ = CJBZ;
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

        public String getCZFAMC() {
            return CZFAMC;
        }

        public void setCZFAMC(String CZFAMC) {
            this.CZFAMC = CZFAMC;
        }

        public String getYSBZ() {
            return YSBZ;
        }

        public void setYSBZ(String YSBZ) {
            this.YSBZ = YSBZ;
        }

        public String getDCR() {
            return DCR;
        }

        public void setDCR(String DCR) {
            this.DCR = DCR;
        }

        public String getSHBW() {
            return SHBW;
        }

        public void setSHBW(String SHBW) {
            this.SHBW = SHBW;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }
    }
    public static class LXUPDATEISTBean {
        /**
         * GYDWMC : 环城分公司
         * LXMC : G80 广昆高速 585
         * LRJD : 4
         */

        private String DW;
        private String SL;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDW() {
            return DW;
        }

        public void setDW(String DW) {
            this.DW = DW;
        }



        public String getSL() {
            return SL;
        }

        public void setSL(String SL) {
            this.SL = SL;
        }
    }
}
