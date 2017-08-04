package com.fxl.mvpdemo.pages.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.dialog.NotifyDialog;
import com.fxl.mvpdemo.mvp.base.BaseActivity;
import com.fxl.mvpdemo.pages.amuselist.AmuseFragment;
import com.fxl.mvpdemo.pages.login.LoginActivity;
import com.fxl.mvpdemo.pages.scene.SceneFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContact.HomeView {
    @BindView(R.id.ctl_tab_home)
    CommonTabLayout tabHome;
    private boolean isSettingFragmentFirst = true;
    private NotifyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initViewAndData() {
        initTab();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    /**
     * 下面的TAB
     */
    private void initTab() {
        int[] mIconUnselectIds = {
                R.drawable.ic_tab_discover, R.drawable.ic_tab_amuse
                , R.drawable.ic_tab_amuse, R.drawable.ic_tab_message, R.drawable.ic_tab_me};
        int[] mIconSelectIds = {
                R.drawable.ic_tab_discover_hover, R.drawable.ic_tab_amuse_hover, R.drawable.ic_tab_amuse_hover
                , R.drawable.ic_tab_message_hover, R.drawable.ic_tab_me_hover};
        String[] mTitleList = {"发现", "娱乐圈", "热点", "消息", "我"};

//        AmuseCircleFragment amuseCircleFragment = new AmuseCircleFragment();
//        ConversationFragment conversationFragment = new ConversationFragment();
////        ContactFragment conversationFragment = new ContactFragment();
//        SettingFragment settingFragment = new SettingFragment();
//        HotSpotsVideoFragment hotSpotsVideoFragment = new HotSpotsVideoFragment();
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SceneFragment());
        fragmentList.add(new AmuseFragment());
        fragmentList.add(new SceneFragment());
        fragmentList.add(new SceneFragment());
        fragmentList.add(new SceneFragment());
        ArrayList<CustomTabEntity> tabList = new ArrayList<>();
        for (int i = 0; i < fragmentList.size(); i++) {
            tabList.add(new TabEntity(mTitleList[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabHome.setTabData(tabList, this, R.id.contentPanel, fragmentList);

    }

    @Override
    public void showOtherDeviceLoginDialog(DialogInterface.OnClickListener callback) {
        if (dialog == null && dialog.isHidden()) {
            dialog = new NotifyDialog();
            dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), callback);
        }
    }

    @Override
    public void navToLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void dismissOtherDialog() {
        if (dialog != null && dialog.isVisible()) {
            dialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    class TabEntity implements CustomTabEntity {
        String title;
        int selectedIcon;
        int unSelectedIcon;

        TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }
}
