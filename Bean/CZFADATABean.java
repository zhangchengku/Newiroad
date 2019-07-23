package com.zggk.newiroad.Bean;

/**
 * Created by 张成昆 on 2019-3-18.
 */

public class CZFADATABean {
    /**
     * GCXM : 清除油路路面（1cm）
     * GCXMID : 6e5ad833-bff8-466c-a8ec-4c4bf6bee2dd
     * JSGS : 2
     * SL : 2
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
