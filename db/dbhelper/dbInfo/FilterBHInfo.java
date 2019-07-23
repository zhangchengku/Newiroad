package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 施工维修用于筛选的病害表
 * Created by dongxiaoqing on 2018/11/15.
 */
@DatabaseTable(tableName = DBHelper.TABLE_FILTER_BH)
public class FilterBHInfo {

    @DatabaseField(id = true)
    String BHMCID;
    @DatabaseField(canBeNull = false)
    String BHMC;

    public String getBHMCID() {
        return BHMCID;
    }

    public void setBHMCID(String BHMCID) {
        this.BHMCID = BHMCID;
    }

    public String getBHMC() {
        return BHMC;
    }

    public void setBHMC(String BHMC) {
        this.BHMC = BHMC;
    }
}
