package com.clock.systemui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.clock.systemui.R;
import com.clock.systemui.bean.AuthorInfo;

import java.util.List;

/**
 * Created by Clock on 2016/8/4.
 */
public class AuthorListAdapter extends BaseAdapter {

    private List<AuthorInfo> mAuthorInfoList;

    public AuthorListAdapter(List<AuthorInfo> authorInfoList) {
        setData(authorInfoList);
    }

    @Override
    public int getCount() {
        if (mAuthorInfoList == null) {
            return 0;
        }
        return mAuthorInfoList.size();
    }

    public void setData(List<AuthorInfo> authorInfoList) {
        this.mAuthorInfoList = authorInfoList;
    }

    @Override
    public Object getItem(int position) {
        return mAuthorInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.author_info_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mPortrait = (ImageView) convertView.findViewById(R.id.iv_portrait);
            viewHolder.mNickName = (TextView) convertView.findViewById(R.id.tv_nickname);
            viewHolder.mMotto = (TextView) convertView.findViewById(R.id.tv_motto);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AuthorInfo authorInfo = mAuthorInfoList.get(position);
        viewHolder.mPortrait.setImageResource(authorInfo.getPortrait());
        viewHolder.mNickName.setText(authorInfo.getNickName());
        viewHolder.mMotto.setText(authorInfo.getMotto());

        return convertView;
    }

    /**
     * 更新Item视图，减少不必要的重绘
     *
     * @param listView
     * @param position
     */
    public void updateItemView(ListView listView, int position) {
        int index = position - listView.getFirstVisiblePosition();
        if (index >= 0 && index < listView.getChildCount()) {
            AuthorInfo authorInfo = mAuthorInfoList.get(position);
            authorInfo.setNickName("Google Android");
            authorInfo.setMotto("My name is Android .");
            authorInfo.setPortrait(R.mipmap.ic_launcher);
            View itemView = listView.getChildAt(index);
            getView(position, itemView, listView);
        }
    }

    private static class ViewHolder {
        ImageView mPortrait;
        TextView mNickName;
        TextView mMotto;
    }

}
