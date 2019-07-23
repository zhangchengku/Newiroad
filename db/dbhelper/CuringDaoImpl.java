package com.zggk.newiroad.db.dbhelper;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.zggk.newiroad.db.dbhelper.dbInfo.ConstructionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.ConstructionUploadInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.DisposalSchemeInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterBHInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterLxInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.GcldInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.GyfwInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.InspectionPropertyContentInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.InspectionPropertyInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadLineInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.zggk.newiroad.db.dbhelper.dbInfo.UnitInfo;
import com.zggk.newiroad.utils.Utils;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 数据库信息的访问接口实现类
 */
public class CuringDaoImpl implements CuringDao {
    private static final String TAG = CuringDaoImpl.class.getSimpleName();
    private DBHelper helper;
    private Dao<ConstructionInfo, Integer> daoConstructionInfo;
    private Dao<ConstructionUploadInfo, Integer> daoConstructionUploadInfo;
    private Dao<DiseaseBaseInfo, Integer> daoDiseaseBaseInfo;
    private Dao<DisposalSchemeInfo, Integer> daoDisposalInfo;
    private Dao<InspectionPropertyContentInfo, Integer> daoInspectionContentInfo;
    private Dao<InspectionPropertyInfo, Integer> daoInspectionInfo;
    private Dao<LogRecordInfo, Integer> daoRecordInfo;
    private Dao<RoadLineInfo, Integer> daoRoadLineInfo;
    private Dao<RoadSectionInfo, Integer> daoRoadSectionInfo;
    private Dao<UnitInfo, Integer> daoUnitInfo;
    private Dao<TypeOfInvestigation, Integer> daoInvestigationInfo;
    private Dao<GcldInfo, Integer> daoGcldInfo;
    private Dao<FilterBHInfo, Integer> daoFilterBHInfo;
    private Dao<FilterLxInfo, Integer> daoFilterLXInfo;

    private Dao<GyfwInfo, Integer> daoFilterGYFWInfo;
    private SimpleDateFormat dateFormat;

    public CuringDaoImpl(Context context) {
        helper = OpenHelperManager.getHelper(context, DBHelper.class);
        try {
            daoConstructionInfo = helper.getDao(ConstructionInfo.class);
            daoConstructionUploadInfo = helper.getDao(ConstructionUploadInfo.class);
            daoDiseaseBaseInfo = helper.getDao(DiseaseBaseInfo.class);
            daoDisposalInfo = helper.getDao(DisposalSchemeInfo.class);
            daoInspectionContentInfo = helper.getDao(InspectionPropertyContentInfo.class);
            daoInspectionInfo = helper.getDao(InspectionPropertyInfo.class);
            daoRecordInfo = helper.getDao(LogRecordInfo.class);
            daoRoadLineInfo = helper.getDao(RoadLineInfo.class);
            daoRoadSectionInfo = helper.getDao(RoadSectionInfo.class);
            daoUnitInfo = helper.getDao(UnitInfo.class);
            daoInvestigationInfo = helper.getDao(TypeOfInvestigation.class);
            daoGcldInfo = helper.getDao(GcldInfo.class);
            daoFilterBHInfo = helper.getDao(FilterBHInfo.class);
            daoFilterLXInfo = helper.getDao(FilterLxInfo.class);
            daoFilterGYFWInfo = helper.getDao(GyfwInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    /**
     * 释放各个对象
     */
    @Override
    public void release() {
        OpenHelperManager.releaseHelper();
        daoConstructionInfo = null;
        daoConstructionUploadInfo = null;
        daoDiseaseBaseInfo = null;
        daoDisposalInfo = null;
        daoInspectionContentInfo = null;
        daoInspectionInfo = null;
        daoRecordInfo = null;
        daoRoadLineInfo = null;
        daoRoadSectionInfo = null;
        daoUnitInfo = null;
        daoInvestigationInfo = null;
        helper = null;
        daoGcldInfo = null;
        daoFilterBHInfo = null;
        daoFilterLXInfo = null;
        daoFilterGYFWInfo= null;
    }


    /**
     * 添加施工维修记录 单个
     *
     * @param info
     */
    @Override
    public int addSgwx(ConstructionInfo info) {
        int result=0;
        if (info != null) {
            try {
               result = daoConstructionInfo.create(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 添加施工维修记录 多个
     *
     * @param infoList
     */
    @Override
    public int addSgwx(ArrayList<ConstructionInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoConstructionInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询所有施工维修记录
     */
    @Override
    public List<ConstructionInfo> querySgwx() {
        List<ConstructionInfo> resultList = null;
        QueryBuilder<ConstructionInfo, Integer> qb = daoConstructionInfo.queryBuilder();
        try {
            resultList = qb.orderBy("YQWCSJ", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    /**
     * 根据病害id查询某一个表
     */
    @Override
    public ConstructionInfo querySgwxByBhid(String bhid) {
        ConstructionInfo result = null;
        QueryBuilder<ConstructionInfo, Integer> qb = daoConstructionInfo.queryBuilder();
        Where<ConstructionInfo, Integer> where=qb.where();
        try {
            where.eq("BHID",bhid);
            where.or().eq("BHMC",bhid);
            result = qb.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据 路线名称 病害id 查询施工维修记录  时间倒序
     */
    @Override
    public List<ConstructionInfo> querySgwxByLxidAndBhidDaoXu(String lxmc, String bhid) {
        List<ConstructionInfo> resultList = null;
        if (!Utils.isNull(lxmc) && !Utils.isNull(bhid)) {
            QueryBuilder<ConstructionInfo, Integer> qb = daoConstructionInfo.queryBuilder();
            Where<ConstructionInfo, Integer> where = qb.where();
            try {
                where.eq("LXMC", lxmc);
                where.and().eq("BHID", bhid);
                resultList = qb.orderBy("YQWCSJ", false).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 根据 路线名称 病害id 查询施工维修记录  时间正序
     */
    @Override
    public List<ConstructionInfo> querySgwxByLxidAndBhidZhengXu(String lxmc, String bhid) {
        List<ConstructionInfo> resultList = null;
        if (!Utils.isNull(lxmc) && !Utils.isNull(bhid)) {
            QueryBuilder<ConstructionInfo, Integer> qb = daoConstructionInfo.queryBuilder();
            Where<ConstructionInfo, Integer> where = qb.where();
            try {
                where.eq("LXMC", lxmc);
                where.and().eq("BHID", bhid);
                resultList = qb.orderBy("YQWCSJ", true).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 当前病害id是否在施工维修表里存在
     * @param bhid
     * @return
     */
    @Override
    public boolean isExitByBhid(String bhid) {
        boolean result=false;
        List<ConstructionInfo> resultList = null;
        if (!Utils.isNull(bhid)) {
            QueryBuilder<ConstructionInfo, Integer> qb = daoConstructionInfo.queryBuilder();
            Where<ConstructionInfo, Integer> where = qb.where();
            try {
                where.eq("BHID", bhid);
                resultList = qb.orderBy("DCSJ", false).query();
                if(resultList!=null&&resultList.size()>0){
                    result=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 修改施工维修表
     */
    @Override
    public int updateSgwx(ConstructionInfo info) {
        int result=0;
        try {
            result=daoConstructionInfo.update(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 根据id删除一条施工记录
     *
     * @param sgwxId
     */
    @Override
    public int deleteSgwxById(int sgwxId) {
        int result=0;
        DeleteBuilder<ConstructionInfo, Integer> deleteBuilder=daoConstructionInfo.deleteBuilder();
        Where<ConstructionInfo, Integer> where = deleteBuilder.where();
        try {
            where.eq("SGWXID", sgwxId);
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加施工维修记录 带上传表
     *
     * @param info
     */
    @Override
    public int addSgwxUpload(ConstructionUploadInfo info) {
        int result=0;
        if (info != null) {
            try {
               result=daoConstructionUploadInfo.create(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询所有施工维修记录 带上传表
     */
    @Override
    public List<ConstructionUploadInfo> querySgwxUpload() {
        List<ConstructionUploadInfo> resultList = null;
        QueryBuilder<ConstructionUploadInfo, Integer> qb = daoConstructionUploadInfo.queryBuilder();
        try {
            resultList = qb.orderBy("YQWCSJ", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 根据病害id查询施工维修记录待上传表
     * @param bhid
     * @return
     */
    @Override
    public ConstructionUploadInfo querySgwxUploadByBhid(String bhid) {
        ConstructionUploadInfo result = null;
        QueryBuilder<ConstructionUploadInfo, Integer> qb = daoConstructionUploadInfo.queryBuilder();
        Where<ConstructionUploadInfo, Integer> where=qb.where();
        try {
            where.eq("BHID",bhid);
            where.or().eq("BHMC",bhid);
            result = qb.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 当前病害id是否在施工维修表里存在 待上传表
     * @param bhid
     * @return
     */
    @Override
    public boolean isExitByBhidInUpload(String bhid) {
        boolean result=false;
        List<ConstructionUploadInfo> resultList = null;
        if (!Utils.isNull(bhid)) {
            QueryBuilder<ConstructionUploadInfo, Integer> qb = daoConstructionUploadInfo.queryBuilder();
            Where<ConstructionUploadInfo, Integer> where = qb.where();
            try {
                where.eq("BHID", bhid);
                resultList = qb.orderBy("DCSJ", false).query();
                if(resultList!=null&&resultList.size()>0){
                    result=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 修改施工维修记录 带上传表
     * @param info
     * @return
     */
    @Override
    public int updateSgwxUpload(ConstructionUploadInfo info) {
        int result=0;
        try {
            result=daoConstructionUploadInfo.update(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 根据id删除一条施工记录 带上传表
     *
     * @param sgwxId
     */
    @Override
    public int deleteSgwxUploadById(int sgwxId) {
        int result=0;
        DeleteBuilder<ConstructionUploadInfo, Integer> deleteBuilder=daoConstructionUploadInfo.deleteBuilder();
        Where<ConstructionUploadInfo, Integer> where = deleteBuilder.where();
        try {
            where.eq("SGWXID", sgwxId);
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 添加病害基本信息 单个
     *
     * @param info
     */
    @Override
    public int addBingHaiBase(DiseaseBaseInfo info) {
        int result=0;
        if (info != null) {
            try {
                result=daoDiseaseBaseInfo.create(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 添加病害基本信息 多个
     *
     * @param infoList
     */
    @Override
    public int addBingHaiBase(ArrayList<DiseaseBaseInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoDiseaseBaseInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 修改病害基本信息
     *
     * @param info
     */
    @Override
    public int updateBingHaiBase(DiseaseBaseInfo info) {
        int result=0;
        if (info != null) {
            try {
                result=daoDiseaseBaseInfo.update(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询最后一条害基本信息的（也就是最新添加的一条）id
     *
     * @return
     */
    @Override
    public int queryBingHaiBaseIdForLast() {
        int result = -1;
        QueryBuilder<DiseaseBaseInfo, Integer> queryBuilder = daoDiseaseBaseInfo.queryBuilder();
        try {
            List<DiseaseBaseInfo> infoList = queryBuilder.selectColumns("BHJBID").query();
            result = infoList.get(infoList.size() - 1).getBHJBID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据id查询病害基本信息
     *
     * @param baseid
     * @return
     */
    @Override
    public DiseaseBaseInfo queryBingHaiBaseById(int baseid) {
        DiseaseBaseInfo diseaseBaseInfo = null;
        try {
            diseaseBaseInfo = daoDiseaseBaseInfo.queryForId(baseid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diseaseBaseInfo;
    }

    /**
     * 根据管养单位id查询所有病害基本信息 采集
     *
     * @return
     */
    @Override
    public List<DiseaseBaseInfo> queryAllBingHaiBase(String gydwid) {
        List<DiseaseBaseInfo> resultList = null;
        QueryBuilder<DiseaseBaseInfo, Integer> queryBuilder = daoDiseaseBaseInfo.queryBuilder();
        Where<DiseaseBaseInfo, Integer> where=queryBuilder.where();
        try {
            where.eq("GYDWID",gydwid);
//            resultList = queryBuilder.orderBy("CJSJLONG", false).query();
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 通过时间 路线 路段 合同 清单 查询病害基本信息 倒序
     *
     * @return
     */
    @Override
    public List<DiseaseBaseInfo> queryBingHaiBaseByLdAndSjDaoXu(long start, long end, String lxid, String ldid, String htid, String qdid) {
        List<DiseaseBaseInfo> resultList = null;
        if (!Utils.isNull(lxid) && !Utils.isNull(ldid) && !Utils.isNull(htid) && !Utils.isNull(qdid)) {
            QueryBuilder<DiseaseBaseInfo, Integer> queryBuilder = daoDiseaseBaseInfo.queryBuilder();
            Where<DiseaseBaseInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("LXID", lxid);
                where.and().eq("LDID", ldid);
                where.and().eq("HTID", htid);
                where.and().eq("QDID", qdid);
                where.and().between("CJSJLONG", start, end);
                resultList = queryBuilder.orderBy("CJSJLONG", false).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 通过时间 路线 路段 合同 清单 查询病害基本信息 正序
     *
     * @return
     */
    @Override
    public List<DiseaseBaseInfo> queryBingHaiBaseByLdAndSjZhengXu(long start, long end, String lxid, String ldid, String htid, String qdid) {
        List<DiseaseBaseInfo> resultList = null;
        if (!Utils.isNull(lxid) && !Utils.isNull(ldid) && !Utils.isNull(htid) && !Utils.isNull(qdid)) {
            QueryBuilder<DiseaseBaseInfo, Integer> queryBuilder = daoDiseaseBaseInfo.queryBuilder();
            Where<DiseaseBaseInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("LXID", lxid);
                where.and().eq("LDID", ldid);
                where.and().eq("HTID", htid);
                where.and().eq("QDID", qdid);
                where.and().between("CJSJLONG", start, end);
                resultList = queryBuilder.orderBy("CJSJLONG", true).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 根据单位id 路线id 病害id查询病害基本信息
     * @return
     */
    @Override
    public List<DiseaseBaseInfo> queryBingHaiBaseByDwLxBh(String dwid, String lxid, String bhid,long start,long end) {
        List<DiseaseBaseInfo> resultList = null;
        QueryBuilder<DiseaseBaseInfo, Integer> queryBuilder = daoDiseaseBaseInfo.queryBuilder();
        Where<DiseaseBaseInfo, Integer> where=queryBuilder.where();
        try {
//            where.eq("BHCJZT","2");
            if(!Utils.isNull(dwid)){
                where.eq("GYDWID",dwid);
                if(!Utils.isNull(lxid)){
                    where.and().eq("LXID",lxid);
                }
                where.and().between("CJSJLONG", start, end);
            }else{
                if(!Utils.isNull(lxid)){
                    where.eq("LXID",lxid);
                    where.and().between("CJSJLONG", start, end);
                }else{
                    where.between("CJSJLONG", start, end);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 删除一条病害基本信息
     *
     * @param bhbaseId
     */
    @Override
    public int deleteBingHaiBaseById(int bhbaseId) {
        int result=0;
        DeleteBuilder<DiseaseBaseInfo, Integer> deleteBuilder=daoDiseaseBaseInfo.deleteBuilder();
        Where<DiseaseBaseInfo, Integer> where = deleteBuilder.where();
        try {
            where.eq("BHJBID", bhbaseId);
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }




    /**
     * 增加处置方案
     *
     * @param infoList
     */
    @Override
    public int addCzfa(ArrayList<DisposalSchemeInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoDisposalInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据病害名称查询处置方案
     * @return
     */
    @Override
    public DisposalSchemeInfo queryCzfaByBhmc(String mcStr) {
        DisposalSchemeInfo resultInfo = null;
        if (!Utils.isNull(mcStr)) {
            QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
            Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("BHMC",mcStr);
                resultInfo = queryBuilder.queryForFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultInfo;
    }

    /**
     * 根据处置方案名称或病害名称模糊查询处置方案
     *
     * @return
     */
    @Override
    public List<DisposalSchemeInfo> queryCzfaByMcLike(String mcStr) {
        List<DisposalSchemeInfo> resultList = null;
        if (!Utils.isNull(mcStr)) {
            QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
            Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
            try {
                where.like("TEXT", "%" + mcStr + "%");
                resultList = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    @Override
    public List<DisposalSchemeInfo> queryCzfaByMcLikeAndQdid(String mcStr, String qdid) {
        List<DisposalSchemeInfo> resultList = null;
        if (!Utils.isNull(mcStr)) {
            QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
            Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
            try {
                where.like("TEXT", "%" + mcStr + "%");
                where.and().eq("QDID", qdid);
                resultList = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 根据清单id进行查询 查询获取第一条数据
     *
     * @return
     */
    @Override
    public DisposalSchemeInfo queryCzfaByQdidForFirst(String qdid) {
        DisposalSchemeInfo disposalSchemeInfo = null;
        QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
        Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("QDID", qdid);
            disposalSchemeInfo = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disposalSchemeInfo;
    }

    /**
     * 删除所有处置方案
     */
    @Override
    public int deleteAllCzfa() {
        int result=0;
        DeleteBuilder<DisposalSchemeInfo, Integer> deleteBuilder = daoDisposalInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除所有日志记录
     */
    @Override
    public int deleteAllRzInfo() {
        int result=0;
        DeleteBuilder<LogRecordInfo, Integer> deleteBuilder = daoRecordInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除所有病害
     */
    @Override
    public int deleteAllBhInfo() {
        int result=0;
        DeleteBuilder<DiseaseBaseInfo, Integer> diseaseBaseInfo = daoDiseaseBaseInfo.deleteBuilder();
        try {
            result=diseaseBaseInfo.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除所有待施工
     */
    @Override
    public int deleteAllDSgInfo() {
        int result=0;
        DeleteBuilder<ConstructionInfo, Integer> queryBuilder = daoConstructionInfo.deleteBuilder();
        try {
            result=queryBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除所有施工
     */
    @Override
    public int deleteAllSgInfo() {
        int result=0;
        DeleteBuilder<ConstructionUploadInfo, Integer> queryBuilder = daoConstructionUploadInfo.deleteBuilder();
        try {
            result=queryBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加巡查性质内容
     *
     * @param infoList
     */
    @Override
    public int addXcxznr(ArrayList<InspectionPropertyContentInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
               result = daoInspectionContentInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 通过巡查性质名称查询所有巡查性质内容
     *
     * @param
     * @return
     */
    @Override
    public List<InspectionPropertyContentInfo> queryXcxznrByXcxzmc() {

        List<InspectionPropertyContentInfo> resultList = null;
            QueryBuilder<InspectionPropertyContentInfo, Integer> queryBuilder = daoInspectionContentInfo.queryBuilder();
            try {
                resultList=queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return resultList;
    }

    /**
     * 根据巡查性质内容的id查询巡查性质内容
     * @param xcxznrid
     * @return
     */
    @Override
    public InspectionPropertyContentInfo queryXcxznrById(String xcxznrid) {
        InspectionPropertyContentInfo insContentInfo=null;
        if (!Utils.isNull(xcxznrid)) {
            QueryBuilder<InspectionPropertyContentInfo, Integer> queryBuilder = daoInspectionContentInfo.queryBuilder();
            Where<InspectionPropertyContentInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("NRID", xcxznrid);
                insContentInfo = queryBuilder.queryForFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insContentInfo;
    }

    /**
     * 删除所有巡查性质内容
     */
    @Override
    public int deleteAllXcxznr() {
        int result=0;
        DeleteBuilder<InspectionPropertyContentInfo, Integer> deleteBuilder = daoInspectionContentInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加巡查性质
     *
     * @param infoList
     */
    @Override
    public int addXcxz(ArrayList<InspectionPropertyInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
               result = daoInspectionInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查询所有巡查性质
     *
     * @return
     */
    @Override
    public List<InspectionPropertyInfo> queryXcxz() {
        List<InspectionPropertyInfo> resultList = null;
        QueryBuilder<InspectionPropertyInfo, Integer> queryBuilder = daoInspectionInfo.queryBuilder();
        try {
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 删除所有巡查性质
     */
    @Override
    public int deleteAllXcxz() {
        int result=0;
        DeleteBuilder<InspectionPropertyInfo, Integer> deleteBuilder = daoInspectionInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加日志记录 单个
     *
     * @param info
     */
    @Override
    public int addRzjl(LogRecordInfo info) {
        int result=0;
        if (info != null) {
            try {
                result=daoRecordInfo.create(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 添加日志记录 多个
     *
     * @param infoList
     */
    @Override
    public int addRzjl(ArrayList<LogRecordInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoRecordInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 修改日志记录
     * @param info
     * @return
     */
    @Override
    public int updateRzjl(LogRecordInfo info) {
        int result=0;
        if (info != null) {
            try {
                result=daoRecordInfo.update(info);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据日志记录id查询单个日志
     *
     * @param rzjlid
     * @return
     */
    @Override
    public LogRecordInfo queryRzjlById(int rzjlid) {
        LogRecordInfo logRecordInfo = null;
        try {
            logRecordInfo = daoRecordInfo.queryForId(rzjlid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logRecordInfo;
    }

    /**
     * 查询所有日志记录
     *
     * @return
     */
    @Override
    public List<LogRecordInfo> queryAllRzjl() {
        List<LogRecordInfo> resultList = null;
        QueryBuilder<LogRecordInfo, Integer> queryBuilder = daoRecordInfo.queryBuilder();
        try {
            resultList = queryBuilder.orderBy("JLSJ", true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 按时间查询日志记录 倒序
     *
     * @return
     */
    @Override
    public List<LogRecordInfo> queryRzjlByTimeDaoXu(long start, long end) {
        List<LogRecordInfo> resultList = null;
        QueryBuilder<LogRecordInfo, Integer> queryBuilder = daoRecordInfo.queryBuilder();
        Where<LogRecordInfo, Integer> where = queryBuilder.where();
        try {
            where.between("JLSJLONG", start, end);
            resultList = queryBuilder.orderBy("JLSJ", false).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 按时间查询日志记录 正序
     *
     * @return
     */
    @Override
    public List<LogRecordInfo> queryRzjlByTimeZhengXu(long start, long end) {
        List<LogRecordInfo> resultList = null;
        QueryBuilder<LogRecordInfo, Integer> queryBuilder = daoRecordInfo.queryBuilder();
        Where<LogRecordInfo, Integer> where = queryBuilder.where();
        try {
            where.between("JLSJLONG", start, end);
            resultList = queryBuilder.orderBy("JLSJ", true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 根据id删除一条日志记录
     *
     * @param rzjlId
     */
    @Override
    public int deleteRzjlById(int rzjlId) {
        int result=0;
        DeleteBuilder<LogRecordInfo, Integer> deleteBuilder=daoRecordInfo.deleteBuilder();
        Where<LogRecordInfo, Integer> where = deleteBuilder.where();
        try {
            where.eq("RZID", rzjlId);
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加路线
     *
     * @param infoList
     */
    @Override
    public int addLx(ArrayList<RoadLineInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result= daoRoadLineInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据路线id查询路线
     */
    @Override
    public RoadLineInfo queryLxByLxid(String lxid) {
        RoadLineInfo roadLineInfo = null;
        QueryBuilder<RoadLineInfo, Integer> qb = daoRoadLineInfo.queryBuilder();
        Where<RoadLineInfo, Integer> where = qb.where();
        try {
            where.eq("LXCODE", lxid);
            roadLineInfo = qb.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roadLineInfo;
    }

    /**
     * 根据路段id查询路线
     */
    @Override
    public List<RoadLineInfo> queryLxByLdid(String ldid) {
        List<RoadLineInfo> roadLineInfoList = null;
        RoadSectionInfo roadSectionInfo=queryLdByLdid(ldid);
        return roadLineInfoList;
    }

    /**
     * 根据路线名称查询路线
     */
    @Override
    public RoadLineInfo queryLxByLxmc(String lxmc) {
        RoadLineInfo roadLineInfo = null;
        QueryBuilder<RoadLineInfo, Integer> qb = daoRoadLineInfo.queryBuilder();
        Where<RoadLineInfo, Integer> where = qb.where();
        try {
            where.eq("LXMC", lxmc);
            roadLineInfo = qb.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roadLineInfo;
    }

    /**
     * 查询所有路线
     *
     * @return
     */
    @Override
    public List<RoadLineInfo> queryAllLx() {
        List<RoadLineInfo> resultList = null;
        QueryBuilder<RoadLineInfo, Integer> queryBuilder = daoRoadLineInfo.queryBuilder();
        try {
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 删除所有路线
     */
    @Override
    public int deleteAllLx() {
        int result=0;
        DeleteBuilder<RoadLineInfo, Integer> deleteBuilder = daoRoadLineInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加路段
     *
     * @param infoList
     */
    @Override
    public int addLd(ArrayList<RoadSectionInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoRoadSectionInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据路段id查询路段
     *
     * @param ldmc
     * @return
     */
    @Override
    public RoadSectionInfo queryLdByLdmc(String ldmc) {
        RoadSectionInfo roadSectionInfo = null;
        QueryBuilder<RoadSectionInfo, Integer> queryBuilder = daoRoadSectionInfo.queryBuilder();
        Where<RoadSectionInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("LDMC", ldmc);
            roadSectionInfo = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roadSectionInfo;
    }
    /**
     * 根据路段id查询路段
     *
     * @param ldmc
     * @return
     */
    @Override
    public RoadSectionInfo queryLdByLdid(String ldmc) {
        RoadSectionInfo roadSectionInfo = null;
        QueryBuilder<RoadSectionInfo, Integer> queryBuilder = daoRoadSectionInfo.queryBuilder();
        Where<RoadSectionInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("LDID", ldmc);
            roadSectionInfo = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roadSectionInfo;
    }
    /**
     * 通过路线id查询所对应的路段
     *
     * @return
     */
    @Override
    public List<RoadSectionInfo> queryLdByLxid(String lxid) {
        List<RoadSectionInfo> resultList = null;
        if (!Utils.isNull(lxid)) {
            QueryBuilder<RoadSectionInfo, Integer> queryBuilder = daoRoadSectionInfo.queryBuilder();
            Where<RoadSectionInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("LXID", lxid);
                resultList = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 通过管养单位id查询所对应的路段
     *
     * @return
     */
    @Override
    public List<RoadSectionInfo> queryLdByGydwid(String gydwid) {
        List<RoadSectionInfo> resultList = null;
        if (!Utils.isNull(gydwid)) {
            QueryBuilder<RoadSectionInfo, Integer> queryBuilder = daoRoadSectionInfo.queryBuilder();
            Where<RoadSectionInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("GYDWID", gydwid);
                resultList = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 删除所有路段
     */
    @Override
    public int deleteAllLd() {
        int result=0;
        DeleteBuilder<RoadSectionInfo, Integer> deleteBuilder = daoRoadSectionInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加管养单位
     *
     * @param infoList
     */
    @Override
    public int addGydw(ArrayList<UnitInfo> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result=daoUnitInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**根据qydwid查询单个管养单位
     *
     *
     * @param gydwid
     * @return
     */
    @Override
    public UnitInfo queryGydwById(String gydwid) {
        UnitInfo info = null;
        if (!Utils.isNull(gydwid)) {
            QueryBuilder<UnitInfo, Integer> queryBuilder = daoUnitInfo.queryBuilder();
            Where<UnitInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("GYDWID", gydwid);
                info = queryBuilder.queryForFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    /**
     * 根据父级id查询管养单位
     *
     * @param parentId
     * @return
     */
    @Override
    public List<UnitInfo> queryGydwByParentId(String parentId) {
        List<UnitInfo> resultList = null;
        if (!Utils.isNull(parentId)) {
            QueryBuilder<UnitInfo, Integer> queryBuilder = daoUnitInfo.queryBuilder();
            Where<UnitInfo, Integer> where = queryBuilder.where();
            try {
                where.eq("GYDWPID", parentId);
                resultList = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 删除所有管养单位
     */
    @Override
    public int deleteAllGydw() {
        int result=0;
        DeleteBuilder<UnitInfo, Integer> deleteBuilder = daoUnitInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加调查类型
     *
     * @param infoList
     */
    @Override
    public int addInvestigation(ArrayList<TypeOfInvestigation> infoList) {
        int result=0;
        if (infoList != null && infoList.size() > 0) {
            try {
                result = daoInvestigationInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据id查询调查类型
     *
     * @return
     */
    @Override
    public TypeOfInvestigation queryInvestigationById(String typeId) {
        TypeOfInvestigation typeOfInvestigation = null;
        QueryBuilder<TypeOfInvestigation, Integer> queryBuilder = daoInvestigationInfo.queryBuilder();
        Where<TypeOfInvestigation, Integer> where = queryBuilder.where();
        try {
            where.eq("DCID", typeId);
            typeOfInvestigation = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeOfInvestigation;
    }
    /**
     * 根据mc查询调查类型
     *
     * @return
     */
    @Override
    public TypeOfInvestigation queryInvestigationByMc(String mc) {
        TypeOfInvestigation typeOfInvestigation = null;
        QueryBuilder<TypeOfInvestigation, Integer> queryBuilder = daoInvestigationInfo.queryBuilder();
        Where<TypeOfInvestigation, Integer> where = queryBuilder.where();
        try {
            where.eq("DCMC", mc);
            typeOfInvestigation = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeOfInvestigation;
    }
    /**
     * 查询所有调查类型
     *
     * @return
     */
    @Override
    public List<TypeOfInvestigation> queryAllInvestigation() {
        List<TypeOfInvestigation> resultList = null;
        QueryBuilder<TypeOfInvestigation, Integer> queryBuilder = daoInvestigationInfo.queryBuilder();
        try {
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 删除所有调查类型
     */
    @Override
    public int deleteAllDcLx() {
        int result=0;
        DeleteBuilder<TypeOfInvestigation, Integer> deleteBuilder = daoInvestigationInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加工程路段
     * @param infoList
     * @return
     */
    @Override
    public int addGcld(ArrayList<GcldInfo> infoList) {
        int result=0;
        if(infoList!=null&&infoList.size()>0){
            try {
                result=daoGcldInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return result;
    }

    @Override
    public int addGyfw(ArrayList<GyfwInfo> infoList) {
        int result=0;
        if(infoList!=null&&infoList.size()>0){
            try {
                result=daoFilterGYFWInfo.create(infoList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据gcldid查询工程路段
     * @param gcldid
     * @return
     */
    @Override
    public GcldInfo queryGcld(String gcldid) {
        GcldInfo gcldInfo = null;
        QueryBuilder<GcldInfo, Integer> queryBuilder = daoGcldInfo.queryBuilder();
        Where<GcldInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("GCLDID", gcldid);
            gcldInfo = queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfo;
    }

    /**
     * 根据gcldid查询工程路段
     */
    @Override
    public List<GcldInfo> queryGcldByGcldid(String gcldid) {
        List<GcldInfo> gcldInfoListResult = null;
        QueryBuilder<GcldInfo, Integer> queryBuilder = daoGcldInfo.queryBuilder();
        Where<GcldInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("GCLDID", gcldid);
            gcldInfoListResult = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfoListResult;
    }

    /**
     * 根据路线id和桩号查询工程路段
     * @param lxid
     * @param zh 桩号
     * @return
     */
    @Override
    public List<GcldInfo> queryGcldByLxidAndZh(String lxid,String zh) {
        List<GcldInfo> gcldInfoList = null;
        List<GcldInfo> gcldInfoListResult = null;
        QueryBuilder<GcldInfo, Integer> queryBuilder = daoGcldInfo.queryBuilder();
        Where<GcldInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("LXID", lxid);
            gcldInfoList = queryBuilder.query();
            if(gcldInfoList!=null&&gcldInfoList.size()>0){
                Double zhdouble=Double.parseDouble(zh);
                gcldInfoListResult=new ArrayList<>();
                for(int i=0;i<gcldInfoList.size();i++){
                    GcldInfo gcldInfo=gcldInfoList.get(i);
                    if(gcldInfo!=null){
                        Double qdzh=Double.parseDouble(gcldInfo.getQDZH());
                        Double zdzh=Double.parseDouble(gcldInfo.getZDZH());
                        if(qdzh<=zhdouble&&zhdouble<=zdzh){
                            gcldInfoListResult.add(gcldInfo);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfoListResult;
    }

    /**
     * 删除所有工程路段
     */
    @Override
    public int deleteAllGcld() {
        int result=0;
        DeleteBuilder<GcldInfo, Integer> deleteBuilder = daoGcldInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除所有离线包基础数据表里的数据
     */
    @Override
    public void deleteAllBaseTableData() {
        deleteAllCzfa();
        deleteAllGydw();
        deleteAllLd();
        deleteAllLx();
        deleteAllXcxz();
        deleteAllXcxznr();
        deleteAllDcLx();
        deleteAllGcld();
        deleteAllSgInfo() ;
        deleteAllDSgInfo();
        deleteAllBhInfo();
        deleteAllRzInfo();
    }
    /**
     * 通过路线名称和病害id查询施工记录
     * @param lxmc
     * @param bhid
     * @return
     */
    @Override
    public List<ConstructionInfo> querySgxxBylxidAndBhid(String lxmc, String bhid) {
        List<ConstructionInfo> resultList=null;
        QueryBuilder<ConstructionInfo,Integer> queryBuilder=daoConstructionInfo.queryBuilder();
        try {
            if(!Utils.isNull(lxmc)){
                Where<ConstructionInfo, Integer> where = queryBuilder.where();
                where.eq("LXBM",lxmc);
                if(!Utils.isNull(bhid)){
                    where.and().eq("BHLXID",bhid);
                }
            }else{
                if(!Utils.isNull(bhid)){
                    Where<ConstructionInfo, Integer> where = queryBuilder.where();
                    where.eq("BHLXID",bhid);
                }
            }
            resultList=queryBuilder.orderBy("DCSJ", false).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 通过路线名称和病害id查询施工记录待上传表
     * @param lxmc
     * @param bhid
     * @return
     */

    /**
     * 添加筛选病害信息
     * @param listInfo
     * @return
     */
    @Override
    public int addFilterBHInfo(ArrayList<FilterBHInfo> listInfo) {
        int result=0;
        try {
            result=daoFilterBHInfo.create(listInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 查询所有筛选病害信息
     * @return
     */
    @Override
    public List<FilterBHInfo> queryAllFilterBHInfo() {
        List<FilterBHInfo> result=null;
        QueryBuilder<FilterBHInfo,Integer> queryBuilder=daoFilterBHInfo.queryBuilder();
        try {
            result=queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


//    @Override
//    public List<GyfwInfo> Test() {
//        List<GyfwInfo> result=null;
//        QueryBuilder<GyfwInfo,Integer> queryBuilder=daoFilterGYFWInfo.queryBuilder();
//        try {
//            result=queryBuilder.query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    /**
     * 删除所有筛选病害信息
     * @return
     */
    @Override
    public int deleteAllBHInfo() {
        int result=0;
        DeleteBuilder<FilterBHInfo,Integer> deleteBuilder=daoFilterBHInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 添加筛选路线信息
     * @param listInfo
     * @return
     */
    @Override
    public int addFilterLXInfo(ArrayList<FilterLxInfo> listInfo) {
        int result=0;
        try {
            result=daoFilterLXInfo.create(listInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 查询所有筛选路线信息
     * @return
     */
    @Override
    public List<FilterLxInfo> queryAllFilterLXInfo() {
        List<FilterLxInfo> result=null;
        QueryBuilder<FilterLxInfo,Integer> queryBuilder=daoFilterLXInfo.queryBuilder();
        try {
            result=queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除所有筛选路线信息
     * @return
     */
    @Override
    public int deleteAllLXInfo() {
        int result=0;
        DeleteBuilder<FilterLxInfo,Integer> deleteBuilder=daoFilterLXInfo.deleteBuilder();
        try {
            result=deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<DisposalSchemeInfo> queryAllBHLXInfo() {
        List<DisposalSchemeInfo> result=null;
        try {
            QueryBuilder<DisposalSchemeInfo,Integer> queryBuilder=daoDisposalInfo.queryBuilder();
            List<DisposalSchemeInfo> infoList = queryBuilder.selectColumns("BHLXMC").distinct().query();
            result=infoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //通过病害类型，查询病害名称
    @Override
    public List<DisposalSchemeInfo> queryBH(String bhlx) {
        List<DisposalSchemeInfo> gcldInfoListResult = null;
        QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
        Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("BHLXMC", bhlx);
            List<DisposalSchemeInfo> infoList = queryBuilder.selectColumns("BHMC").distinct().query();

            gcldInfoListResult=infoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfoListResult;
    }
    @Override
    public List<ConstructionUploadInfo> querySgxxUploadBylxidAndBhid(String lxmc, String bhid) {
        List<ConstructionUploadInfo> resultList=null;
        QueryBuilder<ConstructionUploadInfo,Integer> queryBuilder=daoConstructionUploadInfo.queryBuilder();
        try {
            if(!Utils.isNull(lxmc)){
                Where<ConstructionUploadInfo, Integer> where = queryBuilder.where();
                where.eq("LXBM",lxmc);
                if(!Utils.isNull(bhid)){
                    where.and().eq("BHLXID",bhid);
                }
            }else{
                if(!Utils.isNull(bhid)){
                    Where<ConstructionUploadInfo, Integer> where = queryBuilder.where();
                    where.eq("BHLXID",bhid);
                }
            }
            resultList=queryBuilder.orderBy("DCSJ", false).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }
    @Override
    public List<DisposalSchemeInfo> queryCZFA(String bhmc,String bhlx) {
        List<DisposalSchemeInfo> gcldInfoListResult = null;
        QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
        Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("BHLXMC", bhlx);
            where.and().eq("BHMC",bhmc);
            List<DisposalSchemeInfo> infoList = queryBuilder.selectColumns("CZFAMC").distinct().query();
            gcldInfoListResult=infoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfoListResult;
    }
    @Override
    public List<DisposalSchemeInfo> queryGCXM(String czfa,String bhmc) {
        List<DisposalSchemeInfo> gcldInfoListResult = null;
        QueryBuilder<DisposalSchemeInfo, Integer> queryBuilder = daoDisposalInfo.queryBuilder();
        Where<DisposalSchemeInfo, Integer> where = queryBuilder.where();
        try {
            where.eq("BHMC", bhmc);
            where.and().eq("CZFAMC",czfa);
            List<DisposalSchemeInfo> infoList = queryBuilder.selectColumns("XMMC","JLDW","GCXMID").distinct().query();
            gcldInfoListResult=infoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gcldInfoListResult;
    }
}