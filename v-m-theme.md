Meterial：主题
===========================

学习material design：
http://blog.csdn.net/huyuchaoheaven/article/details/47084813
http://www.open-open.com/lib/view/open1416663769680.html

首先要注意的是：
- 使用material design，很多东西要放到values-21里
- 或者在代码里，判断系统版本是否支持material design：if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
- 对于主题选择，AppCompat会为你考虑Material主题的问题，不用你操心


## 1 主题颜色

我们一般用AppCompatActivity，也就只能用AppCompat的主题，这类主题会按照21划分，21以上会加载Material的主题

```
values目录的style.xml

<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
	<!-- Customize your theme here. -->
	<item name="colorPrimary">@color/colorPrimary</item>
	<item name="colorPrimaryDark">@color/colorPrimaryDark</item>
	<item name="colorAccent">@color/colorAccent</item>

	<item name="android:windowNoTitle">true</item>
	<item name="android:windowContentOverlay">@null</item>
	<item name="android:windowBackground">@drawable/logo</item>
</style>

<style name="AppTheme.Transparent" parent="Theme.AppCompat.Light.NoActionBar">
	<item name="android:windowIsTranslucent">true</item>
	<item name="android:windowAnimationStyle">@style/Animation.Activity.Translucent.Style</item>
</style>

values-21目录的style.xml
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
	<!-- Customize your theme here. -->
	<item name="colorPrimary">@color/colorPrimary</item>
	<item name="colorPrimaryDark">@color/colorPrimaryDark</item>
	<item name="colorAccent">@color/colorAccent</item>
	<item name="android:textColorPrimary">@color/textColorPrimary</item>
	<item name="android:navigationBarColor">@color/navigationBarColor</item>
	<item name="android:colorControlHighlight">@color/colorControlHighlight</item>


	<item name="android:windowNoTitle">true</item>
	<item name="android:windowContentOverlay">@null</item>
	<item name="android:windowBackground">@drawable/logo</item>
</style>

```

几个主color控制的地方：
![](./img/l-design.png)