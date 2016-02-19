# 最详细的 Toolbar 开发实践总结

 Google 推出 Material Design 设计已有一年多，由于众多原因，Material Design 在国内普及的速度相较国外来说比较缓慢。最近几天在深入学习使用一个具有  Material Design 设计风格的控件 —— **Toolbar**。本想着很容易就可以掌握此控件，结果还是图样图森破，再次进入踩坑和填坑的过程中。好了，接下来将对 Toolbar 的使用进行详细说明，鉴于文章的篇幅，大体分为 **初识Toolbar** 、**Toolbar踩坑与填坑**和**Toolbar实现知乎客户端首页效果** 三部分来写。

## 初识 Toolbar

说起 **Toolbar** ，它就是一个系统控件，并没有什么高深莫测之处。在 **API21** 开始, Google 开始推荐开发者使用 **Toolbar** 来作为客户端的导航栏，为了把这种设计风格推广到低版本的 Android SDK ，Google 自然也推出了兼容版本的 **Toolbar**，使用的时候只需要导入 **appcompat-v7** 兼容包即可。说了，这么多可能还是云里雾里，接下来我们直接进入正题。

![关键部分代码](http://d.hiphotos.baidu.com/image/pic/item/b7fd5266d0160924c03dfb73d30735fae6cd34b9.jpg)

上面的截图已经圈出核心代码实现部分，下面做一个简单的介绍：

- toolbar包下共两个Activity。ToolBarActivity用于展示Toolbar的基本使用，ZhiHuActivity是在掌握Toolbar的使用后，对知乎主页面的一个高仿实现；

- 两个Activity对应的布局文件(activity_tool_bar.xml 和 activity_zhi_hu.xml)和菜单（base_toolbar_menu.xml 和 zhihu_toolbar_menu.xml）；

- values、values-v19、values-v21中的styles.xml包含了为Toolbar设置的一些theme（后面会再详细介绍）；


![ToolbarActivity效果图](http://b.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513d9fb028b63a6d55fbb2fbd944.jpg)




以知乎作为参考例子

Toolbar用来取代actionBar，它比ActionBar的灵活性要更高

为什么Toolbar在xml里面的设置不生效，是某些机型而已吗？

style里面的属性需要注意一下。actionOverflowButtonStyle 和 android:actionOverflowButtonStyle的区别

自定义属性的问题

菜单文字设置： actionMenuTextColor无效，android:textColorPrimary才有效

对于 Material Design 感兴趣和想要详细了解 Toolbar 的童鞋，可以查看下面两篇官方文档。

- Material Design：http://www.google.com/design/spec/material-design/introduction.html

- Toolbar: http://www.google.com/design/spec/components/toolbars.html