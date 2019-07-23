package com.zggk.newiroad.db.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
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

import java.sql.SQLException;

/**
 * 数据库创建工具类
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private final String TAG=DBHelper.class.getSimpleName();
    private static final String DB_NAME = "rcyh_data.db";
    private static final int DB_VERSION = 2;

    /****************数据库表名****************/
    public static final String TABLE_GUAN_YANG_DAN_WEI = "gydw";//管养单位
    public static final String TABLE_LU_XIAN = "lx";//路线
    public static final String TABLE_LU_DUAN = "ld";//路段
    public static final String TABLE_DCLX = "dclx";//调查类型表
    public static final String TABLE_GYFW = "gyfw";//调查类型表
    public static final String TABLE_CHU_ZHI_FANG_AN = "czfaxx";//处置方案信息
    public static final String TABLE_XUN_CHA_XING_ZHI= "xcxz";//巡查性质
    public static final String TABLE_XUN_CHA_XING_ZHI_NEI_RONG= "xcnr";//巡查性质内容
    public static final String TABLE_GONG_CHENG_LU_DUAN= "gcld";//工程路段表

    public static final String TABLE_BING_HAI_CAI_JI_BASE= "bhcjbase";//病害采集基本信息
    public static final String TABLE_BING_HAI_CAI_JI= "bhcj";//病害采集信息
    public static final String TABLE_RI_ZHI_JI_LU= "rzjl";//日志记录
    public static final String TABLE_SHI_GONG_WEI_XIU= "sgwx";//施工维修
    public static final String TABLE_SHI_GONG_WEI_XIU_UPLPAD= "sgwxupload";//施工维修 待上传表
    public static final String TABLE_FILTER_BH= "filterbh";//用于筛选的病害表
    public static final String TABLE_FILTER_LX= "filterlx";//用于筛选的路线表


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, ConstructionInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstructionUploadInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, DiseaseBaseInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, DisposalSchemeInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, InspectionPropertyInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, InspectionPropertyContentInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, LogRecordInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, RoadLineInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, RoadSectionInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, UnitInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, TypeOfInvestigation.class);
            TableUtils.createTableIfNotExists(connectionSource, GcldInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, FilterBHInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, FilterLxInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, GyfwInfo.class);
            // 若不是第一个版本安装，直接执行数据库升级
            // 请不要修改FIRST_DATABASE_VERSION的值，其为第一个数据库版本大小
            final int FIRST_DATABASE_VERSION = 2;
            onUpgrade(sqLiteDatabase, connectionSource,FIRST_DATABASE_VERSION, DB_VERSION);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 2:

                    break;
                case 3:

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ConstructionInfo.class,true);
            TableUtils.dropTable(connectionSource, ConstructionUploadInfo.class,true);
            TableUtils.dropTable(connectionSource, DiseaseBaseInfo.class,true);
            TableUtils.dropTable(connectionSource, DisposalSchemeInfo.class,true);
            TableUtils.dropTable(connectionSource, InspectionPropertyInfo.class,true);
            TableUtils.dropTable(connectionSource, InspectionPropertyContentInfo.class,true);
            TableUtils.dropTable(connectionSource, LogRecordInfo.class,true);
            TableUtils.dropTable(connectionSource, RoadLineInfo.class,true);
            TableUtils.dropTable(connectionSource, RoadSectionInfo.class,true);
            TableUtils.dropTable(connectionSource, UnitInfo.class,true);
            TableUtils.dropTable(connectionSource, TypeOfInvestigation.class,true);
            TableUtils.dropTable(connectionSource, GcldInfo.class,true);
            TableUtils.dropTable(connectionSource, FilterBHInfo.class,true);
            TableUtils.dropTable(connectionSource, FilterLxInfo.class,true);
            TableUtils.dropTable(connectionSource, GyfwInfo.class,true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
