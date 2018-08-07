package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.List;

import model.AdsCard;
import model.order.OrderListInfo;

/**
 * 钱包明细
 */
public class ListViewAdapter_Order extends CommonAdapter<OrderListInfo> {


    public ListViewAdapter_Order(Context context, List<OrderListInfo> list) {
        super(context, list, R.layout.list_item_order);
    }


    @Override
    public void convert(ViewHolder holder, OrderListInfo orderListInfo) {
        if (orderListInfo != null) {
            holder
                    .setText(R.id.tv_ordertitle, orderListInfo.getTitle())//订单标题：充值、提现
                    .setText(R.id.tv_ordertime, orderListInfo.getCreattimeStr())//
                    .setText(R.id.tv_ordercontent, orderListInfo.getContent());//金额
        }
    }
}
