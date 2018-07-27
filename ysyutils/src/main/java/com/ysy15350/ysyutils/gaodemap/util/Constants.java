package com.ysy15350.ysyutils.gaodemap.util;

import com.amap.api.maps.model.LatLng;

public class Constants {

	public static final int ERROR = 1001;// 网络异常
	public static final int ROUTE_START_SEARCH = 2000;
	public static final int ROUTE_END_SEARCH = 2001;
	public static final int ROUTE_BUS_RESULT = 2002;// 路径规划中公交模式
	public static final int ROUTE_DRIVING_RESULT = 2003;// 路径规划中驾车模式
	public static final int ROUTE_WALK_RESULT = 2004;// 路径规划中步行模式
	public static final int ROUTE_NO_RESULT = 2005;// 路径规划没有搜索到结果

	public static final int GEOCODER_RESULT = 3000;// 地理编码或者逆地理编码成功
	public static final int GEOCODER_NO_RESULT = 3001;// 地理编码或者逆地理编码没有数据

	public static final int POISEARCH = 4000;// poi搜索到结果
	public static final int POISEARCH_NO_RESULT = 4001;// poi没有搜索到结果
	public static final int POISEARCH_NEXT = 5000;// poi搜索下一页

	public static final int BUSLINE_LINE_RESULT = 6001;// 公交线路查询
	public static final int BUSLINE_id_RESULT = 6002;// 公交id查询
	public static final int BUSLINE_NO_RESULT = 6003;// 异常情况

	public static final LatLng BEIJING = new LatLng(39.90403, 116.407525);// 北京市经纬度
	public static final LatLng ZHONGGUANCUN = new LatLng(39.983456, 116.3154950);// 北京市中关村经纬度
	public static final LatLng SHANGHAI = new LatLng(31.238068, 121.501654);// 上海市经纬度
	public static final LatLng FANGHENG = new LatLng(39.989614, 116.481763);// 方恒国际中心经纬度
	public static final LatLng CHENGDU = new LatLng(30.679879, 104.064855);// 成都市经纬度
	public static final LatLng XIAN = new LatLng(34.341568, 108.940174);// 西安市经纬度
	public static final LatLng ZHENGZHOU = new LatLng(34.7466, 113.625367);// 郑州市经纬度

	public static final LatLng LONGKIN = new LatLng(29.646661,106.566095); // 隆金宝坐标
	public static final LatLng LATLNG1 = new LatLng(29.649160,106.566310); // 重庆市渝北区留云路
	public static final LatLng LATLNG2 = new LatLng(29.645766,106.558371); // 重庆市渝北区栖霞路8号
	public static final LatLng LATLNG3 = new LatLng(29.642558,106.566439); // 重庆市渝北区金渝大道68号
	public static final LatLng LATLNG4 = new LatLng(29.645542,106.568499); // 重庆市渝北区龙渊街1号
	public static final LatLng LATLNG5 = new LatLng(29.578981,106.308410); // 重庆市沙坪坝区大学城南二路
	public static final LatLng LATLNG6 = new LatLng(29.578234,106.311457); // 重庆市沙坪坝区康城路
	public static final LatLng LATLNG7 = new LatLng(29.586930,106.308346); // 重庆市沙坪坝区康城路


}
