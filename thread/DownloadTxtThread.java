package com.zggk.newiroad.thread;

import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zggk.newiroad.Bean.LiXianbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.db.dbhelper.dbInfo.DisposalSchemeInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.GcldInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.GyfwInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.InspectionPropertyContentInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.InspectionPropertyInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadLineInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.zggk.newiroad.db.dbhelper.dbInfo.UnitInfo;
import com.zggk.newiroad.utils.Dialog.InsertDatabaseSuccessListener;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * 下载线程
 * Created by dongxiaoqing on 2018/10/22.
 */

public class DownloadTxtThread extends Thread {
    private Gson gson = new Gson();
    private LiXianbean info;
    private InsertDatabaseSuccessListener listener;
    private String table_name;
    private String urlStr_txt;
    private String bbh;


    public DownloadTxtThread(LiXianbean info, InsertDatabaseSuccessListener listener) {
        this.info=info;
        this.listener=listener;
        table_name=info.getFILETYPE();
        urlStr_txt=info.getXDPATH();
        bbh=info.getSJ();
    }

    @Override
    public void run() {
        HttpDownloader httpDownloader = new HttpDownloader();
        String lrc = httpDownloader.download(urlStr_txt);
        if ("czfaxx".equals(table_name)) {
            ArrayList<DisposalSchemeInfo> listDispo;
            Type founderListType = new TypeToken<ArrayList<DisposalSchemeInfo>>() {
            }.getType();
            listDispo = gson.fromJson(lrc, founderListType);
            if (listDispo != null && listDispo.size() > 0) {
                MyApplication.app.curingDao.deleteAllCzfa();
                int size=MyApplication.app.curingDao.addCzfa(listDispo);
                int listSize=listDispo.size();
                if(size==listSize){
                    Log.i("处置方案表添加成功","处置方案表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }
        }else if ("xcnr".equals(table_name)) {
            ArrayList<InspectionPropertyContentInfo> listXcxzNr;
            Type founderListType = new TypeToken<ArrayList<InspectionPropertyContentInfo>>() {
            }.getType();
            listXcxzNr = gson.fromJson(lrc, founderListType);
            if (listXcxzNr != null && listXcxzNr.size() > 0) {
                MyApplication.app.curingDao.deleteAllXcxznr();
                int size = MyApplication.app.curingDao.addXcxznr(listXcxzNr);
                int listSize=listXcxzNr.size();
                if(size==listSize){
                    Log.i("巡查性质内容表添加成功","巡查性质内容表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }
        } else if ("lx".equals(table_name)) {

            ArrayList<RoadLineInfo> listRoadLine;
            Type founderListType = new TypeToken<ArrayList<RoadLineInfo>>() {
            }.getType();
            listRoadLine = gson.fromJson(lrc, founderListType);
            if (listRoadLine != null && listRoadLine.size() > 0) {
                MyApplication.app.curingDao.deleteAllLx();
                int size=MyApplication.app.curingDao.addLx(listRoadLine);
                int listSize=listRoadLine.size();
                if(size==listSize){
                    Log.i("路线表添加成功","路线表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }

        } else if ("gydw".equals(table_name)) {

            ArrayList<UnitInfo> listUnit;
            Type founderListType = new TypeToken<ArrayList<UnitInfo>>() {
            }.getType();
            listUnit = gson.fromJson(lrc, founderListType);
            if (listUnit != null && listUnit.size() > 0) {
                MyApplication.app.curingDao.deleteAllGydw();
                int size=MyApplication.app.curingDao.addGydw(listUnit);
                int listSize=listUnit.size();
                if(size==listSize){
                    Log.i("管养单位表添加成功","管养单位表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }

        } else if ("ld".equals(table_name)) {

            ArrayList<RoadSectionInfo> listRoadSectioin;
            Type founderListType = new TypeToken<ArrayList<RoadSectionInfo>>() {
            }.getType();
            listRoadSectioin = gson.fromJson(lrc, founderListType);
            if (listRoadSectioin != null && listRoadSectioin.size() > 0) {
                MyApplication.app.curingDao.deleteAllLd();
                int size=MyApplication.app.curingDao.addLd(listRoadSectioin);
                int listSize=listRoadSectioin.size();
                if(size==listSize){
                    Log.i("路段表添加成功","路段表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }

        }else if ("dclx".equals(table_name)) {
            ArrayList<TypeOfInvestigation> listInvestigation;
            Type founderListType = new TypeToken<ArrayList<TypeOfInvestigation>>() {
            }.getType();
            listInvestigation = gson.fromJson(lrc, founderListType);
            if (listInvestigation != null && listInvestigation.size() > 0) {
                MyApplication.app.curingDao.deleteAllDcLx();
                int size=MyApplication.app.curingDao.addInvestigation(listInvestigation);
                int listSize=listInvestigation.size();
                if(size==listSize){
                    Log.i("调查类型表添加成功","调查类型表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }

        } else if ("xcxz".equals(table_name)) {

            ArrayList<InspectionPropertyInfo> listXcxz;
            Type founderListType = new TypeToken<ArrayList<InspectionPropertyInfo>>() {
            }.getType();
            listXcxz = gson.fromJson(lrc, founderListType);
            if (listXcxz != null && listXcxz.size() > 0) {
                MyApplication.app.curingDao.deleteAllXcxz();
                int size= MyApplication.app.curingDao.addXcxz(listXcxz);
                int listSize=listXcxz.size();
                if(size==listSize){
                    Log.i("巡查性质表添加成功","巡查性质表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }
           } else if("gcld".equals(table_name)){
            ArrayList<GcldInfo> listGcld;
            Type founderListType = new TypeToken<ArrayList<GcldInfo>>() {
            }.getType();
            listGcld = gson.fromJson(lrc, founderListType);
            if (listGcld != null && listGcld.size() > 0) {
                MyApplication.app.curingDao.deleteAllGcld();
                int size = MyApplication.app.curingDao.addGcld(listGcld);
                int listSize=listGcld.size();
                if(size==listSize){
                    Log.i("工程路段表添加成功","工程路段表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }
        }else if("gyfw".equals(table_name)){
            ArrayList<GyfwInfo> listGyfw;
            Type founderListType = new TypeToken<ArrayList<GyfwInfo>>() {
            }.getType();
            listGyfw = gson.fromJson(lrc, founderListType);
            if (listGyfw != null && listGyfw.size() > 0) {
                MyApplication.app.curingDao.deleteAllGcld();
                int size = MyApplication.app.curingDao.addGyfw(listGyfw);
                int listSize=listGyfw.size();
                if(size==listSize){
                    Log.i("管养范围表添加成功","管养范围表添加成功"+size);
                    String table_key = table_name + "_bbh";
                    MyApplication.app.spUtils.put(table_key, bbh);
                    listener.insertSuccess();
                }
            }
        }
//        Log.i("离线信息表内容===",lrc);
    }

}