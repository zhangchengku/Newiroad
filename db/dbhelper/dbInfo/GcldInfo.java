package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 工程路段表
 * Created by dongxiaoqing on 2018/10/24.
 */

@DatabaseTable(tableName = DBHelper.TABLE_GONG_CHENG_LU_DUAN)
public class GcldInfo {

    @DatabaseField(generatedId=true)
    private int GCLDAUTOID;//工程路段自增id

    @DatabaseField(canBeNull = false)
    private String GCLDID;//工程路段id

    @DatabaseField
    private String LXID;//路线id

    @DatabaseField
    private String QDZH;//起点桩号

    @DatabaseField
    private String ZDZH;//终点桩号

    public int getGCLDAUTOID() {
        return GCLDAUTOID;
    }

    public void setGCLDAUTOID(int GCLDAUTOID) {
        this.GCLDAUTOID = GCLDAUTOID;
    }

    public String getGCLDID() {
        return GCLDID;
    }

    public void setGCLDID(String GCLDID) {
        this.GCLDID = GCLDID;
    }

    public String getLXID() {
        return LXID;
    }

    public void setLXID(String LXID) {
        this.LXID = LXID;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getZDZH() {
        return ZDZH;
    }

    public void setZDZH(String ZDZH) {
        this.ZDZH = ZDZH;
    }

    @Override
    public String toString() {
        return "GcldInfo{" +
                "GCLDID='" + GCLDID + '\'' +
                ", LXID='" + LXID + '\'' +
                ", QDZH='" + QDZH + '\'' +
                ", ZDZH='" + ZDZH + '\'' +
                '}';
    }
}
