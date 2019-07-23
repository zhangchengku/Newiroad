package com.zggk.newiroad.Bean;

/**
 * Created by 张成昆 on 2019-3-17.
 */

public class BhDetailInfo {
    /**
     * QDZH : 82.083
     * DCSJ : 2019-03-18T09:47:21
     * DCR : 马睿
     * WZFX : 上行—右
     * BHMC : 泛油
     * DCLX : 1
     * TJQDZH : null
     * YGSL : null
     * BH : null
     * CZFAMC : 修复
     * BHLXID : a18d2cc8-9029-4874-b4a6-3c6e767f879f
     * YQWCSJ : null
     * ZJE : null
     * SGDW : null
     * LXMC : G303
     * SHBW : 路面
     * BHZT : 2
     * BZ : null
     * WXBZ : null
     */
    private String DCR;//采集人
    private String LXMC;//巡查路线
    private String DCSJ;//巡查时间
    private String QDZH;//起点桩号
    private String DCLX;//调查类型
    private String WZFX;//形式方向
    private String SHBW;//病害类型
    private String BHMC;//病害名称
    private String CZFAMC;//施工方式
    private String LXCODE;//路线ID

    public String getLXCODE() {
        return LXCODE;
    }

    public void setLXCODE(String LXCODE) {
        this.LXCODE = LXCODE;
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

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getDCLX() {
        return DCLX;
    }

    public void setDCLX(String DCLX) {
        this.DCLX = DCLX;
    }

    public String getWZFX() {
        return WZFX;
    }

    public void setWZFX(String WZFX) {
        this.WZFX = WZFX;
    }

    public String getSHBW() {
        return SHBW;
    }

    public void setSHBW(String SHBW) {
        this.SHBW = SHBW;
    }

    public String getBHMC() {
        return BHMC;
    }

    public void setBHMC(String BHMC) {
        this.BHMC = BHMC;
    }

    public String getCZFAMC() {
        return CZFAMC;
    }

    public void setCZFAMC(String CZFAMC) {
        this.CZFAMC = CZFAMC;
    }
}
