# Translucent System Bar 的最佳实践

近几天准备抽空总结Android一些系统UI的实践使用，于是开始动手建了一个库 [AndroidSystemUiTraining](https://github.com/D-clock/AndroidSystemUiTraining) ，边撸代码边写总结

今天开写第一篇，对 Translucent System Bar 的实践做一些总结。说起 Translucent System Bar 的特性，可能有些朋友还比较陌生，这里做一下简单的介绍。

![Android 4.3豌豆荚](http://h.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1fd67f8243374e251f95ca5f50.jpg)

看上图，Android 4.4之前，即使我们打开手机app，我们还总是能看到系统顶部那条黑乎乎的通知栏，这样会使得app稍显突兀。于是Android 4.4开始，便引入了Translucent System Bar的系特性，用于弥补系统通知栏突兀之处。（估计也是向ios学习，因为ios一大早就有这个特性）。我们先来看看 Translucent System Bar 新特性引入后，发生了什么样的变化。下面截取了 **中华万年历的天气预报界面** 和 **QQ音乐主界面** 的效果（两个界面的效果实现 Translucent System Bar 的方式有些区别，下文会细讲）

![中华万年历](http://c.hiphotos.baidu.com/image/pic/item/9345d688d43f879416be6a57d51b0ef41bd53a01.jpg) ![QQ音乐](http://d.hiphotos.baidu.com/image/pic/item/0824ab18972bd407f672f1847c899e510fb30915.jpg)

可以看到，系统的通知栏和app界面融为一体，妈妈再也不用面对黑乎乎的通知栏了。有关 Translucent System Bar 的特性就暂且介绍到此。

## 工程简介

先简单介绍一下工程的结构，核心部分已经圈出，待我逐一讲解

![工程结构](http://b.hiphotos.baidu.com/image/pic/item/7aec54e736d12f2ed836811948c2d56285356893.jpg)

- 主要的操作都在style.xml 和 AndroidManifest.xml 中，Activity里面没有任何涉及到Translucent System Bar设置的代码，所以可以忽略不看。

- ColorTranslucentBarActivity 和 ImageTranslucentBarActivity 分别用于展示两种不同实现方式的效果

- 要在Activity中使用 Translucent System Bar 特性，首先需要到AndroidManifest中为指定的Activity设置Theme。但是需要注意的是，我们不能直接在**values/style.xml**直接去自定义 Translucet System Bar 的Theme，因为改特性仅兼容 Android 4.4 开始的平台，所以直接在**values/style.xml**声明引入，工程会报错。有些开发者朋友会在代码中去判断SDK的版本，然后再用代码设置Theme。虽然同样可以实现效果，但个人并不推崇这种做法。我所采取的方法则是建立多个SDK版本的values文件夹，系统会根据SDK的版本选择合适的Theme进行设置。大家可以看到上面我的工程里面有**values**、**values-v19**、**values-v21**。

## 第一种方式

第一种方式，需要做下面三步设置

1、在**values**、**values-v19**、**values-v21**的style.xml都设置一个 Translucent System Bar 风格的Theme

values/style.xml

```xml

<style name="ImageTranslucentTheme" parent="AppTheme">
	<!--在Android 4.4之前的版本上运行，直接跟随系统主题-->
</style>

```

values-v19/style.xml

```xml

<style name="ImageTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	<item name="android:windowTranslucentStatus">true</item>
	<item name="android:windowTranslucentNavigation">true</item>
</style>

```

values-v21/style.xml

```xml

<style name="ImageTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	<item name="android:windowTranslucentStatus">false</item>
	<item name="android:windowTranslucentNavigation">true</item>
	<!--Android 5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色-->
	<item name="android:statusBarColor">@android:color/transparent</item>
</style>

```

上面需要注意的地方是，无论你在哪个SDK版本的values目录下，设置了主题，都应该在最基本的values下设置一个同名的主题。这样才能确保你的app能够正常运行在 Android 4.4 以下的设备。否则，肯定会报找不到Theme的错误。

2、在AndroidManifest.xml中对指定Activity的theme进行设置

```xml

<activity
	android:name=".ui.ImageTranslucentBarActivity"
	android:label="@string/image_translucent_bar"
	android:theme="@style/ImageTranslucentTheme" />

```

3、在Activity的布局文件中设置背景图片，同时，需要把android:fitsSystemWindows设置为true

activity_image_translucent_bar.xml

```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@mipmap/env_bg"
    android:fitsSystemWindows="true">

</RelativeLayout>


```

到此，第一种实现方式完成，大家可以看看下面的效果

![ImageTranslucentTheme效果](http://a.hiphotos.baidu.com/image/pic/item/63d0f703918fa0ecc0b53174219759ee3d6ddb5d.jpg)

就跟中华万年历的天气预报效果界面一样，系统的整个导航栏都融入了app的界面中，背景图片填满了整个屏幕，看起来舒服很多。这里还有一个android:fitsSystemWindows设置需要注意的地方，后面会在细讲。接下来看第二种实现。

## 方式二

相比中华万年历，QQ音乐采用的是另外一种实现的方式，它将app的Tab栏和系统导航栏分开来设置。

![QQ音乐效果风格](http://c.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db1f9d12c1d0e23dd54574e74cf.jpg)

由于它的Tab栏是纯色的，所以只要把系统通知栏的颜色设置和Tab栏的颜色一致即可，实现上相比方法一要简单很多。同样要到不同SDK版本的values下，创建一个同名的theme，在values-v21下，需要设置系统导航栏的颜色：

values-v21/style.xml

```xml

<style name="ColorTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	<item name="android:windowTranslucentStatus">false</item>
	<item name="android:windowTranslucentNavigation">true</item>
	<item name="android:statusBarColor">@color/color_31c27c</item>
</style>

```

再到ColorTranslucentBarActivity的布局文件activity_color_translucent_bar.xml中设置Tab栏的颜色

```xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:orientation="vertical">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="55dp"
        android:background="@color/color_31c27c">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="QQ Music"
            android:textColor="@android:color/white"
            android:textSize="20sp" />

    </RelativeLayout>
</LinearLayout>

```
到此，我们就可以得到和QQ音乐主界面一样的效果了。

![QQ音乐界面实现效果](http://c.hiphotos.baidu.com/image/pic/item/f636afc379310a55c9cd928db04543a9832610d9.jpg)

到此，就大体介绍完了 Translucent System Bar 的两种实现方式了。

## android:fitsSystemWindows的“踩坑”

通过前面的两种方式，大家估计会留意到一个地方，就是所有实现 Translucent System Bar 效果的Activity，都需要在根布局里设置 android:fitsSystemWindows="true" 。设置了该属性的作用在于，不会让系统导航栏和我们app的UI重叠，导致交互问题。这样说可能比较抽象，看看下面两个效果图的对比就知道了。

![有fitsSystemWindows设置](http://h.hiphotos.baidu.com/image/pic/item/0dd7912397dda1444a4427cbb5b7d0a20cf4867e.jpg) ![没有fitsSystemWindows设置](http://b.hiphotos.baidu.com/image/pic/item/960a304e251f95cae09927cfce177f3e6709525e.jpg)

注：上面的演示效果，是借助了我的另一个开源项目，详情请戳：[AndroidAlbum](https://github.com/D-clock/AndroidAlbum)

这样的话，如果我有10个Activity要实现这种效果，就要在10个布局文件中做设置，非常麻烦。所以，想到一种方法，在theme中加上如下的android:fitsSystemWindows设置：

```xml

<item name="android:fitsSystemWindows">true</item>

```

发现果真可以了。所有要实现 Translucent System Bar 的Activity，只需要设置了这个theme即可,改起来也很方便。可惜，后来出现了一个BUG，让我还是得老老实实的回去布局文件中设置。

![Toast文字错位](http://h.hiphotos.baidu.com/image/pic/item/1e30e924b899a901cb46e14f1a950a7b0208f55c.jpg)

Toast打印出来的文字都往上偏移了。这里也是我疏忽的地方，因为在布局文件中设置是对View生效，而到了theme进行设置则是对Window生效了，两者在实现上就不一样了。所以，最终只能改回原来的方式去实现。

## 实践总结

最后做一下小小的总结：

- 方式一适用于app中没有导航栏，且整体的背景是一张图片的界面；
- 方式二适用于app中导航栏颜色为纯色的界面；
- android:fitsSystemWindows设置要在布局文件中，不要到theme中设置；

怎样，介绍到这里，你会使用 Translucent System Bar 了吗？赶快到你的app中引入吧！

## 补充更新（2016-02-19）

一些热心的网友反馈，在Android 4.4平台上使用第二种方法失效。我立马跑到Android4.4的真机运行一遍，果真出现下面的bug，顶部变成黑白渐变了。

![4.4平台上第二种方案出现的BUG](http://g.hiphotos.baidu.com/image/pic/item/8718367adab44aed68e14983b41c8701a18bfb2e.jpg)

在此，先为自己的疏忽向广大读者说声抱歉。以后会最大程度的避免这种低级错误的产生。下面给出此Bug的修复方案：

第一步：去到 ColorTranslucentBarActivity 的布局文件中，将布局划分成为**标题布局**和**内容布局**两部分；

第二步：将 ColorTranslucentBarActivity 的**根布局**颜色设置与**标题布局**的颜色一致，并将**内容布局**设置为白色；

```xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/color_31c27c"
    android:fitsSystemWindows="true"
    android:orientation="vertical">

    <!--标题布局-->
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="55dp"
        android:background="@color/color_31c27c">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="QQ Music"
            android:textColor="@android:color/white"
            android:textSize="20sp" />

    </RelativeLayout>

    <!--内容布局-->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white"
        android:orientation="vertical">

        <Button
            android:id="@+id/btn_show_toast"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Show a toast" />
    </LinearLayout>

</LinearLayout>


```

经过以上两步，即可在 4.4 平台上实现 Translucent System Bar 的效果 。最后附上修复bug后的效果图一张。

![Android 4.4平台bug修复后的效果图](http://g.hiphotos.baidu.com/image/pic/item/37d3d539b6003af34c163607322ac65c1038b62d.jpg)

## 补充更新（2016-02-22）

很多童鞋反应，在每个布局文件中都要写上 **android:fitsSystemWindows="true"** ,有没有更佳方便的方法，本人当时没有思路。今天收到[coder_sharp ](http://www.jianshu.com/users/ef3b7467fa60/timeline)童鞋反馈的一种更为简便的思路

![coder_sharp童鞋提供的新思路](http://c.hiphotos.baidu.com/image/pic/item/d043ad4bd11373f040577ff3a30f4bfbfbed0475.jpg)

个人把他的思路，整理成代码，如下：

```java

public abstract class TranslucentBarBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(getLayoutResId());//把设置布局文件的操作交给继承的子类

        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */
    abstract protected int getLayoutResId();
}

```

所有需要实现效果的界面继承以上的父类，并实现 **getLayoutResId** 抽象方法即可，就可以不用在布局文件中不断做重复操作了，具体代码详见工程中的 **TranslucentBarBaseActivity** 和 **BestTranslucentBarActivity**。

## 补充更新（2016-02-25）

近几天在琢磨 Material Design 的一些新控件效果，意外的发现上面提到的第二种方式，在将原 **values-v21/style.xml**

```xml

<style name="ColorTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	....
	.... 
	<item name="android:statusBarColor">@color/color_31c27c</item>

</style>

```

换成

```xml

<style name="ColorTranslucentTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	....
	.... 
	<item name="android:statusBarColor">@android:color/transparent</item>

</style>


```

之后，依旧可以实现同样的效果。那么，到了这里你就可以发现，上面提到的两种方式从本质上其实是殊途同归（最终总结得到的就是一种方式）！