package com.zggk.newiroad.popuwindo;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10270 on 2018/10/25.
 */

public class popuwindosa extends PopupWindow {

    private Activity activity;
    private List<String> listDataStr;

    private TextView vTitleTxt;
    private EditSearchAdapter adapter;
    private popuwindowsslistener listener;
    private String titleStr;
    private RecyclerView listView;
    private CommonAdapter<String> logrvadapter;
    private TextView headers;
    private List<String> listfan =new ArrayList<>();
    private List<Integer> listfanposition =new ArrayList<>();
    private CheckBox cb;
    private TextView queding;
   private boolean tpp = true;
    public popuwindosa(Activity activity, String titleStr, List<String> listDataStr, popuwindowsslistener listener){

        this.activity = activity;
        this.listDataStr=listDataStr;
        this.listener=listener;
        this.titleStr=titleStr;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.lxlist, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity));
        setHeight(Tooklkit.getHeight(activity));
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        initViews(contentView);
    }

    private void initViews(View contentView){
        headers = (TextView)contentView.findViewById(R.id.header);
        headers.setText(titleStr);
        queding = (TextView)contentView.findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectPosition(listfan,listfanposition);
                close();
            }
        });
        listView=(RecyclerView)contentView.findViewById(R.id.lx_recycle);
        listView.setLayoutManager(new LinearLayoutManager(activity));
        logrvadapter = new CommonAdapter<String>(activity,
                R.layout.item_addxz, listDataStr) {
            @Override
            protected void convert(ViewHolder holder, final String tubiaoVo, final int position) {
                holder.setText(R.id.neirongte, tubiaoVo);
                final CheckBox cb=(CheckBox)holder.getView(R.id.cb);
                holder.setOnClickListener(R.id.ref, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(cb.isChecked()){
                            cb.setChecked(false);
                        }else {
                            cb.setChecked(true);
                        }
                    }
                });
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b) {
                            listfanposition.add(position);
                            listfan.add(tubiaoVo);
                        }else {
                            listfan.remove(tubiaoVo);
                        }
                    }
                });


            }
        };
        listView.setAdapter(logrvadapter);

//        adapter=new EditSearchAdapter(activity,listDataStr);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listener.selectPosition(position);
//                close();
//            }
//        });


    }

    /**
     * 显示筛选窗口
     */
    public void show(View anchor) {
        if (isShowing()) {
            return;
        }
        if (anchor != null) {
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 关闭筛选窗口
     */
    public void close() {
        if (isShowing()) {
            dismiss();
        }
    }

    public void notifityData(){
        adapter.notifyDataSetChanged();
    }
}
