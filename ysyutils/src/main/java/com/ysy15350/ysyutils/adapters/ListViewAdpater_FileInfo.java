package com.ysy15350.ysyutils.adapters;

import android.content.Context;

import com.ysy15350.ysyutils.R;
import com.ysy15350.ysyutils.adapters.base.CommonAdapter;
import com.ysy15350.ysyutils.common.ViewHolder;
import com.ysy15350.ysyutils.model.FileInfo;

import java.util.List;


/**
 * 文件列表
 *
 * @author yangshiyou
 */
public class ListViewAdpater_FileInfo extends CommonAdapter<FileInfo> {

    public ListViewAdpater_FileInfo(Context context, List<FileInfo> list) {
        super(context, list, R.layout.list_item_file_info);

    }

    @Override
    public void convert(ViewHolder holder, FileInfo t) {
        if (t != null) {

//            tv_new
            //holder.setText(R.id.tv_title, t.getName()).setText(R.id.tv_path, t.getPath()).setText(R.id.tv_length, t.getLength() + "");

        }

    }

}
