package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 施工维修用于筛选的路线表
 * Created by dongxiaoqing on 2018/11/15.
 */
@DatabaseTable(tableName = DBHelper.TABLE_FILTER_LX)
public class FilterLxInfo {

    @DatabaseField(id = true)
    String LXID;
    @DatabaseField(canBeNull = false)
    String LXMC;

    public String getLXID() {
        return LXID;
    }

    public void setLXID(String LXID) {
        this.LXID = LXID;
    }

    public String getLXMC() {
        return LXMC;
    }

    public void setLXMC(String LXMC) {
        this.LXMC = LXMC;
    }
}
