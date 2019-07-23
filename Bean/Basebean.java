package com.zggk.newiroad.Bean;

/**
 * Created by 张成昆 on 2019-3-8.
 */

public class Basebean {
    private String ERROR_CODE;//状态码
    private String MSG;//返回错误提示语
    private String STATE; //1成功

    public String getERROR_CODE() {
        return ERROR_CODE;
    }

    public void setERROR_CODE(String ERROR_CODE) {
        this.ERROR_CODE = ERROR_CODE;
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
}
