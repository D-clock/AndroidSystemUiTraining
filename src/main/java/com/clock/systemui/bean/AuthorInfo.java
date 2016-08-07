package com.clock.systemui.bean;

import com.clock.systemui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clock on 2016/7/28.
 */
public class AuthorInfo {

    private int portrait = R.mipmap.lufy;

    private String nickName;

    private String motto;


    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    /**
     * 生成一些测试数据
     *
     * @return
     */
    public static List<AuthorInfo> createTestData() {
        List<AuthorInfo> authorInfoList = new ArrayList<>();
        for (int count = 0; count < 6; count++) {
            AuthorInfo authorInfo1 = new AuthorInfo();
            authorInfo1.setMotto("我是要成为海贼王的男人！");
            authorInfo1.setNickName("蒙奇 D 路飞");
            authorInfoList.add(authorInfo1);

            AuthorInfo authorInfo2 = new AuthorInfo();
            authorInfo2.setMotto("爱看书，爱做饭，爱玩手游！");
            authorInfo2.setNickName("葱花妹妹");
            authorInfo2.setPortrait(R.mipmap.conghua);
            authorInfoList.add(authorInfo2);

            AuthorInfo authorInfo3 = new AuthorInfo();
            authorInfo3.setMotto("Diycode大管家，闭关写代码");
            authorInfo3.setNickName("D_clock爱吃葱花");
            authorInfoList.add(authorInfo3);
        }
        return authorInfoList;
    }
}
