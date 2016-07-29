package com.clock.systemui.activity.bt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.activity.base.BaseActivity;
import com.clock.systemui.fragment.AuthorInfoFragment;

/**
 * AppBarLayout 和 TabLayout 的使用 Demo
 * <p/>
 * 关于 TabLayout 使用的很详细的文章：http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 * 关于 CoordinatorLayout的文章：http://blog.csdn.net/xyz_lmn/article/details/48055919
 * 关于 Material Design 控件的文章：http://blog.csdn.net/eclipsexys/article/details/46349721
 */
public class BarTabDemoActivity extends BaseActivity {

    private Fragment[] mFragmentArrays = new Fragment[3];
    private String[] mTabTitles = new String[3];

    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_tab_demo);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTabTitles[0] = "Clock";
        mTabTitles[1] = "Dannie";
        mTabTitles[2] = "Lufy";

        mFragmentArrays[0] = AuthorInfoFragment.newInstance();
        mFragmentArrays[1] = AuthorInfoFragment.newInstance();
        mFragmentArrays[2] = AuthorInfoFragment.newInstance();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new AuthorPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        //初始化TabLayout，增加Tab，同时关联ViewPager
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(pagerAdapter);

    }

    private class AuthorPagerAdapter extends FragmentPagerAdapter {

        public AuthorPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }

        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }

}
