package com.zggk.newiroad.examinedone;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenterImpl;
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

public class ExamineDonePresenter extends BasePresenterImpl<ExamineDoneContract.View> implements ExamineDoneContract.Presenter{
    @Override
    public void  addDisease(String startTime,String lxid,String bhlx,String gydwid,String listtype ) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("starttime","" )
                .addParams("endTime","" )
                .addParams("lxcode","" )
                .addParams("bhlx","" )
                .addParams("gydwid",gydwid )
                .addParams("listtype","1" )
                .addParams("dataid","0" )
                .addParams("action","0" )
                .addParams("pagesize","10" )
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
