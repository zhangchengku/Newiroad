package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-5-13.
 */

public class CdflInfo {
    private String CDFL;
    private List<ZCDBean> ZCD;

    public String getCDFL() {
        return CDFL;
    }

    public void setCDFL(String CDFL) {
        this.CDFL = CDFL;
    }

    public List<ZCDBean> getZCD() {
        return ZCD;
    }

    public void setZCD(List<ZCDBean> ZCD) {
        this.ZCD = ZCD;
    }

    public class ZCDBean {
        /**
         * CDMC : 巡查日志
         */

        private String CDMC;

        public String getCDMC() {
            return CDMC;
        }

        public void setCDMC(String CDMC) {
            this.CDMC = CDMC;
        }
    }
}
