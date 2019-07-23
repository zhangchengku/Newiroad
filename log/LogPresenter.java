package com.zggk.newiroad.log;

import com.alibaba.fastjson.JSON;

import com.zggk.newiroad.Bean.LogListbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LogPresenter extends BasePresenterImpl<LogContract.View> implements LogContract.Presenter{

    @Override
    public void testinfo(String GYDWID ,String STIME,String ETIME,String DATAID,String ACTION,String PAGESIZE) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", MyApplication.spUtils.getString("dqgydwid"))
                .addParams("starttime",STIME)
                .addParams("endtime",ETIME )
                .addParams("dataid", DATAID)
                .addParams("action",ACTION )
                .addParams("pagesize", "20")

                .url(MyApplication.BASEURL+"QueryRzListByTime")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LogListbean videoVos2 = JSON.parseObject(response, LogListbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }



}



