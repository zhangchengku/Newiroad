package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-31.
 */

public class czfaxx {
    private List<czfaxx.CZFABean> CZFA;

    public List<CZFABean> getCZFA() {
        return CZFA;
    }

    public void setCZFA(List<CZFABean> CZFA) {
        this.CZFA = CZFA;
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
