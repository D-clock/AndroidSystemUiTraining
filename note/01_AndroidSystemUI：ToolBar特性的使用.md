# 最详细的 Toolbar 开发实践总结

 Google 推出 Material Design 设计已有一年多，由于众多原因，Material Design 在国内普及的速度相较国外来说比较缓慢。最近几天在深入学习使用一个具有  Material Design 设计风格的控件 —— **Toolbar**。本想着很容易就可以掌握此控件，结果还是图样图森破，再次进入踩坑和填坑的过程中。好了，接下来将对 Toolbar 的使用进行详细说明，鉴于文章的篇幅，大体分为 **初识Toolbar** 、**Toolbar踩坑与填坑**和**Toolbar实现知乎客户端首页效果** 三部分来写。

## 初识 Toolbar

说起 **Toolbar** ，其实并没有什么高深莫测，它本质上就是一个系统控件。若要说它和其他系统控件有什么区别，那应该就是 Google 推荐大家使用 **Toolbar** 来作为Android客户端的导航栏，除此之外并没有其他太大区别。而 **Toolbar** 和 **Actionbar** 相比，







以知乎作为参考例子

Toolbar用来取代actionBar，它比ActionBar的灵活性要更高

为什么Toolbar在xml里面的设置不生效，是某些机型而已吗？

style里面的属性需要注意一下。actionOverflowButtonStyle 和 android:actionOverflowButtonStyle的区别

自定义属性的问题

菜单文字设置： actionMenuTextColor无效，android:textColorPrimary才有效

对于 Material Design 感兴趣和想要详细了解 Toolbar 的童鞋，可以查看下面两篇官方文档。

- Material Design：http://www.google.com/design/spec/material-design/introduction.html

- Toolbar: http://www.google.com/design/spec/components/toolbars.html