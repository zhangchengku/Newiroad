package com.zggk.newiroad.guide;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.land.LandActivity;
import com.zggk.newiroad.main.MainActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.StringUtils;
import com.zggk.newiroad.utils.Utils;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GuideActivity extends MVPBaseActivity<GuideContract.View, GuidePresenter> implements GuideContract.View {
    private String pas;
    private String username;
    private String password;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_guide);
        handler=new Handler();
        initdate();
    }

    private void initdate() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        } else {
            username = MyApplication.app.spUtils.getString("username", "");
            password = MyApplication.app.spUtils.getString("password", "");
            if (Utils.isNull(username) && Utils.isNull(password)) {   //未登陆过
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(GuideActivity.this, LandActivity.class));
                        finish();
                    }
                }, 500);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                        intent.putExtra("isCheckOfflineData", true);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        }
    }

}
