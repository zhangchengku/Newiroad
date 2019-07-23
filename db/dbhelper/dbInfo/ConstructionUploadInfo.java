package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;


/**
 * 施工维修表 待上传表 只存储带上传的数据
 * Created by dongxiaoqing on 2018/10/17.
 */
@DatabaseTable(tableName = DBHelper.TABLE_SHI_GONG_WEI_XIU_UPLPAD)
public class ConstructionUploadInfo {
    @DatabaseField(generatedId = true)
    private int SGWXID;//自增编号
    @DatabaseField
    private String DCR;//采集人
    @DatabaseField
    private String LXBM;//巡查路线
    @DatabaseField
    private String DCSJ;//巡查时间
    @DatabaseField
    private String DCLX;//调查类型
    @DatabaseField
    private String QDZH;//起点桩号
    @DatabaseField
    private String WZFX;//形式方向
    @DatabaseField
    private String TPDZ;//图片地址
    @DatabaseField
    private String BHLX;//病害类型
    @DatabaseField
    private String BHLXID;//病害类型ID
    @DatabaseField
    private String BHMC;//病害名称
    @DatabaseField
    private String SGMX;//施工说明
    @DatabaseField
    private String SGQTP;//施工前图片
    @DatabaseField
    private String SGZTP;//施工中图片
    @DatabaseField
    private String SGHTP;//施工后图片
    @DatabaseField
    private String BHID;//施工后图片
    @DatabaseField
    private String CZFAMC;//处置方安
    @DatabaseField
    private String GCXM;//施工方式
    @DatabaseField
    private String GCXMID;//工程细目id
    @DatabaseField
    private String JSGS;//计算公式
    @DatabaseField
    private String DW;//单位
    @DatabaseField
    private String HD;//厚度
    @DatabaseField
    private String SL;//数量

    public String getBHLXID() {
        return BHLXID;
    }

    public void setBHLXID(String BHLXID) {
        this.BHLXID = BHLXID;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }
    public int getSGWXID() {
        return SGWXID;
    }

    public void setSGWXID(int SGWXID) {
        this.SGWXID = SGWXID;
    }

    public String getDCR() {
        return DCR;
    }

    public void setDCR(String DCR) {
        this.DCR = DCR;
    }

    public String getLXBM() {
        return LXBM;
    }

    public void setLXBM(String LXBM) {
        this.LXBM = LXBM;
    }

    public String getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(String DCSJ) {
        this.DCSJ = DCSJ;
    }

    public String getDCLX() {
        return DCLX;
    }

    public void setDCLX(String DCLX) {
        this.DCLX = DCLX;
    }

    public String getQDZH() {
        return QDZH;
    }

    public void setQDZH(String QDZH) {
        this.QDZH = QDZH;
    }

    public String getWZFX() {
        return WZFX;
    }

    public void setWZFX(String WZFX) {
        this.WZFX = WZFX;
    }

    public String getTPDZ() {
        return TPDZ;
    }

    public void setTPDZ(String TPDZ) {
        this.TPDZ = TPDZ;
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

    public String getSGMX() {
        return SGMX;
    }

    public void setSGMX(String SGMX) {
        this.SGMX = SGMX;
    }

    public String getSGQTP() {
        return SGQTP;
    }

    public void setSGQTP(String SGQTP) {
        this.SGQTP = SGQTP;
    }

    public String getSGZTP() {
        return SGZTP;
    }

    public void setSGZTP(String SGZTP) {
        this.SGZTP = SGZTP;
    }

    public String getSGHTP() {
        return SGHTP;
    }

    public void setSGHTP(String SGHTP) {
        this.SGHTP = SGHTP;
    }

    public String getBHID() {
        return BHID;
    }

    public void setBHID(String BHID) {
        this.BHID = BHID;
    }

    public String getCZFAMC() {
        return CZFAMC;
    }

    public void setCZFAMC(String CZFAMC) {
        this.CZFAMC = CZFAMC;
    }

    public String getGCXM() {
        return GCXM;
    }

    public void setGCXM(String GCXM) {
        this.GCXM = GCXM;
    }

    public String getGCXMID() {
        return GCXMID;
    }

    public void setGCXMID(String GCXMID) {
        this.GCXMID = GCXMID;
    }

    public String getJSGS() {
        return JSGS;
    }

    public void setJSGS(String JSGS) {
        this.JSGS = JSGS;
    }

    public String getDW() {
        return DW;
    }

    public void setDW(String DW) {
        this.DW = DW;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }
}