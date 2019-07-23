package com.zggk.newiroad.check.checkready;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.checkwaibean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckreadyPresenter extends BasePresenterImpl<CheckreadyContract.View> implements CheckreadyContract.Presenter{
    private HashMap<String, String> params;
    private HashMap<String, String> paramss;
    @Override
    public void getlist(String LXID, String GYDWID, String BHID, String STIME, String ETIME, String DATAID, String ACTION, String PAGESIZE) {
        params = new HashMap<String, String>();
        params.put("lxcode", LXID);
        params.put("bhlx", BHID);
        params.put("listtype", "1");
        params.put("starttime", STIME);
        params.put("endtime", ETIME);
        params.put("gydwid", GYDWID);
        params.put("dataid", DATAID);
        params.put("action", ACTION);
        params.put("pagesize", PAGESIZE);
        OkHttpUtils.get()
                .tag(this)
                .params(params)
                .url(MyApplication.BASEURL+"QueryYsjlList")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        checkwaibean videoVos2 = JSON.parseObject(response, checkwaibean.class);
                        if(videoVos2!=null&&mView!=null){
                            mView.getData(videoVos2);
                        }
                    }
                });

    }
}
