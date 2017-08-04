package com.example.mythreeview.autoscrollviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * 轮播图片的适配器
 *
 * @author yao
 */
public class ImagePagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> albumImgUrl;
    private OnPageClickListener onPageClickListener;
    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
    private OnShowImage onShowImage;

    public ImagePagerAdapter(Context context, List<String> albumImgUrl,OnShowImage onShowImage) {
        super();
        this.context = context;
        this.albumImgUrl = albumImgUrl;
        this.onShowImage = onShowImage;
    }

    public void setNewData(List<String> albumImgUrl) {
        this.albumImgUrl.clear();
        this.albumImgUrl.addAll(albumImgUrl);
        notifyDataSetChanged();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    @Override
    public int getCount() {//支持无限轮播
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return !(arg1 == null || arg0 == null) && arg0 == arg1;
    }

    @Override
    public ImageView instantiateItem(ViewGroup container, final int position) {
        if (albumImgUrl == null || albumImgUrl.size() == 0) {
            return null;
        }
        final ImageView iv = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(scaleType);
        String imgUrl = albumImgUrl.get(position % (albumImgUrl==null?1:albumImgUrl.size()));
        // LogUtils.e("imgurl", imgUrl==null?"null":imgUrl);
        if (imgUrl != null && imgUrl.length() > 0&&onShowImage!=null) {
            onShowImage.showImage(context,iv,imgUrl);
        }
        container.addView(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPageClickListener != null) {
                    onPageClickListener.onPageClick(position % (albumImgUrl == null ? 1 : albumImgUrl.size()));
                }
            }
        });
        return iv;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }

    public interface OnPageClickListener {
        void onPageClick(int position);
    }

    public interface OnShowImage {
        void showImage(Context context,ImageView imageView ,String imgUrl);
    }

}
