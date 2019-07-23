package com.zggk.newiroad.season.addtask;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.SUCCESBEAN;
import com.zggk.newiroad.Bean.adddiseasebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddTaskPresenter extends BasePresenterImpl<AddTaskContract.View> implements AddTaskContract.Presenter{

    @Override
    public void  addTask(String json ) {

        OkHttpUtils.post()
                .tag(this)
                .addParams("json",json )
                .url(MyApplication.BASEURL+"SaveJjxyhLC")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        SUCCESBEAN videoVos2 = JSON.parseObject(response, SUCCESBEAN.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
