package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-27.
 */

public class Seasonlistbean {

    /**
     * MSG : SUCCESS
     * STATE : 1
     * LISTDATA : [{"RNINDEX":1,"RN":1,"GUID_OBJ":"4cc113b6-4237-427a-b657-c45c8939bd10","PFTIME":"2019-02-19 15:09:32","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"通化市公路管理处","SGLXMC":"刷白","STATE":"1"},{"RNINDEX":2,"RN":2,"GUID_OBJ":"01855b79-72b8-4248-bf8d-6409392b7a9e","PFTIME":"2019-03-26 09:38:49","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"通化市郊公路管理段","SGLXMC":"刷白","STATE":"2"},{"RNINDEX":3,"RN":3,"GUID_OBJ":"b377bd21-c11a-4594-a14e-20abda00aebe","PFTIME":"2018-10-16 11:27:37","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"金厂道班","SGLXMC":"刷白","STATE":"2"},{"RNINDEX":4,"RN":4,"GUID_OBJ":"3ae4cde1-998b-49b7-958f-c82188a7cc0e","PFTIME":"2018-10-12 14:31:36","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"金厂道班","SGLXMC":"刷白","STATE":"2"},{"RNINDEX":5,"RN":5,"GUID_OBJ":"f581030f-ed11-4088-b92d-e19b67b1c7e2","PFTIME":"2018-10-12 14:15:40","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"金厂道班","SGLXMC":"刷白","STATE":"2"},{"RNINDEX":6,"RN":6,"GUID_OBJ":"6502e0df-1190-4897-83d9-e322f15626f8","PFTIME":"2018-10-12 11:46:32","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"金厂道班","SGLXMC":"刷白","STATE":null},{"RNINDEX":7,"RN":7,"GUID_OBJ":"b1f1e976-e42c-4daf-b373-e5776ae9469a","PFTIME":"2018-10-11 17:45:49","SGTIME":null,"LXMC":"G303 集阿线","GYDWMC":"金厂道班","SGLXMC":"刷白","STATE":null}]
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
    private List<LISTDATABean> LISTDATA;

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

    public List<LISTDATABean> getLISTDATA() {
        return LISTDATA;
    }

    public void setLISTDATA(List<LISTDATABean> LISTDATA) {
        this.LISTDATA = LISTDATA;
    }

    public static class LISTDATABean {
        /**
         * RNINDEX : 1
         * RN : 1
         * GUID_OBJ : 4cc113b6-4237-427a-b657-c45c8939bd10
         * PFTIME : 2019-02-19 15:09:32
         * SGTIME : null
         * LXMC : G303 集阿线
         * GYDWMC : 通化市公路管理处
         * SGLXMC : 刷白
         * STATE : 1
         */

        private int RNINDEX;
        private int RN;
        private String GUID_OBJ;
        private String PFTIME;
        private Object SGTIME;
        private String LXMC;
        private String GYDWMC;
        private String SGLXMC;
        private String STATE;

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

        public String getGUID_OBJ() {
            return GUID_OBJ;
        }

        public void setGUID_OBJ(String GUID_OBJ) {
            this.GUID_OBJ = GUID_OBJ;
        }

        public String getPFTIME() {
            return PFTIME;
        }

        public void setPFTIME(String PFTIME) {
            this.PFTIME = PFTIME;
        }

        public Object getSGTIME() {
            return SGTIME;
        }

        public void setSGTIME(Object SGTIME) {
            this.SGTIME = SGTIME;
        }

        public String getLXMC() {
            return LXMC;
        }

        public void setLXMC(String LXMC) {
            this.LXMC = LXMC;
        }

        public String getGYDWMC() {
            return GYDWMC;
        }

        public void setGYDWMC(String GYDWMC) {
            this.GYDWMC = GYDWMC;
        }

        public String getSGLXMC() {
            return SGLXMC;
        }

        public void setSGLXMC(String SGLXMC) {
            this.SGLXMC = SGLXMC;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }
    }
}
