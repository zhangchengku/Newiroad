package com.zggk.newiroad.check;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.checkready.CheckreadyFragment;
import com.zggk.newiroad.check.checkwai.CheckwaiFragment;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckActivity extends MVPBaseActivity<CheckContract.View, CheckPresenter> implements CheckContract.View {
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
    private CheckwaiFragment checkwaiFragment;
    private CheckreadyFragment checkreadyFragment;
    private SgRInfo videoVos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_check);
        ButterKnife.bind(this);
        title.setText("工程验收");
        checkwaiFragment = new CheckwaiFragment();
        checkreadyFragment = new CheckreadyFragment();
        listener();
        initdate();

    }

    private void initdate() {
        mPresenter.getsx(MyApplication.app.spUtils.getString("dqgydwid", ""));
    }

    private void listener() {

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextShowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(checkreadyFragment);
                nowShowing.setTextColor(getResources().getColor(R.color.theme_color));
                nextShowing.setTextColor(getResources().getColor(R.color.white));
            }
        });
        nowShowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(checkwaiFragment);
                nowShowing.setTextColor(getResources().getColor(R.color.white));
                nextShowing.setTextColor(getResources().getColor(R.color.theme_color));
            }
        });
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


    @Override
    public void getData(SgRInfo videoVos) {
        if (videoVos.getSTATE().equals("1")){
            this.videoVos = videoVos;
            goToFragment(checkwaiFragment);
        }
    }

    @Override
    public void onRequestError(String msg) {

    }
    public void showFilterZheZhaoView() {

    }
    public void hideFilterZheZhaoView() {

    }
    @Override
    public void onRequestEnd() {

    }
    public SgRInfo getSxDate(){
        return this.videoVos;
    }
}

