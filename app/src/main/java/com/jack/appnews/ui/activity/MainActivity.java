package com.jack.appnews.ui.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jack.appnews.R;
import com.jack.appnews.ui.BaseActivity;
import com.jack.appnews.ui.fragment.ImageListFragment;
import com.jack.appnews.ui.fragment.MineFragment;
import com.jack.appnews.ui.fragment.NewsListFragment;
import com.jack.appnews.ui.fragment.VideoListFragment;
import com.jack.appnews.widget.MainTabItem;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    private long exitTime = 0;

    private ArrayList<MainTabItem> mTabs = new ArrayList<>(4);

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initTabHost();
    }

    protected void initTabHost() {
        //1)实例化4个Tab类的对象
        mTabs.add(new MainTabItem(R.drawable.main_tab_btn_news, R.string.main_tab_news, NewsListFragment.class));
        mTabs.add(new MainTabItem(R.drawable.main_tab_btn_video, R.string.main_tab_video, VideoListFragment.class));
        mTabs.add(new MainTabItem(R.drawable.main_tab_btn_image, R.string.main_tab_image, ImageListFragment.class));
        mTabs.add(new MainTabItem(R.drawable.main_tab_btn_mine, R.string.main_tab_mine, MineFragment.class));

        //2)调用 setup 方法
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_content);

        //3)添加 Tab
        for (MainTabItem tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(tab.getText())).setIndicator(getTabItemView(tab));
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }

        //4)去除掉底部菜单图表之间的分割线
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        //5)事件绑定
        mTabHost.setOnTabChangedListener(this);
    }

    /**
     * 设置Indicator中的View
     *
     * @param tab
     * @return
     */
    private View getTabItemView(MainTabItem tab) {
        View view = getLayoutInflater().inflate(R.layout.main_tab_indicator, null);
        ImageView Tab_img = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView Tab_txt = (TextView) view.findViewById(R.id.tv_tab_text);

        Tab_img.setBackgroundResource(tab.getImage());
        Tab_txt.setText(tab.getText());
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}
