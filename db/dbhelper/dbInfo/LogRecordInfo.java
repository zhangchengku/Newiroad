package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 日志记录表
 * Created by dongxiaoqing on 2018/9/30.
 */
@DatabaseTable(tableName = DBHelper.TABLE_RI_ZHI_JI_LU)
public class LogRecordInfo {
    @DatabaseField(generatedId = true)
    private int RZID;//自增编号

    @DatabaseField
    private String GYDWID;//管养单位id
    @DatabaseField
    private String GYDWMC;//管养单位名称
    @DatabaseField
    private String LDMC;//路段名称
    @DatabaseField
    private String XCLD;//路段名称

    public String getXCLD() {
        return XCLD;
    }

    public void setXCLD(String XCLD) {
        this.XCLD = XCLD;
    }

    @DatabaseField
    private String JLSJ;//日志记录的时间
    @DatabaseField
    private String TQ;//天气
    @DatabaseField
    private String XCXZMC;//巡查性质名称
    @DatabaseField
    private String CLJL;//事件详情
    @DatabaseField
    private String QCLJ;//清理垃圾
    @DatabaseField
    private String QCSS;//清除死树
    @DatabaseField
    private String QCZC;//清除杂草
    @DatabaseField
    private String WJJ;//挖掘机
    @DatabaseField
    private String WG;//购机
    @DatabaseField
    private String LMZHYH;//路面养护
    @DatabaseField
    private String GFJ;//灌缝机
    @DatabaseField
    private String YSC;//运输车
    @DatabaseField
    private String TPDZ;//病害图片 多个逗号隔开




    public int getRZID() {
        return RZID;
    }

    public void setRZID(int RZID) {
        this.RZID = RZID;
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

    public String getLDMC() {
        return LDMC;
    }

    public void setLDMC(String LDMC) {
        this.LDMC = LDMC;
    }

    public String getJLSJ() {
        return JLSJ;
    }

    public void setJLSJ(String JLSJ) {
        this.JLSJ = JLSJ;
    }

    public String getTQ() {
        return TQ;
    }

    public void setTQ(String TQ) {
        this.TQ = TQ;
    }

    public String getXCXZMC() {
        return XCXZMC;
    }

    public void setXCXZMC(String XCXZMC) {
        this.XCXZMC = XCXZMC;
    }

    public String getCLJL() {
        return CLJL;
    }

    public void setCLJL(String CLJL) {
        this.CLJL = CLJL;
    }

    public String getQCLJ() {
        return QCLJ;
    }

    public void setQCLJ(String QCLJ) {
        this.QCLJ = QCLJ;
    }

    public String getQCSS() {
        return QCSS;
    }

    public void setQCSS(String QCSS) {
        this.QCSS = QCSS;
    }

    public String getQCZC() {
        return QCZC;
    }

    public void setQCZC(String QCZC) {
        this.QCZC = QCZC;
    }

    public String getWJJ() {
        return WJJ;
    }

    public void setWJJ(String WJJ) {
        this.WJJ = WJJ;
    }

    public String getWG() {
        return WG;
    }

    public void setWG(String WG) {
        this.WG = WG;
    }

    public String getLMZHYH() {
        return LMZHYH;
    }

    public void setLMZHYH(String LMZHYH) {
        this.LMZHYH = LMZHYH;
    }

    public String getGFJ() {
        return GFJ;
    }

    public void setGFJ(String GFJ) {
        this.GFJ = GFJ;
    }

    public String getYSC() {
        return YSC;
    }

    public void setYSC(String YSC) {
        this.YSC = YSC;
    }

    public String getTPDZ() {
        return TPDZ;
    }

    public void setTPDZ(String TPDZ) {
        this.TPDZ = TPDZ;
    }
}
