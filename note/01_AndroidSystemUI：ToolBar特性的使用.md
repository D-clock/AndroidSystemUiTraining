# 最详细的 Toolbar 开发实践总结

以知乎作为参考例子

Toolbar用来取代actionBar，它比ActionBar的灵活性要更高

官方文档说明：

http://developer.android.com/reference/android/widget/Toolbar.html

http://www.google.com/design/spec/components/toolbars.html#toolbars-usage

为什么Toolbar在xml里面的设置不生效，是某些机型而已吗？

style里面的属性需要注意一下。actionOverflowButtonStyle 和 android:actionOverflowButtonStyle的区别

自定义属性的问题

菜单文字设置： actionMenuTextColor无效，android:textColorPrimary才有效