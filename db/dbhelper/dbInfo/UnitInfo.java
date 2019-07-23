package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 管养单位信息表
 */
@DatabaseTable(tableName = DBHelper.TABLE_GUAN_YANG_DAN_WEI)
public class UnitInfo {

    @DatabaseField(id=true)
    private String GYDWID;//管养单位id

    @DatabaseField(canBeNull = false)
    private String GYDWMC;//管养单位名称

    @DatabaseField(canBeNull = false)
    private String GYDWPID;//父级管养单位id

    public String getGYDWID() {
        return GYDWID;
    }

    public void setGYDWID(String GYDWID) {
        this.GYDWID = GYDWID;
    }

    public String getGYDWMC() {
        return GYDWMC;
    }

    public void setGYDWMC(String GYDWMC) {
        this.GYDWMC = GYDWMC;
    }

    public String getGYDWPID() {
        return GYDWPID;
    }

    public void setGYDWPID(String GYDWPID) {
        this.GYDWPID = GYDWPID;
    }

    @Override
    public String toString() {
        return "UnitInfo{" +
                "GYDWID='" + GYDWID + '\'' +
                ", GYDWMC='" + GYDWMC + '\'' +
                ", GYDWPID='" + GYDWPID + '\'' +
                '}';
    }
}
