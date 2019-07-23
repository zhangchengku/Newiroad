package com.zggk.newiroad.Bean;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-7.
 */

public class Landbean extends Basebean {


    /**
     * TJRESOURCE : []
     * TELEPHONE : 123
     * DQGYDWDJ : 3
     * RESOURCE : [{"CDFL":"日常养护","ZCD":[{"CDMC":"巡查日志"},{"CDMC":"巡查病害"},{"CDMC":"审核派发"},{"CDMC":"施工维修"},{"CDMC":"工程验收"},{"CDMC":"季节性养护"},{"CDMC":"其他"},{"CDMC":"养护统计"},{"CDMC":"日常考勤"},{"CDMC":"桥梁管理"},{"CDMC":"桥梁扫码"}]},{"CDFL":"养护应急","ZCD":[{"CDMC":"应急预警"},{"CDMC":"应急报送"},{"CDMC":"调度管理"},{"CDMC":"水毁阻断"}]}]
     * DLDQ : 通化市东昌区
     * DQGYDW : d4078045-645d-4802-bc74-b0ce703ff9cc
     * DQGYDWCODE : 220501
     * DLR : 成坤
     * XTMC : 吉林省智能养护巡查手机系统
     * ROLE : 县公路段
     * GYDW : [{"GYDWID":"d4078045-645d-4802-bc74-b0ce703ff9cc","GYDWCODE":"220501","GYDWMC":"通化市郊公路管理段","PARENTID":"7c57bda0-6861-48b9-b1d6-09aa12e8b236","children":[{"GYDWID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","GYDWCODE":"luxianggs","GYDWMC":"路祥养护公司","PARENTID":"d4078045-645d-4802-bc74-b0ce703ff9cc","children":[{"GYDWID":"427b6c94-ab0e-4191-be92-ab1cc10f95fd","GYDWCODE":"edjdb","GYDWMC":"二道江道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]},{"GYDWID":"5c16c3d8-13c5-40ac-95b7-1c9496ff208a","GYDWCODE":"jcdb","GYDWMC":"金厂道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]},{"GYDWID":"eef55158-0cf1-45fc-a7f9-e05e23f2fc79","GYDWCODE":"wdjdb","GYDWMC":"五道江道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]},{"GYDWID":"2e041626-38c6-46cb-8173-b094704de01b","GYDWCODE":"jgldb","GYDWMC":"九公里道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]},{"GYDWID":"c7e454c2-02bc-49f4-85a1-0eb356ff8d05","GYDWCODE":"yydb","GYDWMC":"鸭园道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]},{"GYDWID":"4253c068-6f80-4827-92c7-ad6dc683d774","GYDWCODE":"hsdb","GYDWMC":"桦树道班","PARENTID":"888448d6-7de3-4227-9adc-cbb5a752e5f1","children":[]}]},{"GYDWID":"a6e8b3fd-3ff4-4f07-8d08-744ba1d0aaed","GYDWCODE":"luqiaogs","GYDWMC":"路桥养护公司","PARENTID":"d4078045-645d-4802-bc74-b0ce703ff9cc","children":[{"GYDWID":"332408b2-8b8f-4034-84b5-76d61598a5b2","GYDWCODE":"gdldb","GYDWMC":"官道岭道班","PARENTID":"a6e8b3fd-3ff4-4f07-8d08-744ba1d0aaed","children":[]},{"GYDWID":"aa6815aa-c139-4b27-be53-09bb725d97bc","GYDWCODE":"tmdb","GYDWMC":"通梅道班","PARENTID":"a6e8b3fd-3ff4-4f07-8d08-744ba1d0aaed","children":[]}]}]}]
     * DQGYDWMC : 通化市郊公路管理段
     */

    private String TELEPHONE;
    private String DQGYDWDJ;
    private String RESOURCE;
    private String DLDQ;
    private String DQGYDW;
    private String DQGYDWCODE;
    private String DLR;
    private String XTMC;
    private String ROLE;
    private String GYDW;
    private String DQGYDWMC;


    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public String getDQGYDWDJ() {
        return DQGYDWDJ;
    }

    public void setDQGYDWDJ(String DQGYDWDJ) {
        this.DQGYDWDJ = DQGYDWDJ;
    }

    public String getRESOURCE() {
        return RESOURCE;
    }

    public void setRESOURCE(String RESOURCE) {
        this.RESOURCE = RESOURCE;
    }

    public String getDLDQ() {
        return DLDQ;
    }

    public void setDLDQ(String DLDQ) {
        this.DLDQ = DLDQ;
    }

    public String getDQGYDW() {
        return DQGYDW;
    }

    public void setDQGYDW(String DQGYDW) {
        this.DQGYDW = DQGYDW;
    }

    public String getDQGYDWCODE() {
        return DQGYDWCODE;
    }

    public void setDQGYDWCODE(String DQGYDWCODE) {
        this.DQGYDWCODE = DQGYDWCODE;
    }

    public String getDLR() {
        return DLR;
    }

    public void setDLR(String DLR) {
        this.DLR = DLR;
    }

    public String getXTMC() {
        return XTMC;
    }

    public void setXTMC(String XTMC) {
        this.XTMC = XTMC;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getGYDW() {
        return GYDW;
    }

    public void setGYDW(String GYDW) {
        this.GYDW = GYDW;
    }

    public String getDQGYDWMC() {
        return DQGYDWMC;
    }

    public void setDQGYDWMC(String DQGYDWMC) {
        this.DQGYDWMC = DQGYDWMC;
    }


}

