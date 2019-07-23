package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-25.
 */

public class Dingbean {

    /**
     * MSG : SUCCESS
     * GPS : [{"QDZH":"1071.71","LXID":"92095fb4-7a27-4d86-a277-1d5b1aced9e9"}]
     * STATE : 1
     */

    private String MSG;
    private String STATE;
    private List<GPSBean> GPS;

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

    public List<GPSBean> getGPS() {
        return GPS;
    }

    public void setGPS(List<GPSBean> GPS) {
        this.GPS = GPS;
    }

    public static class GPSBean {
        /**
         * QDZH : 1071.71
         * LXID : 92095fb4-7a27-4d86-a277-1d5b1aced9e9
         */

        private String QDZH;
        private String LXID;

        public String getQDZH() {
            return QDZH;
        }

        public void setQDZH(String QDZH) {
            this.QDZH = QDZH;
        }

        public String getLXID() {
            return LXID;
        }

        public void setLXID(String LXID) {
            this.LXID = LXID;
        }
    }
}
