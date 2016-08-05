package com.clock.systemui.activity.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.clock.systemui.R;
import com.clock.systemui.adapter.AuthorListAdapter;
import com.clock.systemui.bean.AuthorInfo;

import java.util.List;

public class ListViewDemoActivity extends AppCompatActivity {

    private final static String TAG = ListViewDemoActivity.class.getSimpleName();

    private ListView mListView;
    private List<AuthorInfo> mAuthorInfoList;
    private AuthorListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);

        initData();
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listview_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.normal) {
            initData();
            mListAdapter.setData(mAuthorInfoList);
            mListAdapter.notifyDataSetChanged();

        } else if (itemId == R.id.clear) {
            mAuthorInfoList.clear();
            mListAdapter.notifyDataSetChanged();

        } else if (itemId == R.id.partial) {
            int position = mListView.getFirstVisiblePosition() + 2;//更新某个位置
            mListAdapter.updateItemView(mListView, position);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        mAuthorInfoList = AuthorInfo.createTestData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        //mListView.addHeaderView(View.inflate(this, R.layout.author_card_layout, null));
        //mListView.addFooterView(View.inflate(this, R.layout.author_card_layout, null));
        mListView.setEmptyView(findViewById(R.id.empty_layout));//设置内容为空时显示的视图
        mListAdapter = new AuthorListAdapter(mAuthorInfoList);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position : " + position);
                Log.i(TAG, "id : " + id);
            }
        });
    }

}
