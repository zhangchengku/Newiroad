package com.zggk.newiroad.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张成昆 on 2019-3-17.
 */

public class Lookdiseasebean {



    private String state;
    private ArrayList<TPListInfo> TPDZ;
    private ArrayList<CZFADATABean> CZFADATA;
    private ArrayList<BhDetailInfo> BHDATA;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<TPListInfo> getTPDZ() {
        return TPDZ;
    }

    public void setTPDZ(ArrayList<TPListInfo> TPDZ) {
        this.TPDZ = TPDZ;
    }

    public ArrayList<CZFADATABean> getCZFADATA() {
        return CZFADATA;
    }

    public void setCZFADATA(ArrayList<CZFADATABean> CZFADATA) {
        this.CZFADATA = CZFADATA;
    }

    public ArrayList<BhDetailInfo> getBHDATA() {
        return BHDATA;
    }

    public void setBHDATA(ArrayList<BhDetailInfo> BHDATA) {
        this.BHDATA = BHDATA;
    }
}
