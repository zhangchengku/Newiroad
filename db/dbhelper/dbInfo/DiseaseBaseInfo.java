package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

import java.util.List;

/**
 * 病害采集信息基本表
 * Created by dongxiaoqing on 2018/9/30.
 */
@DatabaseTable(tableName = DBHelper.TABLE_BING_HAI_CAI_JI_BASE)
public class DiseaseBaseInfo {

    @DatabaseField(generatedId = true)
    private int BHJBID;//自增编号 主键
    @DatabaseField
    private String GYDWID;//管养单位id

    public String getGYDWID() {
        return GYDWID;
    }

    public void setGYDWID(String GYDWID) {
        this.GYDWID = GYDWID;
    }

    @DatabaseField
    private String LDMC;//路段名称
    @DatabaseField
    private String LXID;//路段Id
    @DatabaseField
    private String CJSJ;//采集时间
    @DatabaseField
    private String DCLX;//调查类型
    @DatabaseField
    private String ZH;//桩号
    @DatabaseField
    private String WZFX;//位置方向 行驶方向

    @DatabaseField
    private String BHLX;//病害类型
    @DatabaseField
    private String BHMC;//病害名称
    @DatabaseField
    private String SGFS;//施工方式

        @DatabaseField
    private String GGXMMC;//工程细目名称
    @DatabaseField
    private String GGXMID;//工程细目ID

    public String getGGXMID() {
        return GGXMID;
    }

    public void setGGXMID(String GGXMID) {
        this.GGXMID = GGXMID;
    }

    @DatabaseField
    private String SL;//数量
    @DatabaseField
    private String DW;//单位

    public String getLXID() {
        return LXID;
    }

    public void setLXID(String LXID) {
        this.LXID = LXID;
    }

    public String getGGXMMC() {
        return GGXMMC;
    }

    public void setGGXMMC(String GGXMMC) {
        this.GGXMMC = GGXMMC;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getDW() {
        return DW;
    }

    public void setDW(String DW) {
        this.DW = DW;
    }

    @DatabaseField
    private String TPDZ;//病害图片 多个逗号隔开



    public int getBHJBID() {
        return BHJBID;
    }

    public void setBHJBID(int BHJBID) {
        this.BHJBID = BHJBID;
    }

    public String getLDMC() {
        return LDMC;
    }

    public void setLDMC(String LDMC) {
        this.LDMC = LDMC;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getDCLX() {
        return DCLX;
    }

    public void setDCLX(String DCLX) {
        this.DCLX = DCLX;
    }

    public String getZH() {
        return ZH;
    }

    public void setZH(String ZH) {
        this.ZH = ZH;
    }

    public String getWZFX() {
        return WZFX;
    }

    public void setWZFX(String WZFX) {
        this.WZFX = WZFX;
    }

    public String getBHLX() {
        return BHLX;
    }

    public void setBHLX(String BHLX) {
        this.BHLX = BHLX;
    }

    public String getBHMC() {
        return BHMC;
    }

    public void setBHMC(String BHMC) {
        this.BHMC = BHMC;
    }

    public String getSGFS() {
        return SGFS;
    }

    public void setSGFS(String SGFS) {
        this.SGFS = SGFS;
    }



    public String getTPDZ() {
        return TPDZ;
    }

    public void setTPDZ(String TPDZ) {
        this.TPDZ = TPDZ;
    }

}
