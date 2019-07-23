package com.zggk.newiroad.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-8.
 */

public class LogListbean {
    /**
     * MSG : SUCCESS
     * STATE : 1
     * RZDATA : [{"RNINDEX":1,"RN":1,"RZID":"c5c64cd3-8de4-4cd9-8f7c-a117628e5c76","XCSJ":"2019-03-19","GYDW":"金厂道班"},{"RNINDEX":2,"RN":2,"RZID":"976b2e45-2a76-4f57-ba81-0164dd33194f","XCSJ":"2019-03-19","GYDW":"金厂道班"},{"RNINDEX":3,"RN":3,"RZID":"e90f5601-2bd3-421c-84ea-b765b26c4602","XCSJ":"2019-03-19","GYDW":"金厂道班"},{"RNINDEX":4,"RN":4,"RZID":"0f264874-4bd0-4941-b165-7f7b2dc640f6","XCSJ":"2019-03-19","GYDW":"金厂道班"},{"RNINDEX":5,"RN":5,"RZID":"9fdf3c4a-10f2-4e94-9226-a02cea206cec","XCSJ":"2019-03-19","GYDW":"金厂道班"},{"RNINDEX":6,"RN":6,"RZID":"2776d7a6-f005-4fed-8f7a-5925d4170208","XCSJ":"2019-03-08","GYDW":"金厂道班"}]
     * ISALSO : 0
     */

    private String MSG;
    private String STATE;
    private String ISALSO;
    @SerializedName("RZDATA")
    private List<LoglistSbean> RZDATA;

    public List<LoglistSbean> getRZDATA() {
        return RZDATA;
    }

    public void setRZDATA(List<LoglistSbean> RZDATA) {
        this.RZDATA = RZDATA;
    }

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


}
