package com.clock.systemui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clock.systemui.R;
import com.clock.systemui.adapter.AuthorRecyclerAdapter;
import com.clock.systemui.bean.AuthorInfo;

import java.util.ArrayList;

/**
 * Created by Clock on 2016/7/26.
 */
public class AuthorInfoFragment extends Fragment {

    public static Fragment newInstance() {
        AuthorInfoFragment fragment = new AuthorInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //需要利用实现了嵌套滚动机制的控件，才能出现AppBarLayout往上推的效果
        View rootView = inflater.inflate(R.layout.fragment_author_info, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new AuthorRecyclerAdapter(AuthorInfo.createTestData()));
        return rootView;
    }
}
