package com.ysy15350.redpacket_fc.mine.cityowner.cityowner_transaction;

import android.content.Intent;
import android.util.ArrayMap;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.share.ShareActivity;
import com.ysy15350.redpacket_fc.others.SettingActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.citychoice.CitypickerWheelActivity;
import com.ysy15350.ysyutils.citychoice.Interface.OnCityItemClickListener;
import com.ysy15350.ysyutils.citychoice.bean.CityBean;
import com.ysy15350.ysyutils.citychoice.bean.DistrictBean;
import com.ysy15350.ysyutils.citychoice.bean.ProvinceBean;
import com.ysy15350.ysyutils.citychoice.citywheel.CityConfig;
import com.ysy15350.ysyutils.citychoice.style.citypickerview.CityPickerView;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.SystemModels;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.common.string.JsonConvertor;
import com.ysy15350.ysyutils.custom_view.dialog.AgreementDialog;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import alipay.PayDemoActivity;
import model.cityowner.CityOwnerInfo;
import model.protocol.ProtoclInfo;

/**
 * 城主交易界面
 */
@ContentView(R.layout.activity_cityowner_transaction)
public class CityOwnerTransactionActivity extends MVPBaseActivity<CityOwnerTransactionViewInterface, CityOwnerTransactionPresenter>
        implements CityOwnerTransactionViewInterface {


    /**
     * 用户协议
     */
    private String userProtocl = "";

    /**
     * 地区编号
     */
    private int code = 0;

    /**
     * 城市选择器
     */
    CityPickerView mCityPickerView = new CityPickerView();

    @Override
    protected CityOwnerTransactionPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new CityOwnerTransactionPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead(SystemModels.locationInfo.getDistrict());
        setMenuText("规则");

        code = CommFun.toInt32(SystemModels.locationInfo.getAdCode(), 0);

    }

    @Override
    public void initData() {
        super.initData();

        MessageBox.showWaitDialog(this, "数据加载中...");

        // 城主协议
        mPresenter.getProtocol();
        // 城主信息
        mPresenter.getCityOwner(code);
    }

    @Override
    public void bindProtocolCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();


            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {


                        ProtoclInfo protoclInfo = response.getData(ProtoclInfo.class);
                        if (protoclInfo != null) {
                            if (!protoclInfo.getProtocol().equals("")) {
                                userProtocl = protoclInfo.getProtocol();
                                userProtocl = userProtocl.replace("\\n", "\n");
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCityOwnerCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        CityOwnerInfo cityOwnerInfo = response.getData(CityOwnerInfo.class);
                        if (cityOwnerInfo != null) {
                            bindCityOwener(cityOwnerInfo);
                        }
                    } else {
                        showMsg(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定城主信息
     */
    private void bindCityOwener(CityOwnerInfo cityOwnerInfo) {

        String region = "";// 地区
        String cityUserneme = ""; // 城主姓名
        String strprice = "";

        if (cityOwnerInfo != null) {
            if (CommFun.notNullOrEmpty(cityOwnerInfo.getProvence())
                    && CommFun.notNullOrEmpty(cityOwnerInfo.getCity())
                    && CommFun.notNullOrEmpty(cityOwnerInfo.getDistrict())
                    ) {
                region = cityOwnerInfo.getProvence() + cityOwnerInfo.getCity() + cityOwnerInfo.getDistrict();
            }

            if (cityOwnerInfo.getSysUser() != null) {
                cityUserneme = cityOwnerInfo.getSysUser().getNickname();
            }
            strprice = "¥" + cityOwnerInfo.getPrice();
        }

        // 头像

        // 地区
        mHolder.setText(R.id.tv_region, region);

        // 城主姓名
        mHolder.setText(R.id.tv_cityowneruser, cityUserneme);

        // 城主价格
        mHolder.setText(R.id.tv_cityowneraccount, strprice);
    }


    /**
     * 我要成为城主
     *
     * @param view
     */
    @Event(value = R.id.btn_cityowner)
    private void btn_cityownerClick(View view) {
        String title = "方寸红包城主协议";
        String content = userProtocl;
        AgreementDialog agreementDialog = new AgreementDialog(this, title, content, "同意", "拒绝", 0);
        agreementDialog.setDialogListener(new AgreementDialog.DialogListener() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
//                startActivity(new Intent(CityOwnerTransactionActivity.this, PayDemoActivity.class));
                mPresenter.buyCityOwner(code);
            }
        });

        agreementDialog.show();
    }

    @Override
    public void buyCityOwnerCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    CityOwnerInfo cityOwnerInfo = response.getData(CityOwnerInfo.class);
                    if (cityOwnerInfo != null) {
                        double price = cityOwnerInfo.getPrice();
                        mPresenter.buildCityOwnerPayOrder(price, 1);
                    }
                    if (status == 100) {
//                        CityOwnerInfo cityOwnerInfo = response.getData(CityOwnerInfo.class);
//                        if (cityOwnerInfo != null) {
//                            double price =  cityOwnerInfo.getPrice();
//                            mPresenter.alipay(price+"",this);
//                        }
                    } else {
                        showMsg(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildCityOwnerPayOrderCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();


            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        ArrayMap<String, String> arrayMap = response.getData(new TypeToken<ArrayMap<String, String>>() {
                        }.getType());
                        if (arrayMap != null) {

                            String type = arrayMap.get("type");

                            if (CommFun.isEquals("1", type)) {

                                String params = arrayMap.get("params");

                                if (!CommFun.isNullOrEmpty(params)) {

                                    mPresenter.alipay(params, this);//调用支付宝SDK

                                }
                            }
                        }
                    } else
                        showMsg(msg);

                }
            } else {
                showMsg("系统错误");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAliPayResult(String msg) {

    }

    /**
     * 其他领地
     *
     * @param view
     */
    @Event(value = R.id.ll_othercity11)
    private void ll_othercity11Click(View view) {
        wheel();
    }

    /**
     * 弹出选择器
     */
    private void wheel() {

        mCityPickerView.init(this);

        CityConfig cityConfig = new CityConfig.Builder().title("")//标题
                .province(SystemModels.locationInfo.getProvince())
                .city(SystemModels.locationInfo.getCity())
                .district(SystemModels.locationInfo.getDistrict())
                .build();

        mCityPickerView.setConfig(cityConfig);
        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                StringBuilder sb = new StringBuilder();
                sb.append("选择的结果：\n");
                if (province != null) {
                    sb.append(province.getName() + " " + province.getId() + "\n");
                }

                if (city != null) {
                    sb.append(city.getName() + " " + city.getId() + ("\n"));
                }

                if (district != null) {
                    sb.append(district.getName() + " " + district.getId() + ("\n"));
                }

                mPresenter.getCityOwner(CommFun.toInt32(district.getId(), 0));

            }

            @Override
            public void onCancel() {
                MessageBox.show("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }
}
