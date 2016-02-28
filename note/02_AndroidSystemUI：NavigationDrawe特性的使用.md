# 最详细的 NavigationDrawer 开发实践总结 

继前面写的两篇文章之后（**有问题欢迎反馈哦**）：

1. [Android开发：Translucent System Bar 的最佳实践](http://www.jianshu.com/p/0acc12c29c1b)
2. [Android开发：最详细的 Toolbar 开发实践总结](http://www.jianshu.com/p/79604c3ddcae)

接着来写写Android系统UI新特性，本文是我对最近开发过程中应用 **NavigationDrawer** 特性的详细总结。**如有问题，欢迎在下方留言讨论，本人不胜荣幸** 。

## NavigationDrawer 简介

**NavigationDrawer** 是 Google 在 Material Design 中推出的一种导航设计风格。这样说可能有点抽象，我们直接来看看 **网易云音乐** 的侧滑导航栏






## DrawerLayout 

SlidingMenu 开源项目：https://github.com/jfeinstein10/SlidingMenu

SlidingDrawer 在 API 17 上被deprecated了

默认的图标会变成灰色的。。

推荐第三方的库 https://github.com/mikepenz/MaterialDrawer

参考文章：

http://myihsan.farbox.com/post/use-navigation-view-to-make-navigation-drawer

https://guides.codepath.com/android/Fragment-Navigation-Drawer

http://developer.android.com/training/implementing-navigation/nav-drawer.html

http://www.google.com/design/spec/patterns/navigation-drawer.html#navigation-drawer-content

http://developer.android.com/reference/android/support/v4/widget/DrawerLayout.html

http://developer.android.com/reference/android/support/design/widget/NavigationView.html

http://developer.android.com/reference/android/support/v7/app/ActionBarDrawerToggle.html

左上角的动画效果：http://www.mincoder.com/article/5541.shtml


## 需要注意的问题

DrawerLayout 在设置 android:layout_width="wrap_content" 的时候会闪退报错

需要引入最新的 库，引入 compile 'com.android.support:design:23.1.1' 正常 ，，，但是 compile 'com.android.support:design:23.1.0' 会报错


## 动画效果

