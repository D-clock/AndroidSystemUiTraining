package com.clock.systemui.activity.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.clock.systemui.R;
import com.clock.systemui.adapter.AuthorRecyclerAdapter;
import com.clock.systemui.bean.AuthorInfo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDemoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AuthorRecyclerAdapter mRecyclerAdapter;
    private List<AuthorInfo> mAuthorInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);

        initData();
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.recyclerview_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.linear) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerDemoActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerAdapter.setSmallType(false);
            mRecyclerView.setAdapter(mRecyclerAdapter);

        } else if (itemId == R.id.grid) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerDemoActivity.this, 3);
            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerAdapter.setSmallType(true);
            mRecyclerView.setAdapter(mRecyclerAdapter);

        } else if (itemId == R.id.staggered) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
            mRecyclerAdapter.setSmallType(true);
            mRecyclerView.setAdapter(mRecyclerAdapter);

        } else if (itemId == R.id.insert_item) {
            mRecyclerAdapter.notifyItemInserted(2);

        } else if (itemId == R.id.delete_item) {
            mRecyclerAdapter.notifyItemChanged(3);

        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
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
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapter = new AuthorRecyclerAdapter(mAuthorInfoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerDemoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter.setSmallType(false);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
