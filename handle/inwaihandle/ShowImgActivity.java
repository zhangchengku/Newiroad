package com.zggk.newiroad.handle.inwaihandle;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.tooklkit.Tooklkit;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.utils.PhotoView;
import com.zggk.newiroad.utils.PhotoViewAttacher;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-22.
 */
public class ShowImgActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vShowVp;
    private ArrayList<String> img;
    private ArrayList<String> img2;
    private int currentPosition = 0;// 当前位置
    private ViewPagerAdapter pagerAdapter;
    private GridView vImgBottomGrid;// 底部图片圆点页卡的gridview
    private TabBarAdapter tabBarAdapter;// 底部图片圆点页卡的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.show_imager_layout);
        img = getIntent().getStringArrayListExtra("img");
        img2 = getIntent().getStringArrayListExtra("img2");
        currentPosition = getIntent().getIntExtra("position", 0);
        initView();
    }

    /**
     * 实例化控件
     */
    private void initView(){
        if (img == null) {
            img = new ArrayList<String>();
        }
        if (img2 == null) {
            img2 = new ArrayList<String>();
        }

        // 去除缩略图后缀
        for (int i = 0; i < img.size(); i++) {
            if (img.get(i).contains("thumb")) {
                img.set(i, img.get(i).substring(0, img.get(i).indexOf("thumb") - 1));
            }
        }

        vImgBottomGrid = (GridView) findViewById(R.id.grid_view_img_bottom);
        if(img2.size()>0){
            setTabBar(img2.size());
        }else{
            setTabBar(img.size());
        }

        if (img.size() == 1) {
            vImgBottomGrid.setVisibility(View.GONE);
        }

        vShowVp = (ViewPager) findViewById(R.id.show_vp);
        if (img.size() + img2.size() != 0) {
            pagerAdapter = new ViewPagerAdapter();
            vShowVp.setAdapter(pagerAdapter);
            vShowVp.setCurrentItem(currentPosition);
            tabBarAdapter.setOptFor(currentPosition);
        }
        vShowVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                tabBarAdapter.setOptFor(index);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * ViewPager的适配器
     *
     * @author guolin
     */
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(ShowImgActivity.this).inflate(R.layout.show_image_layout, null);
            final PhotoView photoView = (PhotoView) view.findViewById(R.id.zoom_image_view);
            if (img.size() > position) {
                String imagePath = img.get(position);
                imagePath = imagePath.startsWith("http://") || imagePath.startsWith("https://")
                        ? imagePath : (MyApplication.BASEURL + imagePath);
                Glide.with(ShowImgActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(imagePath)
                        .into(photoView);

            } else {
                Glide.with(ShowImgActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(img2.get(position - img.size()))
                        .into(photoView);

            }
            ((ViewPager) container).addView(view);
            // 图片任何部分的点击事件
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {

                @Override
                public void onViewTap(View view, float x, float y) {
                    finish();
                }

            });
            // 长按保存图片监听
//            photoView.setLongClickable(true);
//            photoView.setOnLongClickListener(new OnLongClickListener() {
//
//                @Override
//                public boolean onLongClick(View v) {
//                    if (saveDialog != null) {
//                        if (img.size() > position) {
//                            current_img_url = img.get(position);
//                            saveDialog.show();
//                        }
//                    }
//                    return false;
//                }
//            });
            return view;
        }

        @Override
        public int getCount() {
            return img.size() + img2.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    /**
     * 设置页卡
     *
     * @param count
     */
    private void setTabBar(int count) {
        int width = Tooklkit.getWidth(this);
        if (width > count * Tooklkit.dip2px(this, 15) + (count - 1) * Tooklkit.dip2px(this, 5)) {
            width = count * Tooklkit.dip2px(this, 15) + (count - 1) * Tooklkit.dip2px(this, 5);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width,
                Tooklkit.dip2px(this, 12));
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        layoutParams.setMargins(0, 0, 0, Tooklkit.dip2px(this, 30));
        vImgBottomGrid.setLayoutParams(layoutParams);
        vImgBottomGrid.setNumColumns(count);
        tabBarAdapter = new TabBarAdapter(count, this);
        vImgBottomGrid.setAdapter(tabBarAdapter);
        if(count>1){
            vImgBottomGrid.setVisibility(View.VISIBLE);
        }else{
            vImgBottomGrid.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        // 图片保存操作
//        saveDialog.close();
//        if (!Utils.isNull(current_img_url)) {
//            new SavePictureToGalleryTask(ShowImgActivity.this, current_img_url, "").execute();
//        }
    }

}
