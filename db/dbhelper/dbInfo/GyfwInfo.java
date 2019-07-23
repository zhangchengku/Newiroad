package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by 张成昆 on 2019-3-27.
 */

public class GyfwInfo {
    @DatabaseField(generatedId=true)
    private int GYFWAUTOID;//工程路段自增id

    @DatabaseField
    private String GYDWID;//

    @DatabaseField
    private String GYDWMC;//

    @DatabaseField
    private String LXID;//起点桩号

    @DatabaseField
    private String LXMC;//终点桩号
    @DatabaseField
    private String QDZH;//终点桩号
    @DatabaseField
    private String ZDZH;//终点桩号
    @DatabaseField
    private String SJ;//终点桩号

    @Override
    public String toString() {
        return "GyfwInfo{" +
                "GYFWAUTOID=" + GYFWAUTOID +
                ", GYDWID='" + GYDWID + '\'' +
                ", GYDWMC='" + GYDWMC + '\'' +
                ", LXID='" + LXID + '\'' +
                ", LXMC='" + LXMC + '\'' +
                ", QDZH='" + QDZH + '\'' +
                ", ZDZH='" + ZDZH + '\'' +
                ", SJ='" + SJ + '\'' +
                '}';
    }

    public int getGYFWAUTOID() {
        return GYFWAUTOID;
    }

    public void setGYFWAUTOID(int GYFWAUTOID) {
        this.GYFWAUTOID = GYFWAUTOID;
    }

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

    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }
}
