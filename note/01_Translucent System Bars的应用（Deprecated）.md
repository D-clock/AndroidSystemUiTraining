# Translucent System Bars的应用

关于Translucent System Bars想必很多朋友既熟悉又陌生。熟悉是大家经常能够见到，陌生是不知道这种效果到底叫什么。首先，先来一张网易新闻客户端的效果图

![网易新闻客户端效果图](http://d.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d1e4af09169d504fc2d5626974.jpg)

有没有发现顶部的Notification Bar和整个UI界面融成一体了，哈哈，用过IOS的童鞋，肯定对这种效果很熟悉。其实，从Android 4.4（KitKat）开始，系统也支持了这种效果，这可以让app和系统更加一体化。用户再也不用纠结那个黑乎乎的Notification Bar了。

## How to do it

要怎么去实现这种效果呢？在搜索了一下之后，发现，其实网上的资料还很少，而且很多还存在**概念性的误区（后面再解释什么误区）**。

首先介绍一下我的开发环境和真机运行环境：

* **IDE：AndroidStudio 1.2**
* **真机运行环境：Android4.4、 Android 5.1**

接下来进入代码环节，我们要做到像网易新闻那样的效果，就需要去设置系统的主题的一些属性。涉及到设置系统主题和属性，实现的方式无非两种

* **在代码中设置，需要判断当前系统的版本号**
* **在styles文件下配置我们的AppTheme**

网上有很多童鞋都说直接代码中设置。但其实这种方法是非常鸡肋的，为什么咧？如果你新建一个Android项目，你会发现资源文件夹下面除了values文件夹外，还有另外的values-vXX(**XX代表Android版本号，例如Android 4.4对应的就是values-v19**)。这些文件夹的作用在于，Android系统会根据app运行在不同真机环境下做的主题设配，如果我在工程里面创建了values-v19和values-v21两个文件夹，并在里面写好两个style文件，做好了主题配置。那么，它运行到Android4.4和5.0的真机就会分别去读取这两个文件夹下的主题，如果没有这些文件夹，系统就会一次找到当前配置的最高版本号的主题做设置。有没有发现，其本质和app做国际化语言适配是同个道理的。这也是我不把主题设置的操作写在代码中的原因。

所以，我在自己创建的Demo工程下面新建了values-v19和values-v21两个文件夹，并建立了style.xml文件做设置

```xml
<!-- values-v19/style.xml. -->
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <!-- 把状态栏设置为透明化状态 -->
        <item name="android:windowTranslucentStatus">true</item>
        <!-- 把导航栏设置为透明化状态 -->
        <item name="android:windowTranslucentNavigation">true</item>
    </style>

</resources>

```

```xml
<!-- values-v21/style.xml. -->
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="android:Theme.Material.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <!-- 取消状态栏透明化的设置 -->
        <item name="android:windowTranslucentStatus">false</item>
        <!-- 设置导航栏透明 -->
        <item name="android:windowTranslucentNavigation">true</item>
		<!-- 把通知栏的颜色设置成为透明的 -->
        <item name="android:statusBarColor">@android:color/transparent</item>
    </style>

</resources>

```

再渠道我的MainActivity的布局文件里面，为我的根布局设置一下背景

```xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@mipmap/login_bg"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
	<!-- 不然当前配置影响了你Activity顶部的布局，避免某些按钮和你的NotificationBar重叠了 -->
	android:fitsSystemWindows="true"
    tools:context=".MainActivity">

</RelativeLayout>

```

> **注意：只有在设置了Activity根布局背景的情况下**，顶部的NotificationBar才回和你的Activity融成一体，形成Translucent System Bars的效果。Demo工程最后运行的效果图如下

![Demo效果图](http://d.hiphotos.baidu.com/image/pic/item/79f0f736afc3793149100337edc4b74543a911b7.jpg)

## 误区解释

前面提到很多开发者对这种Translucent System Bars存在误区，因为很多人都将其称之为**沉浸栏（Immersive Mode）**。而从Google的开发者文档来看，这两者其实是不同的东东。有兴趣的朋友可以自行阅读下面的文档（需要翻墙）

[http://developer.android.com/about/versions/android-4.4.html#UI](http://developer.android.com/about/versions/android-4.4.html#UI)

翻不了墙的朋友我把重点的那段描述拿出来了

```

Immersive full-screen mode

To provide your app with a layout that fills the entire screen, the new SYSTEM_UI_FLAG_IMMERSIVE flag for setSystemUiVisibility() (when combined with SYSTEM_UI_FLAG_HIDE_NAVIGATION) enables a new immersive full-screen mode. While immersive full-screen mode is enabled, your activity continues to receive all touch events. The user can reveal the system bars with an inward swipe along the region where the system bars normally appear. This clears the SYSTEM_UI_FLAG_HIDE_NAVIGATION flag (and the SYSTEM_UI_FLAG_FULLSCREEN flag, if applied) so the system bars remain visible. However, if you'd like the system bars to hide again after a few moments, you can instead use the SYSTEM_UI_FLAG_IMMERSIVE_STICKY flag.

Translucent system bars

You can now make the system bars partially translucent with new themes, Theme.Holo.NoActionBar.TranslucentDecor and Theme.Holo.Light.NoActionBar.TranslucentDecor. By enabling translucent system bars, your layout will fill the area behind the system bars, so you must also enable fitsSystemWindows for the portion of your layout that should not be covered by the system bars.

If you're creating a custom theme, set one of these themes as the parent theme or include the windowTranslucentNavigation and windowTranslucentStatus style properties in your theme.

```

还有一个错误的地方在，很多朋友喜欢把**android:fitsSystemWindows="true"**这个属性的配置，提取放到style里面去。这种方式会产生一个UI上的BUG，就是使得配置了改style的Activity打印的Toast都出现错位问题，有兴趣的可以试试。Google工程师给出的建议是：

> **老老实实去布局的xml文件里面配置**


最后，欢迎看完我这篇罗里吧嗦的文章，如果需要Demo的源码，请移步到

[https://github.com/D-clock/AndroidTranslucentBar](https://github.com/D-clock/AndroidTranslucentBar)