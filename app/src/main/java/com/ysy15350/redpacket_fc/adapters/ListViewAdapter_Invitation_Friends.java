package com.ysy15350.redpacket_fc.adapters;

import android.content.Context;
import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import model.follow.FollowListInfo;
import model.invitation.MailList;

/**
 * 邀请好友
 */
public class ListViewAdapter_Invitation_Friends extends CommonAdapter<MailList> {


    List<MailList> mailLists = new ArrayList<>();


    public ListViewAdapter_Invitation_Friends(Context context, List<MailList> list) {
        super(context, list, R.layout.list_item_maillist);


    }


    @Override
    public void convert(final ViewHolder holder, final MailList mailList) {
        try {
            if(mailList !=null){
                String name  = mailList.getName();
                holder.setText(R.id.tv_name,mailList.getName());

                // 是否已邀请
                if(mailList.isSelect()){
                    holder.setText(R.id.tv_invita,"已邀请");
                    holder.getView(R.id.ll_btn1).setEnabled(false);
                    holder.setVisibility_GONE(R.id.imgbtn_circular);
                }else {
                    holder.setText(R.id.tv_invita,"立即邀请");
                    holder.getView(R.id.ll_btn1).setEnabled(true);
                    holder.setVisibility_VISIBLE(R.id.imgbtn_circular);
                }

                // 选中状态
                boolean isStatus = true;
                if(mailList.getStatus() == 1){
                    isStatus = false;
                }else {
                    isStatus = true;
                }
                holder.getView(R.id.imgbtn_circular).setEnabled(isStatus);

            }
        } catch (Exception ex) {

        }

        final View viewcircular = holder.getView(R.id.imgbtn_circular);


        holder.getView(R.id.llbtn_circular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewcircular.setEnabled(!viewcircular.isEnabled());
                boolean b = viewcircular.isEnabled();
                if(b){
                    mailList.setStatus(0);
                }else {
                    mailList.setStatus(1);
                }
            }
        });
        mailLists.add(mailList);
        if(MyMailListsListener != null){
            MyMailListsListener.onFinalMailLists(mailLists);
        }
    }

    private MailListsListener MyMailListsListener;

    public interface MailListsListener {
        void onFinalMailLists(List<MailList> mailLists);
    }

    public void setMailListsListener(MailListsListener mailListsListeners){
        if(mailListsListeners != null){
            MyMailListsListener = mailListsListeners;
        }
    }
}
