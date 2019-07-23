package com.zggk.newiroad.Bean;

/**
 * Created by 张成昆 on 2019-3-19.
 */

public class ToExamineInfo {
//    "BHID": "f45b4b29-eea9-4b87-9e47-e744f7241f6d",
//            "BHZT": "2",
//            "DCSJ": "2019-03-18T16:54:00",
//            "BHMC": "路肩高草",
//            "LXBM": "G303",
//            "SPDWDJ": null,
//            "DCR": "马睿"


    private String BHID;//病害id
    private String BHZT;//病害状态
    private String DCSJ;//要求完成时间
    private String BHMC;//病害类型名称
    private String LXBM;//位置（路线-桩号）
    private String DCR;//采集单位

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

    public String getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(String DCSJ) {
        this.DCSJ = DCSJ;
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

    public String getDCR() {
        return DCR;
    }

    public void setDCR(String DCR) {
        this.DCR = DCR;
    }
}
