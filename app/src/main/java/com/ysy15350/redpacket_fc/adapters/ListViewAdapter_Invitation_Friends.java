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
                if(mailList.getStatus() == 1){
                    holder.getView(R.id.imgbtn_circular).setEnabled(false);
                }else {
                    holder.getView(R.id.imgbtn_circular).setEnabled(true);
                }
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
