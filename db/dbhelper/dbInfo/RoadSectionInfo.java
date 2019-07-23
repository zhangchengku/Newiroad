package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 路段信息表
 * Created by dongxiaoqing on 2018/9/30.
 */
@DatabaseTable(tableName = DBHelper.TABLE_LU_DUAN)
public class RoadSectionInfo {

    @DatabaseField
    private String GYDWID;//管养单位id

    @DatabaseField(id=true)
    private String LDID;//路段id

    @DatabaseField(canBeNull = false)
    private String LDMC;//路段名称

    @DatabaseField
    private String LXID;//路线id

    @DatabaseField
    private String QDZH;//起点桩号

    @DatabaseField
    private String ZDZH;//终点桩号



    public String getGYDWID() {
        return GYDWID;
    }

    public void setGYDWID(String GYDWID) {
        this.GYDWID = GYDWID;
    }

    public String getLDID() {
        return LDID;
    }

    public void setLDID(String LDID) {
        this.LDID = LDID;
    }

    public String getLDMC() {
        return LDMC;
    }

    public void setLDMC(String LDMC) {
        this.LDMC = LDMC;
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
        return "RoadSectionInfo{" +
                "GYDWID='" + GYDWID + '\'' +
                ", LDID='" + LDID + '\'' +
                ", LDMC='" + LDMC + '\'' +
                ", LXID='" + LXID + '\'' +
                ", QDZH='" + QDZH + '\'' +
                ", ZDZH='" + ZDZH + '\'' +
                '}';
    }
}
