package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 巡查性质内容表
 * Created by dongxiaoqing on 2018/9/30.
 */
@DatabaseTable(tableName = DBHelper.TABLE_XUN_CHA_XING_ZHI_NEI_RONG)
public class InspectionPropertyContentInfo {
    @DatabaseField(generatedId = true)
    private int XCXZNRAUTOID;//巡查性质内容自增id

    @DatabaseField(canBeNull = false)
    private String XCNRID;//巡查性质内容id

    @DatabaseField(canBeNull = false)
    private String XCNRMC;//巡查性质内容名称

    @DatabaseField(canBeNull = false)
    private String XCNRDW;//所属巡查性质名称

    public String getXCNRID() {
        return XCNRID;
    }

    public void setXCNRID(String XCNRID) {
        this.XCNRID = XCNRID;
    }

    public int getXCXZNRAUTOID() {
        return XCXZNRAUTOID;
    }

    public void setXCXZNRAUTOID(int XCXZNRAUTOID) {
        this.XCXZNRAUTOID = XCXZNRAUTOID;
    }



    public String getXCNRMC() {
        return XCNRMC;
    }

    public void setXCNRMC(String XCNRMC) {
        this.XCNRMC = XCNRMC;
    }

    public String getXCNRDW() {
        return XCNRDW;
    }

    public void setXCNRDW(String XCNRDW) {
        this.XCNRDW = XCNRDW;
    }
}
