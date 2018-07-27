package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.List;

import model.AdsCard;

/**
 * 个人主页 广告卡牌
 */
public class ListViewAdapter_AdsCard extends CommonAdapter<AdsCard> {


    public ListViewAdapter_AdsCard(Context context, List<AdsCard> list) {
        super(context, list, R.layout.list_item_ads_card);


    }


    @Override
    public void convert(ViewHolder holder, AdsCard adsCard) {
        try {
            if(adsCard!=null){
                holder.setImageURL(R.id.img1,"");
            }
        } catch (Exception ex) {

        }
    }
}
