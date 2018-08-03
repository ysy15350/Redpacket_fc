package api.impl;

import com.ysy15350.ysyutils.Ysy;
import com.ysy15350.ysyutils.api.ApiCallBack;
import com.ysy15350.ysyutils.api.model.RequestOptions;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.CommFunAndroid;
import com.ysy15350.ysyutils.model.SysUser;

import api.AccountAPi;
import api.UserApi;
import model.withdraw.Withdraw;

/**
 * Created by yangshiyou on 2017/12/15.
 */

public class AccounApiImpl implements AccountAPi {

    private static final String moduleName = "api/account/";

    @Override
    public void withdraw(Withdraw withdraw, ApiCallBack callBack) {
        try {

            RequestOptions.Builder builder = new RequestOptions.Builder();
            builder.setRequestMapping(moduleName + "withdraw");


            // 提现金额
            if (CommFun.notNullOrEmpty(withdraw.getPrice())) {
                builder.addBodyParameter("price", withdraw.getPrice());
            }

            // 真实姓名
            if (CommFun.notNullOrEmpty(withdraw.getRealname())) {
                builder.addBodyParameter("realname", withdraw.getRealname());
            }

            // 支付宝账号
            if (CommFun.notNullOrEmpty(withdraw.getAlipayAccount())) {
                builder.addBodyParameter("alipayAccount", withdraw.getAlipayAccount());
            }

            RequestOptions requestOptions = builder.build();

            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invite(String mobile, ApiCallBack callBack) {

        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "invite")
                .addBodyParameter("mobile", mobile)
                .build();

        Ysy.http().requestPost(requestOptions, callBack);
    }

    @Override
    public void getInviteList(String mobile,ApiCallBack callBack) {
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setRequestMapping(moduleName + "getInviteList")
                .addBodyParameter("mobile", mobile)
                .build();

        Ysy.http().requestPost(requestOptions, callBack);
    }

    @Override
    public void buildCityOwnerPayOrder(double price, int type, ApiCallBack callBack) {
        try {
            RequestOptions requestOptions = new RequestOptions.Builder()
                    .setRequestMapping(moduleName + "buildCityOwnerPayOrder")
                    .addBodyParameter("price",price) // 金额
                    .addBodyParameter("type",type) // 支付类型
                    .build();


            Ysy.http().requestPost(requestOptions, callBack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
