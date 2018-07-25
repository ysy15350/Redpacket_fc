package com.ysy15350.ysyutils.citychoice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.citychoice.Interface.OnCityItemClickListener;
import com.ysy15350.ysyutils.citychoice.bean.CityBean;
import com.ysy15350.ysyutils.citychoice.bean.DistrictBean;
import com.ysy15350.ysyutils.citychoice.bean.ProvinceBean;
import com.ysy15350.ysyutils.citychoice.citywheel.CityConfig;
import com.ysy15350.ysyutils.citychoice.style.citypickerview.CityPickerView;
import com.ysy15350.ysyutils.common.message.MessageBox;

public class CitypickerWheelActivity extends AppCompatActivity {
    
    EditText mProEt;
    
    EditText mCityEt;
    
    EditText mAreaEt;
    
    EditText mProVisibleCountEt;
    
    CheckBox mProCyclicCk;
    
    CheckBox mCityCyclicCk;
    
    CheckBox mAreaCyclicCk;
    
    CheckBox mHalfBgCk;
    
    TextView mResetSettingTv;
    
    TextView mSubmitTv;
    
    TextView mResultTv;
    
    TextView mOneTv;
    
    TextView mTwoTv;
    
    TextView mThreeTv;
    
    private int visibleItems = 5;
    
    private boolean isProvinceCyclic = true;
    
    private boolean isCityCyclic = true;
    
    private boolean isDistrictCyclic = true;
    
    private boolean isShowBg = true;
    
    private String defaultProvinceName = "江苏";
    
    private String defaultCityName = "常州";
    
    private String defaultDistrict = "新北区";
    
    public CityConfig.WheelType mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
    
    CityPickerView mCityPickerView = new CityPickerView();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_wheel);
        findView();
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mCityPickerView.init(this);
        
    }
    
    private void findView() {
        
        mResultTv = (TextView) findViewById(R.id.result_tv);
        mProEt = (EditText) findViewById(R.id.pro_et);
        mCityEt = (EditText) findViewById(R.id.city_et);
        mAreaEt = (EditText) findViewById(R.id.area_et);
        mProVisibleCountEt = (EditText) findViewById(R.id.pro_visible_count_et);
        mProCyclicCk = (CheckBox) findViewById(R.id.pro_cyclic_ck);
        mCityCyclicCk = (CheckBox) findViewById(R.id.city_cyclic_ck);
        mAreaCyclicCk = (CheckBox) findViewById(R.id.area_cyclic_ck);
        mHalfBgCk = (CheckBox) findViewById(R.id.half_bg_ck);
        mResetSettingTv = (TextView) findViewById(R.id.reset_setting_tv);
        mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        mOneTv = (TextView) findViewById(R.id.one_tv);
        mTwoTv = (TextView) findViewById(R.id.two_tv);
        mThreeTv = (TextView) findViewById(R.id.three_tv);
        
        //提交
        mSubmitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheel();
            }
        });
        
        //重置属性
        mResetSettingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        
        //一级联动，只显示省份，不显示市和区
        mOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO;
                setWheelType(mWheelType);
            }
        });
        
        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY;
                setWheelType(mWheelType);
            }
        });
        
        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
                setWheelType(mWheelType);
            }
        });
        
        //省份是否循环显示
        mProCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isProvinceCyclic = isChecked;
            }
        });
        
        //市是否循环显示
        mCityCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCityCyclic = isChecked;
            }
        });
        
        //区是否循环显示
        mAreaCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDistrictCyclic = isChecked;
            }
        });
        
        //区是否循环显示
        mHalfBgCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowBg = isChecked;
            }
        });
        
        setWheelType(mWheelType);
    }
    
    /**
     * 重置属性
     */
    private void reset() {
        visibleItems = 5;
        isProvinceCyclic = true;
        isCityCyclic = true;
        isDistrictCyclic = true;
        isShowBg = true;
        
        defaultProvinceName = "江苏";
        defaultCityName = "常州";
        defaultDistrict = "新北区";
        
        mWheelType = CityConfig.WheelType.PRO_CITY_DIS;
        
        setWheelType(mWheelType);
        
        mProCyclicCk.setChecked(true);
        mCityCyclicCk.setChecked(true);
        mAreaCyclicCk.setChecked(true);
        
        mProEt.setText("" + defaultProvinceName);
        mCityEt.setText("" + defaultCityName);
        mAreaEt.setText("" + defaultDistrict);
        mProVisibleCountEt.setText("" + visibleItems);
        
        mHalfBgCk.setChecked(isShowBg);
        mProCyclicCk.setChecked(isProvinceCyclic);
        mAreaCyclicCk.setChecked(isDistrictCyclic);
        mCityCyclicCk.setChecked(isCityCyclic);
        
        setWheelType(mWheelType);
        
    }
    
    /**
     *
     * @param wheelType
     */
    private void setWheelType(CityConfig.WheelType wheelType) {
        if (wheelType == CityConfig.WheelType.PRO) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#ffffff"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        }
        else if (wheelType == CityConfig.WheelType.PRO_CITY) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));
        }
        else {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));
        }
    }
    
    /**
     * 弹出选择器
     */
    private void wheel() {
        
        defaultProvinceName = mProEt.getText().toString();
        defaultCityName = mCityEt.getText().toString();
        defaultDistrict = mAreaEt.getText().toString();
        
        visibleItems = (Integer.parseInt(mProVisibleCountEt.getText().toString()));
        
        CityConfig cityConfig = new CityConfig.Builder().title("选择城市")//标题
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
                
                mResultTv.setText("" + sb.toString());
                
            }
            
            @Override
            public void onCancel() {
                MessageBox.show("已取消");
            }
        });
        mCityPickerView.showCityPicker();
    }
}
