package com.zggk.newiroad.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * Created by 张成昆 on 2019-3-21.
 */

public class ItemBeanInfo {
    private String xmmc;
    private String jsgs;
    private String jldw;
    private String gcxmid;
    private String sl;

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getGcxmid() {
        return gcxmid;
    }

    public void setGcxmid(String gcxmid) {
        this.gcxmid = gcxmid;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getJsgs() {
        return jsgs;
    }

    public void setJsgs(String jsgs) {
        this.jsgs = jsgs;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }
}
