package com.zggk.newiroad.examinedeal;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.ToExamineDataInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExamineDealPresenter extends BasePresenterImpl<ExamineDealContract.View> implements ExamineDealContract.Presenter{
    @Override
    public void  addDisease(String startTime,String lxid,String bhlx,String gydwid,String listtype ,String action,String dataid) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("starttime","")
                .addParams("endTime","" )
                .addParams("lxcode","" )
                .addParams("bhlx","" )
                .addParams("gydwid",gydwid )
                .addParams("listtype","0" )
                .addParams("dataid",dataid )
                .addParams("action",action)
                .addParams("pagesize","20" )
                .url(MyApplication.BASEURL+"QueryRwpfList")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        ToExamineDataInfo videoVos2 = JSON.parseObject(response, ToExamineDataInfo.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
