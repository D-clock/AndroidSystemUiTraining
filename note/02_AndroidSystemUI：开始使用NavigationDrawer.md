# 最详细的 NavigationDrawer 开发实践总结 



## DrawerLayout 

SlidingMenu 开源项目：https://github.com/jfeinstein10/SlidingMenu

SlidingDrawer 在 API 17 上被deprecated了

介绍一些比较常用的API，有很多API用了没效果，咋回事？？？？？

默认的图标会变成灰色的。。

推荐第三方的库 https://github.com/mikepenz/MaterialDrawer

参考文章：

http://myihsan.farbox.com/post/use-navigation-view-to-make-navigation-drawer

https://guides.codepath.com/android/Fragment-Navigation-Drawer

http://developer.android.com/training/implementing-navigation/nav-drawer.html

http://www.google.com/design/spec/patterns/navigation-drawer.html#navigation-drawer-content

http://developer.android.com/reference/android/support/v4/widget/DrawerLayout.html

http://developer.android.com/reference/android/support/design/widget/NavigationView.html

左上角的动画效果：http://www.jianshu.com/p/3fe2acac0ddb


使用 ScrimInsetsFrameLayout 来仿造实现网易的效果

## 需要注意的问题

DrawerLayout 在设置 android:layout_width="wrap_content" 的时候会闪退报错

需要引入最新的 库，引入 compile 'com.android.support:design:23.1.1' 正常 ，，，但是 compile 'com.android.support:design:23.1.0' 会报错
