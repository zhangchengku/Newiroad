package com.zggk.newiroad.disease.newdisease;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.Adddiseasejson;
import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.adddiseasebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.DisposalSchemeInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.zggk.newiroad.disease.ListAdapter;
import com.zggk.newiroad.disease.SendTopicPictureGridAdapter;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.log.add.AddActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.DiseaeNewSelectObjectPopupWindow;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.NoScroolGridView;
import com.zggk.newiroad.utils.PopSelectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.WheelDateAndTimeSelectPopupWindow;

import java.io.File;
import java.lang.ref.Reference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NewdiseaseActivity extends MVPBaseActivity<NewdiseaseContract.View, NewdiseasePresenter> implements NewdiseaseContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_new_unit_name_txt)
    TextView diseaseNewUnitNameTxt;
    @Bind(R.id.luxian_warm_txt)
    TextView luxianWarmTxt;
    @Bind(R.id.disease_new_road_line_txt_label)
    TextView diseaseNewRoadLineTxtLabel;
    @Bind(R.id.disease_new_road_line_txt)
    TextView diseaseNewRoadLineTxt;
    @Bind(R.id.disease_new_road_line_arrow_img)
    ImageView diseaseNewRoadLineArrowImg;
    @Bind(R.id.disease_new_road_line_layout)
    RelativeLayout diseaseNewRoadLineLayout;
    @Bind(R.id.time_warm_txt)
    TextView timeWarmTxt;
    @Bind(R.id.disease_new_time_txt_label)
    TextView diseaseNewTimeTxtLabel;
    @Bind(R.id.disease_new_time_txt)
    TextView diseaseNewTimeTxt;
    @Bind(R.id.disease_new_time_layout)
    RelativeLayout diseaseNewTimeLayout;
    @Bind(R.id.dclx_warm_txt)
    TextView dclxWarmTxt;
    @Bind(R.id.disease_new_to_examine_type_txt_label)
    TextView diseaseNewToExamineTypeTxtLabel;
    @Bind(R.id.disease_new_to_examine_type_txt)
    TextView diseaseNewToExamineTypeTxt;
    @Bind(R.id.disease_new_to_examine_type_layout)
    RelativeLayout diseaseNewToExamineTypeLayout;
    @Bind(R.id.zh_warm_txt)
    TextView zhWarmTxt;
    @Bind(R.id.disease_new_pile_number_label)
    TextView diseaseNewPileNumberLabel;
    @Bind(R.id.disease_new_pile_number_k)
    TextView diseaseNewPileNumberK;
    @Bind(R.id.disease_new_pile_number_one_edit)
    EditText diseaseNewPileNumberOneEdit;
    @Bind(R.id.disease_new_pile_number_jia)
    TextView diseaseNewPileNumberJia;
    @Bind(R.id.disease_new_pile_number_two_edit)
    EditText diseaseNewPileNumberTwoEdit;
    @Bind(R.id.disease_new_pile_number_location_txt)
    LinearLayout diseaseNewPileNumberLocationTxt;
    @Bind(R.id.disease_new_driver_direction_shang_txt)
    TextView diseaseNewDriverDirectionShangTxt;
    @Bind(R.id.disease_new_driver_direction_xia_txt)
    TextView diseaseNewDriverDirectionXiaTxt;
    @Bind(R.id.disease_new_driver_direction_quan_txt)
    TextView diseaseNewDriverDirectionQuanTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_label_txt)
    TextView diseaseCustomEditItemBhlxLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_txt_btn)
    TextView diseaseCustomEditItemBhlxTxtBtn;
    @Bind(R.id.bhlx)
    LinearLayout bhlx;
    @Bind(R.id.disease_custom_edit_item_bhmc_label_txt)
    TextView diseaseCustomEditItemBhmcLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhmc_txt_btn)
    TextView diseaseCustomEditItemBhmcTxtBtn;
    @Bind(R.id.bhmc)
    LinearLayout bhmc;
    @Bind(R.id.disease_custom_edit_item_sgfs_label_txt)
    TextView diseaseCustomEditItemSgfsLabelTxt;
    @Bind(R.id.disease_custom_edit_item_sgfs_txt_btn)
    TextView diseaseCustomEditItemSgfsTxtBtn;
    @Bind(R.id.sgfs)
    LinearLayout sgfs;
    @Bind(R.id.add_ddisease_list_view)
    ListViewForScrollView addDdiseaseListView;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.disease_new_bhcj_save_txt)
    TextView diseaseNewBhcjSaveTxt;
    @Bind(R.id.disease_new_bhcj_upload_now_txt)
    TextView diseaseNewBhcjUploadNowTxt;
    @Bind(R.id.activity_disease_list_bottom_btn_layout)
    LinearLayout activityDiseaseListBottomBtnLayout;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.dw_im)
    TextView dwim;
    @Bind(R.id.dw_te)
    TextView dwte;
    private CuringDaoImpl curingDao;
    public static boolean isSaveOrUpdateData;//是否修改了数据
    private SimpleDateFormat simpleDateFormat;
    private SendTopicPictureGridAdapter addPictureAdapter;
    private Map<Integer, SendTopicPictureGridAdapter> mapAdapter = new HashMap<>();
    private List<RoadSectionInfo> lxResultdb;
    private ArrayList<String> lxResult = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow LxPop;
    private WheelDateAndTimeSelectPopupWindow TimePop;
    private DiseaeNewSelectObjectPopupWindow DclxPop;
    private List<TypeOfInvestigation> dclxResultdb ;
    private ArrayList<String> dclxResult = new ArrayList<>();
    private ArrayList<String> bhlxResult = new ArrayList<>();
    private TypeOfInvestigation dclxInfo;
    private List<DisposalSchemeInfo> bhlxResultdb;
    private DisposalSchemeInfo bhlxInfo;
    private DiseaeNewSelectObjectPopupWindow BhlxPop;
    private List<DisposalSchemeInfo> bhmcResultdb;
    private ArrayList<String> bhResult = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow BhmcPop;
    private List<DisposalSchemeInfo> sgfsResultdb;
    private ArrayList<String> sgfsResult = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow SgfsPop;
    private ListAdapter gcxmadapter;
    private ArrayList<ItemBeanInfo> gcxmResult = new ArrayList<>();
    private List<DisposalSchemeInfo> gcxmResultdb;
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private String GGXMMC,SL,DW,GGXMID;
    private int directionSelInt = 1;// 1上行-右 2下行-左 3双向-全幅
    private ArrayList<Adddiseasejson> LIST= new ArrayList<>();
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private CommNotificationDialog commNotificationDialog;
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    private String gydwid = MyApplication.app.spUtils.getString("dqgydwid");
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//点击拍照获取的childveiw下标
    private RoadSectionInfo lxInfo;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_add_disease);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        initview();
        initPopWindow();
        initdate();
        listener();
    }

    private void listener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationDialog();
            }
        });
        diseaseNewRoadLineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxPop.show(diseaseNewParentLayout);
            }
        });
        diseaseNewTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePop.show(diseaseNewParentLayout);
            }
        });
        diseaseNewToExamineTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DclxPop.show(diseaseNewParentLayout);
            }
        });
        bhlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhOneStr = diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr = diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhTwoStr="0";
                }
                if (!Utils.isNull(zhOneStr)) {
                    Double zh = Double.parseDouble(zhOneStr + "." + zhTwoStr);
                    if (lxInfo != null) {
                        Double qdzh = Double.parseDouble(lxInfo.getQDZH());
                        Double zdzh = Double.parseDouble(lxInfo.getZDZH());
                        if (qdzh <= zh && zh <= zdzh) {
                            if (bhmc.getVisibility()==View.VISIBLE){
                                bhmc.setVisibility(View.GONE);
                            }
                            if (sgfs.getVisibility()==View.VISIBLE){
                                sgfs.setVisibility(View.GONE);
                            }
                            gcxmResult.clear();
                            gcxmadapter.notifyDataSetChanged();
                            BhlxPop.show(diseaseNewParentLayout);
                        } else {
                            MyApplication.app.customToast("您输入的桩号不在路线范围内，请重新输入");
                        }
                    } else {
                        MyApplication.app.customToast("请选择路线");
                    }
                } else {
                    MyApplication.app.customToast("请输入桩号");
                }
            }
        });
        bhmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sgfs.getVisibility()==View.VISIBLE){
                    sgfs.setVisibility(View.GONE);
                }
                gcxmResult.clear();
                gcxmadapter.notifyDataSetChanged();
                BhmcPop.show(diseaseNewParentLayout);
            }
        });
        sgfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gcxmResult.clear();
                gcxmadapter.notifyDataSetChanged();
                SgfsPop.show(diseaseNewParentLayout);
            }
        });
        diseaseNewDriverDirectionShangTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShangXingSelect();
            }
        });
        diseaseNewDriverDirectionXiaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setXiaXingSelect();
            }
        });
        diseaseNewDriverDirectionQuanTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuanFuSelect();
            }
        });
        diseaseNewBhcjSaveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhOneStr = diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr = diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhTwoStr="0";
                }
                if (!Utils.isNull(zhOneStr)) {
                    Double zh = Double.parseDouble(zhOneStr + "." + zhTwoStr);
                    if (lxInfo != null) {
                        Double qdzh = Double.parseDouble(lxInfo.getQDZH());
                        Double zdzh = Double.parseDouble(lxInfo.getZDZH());
                        if (qdzh <= zh && zh <= zdzh) {
                            save();
                        } else {
                            MyApplication.app.customToast("您输入的桩号不在路线范围内，请重新输入");
                        }
                    } else {
                        MyApplication.app.customToast("请选择路线");
                    }
                }
            }
        });
        diseaseNewBhcjUploadNowTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhOneStr = diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr = diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhTwoStr="0";
                }
                if (!Utils.isNull(zhOneStr)) {
                    Double zh = Double.parseDouble(zhOneStr + "." + zhTwoStr);
                    if (lxInfo != null) {
                        Double qdzh = Double.parseDouble(lxInfo.getQDZH());
                        Double zdzh = Double.parseDouble(lxInfo.getZDZH());
                        if (qdzh <= zh && zh <= zdzh) {
                            updata();
                        } else {
                            MyApplication.app.customToast("您输入的桩号不在路线范围内，请重新输入");
                        }
                    } else {
                        MyApplication.app.customToast("请选择路线");
                    }
                }
            }
        });
        diseaseNewPileNumberLocationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dwim.setBackgroundDrawable(getResources().getDrawable(R.drawable.xcbh_xiangqing_dingwei_icon2));
                dwte.setTextColor(Color.parseColor("#999999"));
                if(locationClient==null){
                    initLocation();
                }
                startLocation();
            }
        });
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    private int isSaveOk() {
        if (Utils.isNull(diseaseCustomEditItemBhlxTxtBtn.getText().toString())) {//病害类型为空
            return 1;
        }else if (Utils.isNull(diseaseCustomEditItemBhmcTxtBtn.getText().toString())) {//病害名称为空
            return 2;
        } else if (Utils.isNull(diseaseCustomEditItemSgfsTxtBtn.getText().toString())) {//施工方式为空
            return 3;
        } else {
            int size = addPictureAdapter.getListImgUrl().size();
            if (size == 0) {
                return 4;
            }
        }
        return 5;
    }
    private void updata() {
        if (Utils.isNetworkAvailable(NewdiseaseActivity.this) == false) {//没网
            MyApplication.app.customToast("您当前没有网络");
        } else {//有网
            int result = isSaveOk();
            if (result == 1) {
                MyApplication.app.customToast("请输入病害类型");
            } else if (result == 2) {
                MyApplication.app.customToast("请输入病害名称");
            } else if (result == 3) {
                MyApplication.app.customToast("请输入施工方式");
            } else if (result == 4) {
                MyApplication.app.customToast("请添加采集图片");
            } else if (result == 5) {
                diseaseNewBhcjUploadNowTxt.setClickable(false);
                showLoadingDialogMethod("上传中...");
                Adddiseasejson adddiseasejson = new Adddiseasejson();
                adddiseasejson.setBHMC(curingDao.queryCzfaByBhmc(diseaseCustomEditItemBhmcTxtBtn.getText().toString()).getBHLXID());
                adddiseasejson.setCREATOR(MyApplication.spUtils.getString("dlr"));
                adddiseasejson.setCZFAMC(diseaseCustomEditItemSgfsTxtBtn.getText().toString());
                adddiseasejson.setDCLX(curingDao.queryInvestigationByMc(diseaseNewToExamineTypeTxt.getText().toString()).getDCID());
                adddiseasejson.setDCR(MyApplication.spUtils.getString("dlr"));
                adddiseasejson.setDCSJ(diseaseNewTimeTxt.getText().toString());
                adddiseasejson.setFXDW(MyApplication.spUtils.getString("dqgydwid"));
                adddiseasejson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
                adddiseasejson.setLXCODE(lxInfo.getLXID());
                String zhStr="0.0";
                String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhStr = zhOneStr;
                }else{
                    zhStr = zhOneStr + "." + zhTwoStr;
                }
                adddiseasejson.setQDZH(zhStr);
                adddiseasejson.setSFJL("0");
                adddiseasejson.setSHBW(diseaseCustomEditItemBhlxTxtBtn.getText().toString());
                String xsfxStr = "";
                if (directionSelInt == 1) {
                    xsfxStr = diseaseNewDriverDirectionShangTxt.getText().toString();
                } else if (directionSelInt == 2) {
                    xsfxStr = diseaseNewDriverDirectionXiaTxt.getText().toString();
                } else if (directionSelInt == 3) {
                    xsfxStr = diseaseNewDriverDirectionQuanTxt.getText().toString();
                }
                adddiseasejson.setWZFX(xsfxStr);
                ArrayList<Adddiseasejson.CZFABean> cZFAlist= new ArrayList<>();
                if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
                    for (int k = 0; k < gcxmadapter.get().size(); k++) {
                        Adddiseasejson.CZFABean   cZFABean  =    new Adddiseasejson.CZFABean();
                        cZFABean.setJSGS(replaceNull(gcxmadapter.get().get(k).getJsgs()));
                        cZFABean.setGCXMID(gcxmadapter.get().get(k).getGcxmid());
                        cZFAlist.add(cZFABean);
                    }
                    adddiseasejson.setCZFA(cZFAlist);
                }
                String imgStr = "";
                ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                if (listImgUrl != null && listImgUrl.size() > 0) {
                    for (int k = 0; k < listImgUrl.size(); k++) {
                        if (k == 0) {
                            imgStr += listImgUrl.get(k);
                        } else {
                            imgStr += "," + listImgUrl.get(k);
                        }
                    }
                }
                if (!Utils.isNull(imgStr)) {
                    String[] strArr = imgStr.split(",");
                    if (strArr != null && strArr.length > 0) {
                        ArrayList<Adddiseasejson.PICBean> listPic = new ArrayList<>();
                        for (int i = 0; i < strArr.length; i++) {
                            Adddiseasejson.PICBean picInfo = new Adddiseasejson.PICBean();
                            String imgUrl = strArr[i];
                            String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                            String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                            picInfo.setPicFileName(nameStr);
                            picInfo.setPicFileType(typeStr);
                            String strBlob = Utils.bmpToBase64String(imgUrl);
                            Log.i("图片提交的二进制流", "=====" + strBlob);
                            picInfo.setPicDataBlob(strBlob);
                            listPic.add(picInfo);
                        }
                        adddiseasejson.setPIC(listPic);
                    }
                }
                LIST.add(adddiseasejson);
                String tijiaodates = gson.toJson(LIST);
                Log.i("图片", tijiaodates);
                mPresenter.addDisease(tijiaodates);

            }
        }
    }
    @Override
    public void getData(adddiseasebean videoVos2) {
        if (videoVos2.getState().equals("1")){
            isSaveOrUpdateData = true;
            MyApplication.app.customToast("上传成功");
            finish();
        }
        diseaseNewBhcjUploadNowTxt.setClickable(true);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }
    /**
     * 实例化退出页面提示对话框
     */
    private void showNotificationDialog() {
        if (commNotificationDialog == null) {
            String title = "未保存的数据将会丢失";
            String okStr = "确认";
            String cancelStr = "取消";
            commNotificationDialog = new CommNotificationDialog(this, title, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    finish();
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        commNotificationDialog.show();
    }
    @Override
    public void getData2(Dingbean videoVos2) {
        dwim.setBackgroundDrawable(getResources().getDrawable(R.drawable.xcbh_xiangqing_dingwei_icon));
        dwte.setTextColor(Color.parseColor("#5db7fc"));
        if (videoVos2.getSTATE().equals("1")){
            if (videoVos2.getGPS().size()>0){
                String zhStr=videoVos2.getGPS().get(0).getQDZH();
                if(!Utils.isNull(zhStr)){
                    if(zhStr.contains(".")){
                        String zhOneStr=zhStr.substring(0,zhStr.lastIndexOf("."));
                        String zhTwoStr=zhStr.substring(zhStr.lastIndexOf(".")+1,zhStr.length());
                        diseaseNewPileNumberOneEdit.setText(zhOneStr);
                        diseaseNewPileNumberTwoEdit.setText(zhTwoStr);
                    }else{
                        diseaseNewPileNumberOneEdit.setText(videoVos2.getGPS().get(0).getQDZH());
                        diseaseNewPileNumberTwoEdit.setText("0");
                    }
                    String str=diseaseNewPileNumberOneEdit.getText().toString();
                    diseaseNewPileNumberOneEdit.setSelection(str.length());
                }
            }else {
                MyApplication.app.customToast("您当前位置暂时无法定位");
            }

        }

    }

    private void save() {
        int result = isSaveOk();
        if (result == 1) {
            MyApplication.app.customToast("请输入病害类型");
        } else if (result == 2) {
            MyApplication.app.customToast("请输入病害名称");
        } else if (result == 3) {
            MyApplication.app.customToast("请输入施工方式");
        } else if (result == 4) {
            MyApplication.app.customToast("请添加采集图片");
        } else if (result == 5) {
            diseaseNewBhcjSaveTxt.setClickable(false);
            showLoadingDialogMethod("上传中...");
            String zhStr="0.0";
            String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
            String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
            if(Utils.isNull(zhTwoStr)){
                zhStr = zhOneStr;
            }else{
                zhStr = zhOneStr + "." + zhTwoStr;
            }
            String xsfxStr = "";
            if (directionSelInt == 1) {
                xsfxStr = diseaseNewDriverDirectionShangTxt.getText().toString();
            } else if (directionSelInt == 2) {
                xsfxStr = diseaseNewDriverDirectionXiaTxt.getText().toString();
            } else if (directionSelInt == 3) {
                xsfxStr = diseaseNewDriverDirectionQuanTxt.getText().toString();
            }
            String imgStr = "";
            ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
            if (listImgUrl != null && listImgUrl.size() > 0) {
                for (int k = 0; k < listImgUrl.size(); k++) {
                    if (k == 0) {
                        imgStr += listImgUrl.get(k);
                    } else {
                        imgStr += "," + listImgUrl.get(k);
                    }
                }
            }
            DiseaseBaseInfo diseaseBaseInfo =     new DiseaseBaseInfo();
            diseaseBaseInfo.setBHJBID(-1);
            diseaseBaseInfo.setGYDWID(MyApplication.spUtils.getString("dqgydwid"));
            diseaseBaseInfo.setLDMC(lxInfo.getLDMC());//路段名称
            diseaseBaseInfo.setLXID(lxInfo.getLDID());
            diseaseBaseInfo.setCJSJ(diseaseNewTimeTxt.getText().toString());
            diseaseBaseInfo.setDCLX(diseaseNewToExamineTypeTxt.getText().toString());
            diseaseBaseInfo.setZH(zhStr);
            diseaseBaseInfo.setWZFX(xsfxStr);
            diseaseBaseInfo.setBHLX(diseaseCustomEditItemBhlxTxtBtn.getText().toString());
            diseaseBaseInfo.setBHMC(diseaseCustomEditItemBhmcTxtBtn.getText().toString());
            diseaseBaseInfo.setSGFS(diseaseCustomEditItemSgfsTxtBtn.getText().toString());
            if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
                for (int k = 0; k < gcxmadapter.get().size(); k++) {
                    if (k == 0) {
                        GGXMMC = gcxmadapter.get().get(k).getXmmc();
                        SL = replaceNullString(gcxmadapter.get().get(k).getJsgs());
                        DW = replaceDwNull(gcxmadapter.get().get(k).getJldw());
                        GGXMID= gcxmadapter.get().get(k).getGcxmid();
                    } else {
                        GGXMMC +="," +gcxmadapter.get().get(k).getXmmc();
                        SL+= "," +replaceNullString(gcxmadapter.get().get(k).getJsgs());
                        DW+= "," +replaceDwNull(gcxmadapter.get().get(k).getJldw());
                        GGXMID+= "," +gcxmadapter.get().get(k).getGcxmid();
                    }
                }
            }
            diseaseBaseInfo.setGGXMID(GGXMID);
            diseaseBaseInfo.setGGXMMC(GGXMMC);
            diseaseBaseInfo.setSL(SL);
            diseaseBaseInfo.setDW(DW);
            diseaseBaseInfo.setTPDZ(imgStr);
            long resltSize = curingDao.addBingHaiBase(diseaseBaseInfo);
            if (resltSize == 1) {
                isSaveOrUpdateData = true;
                MyApplication.app.customToast("保存本地成功");
                finish();
            }
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
            diseaseNewBhcjSaveTxt.setClickable(true);
        }
    }
    public static String replaceNullString(String str) {
        if (str == "") {
            return "0";
        }
        return str;
    }
    public static String replaceNull(String str) {
        if (str == "0") {
            return "";
        }
        return str;
    }
    public static String replaceDwNull(String str) {
        if (str.equals("")||str==null) {
            return "棵";
        }
        return str;
    }
    private void initdate() {
        getlxResult(gydwid);
        getdclxResult();
        getbhlxResult();
        gcxmadapter = new ListAdapter(this,gcxmResult);
        addDdiseaseListView.setAdapter(gcxmadapter);
    }




    private void initPopWindow() {
        LxPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择路线", lxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseNewRoadLineTxt.setText(lxResult.get(position));
                lxInfo = lxResultdb.get(position);
            }
        });
        TimePop = new WheelDateAndTimeSelectPopupWindow(this, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormat.format(dateResult);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                diseaseNewTimeTxt.setText(selDataStr);
            }
        });
        DclxPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择调查类型", dclxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseNewToExamineTypeTxt.setText(dclxResult.get(position));
                dclxInfo = dclxResultdb.get(position);
            }
        });
        BhlxPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择病害类型", bhlxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseCustomEditItemBhlxTxtBtn.setText(bhlxResult.get(position));
                bhmc.setVisibility(View.VISIBLE);
                diseaseCustomEditItemBhmcTxtBtn.setText("");
                getbhmcResult();
            }
        });
        BhmcPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择病害名称", bhResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseCustomEditItemBhmcTxtBtn.setText(bhResult.get(position));
                diseaseCustomEditItemSgfsTxtBtn.setText("");
                sgfs.setVisibility(View.VISIBLE);
                getsgfsResult();


            }
        });
        SgfsPop= new DiseaeNewSelectObjectPopupWindow(this, "请选择施工方式", sgfsResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseCustomEditItemSgfsTxtBtn.setText(sgfsResult.get(position));
                getgcxmResult();
            }
        });
    }
    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }
    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }

    private void setQuanFuSelect() {
        if (directionSelInt != 3) {
            directionSelInt = 3;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
        }
    }
    private void getgcxmResult() {
        gcxmResult.clear();
        gcxmResultdb  = curingDao.queryGCXM(diseaseCustomEditItemSgfsTxtBtn.getText().toString(),diseaseCustomEditItemBhmcTxtBtn.getText().toString());
        for (int i = 0;i<gcxmResultdb.size();i++){
            ItemBeanInfo itembean = new ItemBeanInfo();
            itembean.setGcxmid(gcxmResultdb.get(i).getGCXMID());
            itembean.setXmmc(gcxmResultdb.get(i).getXMMC());
            itembean.setJsgs("");
            itembean.setJldw(gcxmResultdb.get(i).getJLDW());
            gcxmResult.add(itembean);
        }
       gcxmadapter.notifyDataSetChanged();



}

    private void getsgfsResult() {
        sgfsResultdb = curingDao.queryCZFA(diseaseCustomEditItemBhmcTxtBtn.getText().toString(),diseaseCustomEditItemBhlxTxtBtn.getText().toString());
        if (sgfsResultdb != null) {
           sgfsResult.clear();
            for (int i = 0; i < sgfsResultdb.size(); i++) {
                DisposalSchemeInfo typeInfo = sgfsResultdb.get(i);
                sgfsResult.add(typeInfo.getCZFAMC());
            }
            SgfsPop.notifityData();
        }
    }

    private void getbhmcResult() {
        bhmcResultdb = curingDao.queryBH(diseaseCustomEditItemBhlxTxtBtn.getText().toString());
        if (bhmcResultdb != null) {
            bhResult.clear();
            for (int i = 0; i < bhmcResultdb.size(); i++) {
                DisposalSchemeInfo typeInfo = bhmcResultdb.get(i);
                bhResult.add(typeInfo.getBHMC());
            }
            BhmcPop.notifityData();
        }
    }

    private void getbhlxResult() {
        bhlxResultdb = curingDao.queryAllBHLXInfo();
        if (bhlxResultdb != null) {
            if (bhlxResultdb != null) {
                bhlxResult.clear();
                for (int i = 0; i < bhlxResultdb.size(); i++) {
                    DisposalSchemeInfo typeInfo = bhlxResultdb.get(i);
                    bhlxResult.add(typeInfo.getBHLXMC());
                }
                BhlxPop.notifityData();
            }
        }
    }
    private void getdclxResult() {
        dclxResultdb = curingDao.queryAllInvestigation();
        if (dclxResultdb != null) {
            dclxResult.clear();
            for (int i = 0; i < dclxResultdb.size(); i++) {
                TypeOfInvestigation typeInfo = dclxResultdb.get(i);
                if (i == 0) {
                    dclxInfo = typeInfo;
                    diseaseNewToExamineTypeTxt.setText(dclxInfo.getDCMC());
                }
                dclxResult.add(typeInfo.getDCMC());
            }
            DclxPop.notifityData();
        }
    }



    private void getlxResult(String gydwid) {
        lxResultdb = curingDao.queryLdByGydwid(gydwid);
        if (lxResultdb != null) {
            lxResult.clear();
            for (int i = 0; i < lxResultdb.size(); i++) {
                RoadSectionInfo sectionInfo = lxResultdb.get(i);
                String ldstr = sectionInfo.getLDMC();
                String ldqdstr = sectionInfo.getQDZH();
                String ldzdstr = sectionInfo.getZDZH();
                String resultqdStr = Utils.getZHMCByZH(ldqdstr);
                String resultzdStr = Utils.getZHMCByZH(ldzdstr);
                String resultNameStr = ldstr + resultqdStr + "-" + resultzdStr;
                lxResult.add(resultNameStr);
                if (i == 0) {
                    lxInfo = sectionInfo;
                    diseaseNewRoadLineTxt.setText(resultNameStr);
                }
            }
            LxPop.notifityData();
        }
    }

    private void initview() {
        diseaseNewUnitNameTxt.setText(MyApplication.spUtils.getString("dlr"));
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        String currentDate = simpleDateFormat.format(calendar.getTime());
        diseaseNewTimeTxt.setText(currentDate);
        title.setText("病害采集");
        if (Utils.isNetworkAvailable(this)) {
            diseaseNewPileNumberLocationTxt.setVisibility(View.VISIBLE);
        } else {
            diseaseNewPileNumberLocationTxt.setVisibility(View.GONE);
        }
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = diseaseNewBhContentLayout.getChildCount();
        addPictureAdapter = new SendTopicPictureGridAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        mapAdapter.put(childPosition, addPictureAdapter);
    }

    public void showZheZhaoView() {
        if (!activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏遮罩view
     */
    public void hideZheZhaoView() {
        if (activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final SendTopicPictureGridAdapter addPictureAdapter = mapAdapter.get(position);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();

                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(NewdiseaseActivity.this, 480),
                                            Tooklkit.dip2px(NewdiseaseActivity.this, 480));
                                    if (bitmap != null) {
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {//拍照获取照片
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    final SendTopicPictureGridAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(NewdiseaseActivity.this, 480),
                                            Tooklkit.dip2px(NewdiseaseActivity.this, 480));
                                    if (bitmap != null) {
                                        Log.i("图片压缩成功", cameraPath + "地址" + imgPath);
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
    /********定位功能开始**********/
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听keytool -list -v -keystore C:\studio\Newiroad\newiroad.jks
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {//定位成功
                Double longitude = aMapLocation.getLongitude();//经度
                Double latitude = aMapLocation.getLatitude();//纬度
                Log.i("定位结果","经度"+String.valueOf(longitude)+" 纬度"+String.valueOf(latitude));
                mPresenter.getZH(lxInfo.getLXID(),String.valueOf(longitude),String.valueOf(latitude),gydwid);
            } else {//定位失败
                Log.i("定位失败","定位失败");
            }
            stopLocation();
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
//        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        curingDao.release();
    }
}
