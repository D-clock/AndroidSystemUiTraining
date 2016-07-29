package com.clock.systemui.activity.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clock.systemui.R;
import com.clock.systemui.activity.base.BaseActivity;

/**
 * 仿知乎主界面Toolbar的应用
 *
 * @author Clock
 * @since 2016-02-18
 */
public class ZhiHuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.zhihu_toolbar_menu);

        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);

        toolbar.setTitle(R.string.home_page);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }
}
