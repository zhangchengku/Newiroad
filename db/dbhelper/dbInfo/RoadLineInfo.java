package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 路线信息表
 */
@DatabaseTable(tableName = DBHelper.TABLE_LU_XIAN)
public class RoadLineInfo {

    @DatabaseField(id = true)
    private String LXCODE;//路线id

    @DatabaseField(canBeNull = false)
    private String LXMC;//路线名称



    public String getLXCODE() {
        return LXCODE;
    }

    public void setLXCODE(String LXCODE) {
        this.LXCODE = LXCODE;
    }

    public String getLXMC() {
        return LXMC;
    }

    public void setLXMC(String LXMC) {
        this.LXMC = LXMC;
    }

    @Override
    public String toString() {
        return "RoadLineInfo{" +
                "LXCODE='" + LXCODE + '\'' +
                ", LXMC='" + LXMC + '\'' +
                '}';
    }
}
