package com.example.mythreeview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/26.
 * 标题栏
 */
public class TitleBarLayout extends RelativeLayout{
    private TextView mtvTitle, mtvLeft, mtvRight;
    private ImageView mivLeft, mivRight;
    private OnTitleLeftClickListener onTitleLeftClickListener;
    private OnTitleRightClickListener onTitleRightClickListener;
    private OnTitleRightLongClickListener onTitleRightlongClickListener;
    private int leftIcon;
    private int rightIcon;
    private String leftText;
    private String rightText;
    private String title;
    private int backgroundColor;

    public TitleBarLayout(Context context) {
        super(context);
        initView();
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBarLayout
            , 0, 0);
        leftIcon = a.getResourceId(R.styleable.TitleBarLayout_left_icon, 0);
        rightIcon = a.getResourceId(R.styleable.TitleBarLayout_right_icon, 0);
        leftText = a.getString(R.styleable.TitleBarLayout_left_text);
        rightText = a.getString(R.styleable.TitleBarLayout_right_text);
        title = a.getString(R.styleable.TitleBarLayout_title_text);
        a.recycle();
        initView();
    }

    public TitleBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBarLayout
            , defStyleAttr, 0);
        leftIcon = a.getResourceId(R.styleable.TitleBarLayout_left_icon, 0);
        rightIcon = a.getResourceId(R.styleable.TitleBarLayout_right_icon, 0);
        leftText = a.getString(R.styleable.TitleBarLayout_left_text);
        rightText = a.getString(R.styleable.TitleBarLayout_right_text);
        title = a.getString(R.styleable.TitleBarLayout_title_text);
        backgroundColor = a.getColor(R.styleable.TitleBarLayout_background_color, Color.WHITE);
        initView();
    }

    private void initView() {
        View inflate = View.inflate(getContext(), R.layout.include_titlebar, this);
        mivLeft = (ImageView) inflate.findViewById(R.id.titlebar_iv_left);
        mtvLeft = (TextView) inflate.findViewById(R.id.titlebar_tv_left);
        mivRight = (ImageView) inflate.findViewById(R.id.titlebar_iv_right);
        mtvRight = (TextView) inflate.findViewById(R.id.titlebar_tv_right);
        mtvTitle = (TextView) inflate.findViewById(R.id.titlebar_tv);
        setContent();
        setOnClickListener();
    }

    /**
     * 设置内容
     */
    private void setContent() {
        if (leftIcon == 0) {
            mivLeft.setImageResource(R.drawable.icon_left_yellow);
        } else {
            mivLeft.setImageResource(leftIcon);
        }
        if (rightIcon != 0) {
            mivRight.setVisibility(VISIBLE);
            mivRight.setImageResource(rightIcon);
        }
        if (leftText != null) {
            mtvLeft.setVisibility(VISIBLE);
            mtvLeft.setText(leftText);
        }
        if (rightText != null) {
            mtvRight.setVisibility(VISIBLE);
            mtvRight.setText(rightText);
        }
        if (title != null) {
            mtvTitle.setText(title);
        }
        if (backgroundColor != 0) {
            this.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * @param title 标题内容
     *              设置标题栏内容
     */
    public void setTitle(String title) {
        mtvTitle.setText(title);
    }

    /**
     * 设置左右标题栏按键监听
     */
    public void setOnClickListener() {
        OnClickListener leftClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleLeftClickListener == null) {
                    ((Activity) getContext()).finish();
                } else {
                    onTitleLeftClickListener.onLeftClick(v);
                }
            }
        };
        OnClickListener rightClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleRightClickListener != null) {
                    onTitleRightClickListener.onRightClick(v);
                }
            }
        };
        mivLeft.setOnClickListener(leftClick);
        mivRight.setOnClickListener(rightClick);
        mtvLeft.setOnClickListener(leftClick);
        mtvRight.setOnClickListener(rightClick);
        mtvRight.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onTitleRightlongClickListener != null) {
                    onTitleRightlongClickListener.onRightLongClick(v);
                    return true;
                }
                return false;
            }
        });
        mivRight.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onTitleRightlongClickListener != null) {
                    onTitleRightlongClickListener.onRightLongClick(v);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * @param onTitleClickListener 标题左右按键监听
     */
    public void setOnTitleLeftClickListener(OnTitleLeftClickListener onTitleClickListener) {
        this.onTitleLeftClickListener = onTitleClickListener;
    }

    public void setOnTitleRightClickListener(OnTitleRightClickListener onTitleClickListener) {
        this.onTitleRightClickListener = onTitleClickListener;
    }

    public void setOnTitleRightLongClickListener(OnTitleRightLongClickListener onTitleRightLongClickListener) {
        this.onTitleRightlongClickListener = onTitleRightLongClickListener;
    }

    public void setLeftIconVisible(boolean visible) {
        if (visible) {
            mivLeft.setVisibility(VISIBLE);
            mtvLeft.setVisibility(VISIBLE);
        } else {
            mivLeft.setVisibility(GONE);
            mtvLeft.setVisibility(GONE);
        }
    }

    public void setRightIconVisible(boolean visible) {
        if (visible) {
            mivRight.setVisibility(VISIBLE);
            mivRight.setVisibility(VISIBLE);
        } else {
            mivRight.setVisibility(GONE);
            mivRight.setVisibility(GONE);
        }
    }

    /**
     * 代码设置右边控件的文字
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (TextUtils.isEmpty(rightText)) {
            mtvRight.setVisibility(GONE);
        } else {
            mtvRight.setVisibility(VISIBLE);
        }
        mtvRight.setText(rightText);
    }

    public void setRightIcon(int rightIconResourceId){
        this.rightIcon = rightIconResourceId;
        mivRight.setImageResource(rightIcon);
    }


    public interface OnTitleLeftClickListener {
        void onLeftClick(View v);
    }

    public interface OnTitleRightClickListener {
        void onRightClick(View v);
    }

    public interface OnTitleRightLongClickListener {
        void onRightLongClick(View view);
    }
}
