package com.example.mythreeview.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mythreeview.R;


/**
 * Created by Jack Wang on 2016/5/6.
 * 加载中对话框
 */
public class AVLoadingIndicatorDialog extends Dialog {

    private TextView mMessageView;

    public AVLoadingIndicatorDialog(Context context) {
        super(context, R.style.float_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_avld);
        mMessageView= (TextView) findViewById(R.id.message);
    }

    public void setMessage(CharSequence message) {
        mMessageView.setText(message);
        mMessageView.setVisibility(View.VISIBLE);
    }
}
