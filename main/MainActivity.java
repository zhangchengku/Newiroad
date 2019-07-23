package com.zggk.newiroad.main;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.xyz.tabitem.BottmTabItem;
import com.zggk.newiroad.Bean.LiXianDatabean;
import com.zggk.newiroad.Bean.LiXianbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;

import com.zggk.newiroad.home.HomeFragment;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.my.MyFragment;
import com.zggk.newiroad.train.TrainFragment;
import com.zggk.newiroad.utils.AppManager;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.Dialog.LoadOfflineDataDialog;
import com.zggk.newiroad.utils.ToastUtils;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View,View.OnClickListener {
    HomeFragment homeFragment;
    MyFragment myFragment;
    private BottmTabItem home,my;
    private boolean isLoadOfflineData;
    private MainDialog firstLoadOfflineDataWarmDialog;
    private MainDialog refreshOfflineDataWarmDialog;
    private LoadOfflineDataDialog loadOfflineDataDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.activity_main);
        home=(BottmTabItem)findViewById(R.id.home);
        my=(BottmTabItem)findViewById(R.id.my);
        homeFragment = new HomeFragment();
        myFragment = new MyFragment();
        goToFragment(homeFragment);
        home.setOnClickListener(this);
        my.setOnClickListener(this);
        boolean isCheckOfflineData = getIntent().getBooleanExtra("isCheckOfflineData", false);

        if (isCheckOfflineData) {
            mPresenter.updatedb(MyApplication.spUtils.getString("username"),MyApplication.spUtils.getString("dqgydwid"));
        }
//        new UpdateManager(this, "main").checkUpdate();   //检查更新
    }




    private Fragment mContent = null;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }

    /**
     * 底部按钮点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                goToFragment(homeFragment);
                home.setSelectState(true);

                my.setSelectState(false);
                break;

            case R.id.my:
                goToFragment(myFragment);
                home.setSelectState(false);

                my.setSelectState(true);
                break;

        }
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    ToastUtils.showShortToast("再按一次退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void getupdatedb(LiXianDatabean liXianDatabean) {
        if (liXianDatabean != null && "1".equals(liXianDatabean.getSTATE())) {
            ArrayList<LiXianbean> listInfo = liXianDatabean.getVERLIST();
            if (listInfo != null && listInfo.size() > 0) {
                ArrayList<LiXianbean> listNeedLoadOfflineData = new ArrayList<>();
                for (int i = 0; i < listInfo.size(); i++) {
                    LiXianbean info = listInfo.get(i);
                    if (info != null) {
                        String current_bbh = info.getSJ();
                        String table_key = info.getFILETYPE() + "_bbh";
                        String table_bbh = MyApplication.app.spUtils.getString(table_key, "0");
                        if (!current_bbh.equals(table_bbh)) {
                            Log.i("更新离线包", "更新" + info.getFILETYPE());
                            listNeedLoadOfflineData.add(info);
                        }
                    }
                }
                if (listNeedLoadOfflineData.size() > 0) {
                    isLoadOfflineData = MyApplication.spUtils.getBoolean("isLoadOfflineData", false);
                    if (!isLoadOfflineData) {
                        showFirstLoadOfflineDataWarmDialog(listNeedLoadOfflineData);
                    } else {
                        showRefreshLoadOfflineDataWarmDialog(listNeedLoadOfflineData);
                    }
                }
            }
        }
    }
    private void showFirstLoadOfflineDataWarmDialog(final ArrayList<LiXianbean> listInfo) {
        if (firstLoadOfflineDataWarmDialog == null) {
            String title = "有离线数据包需要下载，否则会影响正常使用，请问是否下载？";
            String okStr = "确定";
            String cancelStr ="取消";
            firstLoadOfflineDataWarmDialog = new MainDialog(this, title, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    showLoadOfflineDataDialog(listInfo);
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        firstLoadOfflineDataWarmDialog.show();
    }
    /**
     * 显示刷新离线数据包的提示对话框
     */
    private void showRefreshLoadOfflineDataWarmDialog(final ArrayList<LiXianbean> listInfo) {
        String number = String.valueOf(listInfo.size());
        if (refreshOfflineDataWarmDialog == null) {
            String title = String.format("有%s个离线数据包需要更新，请问是否更新", number);
            String okStr = "确定";
            String cancelStr = "取消";
            refreshOfflineDataWarmDialog = new MainDialog(this, title, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    showLoadOfflineDataDialog(listInfo);
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        String title = String.format("有%s个离线数据包需要更新，请问是否更新？", number);
        refreshOfflineDataWarmDialog.setWarmTitle(title);
        refreshOfflineDataWarmDialog.show();
    }
    /**
     * 显示加载进度条 开始加载离线包数据
     */
    private void showLoadOfflineDataDialog(ArrayList<LiXianbean> listInfo) {
        if (loadOfflineDataDialog == null) {
            loadOfflineDataDialog = new LoadOfflineDataDialog(this, listInfo);
        }
        loadOfflineDataDialog.show();
    }


    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

}