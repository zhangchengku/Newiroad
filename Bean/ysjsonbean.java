package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-26.
 */

public class ysjsonbean {
    private String BHGUID;
    private String YSDW;
    private String YSR;
    private String BZ;
    private List<ysjsonbean.CZFABean> CZFA;

    public List<CZFABean> getCZFA() {
        return CZFA;
    }

    public void setCZFA(List<CZFABean> CZFA) {
        this.CZFA = CZFA;
    }

    public String getBHGUID() {
        return BHGUID;
    }

    public void setBHGUID(String BHGUID) {
        this.BHGUID = BHGUID;
    }

    public String getYSDW() {
        return YSDW;
    }

    public void setYSDW(String YSDW) {
        this.YSDW = YSDW;
    }

    public String getYSR() {
        return YSR;
    }

    public void setYSR(String YSR) {
        this.YSR = YSR;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
    public static class CZFABean{
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
