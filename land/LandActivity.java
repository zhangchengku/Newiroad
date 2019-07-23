package com.zggk.newiroad.land;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zggk.newiroad.Bean.Landbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.main.MainActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LandActivity extends MVPBaseActivity<LandContract.View, LandPresenter> implements LandContract.View {


    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.aaa)
    LinearLayout aaa;
    @Bind(R.id.land)
    TextView land;
    private String userName, passWord;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_land);
        ButterKnife.bind(this);
        linstener();
    }

    private void linstener() {
        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Utils.isNetworkAvailable(LandActivity.this)) {//没网
                    MyApplication.app.customToast("您当前没有网络");
                } else {//有网
                    userName = username.getText().toString().trim();
                    passWord = password.getText().toString().trim();
                    if (Utils.isNull(userName) && Utils.isNull(passWord)) {
                        MyApplication.app.customToast("请输入正确的账号或密码");
                        return;
                    } else {
                        land.setClickable(false);
                        getdate();
                    }
                     }
            }
        });
    }

    private void getdate() {
        OkHttpUtils
                .get()
                .addParams("userName", userName)
                .addParams("passWord", passWord)
                .url(MyApplication.BASEURL + "MobileLogin")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        land.setClickable(true);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("onResponse: ", response);
                        Landbean landBean = JSON.parseObject(response, Landbean.class);
                        if (landBean.getSTATE().equals("1")) {
                            MyApplication.spUtils.put("username", userName);//账号
                            MyApplication.spUtils.put("password", passWord);//密码
                            MyApplication.spUtils.put("dldq", landBean.getDLDQ());//登录地区
                            MyApplication.spUtils.put("dqgydwid", landBean.getDQGYDW());//管养单位id
                            MyApplication.spUtils.put("dqgydwmc", landBean.getDQGYDWMC());//管养单位名称
                            MyApplication.spUtils.put("xtmc", landBean.getXTMC());//系统名称
                            MyApplication.spUtils.put("role", landBean.getROLE());//角色
                            MyApplication.spUtils.put("dlr", landBean.getDLR());//当前登录人
                            MyApplication.spUtils.put("acfl", landBean.getRESOURCE());//
                            MyApplication.spUtils.put("QX", landBean.getDQGYDWDJ());//
                            MyApplication.app.customToast("登陆成功");
                            Intent intent = new Intent(LandActivity.this, MainActivity.class);
                            intent.putExtra("isCheckOfflineData", true);
                            startActivity(intent);
                            finish();
                        } else {
                            land.setClickable(true);
                            MyApplication.app.customToast(landBean.getMSG());
                        }
                    }
                });
    }
}
