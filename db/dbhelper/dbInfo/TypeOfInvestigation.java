package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 调查类型表
 * Created by dongxiaoqing on 2018/10/12.
 */

@DatabaseTable(tableName = DBHelper.TABLE_DCLX)
public class TypeOfInvestigation {

    @DatabaseField(id=true)
    private String DCID;//调查id

    @DatabaseField(canBeNull = false)
    private String DCMC;//调查名称



    public String getDCID() {
        return DCID;
    }

    public void setDCID(String DCID) {
        this.DCID = DCID;
    }

    public String getDCMC() {
        return DCMC;
    }

    public void setDCMC(String DCMC) {
        this.DCMC = DCMC;
    }

    @Override
    public String toString() {
        return "TypeOfInvestigation{" +
                "DCID='" + DCID + '\'' +
                ", DCMC='" + DCMC + '\'' +
                '}';
    }
}
