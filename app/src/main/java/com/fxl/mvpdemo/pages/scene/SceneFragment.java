package com.fxl.mvpdemo.pages.scene;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dinuscxj.itemdecoration.LinearDividerItemDecoration;
import com.example.mythreeview.autoscrollviewpager.FlowIndicator;
import com.example.mythreeview.autoscrollviewpager.ImagePagerAdapter;
import com.example.mythreeview.autoscrollviewpager.SlideAutoLoopView;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.model.bean.Business;
import com.fxl.mvpdemo.model.bean.BusinessDiscover;
import com.fxl.mvpdemo.mvp.base.BaseRefreshFragment;
import com.fxl.mvpdemo.mvp.base.IBaseRefreshView;
import com.fxl.mvpdemo.myutils.CustomToast;
import com.fxl.mvpdemo.myutils.DistanceUtil;
import com.fxl.mvpdemo.myutils.LogUtil;
import com.fxl.mvpdemo.myutils.ScreenUtils;
import com.fxl.mvpdemo.pages.activitylist.ActivityListActivity;
import com.fxl.mvpdemo.pages.amusedetail.AmuseDetailActivity;
import com.fxl.mvpdemo.pages.qrcodescanner.QrcodeScannerActivity;
import com.fxl.mvpdemo.util.ImageLoaderUtil;
import com.yanzhenjie.zbar.Bmp2YUV;
import com.yanzhenjie.zbar.Config;
import com.yanzhenjie.zbar.Image;
import com.yanzhenjie.zbar.ImageScanner;
import com.yanzhenjie.zbar.Symbol;
import com.yanzhenjie.zbar.SymbolSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends BaseRefreshFragment<ScenePresenter, BusinessDiscover> implements SceneContact.SceneView {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_view)
    PtrFrameLayout refreshView;
    private SceneHeaderViewBinder sceneHeaderViewBinder;
    private Unbinder bindHead;
    private BaseQuickAdapter<Business, BaseViewHolder> mAdapter;

    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene;
    }

    @Override
    public PtrFrameLayout getRefreshView() {
        return refreshView;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mAdapter = new BaseQuickAdapter<Business, BaseViewHolder>(R.layout.item_scene) {
            @Override
            protected void convert(BaseViewHolder helper, Business item) {
                ImageLoaderUtil.setPic(mContext, item.getBusinessCover(), helper.getView(R.id.iv_cover));
                if (!TextUtils.isEmpty(item.getBusinessLogo())) {
                    ImageLoaderUtil.setPic(mContext, item.getBusinessLogo(), helper.getView(R.id.civ_logo));
                }
                helper.setText(R.id.tv_scenegroup_name, item.getBusinessName())
                    .setText(R.id.tv_scenegroup_count, Integer.toString(item.getFollowCount()))
                    .setText(R.id.tv_location_distance, DistanceUtil.formatDistance(item.getDistance()));
            }
        };
        return mAdapter;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        LinearDividerItemDecoration decoration = new LinearDividerItemDecoration(mContext
            , LinearDividerItemDecoration.LINEAR_DIVIDER_VERTICAL
            , LinearDividerItemDecoration.DIVIDER_TYPE_OFFSET);
        decoration.registerTypeDrawable(BaseQuickAdapter.HEADER_VIEW, (parent, adapterPosition) -> {
            ShapeDrawable shapeDrawable1 = new ShapeDrawable();
            shapeDrawable1.setIntrinsicHeight(0);
            return shapeDrawable1;
        });
        decoration.registerTypeDrawable(0, (parent, adapterPosition) -> {
            ShapeDrawable shapeDrawable1 = new ShapeDrawable();
            LogUtil.i("SceneFragment", "adapterPositon:" + adapterPosition);
            if (adapterPosition == mAdapter.getData().size() - 1 + mAdapter.getHeaderLayoutCount()) {
                shapeDrawable1.setIntrinsicHeight(0);
            } else {
                shapeDrawable1.setIntrinsicHeight(ScreenUtils.dp2px(getContext(), 10));
            }
            return shapeDrawable1;
        });
        rvList.addItemDecoration(decoration);
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        return rvList;
    }

    private void initHeadView(BusinessDiscover b) {
        sceneHeaderViewBinder = new SceneHeaderViewBinder();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.head_view_scene, null, false);
        bindHead = ButterKnife.bind(sceneHeaderViewBinder, inflate);
        sceneHeaderViewBinder.salvBannerScene.setPlayLoop(sceneHeaderViewBinder.llChannelDots, b.getSlidesList(), "推荐活动", true, new ImagePagerAdapter.OnShowImage() {
            @Override
            public void showImage(Context context, ImageView imageView, String imgUrl) {
                ImageLoaderUtil.setPic(context, imgUrl, imageView);
            }
        });
        sceneHeaderViewBinder.salvBannerScene.startAutoScroll();
        mAdapter.addHeaderView(inflate);
    }

    private void scanQrcode() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wechat,options);
        Image image = new Image(bitmap.getWidth(),bitmap.getHeight(),"Y800");
        byte[] yuv420sp = Bmp2YUV.getYUV420sp(bitmap.getWidth(), bitmap.getHeight(), bitmap);
        LogUtil.e("byteimage", "" + yuv420sp.length);
        image.setData(yuv420sp);
//        image.convert("L");

        ImageScanner imageScanner = new ImageScanner();
        imageScanner.parseConfig("enable");
        imageScanner.setConfig(0, Config.X_DENSITY, 3);
        imageScanner.setConfig(0, Config.Y_DENSITY, 3);
        // configure the reader
        int i = 0;
        try {
            i = imageScanner.scanImage(image);
        } catch (Exception e) {
            LogUtil.e("byteimage", e.getClass().getName());

        }
        if (i != 0) {
            SymbolSet results = imageScanner.getResults();
            StringBuilder sb = new StringBuilder();
            for (Symbol s : results) {
                sb.append(s.getData());
            }
            CustomToast.show(getContext(), "扫描二维码成功" + sb, Toast.LENGTH_LONG);
        } else {
            CustomToast.show(getContext(), "扫描二维码失败", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bindHead != null) {
            bindHead.unbind();
        }
        if (sceneHeaderViewBinder != null && sceneHeaderViewBinder.salvBannerScene != null) {
            sceneHeaderViewBinder.salvBannerScene.stopAutoScroll();
        }
    }

    @OnClick({R.id.iv_take_qrcode_scene, R.id.iv_searh, R.id.iv_invite_gift})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_take_qrcode_scene:
//                scanQrcode();
                startActivity(new Intent(getContext(), QrcodeScannerActivity.class));
                break;
            case R.id.iv_searh:
                AmuseDetailActivity.startAmuseDetailActivity(mContext, 1);
                break;
            case R.id.iv_invite_gift:
                break;

        }
    }

    @Override
    public int onLoadMore(BusinessDiscover baseData, String message, int code) {
        if (baseData != null && code == 1) {
            if (baseData.getBusinessList() != null && baseData.getBusinessList().size() > 0) {
                mAdapter.addData(baseData.getBusinessList());
                return IBaseRefreshView.REFRESH_STATE_SUCCESS;
            }
            return IBaseRefreshView.REFRESH_STATE_END;
        }
        return IBaseRefreshView.REFRESH_STATE_ERROR;
    }

    @Override
    public boolean onLoad(BusinessDiscover baseData, String message, int code) {
        if (mAdapter.getHeaderLayoutCount() <= 0) {
            initHeadView(baseData);
        }
        if (baseData != null && baseData.getBusinessList() != null && baseData.getBusinessList().size() > 0) {
            mAdapter.setNewData(baseData.getBusinessList());
            return true;
        }
        return false;
    }

    @Override
    public boolean onRefresh(BusinessDiscover baseData, String message, int code) {
//        CustomToast.show(getContext(), FxlCppNative.getStringFromC(1), Toast.LENGTH_LONG);
        return onLoad(baseData, message, code);
    }

    /**
     * 黄油刀绑定数据的类
     */
    class SceneHeaderViewBinder {
        @BindView(R.id.salv_banner_scene)
        SlideAutoLoopView salvBannerScene;
        @BindView(R.id.ll_channel_dots)
        FlowIndicator llChannelDots;

        @OnClick({R.id.ll_tab_sunle_scene, R.id.ll_tab_activity_scene, R.id.ll_tab_tribal_scene
            , R.id.ll_tab_friend_scene, R.id.ll_tab_game_scene, R.id.tv_type_drink_scene
            , R.id.tv_type_manyao_scene, R.id.tv_type_ktv_scene})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.ll_tab_sunle_scene:
                    break;
                case R.id.ll_tab_tribal_scene:
                    break;
                case R.id.ll_tab_friend_scene:
                    break;
                case R.id.ll_tab_game_scene:
                    break;
                case R.id.tv_type_drink_scene:
                    break;
                case R.id.tv_type_manyao_scene:
                    break;
                case R.id.tv_type_ktv_scene:
                    break;
                case R.id.ll_tab_activity_scene:
                    startActivity(new Intent(getContext(), ActivityListActivity.class));
                    break;
            }
        }

    }
}
