package com.ysy15350.redpacket_fc.redpackage.open_treasurebox;

import android.content.Intent;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.redpacket_fc.authentication.login.LoginActivity;
import com.ysy15350.redpacket_fc.dialog.WholePointDialog;
import com.ysy15350.redpacket_fc.main_tabs.MainTab1Fragment;
import com.ysy15350.redpacket_fc.mine.wallet.detailed.DetailedListActivity;
import com.ysy15350.redpacket_fc.mine.wallet.withdraw_deposit.WithdrawDepositActivity;
import com.ysy15350.ysyutils.api.model.Response;
import com.ysy15350.ysyutils.api.model.ResponseHead;
import com.ysy15350.ysyutils.base.data.BaseData;
import com.ysy15350.ysyutils.base.mvp.MVPBaseActivity;
import com.ysy15350.ysyutils.common.CommFun;
import com.ysy15350.ysyutils.common.message.MessageBox;
import com.ysy15350.ysyutils.model.PageData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 打开宝箱
 */
@ContentView(R.layout.activity_openred)
public class OpenTreasureBoxActivity extends MVPBaseActivity<OpenTreasureBoxViewInterface, OpenTreasureBoxPresenter>
        implements OpenTreasureBoxViewInterface {



    @Override
    protected OpenTreasureBoxPresenter createPresenter() {
        // TODO Auto-generated method stub
        return new OpenTreasureBoxPresenter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFormHead("打开宝箱");


    }

    @Override
    public void loadData() {
        super.loadData();

        MessageBox.showWaitDialog(this,"数据加载中...");
        mPresenter.grabRedPacket(1);
    }



    private String  strprice;
    private String strimgurl;
    private String strcompany;

    @Override
    public void grabRedPacketCallback(boolean isCache, Response response) {
        try {

            hideWaitDialog();

            if (response != null) {
                ResponseHead responseHead = response.getHead();
                if (responseHead != null) {
                    int status = responseHead.getResponse_status();
                    String msg = responseHead.getResponse_msg();
                    if (status == 100) {
                        PageData pageData = response.getData(PageData.class);

                        if (pageData != null) {

                            double price = (double) pageData.get("price");
                            if (CommFun.notNullOrEmpty(price)) {
                                strprice = "¥"+price;
                                mHolder.setText(R.id.tv_redpackageaccont,price+"");
                            }

                            String imgurl = pageData.getString("imgurl");
                            if (CommFun.notNullOrEmpty(imgurl)) {
                                strimgurl = imgurl;
                                mHolder.setImageURL(R.id.img_pictures,strimgurl);
                            }

                            String company = pageData.getString("company");
                            if (CommFun.notNullOrEmpty(company)) {
                                strcompany = company;
                            }

                        }
                    }else {
                        showMsg(msg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回
     * @param view
     */
    @Event(value = R.id.img_wallet_back)
    private void img_wallet_backClick(View view) {

        finish();

    }
}
