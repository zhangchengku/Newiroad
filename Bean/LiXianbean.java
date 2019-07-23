package com.zggk.newiroad.Bean;

/**
 * Created by 张成昆 on 2019-3-8.
 */

public class LiXianbean {
    private String BBH;//板本号
    private String FILETYPE;//离线包名称
    private String XDPATH;//下载地址
    private String SJ;//时间

    public String getSJ() {
        return SJ;
    }

    public void setSJ(String SJ) {
        this.SJ = SJ;
    }

    public String getBBH() {
        return BBH;
    }

    public void setBBH(String BBH) {
        this.BBH = BBH;
    }

    public String getFILETYPE() {
        return FILETYPE;
    }

    public void setFILETYPE(String FILETYPE) {
        this.FILETYPE = FILETYPE;
    }

    public String getXDPATH() {
        return XDPATH;
    }

    public void setXDPATH(String XDPATH) {
        this.XDPATH = XDPATH;
    }
}
