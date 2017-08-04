package com.example.mythreeview.autoscrollviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * 图片轮播
 *
 * @author yao
 */
public class SlideAutoLoopView extends AutoScrollViewPager {
    Context mContext;
    /**
     * 定义FlowIndicator:图片指示器view
     */
    FlowIndicator mFlowIndicator;
    /**
     * 轮播图片的适配器
     */
    ImagePagerAdapter mAdapter;

    /**
     * 图片轮播间隔时间
     */
    int mDuration = 4000;
    /**
     * 相册的图片下载地址数组
     */
    List<String> mAlbumImgUrl;
    List<Slide> mSlideList;
    /**
     * 滑动速度
     */
    private int speed = 300;

    private ImagePagerAdapter.OnPageClickListener onPageClickListener;
    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

    public SlideAutoLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        setCurrentItem(Integer.MAX_VALUE / 2);
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mContext, speed);
            mScroller.set(this, scroller);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public void setOnPageClickListener(ImagePagerAdapter.OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }

    /**
     * 监听ViewPager页面改变
     */
    private void setOnPageChangeListener() {
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //设置指示器中实心圆的切换
                if (mAlbumImgUrl != null && mAlbumImgUrl.size() > 0) {
                    mFlowIndicator.setFocus(position % mAlbumImgUrl.size());
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 带跳转链接的
     *
     * @param flowIndicator 下面的点点
     * @param albumImgUrl   图片，网页地址
     * @param title         跳转的网页Activity的标题
     */
    public void setPlayLoop(FlowIndicator flowIndicator, final List<Slide> albumImgUrl
        , final String title, boolean clickable, ImagePagerAdapter.OnShowImage onShowImage) {//最后一个参数，因为使用了这个方法的也可能不跳转，运营的原因
        if (albumImgUrl == null) {
            return;
        }
        mAlbumImgUrl = new ArrayList<>();
        this.mSlideList = new ArrayList<>();
        this.mSlideList.addAll(albumImgUrl);
        for (Slide s : albumImgUrl) {
            mAlbumImgUrl.add(s.getSlideImg());
        }
        mFlowIndicator = flowIndicator;
        mFlowIndicator.setCount(albumImgUrl.size() == 0 ? 1 : albumImgUrl.size());
        mFlowIndicator.setFocus(0);
        mAdapter = new ImagePagerAdapter(mContext, mAlbumImgUrl,onShowImage);
        mAdapter.setScaleType(scaleType);
        setAdapter(mAdapter);
        setOnPageChangeListener();
        if (clickable) {
            mAdapter.setOnPageClickListener(new ImagePagerAdapter.OnPageClickListener() {
                @Override
                public void onPageClick(int position) {
                    if (onPageClickListener == null) {
                        if (!TextUtils.isEmpty(albumImgUrl.get(position).getSlideUrl())) {
//                            Intent intent = new Intent(getContext(), WebActivity.class);
//                            intent.putExtra("title", title);
//                            if (albumImgUrl.get(position).getSlideUrl() != null) {
//                                if (albumImgUrl.get(position).getSlideUrl().startsWith("http://")
//                                    || albumImgUrl.get(position).getSlideUrl().startsWith("https://")) {
//                                    intent.putExtra("url", albumImgUrl.get(position).getSlideUrl());
//                                } else {
//                                    return;
//                                }
//                            }
//                            getContext().startActivity(intent);
                        }
                    } else {
                        onPageClickListener.onPageClick(position);
                    }
                }
            });
        }
        setInterval(mDuration);
        if (mAlbumImgUrl != null && mAlbumImgUrl.size() > 1) {
            if (mAlbumImgUrl.size() > 1) {
                setCurrentItem((Integer.MAX_VALUE >> 2) - (Integer.MAX_VALUE >> 2) % mAlbumImgUrl.size());
            }
        }
    }

    /**
     * 开始图片的轮播，不带跳转链接
     */
    public void setPlayLoop(FlowIndicator flowIndicator, List<String> albumImgUrl, ImagePagerAdapter.OnShowImage onShowImage) {
        mFlowIndicator = flowIndicator;
        if (albumImgUrl == null) {
            return;
        }

        mFlowIndicator.setCount(albumImgUrl.size() == 0 ? 1 : albumImgUrl.size());
        mFlowIndicator.setFocus(0);
        mAlbumImgUrl = albumImgUrl;
        mAdapter = new ImagePagerAdapter(mContext, mAlbumImgUrl,onShowImage);
        mAdapter.setScaleType(scaleType);
        setAdapter(mAdapter);
        setOnPageChangeListener();
        if (onPageClickListener != null) {
            mAdapter.setOnPageClickListener(onPageClickListener);
        }
        setInterval(mDuration);
//        if (mAlbumImgUrl != null && mAlbumImgUrl.size() > 0) {
//            long slideAutoLoopView = System.currentTimeMillis();
//            if (mAlbumImgUrl.size() > 1) {
//                setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mAlbumImgUrl.size());
//            }
//            LogUtils.i("SlideAutoLoopView", "设置默认时间: " + (System.currentTimeMillis() - slideAutoLoopView));
//        }
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
        setInterval(mDuration);
    }

    public void setSpeed(int speed) {
        this.speed = speed;

    }

    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 300;//这里是定义切换的时长

        public void setmDuration(int speed) {
            this.mDuration = speed;
        }

        public FixedSpeedScroller(Context context, int speed) {
            super(context);
            mDuration = speed;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, int speed) {
            super(context, interpolator);
            mDuration = speed;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator,
                                  boolean flywheel, int speed) {
            super(context, interpolator, flywheel);
            mDuration = speed;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

}
