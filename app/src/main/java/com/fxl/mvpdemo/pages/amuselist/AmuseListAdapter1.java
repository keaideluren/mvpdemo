package com.fxl.mvpdemo.pages.amuselist;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.bean.Amuse;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 *
 */

public class AmuseListAdapter1 extends BaseQuickAdapter<Amuse, BaseViewHolder> {
    public AmuseListAdapter1(@Nullable List<Amuse> data) {
        super(R.layout.item_guid, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Amuse amuse) {
    }

}
