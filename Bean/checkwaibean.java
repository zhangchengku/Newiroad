package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-25.
 */

public class checkwaibean {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * BHLIST : [{"RNINDEX":1,"RN":1,"BHID":"f1980edf-8e71-4340-bab0-2ec1fcaec9f9","BHZT":"4","XFSJ":"2018-11-26T09:11:17","BHMC":"严重边坡护坡塌陷","LXBM":"S205","QDZH":"37","TPDZ":"http://106.37.229.146:5001"}]
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
    private List<BHLISTBean> BHLIST;

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

    public String getISALSO() {
        return ISALSO;
    }

    public void setISALSO(String ISALSO) {
        this.ISALSO = ISALSO;
    }

    public List<BHLISTBean> getBHLIST() {
        return BHLIST;
    }

    public void setBHLIST(List<BHLISTBean> BHLIST) {
        this.BHLIST = BHLIST;
    }

    public static class BHLISTBean {
        /**
         * RNINDEX : 1
         * RN : 1
         * BHID : f1980edf-8e71-4340-bab0-2ec1fcaec9f9
         * BHZT : 4
         * XFSJ : 2018-11-26T09:11:17
         * BHMC : 严重边坡护坡塌陷
         * LXBM : S205
         * QDZH : 37
         * TPDZ : http://106.37.229.146:5001
         */
        public boolean isCheck;
        private int RNINDEX;
        private int RN;
        private String BHID;
        private String BHZT;
        private String XFSJ;
        private String BHMC;
        private String LXBM;
        private String QDZH;
        private String TPDZ;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getRNINDEX() {
            return RNINDEX;
        }

        public void setRNINDEX(int RNINDEX) {
            this.RNINDEX = RNINDEX;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }

        public String getBHID() {
            return BHID;
        }

        public void setBHID(String BHID) {
            this.BHID = BHID;
        }

        public String getBHZT() {
            return BHZT;
        }

        public void setBHZT(String BHZT) {
            this.BHZT = BHZT;
        }

        public String getXFSJ() {
            return XFSJ;
        }

        public void setXFSJ(String XFSJ) {
            this.XFSJ = XFSJ;
        }

        public String getBHMC() {
            return BHMC;
        }

        public void setBHMC(String BHMC) {
            this.BHMC = BHMC;
        }

        public String getLXBM() {
            return LXBM;
        }

        public void setLXBM(String LXBM) {
            this.LXBM = LXBM;
        }

        public String getQDZH() {
            return QDZH;
        }

        public void setQDZH(String QDZH) {
            this.QDZH = QDZH;
        }

        public String getTPDZ() {
            return TPDZ;
        }

        public void setTPDZ(String TPDZ) {
            this.TPDZ = TPDZ;
        }
    }
}
