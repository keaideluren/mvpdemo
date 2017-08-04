package com.example.mythreeview;

import android.widget.FrameLayout;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mythreeview.loading.AVLoadingIndicatorView;

/**
 * Created by Administrator 可爱的路人 on 2017/3/23.
 * 空界面
 */

public class EmptyView extends FrameLayout {
    private AVLoadingIndicatorView loadingProgressEmpty;
    private TextView tvErrorEmpty;
    private TextView tvNoDataEmpty;
    public static final int TYPE_ERROR = 2;
    public static final int TYPE_NO_DATA = 1;
    public static final int TYPE_LOADING = 0;
    private TitleBarLayout tblTitle;

    public EmptyView(Activity context) {
        super(context);
        initView();
        tblTitle.setVisibility(GONE);
    }

    public EmptyView(Activity context, String titleText) {
        super(context);
        initView();
        tblTitle.setTitle(titleText);

    }

    public void setOnReloadListener(final OnReloadListener listener) {
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyText(null, TYPE_LOADING);
                listener.onReload(v, (Integer) v.getTag());
            }
        };
        if (tvErrorEmpty != null && listener != null) {
            tvErrorEmpty.setTag(TYPE_ERROR);
            tvErrorEmpty.setOnClickListener(onClickListener);
        }
        if (tvNoDataEmpty != null && listener != null) {
            tvNoDataEmpty.setTag(TYPE_NO_DATA);
            tvNoDataEmpty.setOnClickListener(onClickListener);
        }
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
//        View emptyLayout = inflate(getContext(), R.layout.empty, this);
        View emptyLayout = LayoutInflater.from(getContext()).inflate(R.layout.empty, this, false);
        loadingProgressEmpty = (AVLoadingIndicatorView) emptyLayout.findViewById(R.id.loading_progress_empty);
        tvNoDataEmpty = (TextView) emptyLayout.findViewById(R.id.tv_no_data_empty);
        tvErrorEmpty = (TextView) emptyLayout.findViewById(R.id.tv_error_empty);
        tblTitle = (TitleBarLayout) emptyLayout.findViewById(R.id.tbl_title_empty_new);
        addView(emptyLayout);
    }


    /**
     * @param text 没有内容时的文字 TYPE_NO_DATA
     * @param type TYPE_ERROR  TYPE_NO_DATA  TYPE_LOADING
     */
    public void setEmptyText(CharSequence text, int type) {
        switch (type) {
            case TYPE_LOADING:
                loadingProgressEmpty.setVisibility(VISIBLE);
                tvErrorEmpty.setVisibility(GONE);
                tvNoDataEmpty.setVisibility(GONE);
                break;
            case TYPE_NO_DATA:
                loadingProgressEmpty.setVisibility(GONE);
                tvErrorEmpty.setVisibility(GONE);
                tvNoDataEmpty.setVisibility(VISIBLE);
                tvNoDataEmpty.setText(text);
                break;
            case TYPE_ERROR:
                loadingProgressEmpty.setVisibility(GONE);
                tvErrorEmpty.setVisibility(VISIBLE);
                tvNoDataEmpty.setVisibility(GONE);
                break;
            default:
                loadingProgressEmpty.setVisibility(VISIBLE);
                tvErrorEmpty.setVisibility(GONE);
                tvNoDataEmpty.setVisibility(GONE);
                break;
        }
    }

    public interface OnReloadListener {
        void onReload(View view, int type);
    }
}
