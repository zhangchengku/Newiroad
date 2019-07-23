package com.zggk.newiroad.Bean;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-19.
 */

public class ToExamineDataInfo {
    private String state;
    private String ISALSO;
    private ArrayList<ToExamineInfo> BHLIST;
    public String getISALSO() {
        return ISALSO;
    }

    public void setISALSO(String ISALSO) {
        this.ISALSO = ISALSO;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<ToExamineInfo> getBHLIST() {
        return BHLIST;
    }

    public void setBHLIST(ArrayList<ToExamineInfo> BHLIST) {
        this.BHLIST = BHLIST;
    }
}
