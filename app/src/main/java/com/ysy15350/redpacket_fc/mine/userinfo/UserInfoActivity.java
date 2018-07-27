package com.ysy15350.redpacket_fc.mine.userinfo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.mine.setalipay.SetAlipayActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
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
import com.ysy15350.ysyutils.custom_view.dialog.DateDialog;
import com.ysy15350.ysyutils.model.PageData;
import com.ysy15350.ysyutils.model.SysUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


/**
 * 个人资料
 */
@ContentView(R.layout.activity_userinfo)
public class UserInfoActivity extends MVPBaseActivity<UserInfoViewInterface, UserInfoPresenter>
        implements UserInfoViewInterface {

    private static final String TAG = "UserInfoActivity";

    /**
     * 城市选择器
     */
    CityPickerView mCityPickerView = new CityPickerView();

    private TextView tv_time;

    /**
     * 性别
     */
    private int sex;

    /**
     * 出身年、月、日
     */
    private int birthdayYear, birthdayMonth, birthdayDay;

    /**
     * 出身日期
     */
    private String birthday;

    /**
     * 常驻地区
     */
    private StringBuilder habitualResidence;


    @Override
    protected UserInfoPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new UserInfoPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();

        setFormHead("个人资料");

        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        //mCityPickerView.init(this);
    }


    @Override
    public void loadData() {
        super.loadData();

        bindUserInfo(BaseData.getSysUser());
//        MessageBox.showWaitDialog(this, "数据加载中...");
//        mPresenter.userInfo();
    }

    @Override
    public void userInfoCallback(boolean isCache, Response response) {


        try {

            hideWaitDialog();


            if (response != null) {
                String jsonStr = JsonConvertor.toJson(response);
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {


                        SysUser sysUser = response.getData(SysUser.class);
                        if (sysUser != null) {

                            BaseData.setSysUser(sysUser);
                            bindUserInfo(sysUser);
                        }


                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUserInfoCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        loadData();
                    }
                    showMsg(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定个人资料
     *
     * @param sysUser
     */
    private void bindUserInfo(SysUser sysUser) {
        try {
            if (sysUser != null) {
                Log.d(TAG, "bindUserInfo: " + sysUser.getMobile());

                // 头像图片

                // 用户名称
                mHolder.setText(R.id.et_username, sysUser.getNickname());


                // 个性签名
                mHolder.setText(R.id.et_personalitySignature, sysUser.getPersonalitySignature());

                // 支付宝账号
                mHolder.setText(R.id.btn_setalipay, sysUser.getAlipayAccount());

                String sexStr = "";

                // 性别
                if (sysUser.getSex() == 1) {
                    sexStr = "男";
                } else if (sysUser.getSex() == 2) {
                    sexStr = "女";
                } else {
                    sexStr = "未设置";
                }

                mHolder.setText(R.id.tv_sex, sexStr);


                // 生日
                mHolder.setText(R.id.tv_birthday, sysUser.getBirthday());


                // 常驻地区
                mHolder.setText(R.id.tv_habitualResidence, sysUser.getHabitualResidence());

                // 手机号
                mHolder.setText(R.id.et_mobile, sysUser.getMobile());
            }
        } catch (Exception e) {
        }
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
                if (province != null) {
                    sb.append(province.getName() + " ");
                }

                if (city != null) {
                    sb.append(city.getName() + " ");
                }

                if (district != null) {
                    sb.append(district.getName());
                }

                habitualResidence = sb;
                // 常驻地区
                mHolder.setText(R.id.tv_habitualResidence, sb);

            }

            @Override
            public void onCancel() {
                MessageBox.show("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }

    /**
     * 清空昵称
     *
     * @param view
     */
    @Event(value = R.id.imgbtn_fork)
    private void imgbtn_forkClick(View view) {

        mHolder.setText(R.id.et_username, "");

    }

    /**
     * 设置支付宝
     *
     * @param view
     */
    @Event(value = R.id.btn_setalipay)
    private void btn_setalipayClick(View view) {

        if (BaseData.isLogin())//如果需要登录
            startActivity(new Intent(this, SetAlipayActivity.class));
        else
            startActivity(new Intent(this, LoginActivity.class));

    }

    /**
     * 弹框布局
     */
    private View mPopView;
    private PopupWindow mPopupWindow;
    RelativeLayout rl_pop_main;
    LinearLayout ll_boy, ll_gril;

    /**
     * 性别
     *
     * @param view
     */
    @Event(value = R.id.tv_sex)
    private void tv_sexClick(View view) {
        try {
            mPopView = LayoutInflater.from(this).inflate(R.layout.pop_sex,
                    null);

            if (mPopView == null)
                return;

            // 男
            ll_boy = (LinearLayout) mPopView.findViewById(R.id.ll_boy);
            ll_boy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHolder.setText(R.id.tv_sex, "男");
                    sex = 1;
                    mPopupWindow.dismiss();
                }
            });

            // 女
            ll_gril = (LinearLayout) mPopView.findViewById(R.id.ll_girl);
            ll_gril.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHolder.setText(R.id.tv_sex, "女");
                    sex = 2;
                    mPopupWindow.dismiss();
                }
            });


            rl_pop_main = (RelativeLayout) mPopView.findViewById(R.id.rl_pop_main);
            rl_pop_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopupWindow.dismiss();
                }
            });

            mPopupWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);

//            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color
//                    .parseColor("#b0000000"))); // 有背景
            mPopupWindow.setBackgroundDrawable(new ColorDrawable()); // 有背景  无颜色

            //设置显示PopupWindow的位置位于View的左下方，x,y表示坐标偏移量
            mPopupWindow.showAsDropDown(mHolder.getView(R.id.tv_sex), 0, 0);

//            // 表示弹出窗口以ll_NavigateHome组件为参考，位于左侧，偏移0。
//            mPopupWindow.showAtLocation(ll_NavigateHome, Gravity.CENTER, 0, 0);
            mPopupWindow.setAnimationStyle(R.style.app_pop_4);//  设置动画样式
//            mPopupWindow.setOutsideTouchable(false);//设置点击外面不可以消失~注意该效果在设置背景的情况下是无效的
            mPopupWindow.setOutsideTouchable(true);// 设置点击外面可以消失~注意该效果在设置背景的情况下是无效的
            mPopupWindow.setFocusable(true);//  是否具有焦点能力
            mPopupWindow.update();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 出身日期
     *
     * @param view
     */
    @Event(value = R.id.tv_birthday)
    private void tv_birthdayClick(View view) {

        DateDialog dateDialog = new DateDialog();
        dateDialog.show(getFragmentManager(), "");
        dateDialog.setDateListener(new DateDialog.DateListener() {

            @Override
            public void onDateSet(int year, int month, int dayOfMonth, String dateStr) {
                mHolder.setText(R.id.tv_birthday, dateStr);
                birthday = dateStr;
                birthdayYear = year;
                birthdayMonth = month;
                birthdayDay = dayOfMonth;
            }

        });
    }

    /**
     * 常驻地区
     *
     * @param view
     */
    @Event(value = R.id.tv_habitualResidence)
    private void tv_habitualResidenceClick(View view) {

        // 弹出选择器
        wheel();
    }

    /**
     * 保存用户信息
     *
     * @param view
     */
    @Event(value = R.id.btn_preservation)
    private void btn_preservationClick(View view) {

        SysUser sysUser = new SysUser();

        // 头像图片

        // 用户名称
        sysUser.setNickname(mHolder.getViewText(R.id.et_username));

        // 个性签名
        sysUser.setPersonalitySignature(mHolder.getViewText(R.id.et_personalitySignature));

        // 支付宝账号
        if (!mHolder.getViewText(R.id.btn_setalipay).equals("去设置")) {
            sysUser.setAlipayAccount(mHolder.getViewText(R.id.btn_setalipay));
        }

        // 性别
        sysUser.setSex(CommFun.toInt32(sex, -1));

        // 生日
        sysUser.setBirthdayYear(CommFun.toInt32(birthdayYear, -1));
        sysUser.setBirthdayMonth(CommFun.toInt32(birthdayMonth, -1));
        sysUser.setBirthdayDay(CommFun.toInt32(birthdayDay, -1));
        sysUser.setBirthday(birthday);


        // 常驻地区
        sysUser.setHabitualResidence(mHolder.getViewText(R.id.tv_habitualResidence));

        // 手机号
        String phone = mHolder.getViewText(R.id.et_mobile);
        if (CommFun.isNullOrEmpty(phone)) {
            MessageBox.show("请输入您的手机号");
            return;
        }
        sysUser.setMobile(phone);

        MessageBox.showWaitDialog(this, "数据保存中...");
        mPresenter.saveUserInfo(sysUser);

    }


}
