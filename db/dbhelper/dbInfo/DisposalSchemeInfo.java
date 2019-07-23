package com.zggk.newiroad.db.dbhelper.dbInfo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zggk.newiroad.db.dbhelper.DBHelper;

/**
 * 处置方案表 包含了病害类型信息
 * Created by dongxiaoqing on 2018/9/30.
 */
@DatabaseTable(tableName = DBHelper.TABLE_CHU_ZHI_FANG_AN)
public class DisposalSchemeInfo {

    @DatabaseField(generatedId = true)
    private int CZFAKEYID;//自增id

    @DatabaseField
    private String TEXT;//病害-处置方案

    @DatabaseField
    private String BHMC;//病害名称

    @DatabaseField
    private String BHLXID;//病害类型id

    @DatabaseField
    private String BHLXMC;//病害性质名称

    @DatabaseField
    private String CZFAMC;//处置方案名称

    @DatabaseField
    private String GCXMID;//工程细目id
    @DatabaseField
    private String XMMC;//工程细目名称
    @DatabaseField
    private String JLDW;//计量单位

    @DatabaseField
    private String QDID;//清单id

    public String getXMMC() {
        return XMMC;
    }

    public void setXMMC(String XMMC) {
        this.XMMC = XMMC;
    }

    public String getBHLXMC() {
        return BHLXMC;
    }

    public void setBHLXMC(String BHLXMC) {
        this.BHLXMC = BHLXMC;
    }



    public int getCZFAKEYID() {
        return CZFAKEYID;
    }

    public void setCZFAKEYID(int CZFAKEYID) {
        this.CZFAKEYID = CZFAKEYID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getBHMC() {
        return BHMC;
    }

    public void setBHMC(String BHMC) {
        this.BHMC = BHMC;
    }

    public String getBHLXID() {
        return BHLXID;
    }

    public void setBHLXID(String BHLXID) {
        this.BHLXID = BHLXID;
    }

    public String getCZFAMC() {
        return CZFAMC;
    }

    public void setCZFAMC(String CZFAMC) {
        this.CZFAMC = CZFAMC;
    }

    public String getGCXMID() {
        return GCXMID;
    }

    public void setGCXMID(String GCXMID) {
        this.GCXMID = GCXMID;
    }

    public String getJLDW() {
        return JLDW;
    }

    public void setJLDW(String JLDW) {
        this.JLDW = JLDW;
    }

    public String getQDID() {
        return QDID;
    }

    public void setQDID(String QDID) {
        this.QDID = QDID;
    }

    @Override
    public String toString() {
        return "DisposalSchemeInfo{" +
                "CZFAKEYID=" + CZFAKEYID +
                ", TEXT='" + TEXT + '\'' +
                ", BHMC='" + BHMC + '\'' +
                ", BHLXID='" + BHLXID + '\'' +
                ", CZFAMC='" + CZFAMC + '\'' +
                ", GCXMID='" + GCXMID + '\'' +
                ", JLDW='" + JLDW + '\'' +
                ", QDID='" + QDID + '\'' +
                '}';
    }
}
