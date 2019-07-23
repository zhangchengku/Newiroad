package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 巡查性质表
 * Created by dongxiaoqing on 2018/9/30.
 */

@DatabaseTable(tableName = DBHelper.TABLE_XUN_CHA_XING_ZHI)
public class InspectionPropertyInfo {

    @DatabaseField(id=true)
    private String XCXZID;//巡查性质id

    @DatabaseField(canBeNull = false)
    private String XCXZMC;//巡查性质名称


    public String getXCXZID() {
        return XCXZID;
    }

    public void setXCXZID(String XCXZID) {
        this.XCXZID = XCXZID;
    }

    public String getXCXZMC() {
        return XCXZMC;
    }

    public void setXCXZMC(String XCXZMC) {
        this.XCXZMC = XCXZMC;
    }

    @Override
    public String toString() {
        return "InspectionPropertyInfo{" +
                "XCXZID='" + XCXZID + '\'' +
                ", XCXZMC='" + XCXZMC + '\'' +
                '}';
    }
}
