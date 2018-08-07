package com.ysy15350.redpacket_fc.others.instructions;

import android.view.View;

import com.ysy15350.redpacket_fc.R;
import com.ysy15350.ysyutils.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * 使用说明
 */
@ContentView(R.layout.activity_instructions)
public class InstructionsActivity  extends BaseActivity{


    @Override
    public void initView() {
        super.initView();
        setFormHead("方寸红包使用说明");
    }

    /**
     * 收红包
     * @param view
     */
    @Event(value = R.id.ll_instructions_item1)
    private void ll_instructions_item1Click(View view) {

    }

    /**
     * 发红包
     * @param view
     */
    @Event(value = R.id.ll_instructions_item2)
    private void ll_instructions_item2Click(View view) {

    }

    /**
     * 抢红包
     * @param view
     */
    @Event(value = R.id.ll_instructions_item3)
    private void ll_instructions_item3Click(View view) {

    }

    /**
     * 盟主
     * @param view
     */
    @Event(value = R.id.ll_instructions_item4)
    private void ll_instructions_item4Click(View view) {

    }

    /**
     * FCB
     * @param view
     */
    @Event(value = R.id.ll_instructions_item5)
    private void ll_instructions_item5Click(View view) {

    }
}
