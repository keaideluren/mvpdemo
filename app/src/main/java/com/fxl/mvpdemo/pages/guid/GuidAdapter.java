package com.fxl.mvpdemo.pages.guid;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hwangjr.rxbus.RxBus;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.RxBusTags;
import com.fxl.mvpdemo.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 * ����ҳ
 */

public class GuidAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> guidImageList;

    public GuidAdapter(Context mContext, List<Integer> guidImageList) {
        this.mContext = mContext;
        this.guidImageList = guidImageList;
    }

    @Override
    public int getCount() {
        return guidImageList == null ? 0 : guidImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_guid, container, false);
        //小米的坑，不使用Glide设置大图片会不显示
        ImageLoaderUtil.setPic(mContext, guidImageList.get(position), (ImageView) item.findViewById(R.id.iv_content_guid));
        //�RxBus发送到GuidPreserter
        if (position == getCount() - 1 && position > 0) {
            item.findViewById(R.id.iv_enter_guid).setVisibility(View.VISIBLE);
        } else {
            item.findViewById(R.id.iv_enter_guid).setVisibility(View.GONE);
        }
        item.findViewById(R.id.iv_enter_guid).setOnClickListener(v -> RxBus.get().post(RxBusTags.GUID, "点击引导页最后一页的欢迎光临"));
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
