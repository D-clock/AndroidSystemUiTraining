package com.clock.systemui.activity.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clock.systemui.R;
import com.clock.systemui.adapter.AuthorRecyclerAdapter;
import com.clock.systemui.bean.AuthorInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDemoActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RecyclerView mRecyclerView;

    private List<AuthorInfo> mAuthorInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);

        mAuthorInfoList = new ArrayList<>();
        for (int counter = 0; counter < 6; counter++) {
            AuthorInfo authorInfo1 = new AuthorInfo();
            authorInfo1.setMotto("Hello World!Hello World!Hello World!");
            authorInfo1.setNickName("D_clock爱吃葱花");
            mAuthorInfoList.add(authorInfo1);

            AuthorInfo authorInfo2 = new AuthorInfo();
            authorInfo2.setMotto("I am Android Developer！HaHaHaHaHaHaHaHaHa");
            authorInfo2.setNickName("D_clock爱吃大西瓜西瓜西瓜西瓜西瓜");
            mAuthorInfoList.add(authorInfo2);

            AuthorInfo authorInfo3 = new AuthorInfo();
            authorInfo3.setMotto("Hello World!Hello World!Hello World!Hello World!Hello World!");
            authorInfo3.setNickName("D_clock爱吃葱花葱花葱花葱花葱花葱花");
            mAuthorInfoList.add(authorInfo3);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_layout_option);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_linear) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerDemoActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(new AuthorRecyclerAdapter());

                } else if (checkedId == R.id.rb_grid) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerDemoActivity.this, 3);
                    gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                    mRecyclerView.setLayoutManager(gridLayoutManager);
                    mRecyclerView.setAdapter(AuthorRecyclerAdapter.newGridInstance());

                } else if (checkedId == R.id.rb_staggered) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                    mRecyclerView.setAdapter(AuthorRecyclerAdapter.newStaggeredInstance(mAuthorInfoList));

                }

            }
        });
        RadioButton linearRadioButton = (RadioButton) findViewById(R.id.rb_linear);
        linearRadioButton.setChecked(true);
    }
}
