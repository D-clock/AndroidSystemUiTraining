package com.clock.systemui.ui.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clock.systemui.R;
import com.clock.systemui.ui.base.BaseActivity;

/**
 * Toolbar的基本使用
 */
public class ToolBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单

        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//第一个图标
        toolbar.setLogo(R.mipmap.ic_launcher);//第二个图标

        toolbar.setTitle("Title");//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));//设置主标题颜色
        toolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);//修改主标题的外观，包括文字颜色，文字大小等

        toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.darker_gray));//设置子标题颜色
        toolbar.setSubtitleTextAppearance(this, R.style.Theme_ToolBar_Base_Subtitle);//设置子标题的外观，包括文字颜色，文字大小等

    }

}
