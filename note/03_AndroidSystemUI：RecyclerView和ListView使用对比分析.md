# RecyclerView 和 ListView 使用对比分析

又到了更新博文的时间了，最近在看一本很不错的心理学书籍，名字叫做 《拖延心理学》，封面长下面这样子

![](http://diycode.b0.upaiyun.com/photo/2016/24437c3545a6094134f168d5923a6b14.jpg)

书的内容主要是分析拖延症患者是如何把一件事一拖再拖，最后引发最终心里的焦虑和不安，甚至找了各种借口来做一些然并卵的自我安慰，最后再告诉你如何去改善自己的拖延症。目前我看了这书大约六分之一，就被各种心理分析打脸无数次，奈何此书写得太好，所以分享给有需要的童鞋（拖延症患者做好被无情打脸的心理准备）。刚好本人平时也有看书的好习惯，以后看到什么不错的书，也在博文里面分享一下好了。好了，吹水就先到这里，接下来进入文章的正题。

今天这篇文章主要是向大家介绍 RecyclerView 和 ListView 的使用对比，文章主要包括以下几点的内容：

- RecyclerView 和 ListView 布局效果的对比
- RecyclerView 和 ListView 一些常用的功能 和 API 的对比
- RecyclerView 和 ListView 在 Android L 引入**嵌套滚动机制**之后的对比

**有一点需要强调下，文中所有的效果在真机上都是很流畅的，因为录制 GIF 图很容易掉帧，所以特地放慢了操作，千万不要误会成卡顿了啊！**

## 布局效果对比

作为一枚控件，要引起开发者使用的欲望自然先是从显示效果看起（看脸的世界），ListView 大家对效果已经很熟悉了，这里直接跳过，而作为 RecyclerView，它能带给效果要比 ListView 强大得多，如下图

![](http://diycode.b0.upaiyun.com/photo/2016/c27575b1c8ced577e52e8815a28d9945.gif)

Android 默认提供的 RecyclerView 就能支持 **线性布局**、**网格布局**、**瀑布流布局** 三种（这里我们暂且不提代码细节，后文再说），而且同时还能够控制横向还是纵向滚动。怎样，从效果上足以碾压 ListView 有木有。

- **横向滚动的ListView开源控件是不是可以不用再找了？对，你没看错！**
- **瀑布流效果的开源控件是不是可以不用再找了？对，你没看错！**
- **连横向滚动的GridView都不用找了！对，你没看错！**

到此，展示效果上的差距一目了然。

## API 使用对比

当然，一个控件我们不能完全只看效果，关键还是要看实用性，看看有没有方便我们调用的 API提高我们的开发效率。所以，接下来我们就从各个方面来看看 RecyclerView 和 ListView 在提供的API调用上的一些实践比较。

## 基础使用

ListView 的基础使用大家再熟悉不过，其使用的关键点主要如下：

- 继承重写 BaseAdapter 类
- 自定义 ViewHolder 和 convertView 一起完成复用优化工作

由于 ListView 已经老生常谈，所以此处就不去写示例代码了。 RecyclerView 基础使用关键点同样有两点：

- 继承重写 RecyclerView.Adapter 和 RecyclerView.ViewHolder
- 设置布局管理器，控制布局效果

示例代码大致如下：

```java

// 第一步：继承重写 RecyclerView.Adapter 和 RecyclerView.ViewHolder
public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder> {

    ...

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ...
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        ...
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        ...

        public AuthorViewHolder(View itemView) {
            super(itemView);
            ...

        }
    }
}

mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
mRecyclerAdapter = new AuthorRecyclerAdapter(mData);

// 第二步：设置布局管理器，控制布局效果
LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerDemoActivity.this);
linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
mRecyclerView.setLayoutManager(linearLayoutManager);

mRecyclerView.setAdapter(mRecyclerAdapter);

```

从基础使用上看，我们明显可以看出，RecyclerView 相比 ListView 在基础使用上的区别主要有如下几点：

- ViewHolder 的编写规范化了
- RecyclerView 复用 Item 的工作 Google 全帮你搞定，不再需要像 ListView 那样自己调用 setTag
- RecyclerView 需要多出一步 LayoutManager 的设置工作

## 布局效果

在最开始就提到，RecyclerView 能够支持各种各样的布局效果，这是 ListView 所不具有的功能，那么这个功能如何实现的呢？其核心关键在于 [RecyclerView.LayoutManager](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.LayoutManager.html) 类中。从前面的基础使用可以看到，RecyclerView 在使用过程中要比 ListView 多一个 setLayoutManager 步骤，这个 LayoutManager 就是用于控制我们 RecyclerView 最终的展示效果的。

![](http://diycode.b0.upaiyun.com/photo/2016/46436882d32acdb57ea5888ac6eb6e2a.png)

而 LayoutManager 只是一个抽象类而已，系统已经为我们提供了三个相关的实现类 **LinearLayoutManager（线性布局效果）**、**GridLayoutManager（网格布局效果）**、**StaggeredGridLayoutManager（瀑布流布局效果）**。如果你想用 RecyclerView 来实现自己 YY 出来的一种效果，则应该去继承实现自己的 LayoutManager，并重写相应的方法，而不应该想着去改写 RecyclerView。关于 LayoutManager 的使用有下面一些常见的 API（有些在 LayoutManager 实现的子类中）

```java

	canScrollHorizontally();//能否横向滚动
	canScrollVertically();//能否纵向滚动
	scrollToPosition(int position);//滚动到指定位置

	setOrientation(int orientation);//设置滚动的方向
	getOrientation();//获取滚动方向

	findViewByPosition(int position);//获取指定位置的Item View
	findFirstCompletelyVisibleItemPosition();//获取第一个完全可见的Item位置
	findFirstVisibleItemPosition();//获取第一个可见Item的位置
	findLastCompletelyVisibleItemPosition();//获取最后一个完全可见的Item位置
	findLastVisibleItemPosition();//获取最后一个可见Item的位置

```

上面仅仅是列出一些常用的 API 而已，更多的 API 可以查看官方文档，通常你想用 RecyclerView 实现某种效果，例如指定滚动到某个 Item 位置，但是你在 RecyclerView 中又找不到可以调用的 API 时，就可以跑到 LayoutManager 的文档去看看，基本都在那里。**另外还有一点关于瀑布流布局效果 StaggeredGridLayoutManager 想说的，看到网上有些文章写的示例代码，在设置了 StaggeredGridLayoutManager 后仍要去 Adapter 中动态设置 View 的高度，才能实现瀑布流，这种做法是完全错误的，之所以 StaggeredGridLayoutManager 的瀑布流效果出不来，基本是 item 布局的 xml 问题以及数据问题导致。如果要在 Adapter 中设置 View 的高度，则完全违背了 LayoutManager 的设计理念了。**

## 空数据处理

ListView 提供了 setEmptyView 这个 API 来让我们处理 Adapter 中数据为空的情况，只需轻轻一 set 就能搞定一切。代码设置和效果如下

```java

		mListView = (ListView) findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.empty_layout));//设置内容为空时显示的视图

```

![](http://diycode.b0.upaiyun.com/photo/2016/edf56bd5df3d1dea356c21edb38277f5.gif)

而 RecyclerView 并没有提供此类 API，所以，这些工作需要自己来干。虽说这类逻辑并不复杂，但是作为一个有追求的程序猿，能偷懒还是要想着偷懒的嘛...

## HeaderView 和 FooterView

在 ListView 的设计中，存在着 HeaderView 和 FooterView 两种类型的视图，并且系统也提供了相应的 API 来让我们设置

![](http://diycode.b0.upaiyun.com/photo/2016/946d0b54b430a0588c8f5916105184c7.png)

使用 HeaderView 和 FooterView 的好处在于，当我们指向在 ListView 的头部或者底部添加一个 View 的时候（例如：添加一个下拉刷新视图，底部加载更多视图），我们可以不用影响到 Adapter 的编写，使用起来相当方便。而到了 RecyclerView 中，翻来翻去你都不会看到类似 addFooterView 、 addFooterView 这种 API，是的，没错，压根就没有...这也是 RecyclerView 让我觉得很鸡肋的地方，按道理说应该是使用频率很高的 API，居然都不给我（一脸懵逼）。那有木有解决方法呢，肯定有，系统不给就自己动手丰衣足食呗。我想到的方法比较笨，就是在 Adapter 中提供三种类型（Header，Footer以及普通Item）的 Type 和 View，但是这种方法写起来很麻烦，对 Adapter 的影响很大，改动的代码量多并且也容易产生BUG。这里需要吹一下鸿洋老师的解决方案了，大家可以看他的文章：[优雅的为RecyclerView添加HeaderView和FooterView](http://blog.csdn.net/lmj623565791/article/details/51854533) 。他的实现思路是通过装饰者模式来扩充 Adapter 的功能，从而实现添加 HeaderView 和 FooterView，并且不影响 Adapter 的编写工作，牛逼的是还能支持多个 HeaderView 和 FooterView （虽然我暂时想不到有什么应用场景，哈哈，不过先记着，以后说不定有用）。这是我目前看到的最赞成的方案了，如果你有更 nice 的方案，也欢迎给我留言。

## 局部刷新

在 ListView 中，说到刷新很多童鞋会记得 notifyDataSetChanged() ，但是说到局部刷新估计有很多童鞋就知道得比较少了。我们知道在更新了 ListView 的数据源后，需要通过 Adapter 的 notifyDataSetChanged 来通知视图更新变化，这样做比较的好处就是调用简单，坏处就是它会重绘每个 Item，但实际上并不是每个 Item 都需要重绘。最常见的，例如：朋友圈点赞，点赞只是更新当前点赞的Item，并不需要每个 Item 都更新。然而 ListView 并没有提供局部刷新刷新某个 Item 的 API 给我们，同样自己自足，套路大致如下方的 updateItemView：

```java

public class AuthorListAdapter extends BaseAdapter {

    ...

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ...
        return convertView;
    }

    /**
     * 更新Item视图，减少不必要的重绘
     *
     * @param listView
     * @param position
     */
    public void updateItemView(ListView listView, int position) {
		//换算成 Item View 在 ViewGroup 中的 index
        int index = position - listView.getFirstVisiblePosition();
        if (index >= 0 && index < listView.getChildCount()) {
			//更新数据
            AuthorInfo authorInfo = mAuthorInfoList.get(position);
            authorInfo.setNickName("Google Android");
            authorInfo.setMotto("My name is Android .");
            authorInfo.setPortrait(R.mipmap.ic_launcher);
			//更新单个Item
            View itemView = listView.getChildAt(index);
            getView(position, itemView, listView);
        }
    }

}

```

即可实现刷新单个 Item 的效果

![](http://diycode.b0.upaiyun.com/photo/2016/66393468cae0338d021ac4118a2b188d.gif)

[RecyclerView.Adapter](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html) 则我们提供了 notifyItemChanged 用于更新单个 Item View 的刷新，我们可以省去自己写局部更新的工作。

![](http://diycode.b0.upaiyun.com/photo/2016/bd37598114010bc84aadf7d3114f90d8.png)

实现效果如下

![](http://diycode.b0.upaiyun.com/photo/2016/59505c924245231463ed2c202d2325b2.gif)

## 动画效果

如果你细心观察上面 ListView 和 RecyclerView 局部更新 Item 的效果，你会发现相比 ListView 而言， RecyclerView 在做局部刷新的时候有一个渐变的动画效果。这也是 RecyclerView 相对非常值得一提的地方，作为 ListView 自身并没有为我们提供封装好的 API 来实现动画效果切换。所以，如果要给 ListView 的 Item 加动画，我们只能自己通过属性动画来操作 Item 的视图。 Github 也有很多封装得好好的开源库给我们用，如：[ListViewAnimations](https://github.com/nhaarman/ListViewAnimations) 就封装了大量的效果供我们玩耍，童鞋们可以自行学习一下

![](http://diycode.b0.upaiyun.com/photo/2016/cd96683cf2a6a688fa60b13171560758.gif)

ListViewAnimations 主要大致实现方式是通过装饰者模式来扩充 Adapter ，并结合属性动画 Animator 来添加动画效果。相比之下，RecyclerView 则为我们提供了很多基本的动画 API ，如下方的**增删移改**

![](http://diycode.b0.upaiyun.com/photo/2016/90b72153262dc3248e344e2b61283aaa.png)

简单的调用即可实现相应的效果，用起来方便很多，视觉交互上也会更好些

![](http://diycode.b0.upaiyun.com/photo/2016/484664054d68c71f46d2c41c21ff167e.gif)

如果你对动画效果有追求，觉得系统提供的并不能满足你的需求，也可以通过相应接口实现自己的动画效果，方式也非常简单，继承 [RecyclerView.ItemAnimator](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ItemAnimator.html) 类，并实现相应的方法，再调用 RecyclerView 的 setItemAnimator(RecyclerView.ItemAnimator animator) 方法设置完即可实现自定义的动画效果。

![](http://diycode.b0.upaiyun.com/photo/2016/280e2e35e337af045089e6b3dcd831e2.png)

系统也为我们提供了两个默认的动画实现：SimpleItemAnimator 和 DefaultItemAnimator。而 RecyclerView 在不手动调用 setItemAnimator 的情况下，则默认用了内置的 DefaultItemAnimator 。

![](http://diycode.b0.upaiyun.com/photo/2016/5dd11c95e93b35a56689d2b06d5f83a5.png)

当然编写自定义的 ItemAnimator 也是需要一定工作量的，这里同样为大家介绍一个针对 RecyclerView 开源的动画库：[recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)。其内部封装了大量的动画效果给供我们调用。

![](http://diycode.b0.upaiyun.com/photo/2016/c6719eb660da42647060a6a5bf2993ba.gif)

如果想要学习怎么写一个自定义 ItemAnimator ，上面介绍的开源库的代码同样不容错过。哦，对了，如果谈到动画效果，还有一个很关键的类不得不提，那就是 ItemTouchHelper 。

![](http://diycode.b0.upaiyun.com/photo/2016/bd731c6cfbfc444a980317b112339004.png)

ItemTouchHelper 是系统为我们提供的一个用于滑动和删除 RecyclerView 条目的工具类，用起来也是非常简单的，大致两步：

- 创建 ItemTouchHelper 实例，同时实现 ItemTouchHelper.SimpleCallback 中的抽象方法，用于初始化 ItemTouchHelper
- 调用 ItemTouchHelper 的 attachToRecyclerView 方法关联上 RecyclerView 即可

示例代码大致如下：

```java

	//ItemTouchHelper 用于实现 RecyclerView Item 拖曳效果的类
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
                int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
                int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
            }

            /**
             * @param recyclerView attach的RecyclerView
             * @param viewHolder 拖动的Item
             * @param target 放置Item的目标位置
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//要拖曳的位置
                int toPosition = target.getAdapterPosition();//要放置的目标位置
                Collections.swap(mData, fromPosition, toPosition);//做数据的交换
        		notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            /**
             * @param viewHolder 滑动移除的Item
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
                mData.remove(position);//删除数据
        		notifyItemRemoved(position);
            }

        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

```

虽然代码中有注释，但还是稍稍解释一下，主要重写的是 getMovementFlags 、 onMove 、 onSwiped 三个抽象方法，getMovementFlags 用于告诉系统，我们的 RecyclerView 到底是支持滑动还是拖曳。如上面的示例代码，就是表示着同时支持上下左右四个方向的拖曳和左右两个方向的滑动效果。如果时滑动，则 onSwiped 会被回调，如果是拖曳 onMove 会被回调。我们再到其中实现相应的业务操作即可。最终效果如下

![](http://diycode.b0.upaiyun.com/photo/2016/fba48cc9812a3aed80fb393c7553bd6f.gif)

想想我们以前用 ListView 的时候要怎么做，RecyclerView 真的爽多了。

## 监听 Item 的事件

ListView 为我们准备了几个专门用于监听 Item 的回调接口，如单击、长按、选中某个 Item 等

![](http://diycode.b0.upaiyun.com/photo/2016/335bd70d86ec112c3ffeb5a49185a648.png)

说实话，其实我并不大喜欢这样的设计，如 setOnItemClickListener ，在我们不添加 HeaderView 和 FooterView 的时候，我们可以通过回调参数中的 position 去拿到数据源列表中对应 Item 的数据。

![](http://diycode.b0.upaiyun.com/photo/2016/01ada355feba638774f1b52c71148132.png)

但是，添加了 HeaderView 和 FooterView 之后就不一样了，ListView 会把 HeaderView 和 FooterView 算入 position 内。假设你原先在 onItemClick 回调方法中写了 mDataList.get(position) 这样的业务代码并且这段代码运行良好许久，但在某天你突然加了个 HeaderView 后，这段代码就开始变的有问题了，此时因为 HeaderView 占用的位置算入了 position 之内，所以 position 的最大值实际上是大于 mDataList 包含元素的个数值的，因此代码会报数组越界的错误。当然，我们可以去避免这种问题的发生，就是不通过 position 来获取数据，二是通过回调方法中的 id 。

![](http://diycode.b0.upaiyun.com/photo/2016/0a8b4c0f522f35a4eb01e60b92a63e93.png)

这样就不会受到添加 HeaderView 和 FooterView 的影响了，这个 id 的值就是来自我们编写好的 Adapter 中的 getItemId 函数中返回的 id，使用 IDE 生成此函数时，默认是返回0，需要将 position 作为 Item 的 id 返回。

![](http://diycode.b0.upaiyun.com/photo/2016/30df2553df77c510ec2b130ece57007e.png)

并同时在 onItemClick 中判断 id 是否值为 -1，因为 HeaderView 和 FooterView 的返回值就是 -1。前面讲到我并不大喜欢 setOnItemClickListener 这种设计，除了由这些因素的影响外，更关键的是个人认为针对 Item 的事件实际上写在 getView 方法中会更加合适，如 setOnItemClickListener 我更喜欢用在 getView 中为每个 convertView 设置 setOnClickListener 的方式去取代它。

而再来看看 RecyclerView ，它并没有像 ListView 提供太多关于 Item 的某种事件监听，唯一的就是 addOnItemTouchListener 

![](http://diycode.b0.upaiyun.com/photo/2016/f80280e56e3e7ae7e383385350de4afd.png)

API 的名字言简意赅，就是监听 Item 的触摸事件。如果你想要拥有 ListView 那样监听某个 Item 的某个操作方法，可以看看这篇文章 [RecyclerView无法添加onItemClickListener最佳的高效解决方案](http://blog.csdn.net/liaoinstan/article/details/51200600) ，作者的实现思路就是通过 addOnItemTouchListener 和系统提供的 GestureDetector 手势判断结合实现的。不过，我还是更喜欢原先自己用惯的方式，虽然会被人吐槽 new 出了大量的监听器，但个人觉得这样封装会更好（哈哈，也换大家吐槽这种方式的其他劣处，看看我是不是需要改改了）。

OK，关于 RecyclerView 和 ListView 一些常用的功能和 API 的对比，就大致到此。最后再来谈谈 Android L 开始之后，对 RecyclerView 和 ListView 的使用存在什么影响。

## 嵌套滚动机制

熟悉 Android 触摸事件分发机制的童鞋肯定知道，Touch 事件在进行分发的时候，由父 View 向它的子 View 传递，一旦某个子 View 开始接收进行处理，那么接下来所有事件都将由这个 View 来进行处理，它的 ViewGroup 将不会再接收到这些事件，直到下一次手指按下。而嵌套滚动机制（NestedScrolling）就是为了弥补这一机制的不足，为了让子 View 能和父 View 同时处理一个 Touch 事件。关于嵌套滚动机制（NestedScrolling），实现上相对是比较复杂的，此处就不去拓展说明，其关键在于 **NestedScrollingChild** 和 **NestedScrollingParent** 两个接口，以及系统对这两个接口的实现类 **NestedScrollingChildHelper** 和 **NestedScrollingParentHelper** 大家可以查阅相关的资料。可能说起来太抽象了，这里拿一个简单的示例效果来说明好了，如下方是用 CollapsingToolbarLayout 和 RecyclerView 搭配的效果：

![](http://diycode.b0.upaiyun.com/photo/2016/e4b453a5f25223ba0bc7982da1684fa2.gif)

一开始上面一大块区域就是 CollapsingToolbarLayout ，下方的列表是 RecyclerView ，当然 RecyclerView 向上滑动时，CollapsingToolbarLayout 能够同时网上收缩，直到只剩下顶部的 Toolbar。之所以能够实现这种效果，就是完全依赖于嵌套滚动机制，如果没有这套机制，按照原有的触摸事件分发逻辑， RecyclerView 内部已经把 Touch 事件消耗掉了，完全无法引起顶部的 CollapsingToolbarLayout 产生联动收缩的效果。我们可以查看 RecyclerView 的代码实现，发现它已经实现了 NestedScrollingChild 接口

![](http://diycode.b0.upaiyun.com/photo/2016/0af6bd92da547da00c431f473b08a1af.png)

如果在其他代码布局都不变的情况下，我们把 RecyclerView 替换成 ListView ，则无法产生上面图中的动态效果，因为 ListView 并不支持嵌套滚动机制，事件在 ListView 内部已经被消耗且无法传递出来，大家可以自行尝试验证一下。对下方 AppBarLayout 的使用也是同理。

![](http://diycode.b0.upaiyun.com/photo/2016/2ecb34e20deb41a979cdcfd8a1881005.gif)

关于 AppBarLayout 和 CollapsingToolbarLayout，它们并不是什么第三方控件，而是 Android 官方提供的 MaterialDesign 设计风格的控件，大家可以在官方文档中搜索到它们的资料，如果你用过 Android 原生系统，你可以在通讯录等系统内置应用看到它们的身影。如果你想使用类似 AppBarLayout 、 CollapsingToolbarLayout 这种需要嵌套滚动的机制才能达到效果的控件，那么 RecyclerView 将是你的不二之选，因为 ListView 在此根本无法发挥作用。同样的，ScrollView 也是不支持嵌套滚动机制，但是你可以使用 NestedScrollView 。

## 总结

这里只是客观的去分析一些使用上的差异，并不是想突出哪个控件好哪个控件不好，大家可以根据自己的使用场景来选择是要用 RecyclerView 还是 ListView，毕竟，**合适的才是最好的**。洋洋晒晒写了一大堆，相信你已经看到不少 RecyclerView 和 ListView 使用上的区别了，不知道有没有我在文中没提到的呢，欢迎下方留言。文中所有的项目实践代码都在这里：https://github.com/D-clock/AndroidSystemUiTraining ，需要的同学可以自行下载查看。

**本文同步更新到 [我的简书](http://www.jianshu.com/users/ec95b5891948/latest_articles) 和 [我的Diycode](http://www.diycode.cc/d_clock)，如果你觉得文章不错，期待我的新作品，欢迎关注 [我的简书](http://www.jianshu.com/users/ec95b5891948/latest_articles) 和 [我的Diycode](http://www.diycode.cc/d_clock) ，如果能够再 Follow 一下 [我的Github](https://github.com/D-clock) 就更好了。**
 


