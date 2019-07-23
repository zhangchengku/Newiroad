package com.zggk.newiroad.disease;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.DiseaseListbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DiseasePresenter extends BasePresenterImpl<DiseaseContract.View> implements DiseaseContract.Presenter{
    @Override
    public void testinfo(String GYDWID ,String STIME,String ETIME,String Dataid,String PAGEINDEX,String PAGESIZE) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("starttime",STIME )
                .addParams("endtime", ETIME)
                .addParams("gydwid",GYDWID )
                .addParams("dataid", Dataid)
                .addParams("action",PAGEINDEX )
                .addParams("pagesize", PAGESIZE)

                .url(MyApplication.BASEURL+"QueryBhList")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        DiseaseListbean videoVos2 = JSON.parseObject(response, DiseaseListbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
