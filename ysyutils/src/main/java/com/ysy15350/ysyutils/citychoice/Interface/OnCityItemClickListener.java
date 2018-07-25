package com.ysy15350.ysyutils.citychoice.Interface;


import com.ysy15350.ysyutils.citychoice.bean.CityBean;
import com.ysy15350.ysyutils.citychoice.bean.DistrictBean;
import com.ysy15350.ysyutils.citychoice.bean.ProvinceBean;

/**
 * 作者：liji on 2017/11/16 10:06
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public abstract class OnCityItemClickListener {
    
    /**
     * 当选择省市区三级选择器时，需要覆盖此方法
     * @param province
     * @param city
     * @param district
     */
    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
        
    }
    
    /**
     * 取消
     */
    public void onCancel() {
        
    }
}
