package com.zggk.newiroad.handle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.R;
import com.zggk.newiroad.handle.readyhandle.ReadyhandleFragment;
import com.zggk.newiroad.handle.waihandle.WaihandleFragment;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.thread.LoadDataDialog;
import com.zggk.newiroad.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HandleActivity extends MVPBaseActivity<HandleContract.View, HandlePresenter> implements HandleContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.now_showing)
    RadioButton nowShowing;
    @Bind(R.id.next_showing)
    RadioButton nextShowing;
    @Bind(R.id.radio_layout)
    RadioGroup radioLayout;
    @Bind(R.id.hedler_layout)
    RelativeLayout hedlerLayout;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    private WaihandleFragment businessFragment;
    private ReadyhandleFragment statisticsFragment;
    private LoadDataDialog loadDataDialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_handle);
        ButterKnife.bind(this);
        title.setText("施工维修");
        showFilterZheZhaoView();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        businessFragment = new WaihandleFragment();
        statisticsFragment = new ReadyhandleFragment();
        goToFragment(businessFragment);
        nextShowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getContext()) == false) {//没网
                    nextShowing.setChecked(false);
                    nowShowing.setChecked(true);
                } else {//有网
                    goToFragment(statisticsFragment);
                    nowShowing.setTextColor(getResources().getColor(R.color.theme_color));
                    nextShowing.setTextColor(getResources().getColor(R.color.white));
                }

            }
        });
        nowShowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(businessFragment);
                nowShowing.setTextColor(getResources().getColor(R.color.white));
                nextShowing.setTextColor(getResources().getColor(R.color.theme_color));
            }
        });


    }

    public void showFilterZheZhaoView() {

    }
    public void hideFilterZheZhaoView() {

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
    public SgRInfo getSxDate(){
        return new Gson().fromJson(getIntent().getStringExtra("videoVos"),SgRInfo.class);
    }



}

