# AyoAnim
笔记 for：各种安卓动画； 集合 for：好看动画收集和仿写；库 for：动画便利库

* 特别说明：
    * 本项目既是动画的学习笔记和demo
    * 也是收集了一些好看的好用的好玩的动画效果
    * 最主要的，就是一个通用动画库，本来之前一直用daimajia的ease和AndroidViewAnimation，但发现hujiaweibujidao的yava和wava代码更简洁
    * hujiawei ease：https://github.com/hujiaweibujidao/yava
    * hujiawei anim：https://github.com/hujiaweibujidao/wava
    * 代码家 ease：https://github.com/daimajia/AnimationEasingFunctions
    * 代码家 anim：https://github.com/daimajia/AndroidViewAnimations
    * 注意：hujiawei的代码是代码家的衍生，是改进，而不是再造的轮子
    * 像Flyco提供了一组控件库，挺好用，但动画特效确实基于他们自己的一个库，太不统一了，这种情况以后要避免，谁用谁自己避免
    * hujiawei这个怎么都好，就是名字不讲究，其实给开源项目起名字，是很重要的
    * 另外注意：nineold都将被剔除，不再考虑

* 关于animator库
    * 在ayo-animate里
    * ease包下是hujiawei的ease库，只不过我改了一下
    * 为什么改，为了和YoYo结构一样，一组子类代表效果，一个枚举整合


## 1 动画基本概念

这里我们从ValueAnimator出发，来说说TypeEvaluator和TimeInterpolator

首先要明白，动画就是组件的某个属性从一个值到另一个值的变化过程，在变化的持续时间内，属性值产生一系列的连续变化，  
将这些属性值作用于组件上，就看到了动画效果    

ValueAnimator决定了：每一帧在哪里取，取多少帧  
TimeInterpolator：插值器的输入是时间变化率，其实就是时间，输出的是根据时间变化率得到的属性值变化率  
所以如果使用LinearInterpolator，时间变化率和属性值变化率就是线性关系，相当于跳过了Interpolator计算过程  
TypeEvaluator：估值器是根据属性值的变化率计算真正的属性值，这取决于属性值的类型，int，float，color等  
默认的IntEvaluator,FloatEvaluator，是最典型的行为，我们也可以认为这种行为相当于跳过了Evaluate过程  

### 1 TimeInterpolator和TypeEvaluator

先来说说插值器的基本用法，注意,下面代码中的两个动画效果是一样的

```java
ValueAnimator animator1 = new ValueAnimator();
animator1.setFloatValues(0.0f, 1.0f);
animator1.setDuration(1000);
animator1.setInterpolator(new LinearInterpolator());//传入null也是LinearInterpolator
animator1.setEvaluator(new TypeEvaluator() {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        return 100 * fraction;
    }
});
animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Log.e("demo 1", "" + animation.getAnimatedValue());
    }
});

ValueAnimator animator2 = new ValueAnimator();
animator2.setFloatValues(0.0f, 1.0f);
animator2.setDuration(1000);
animator2.setInterpolator(new Interpolator() {
    @Override
    public float getInterpolation(float input) {
        return 100 * input;
    }
});
animator2.setEvaluator(new TypeEvaluator() {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        return fraction;
    }
});
animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Log.e("demo 2", "" + animation.getAnimatedValue());
    }
});

animator1.start();
animator2.start();
```


基于上面的示例，其实daimajia的开源项目AnimationEasingFunctions，也是基于Evaluator的  
而hujiawei的ease库，同时支持插值用法和估值用法



## 2 ease库用法

ease库是取自：
https://github.com/hujiaweibujidao/yava

```java

///枚举用法：as Evaluator
ObjectAnimator animator1 = new ObjectAnimator();
animator1.setTarget(textView1);
animator1.setPropertyName("translationY");
animator1.setFloatValues(0f, -100f);
animator1.setDuration(1000);

animator1.setInterpolator(new LinearInterpolator());
animator1.setEvaluator(EasingFunction.BOUNCE_OUT.getEasingFunction()); //use `EasingFunction.BOUNCE_OUT` as `TypeEvaluator`
animator1.start();


///枚举用法：as Interpolator
ObjectAnimator animator2 = new ObjectAnimator();
animator2.setTarget(textView2);
animator2.setPropertyName("translationY");
animator2.setFloatValues(0f, -100f);
animator2.setDuration(1000);

animator2.setInterpolator(EasingFunction.BOUNCE_OUT.getEasingFunction()); //use `EasingFunction.BOUNCE_OUT` as `Interpolator`
animator2.setEvaluator(new FloatEvaluator());

animator2.start();

///自定义ease函数（也支持插值用法和估值用法）
ObjectAnimator animator1 = new ObjectAnimator();
animator1.setTarget(textView1);
animator1.setPropertyName("translationY");
animator1.setFloatValues(0f, -100f);
animator1.setDuration(1000);

animator1.setInterpolator(new LinearInterpolator());
animator1.setEvaluator(EasingFunction.with(new IFunction() { //customized TypeEvaluator
    @Override
    public float getValue(float input) {
        return input * 2 + 3;
    }
}));

animator1.start();

///如何支持ColorEvaluator或者ARGBEvaluator


```

安卓内置的几个插值器：
```java

TimeInterpolator   --> Interpolator --> BaseInterpolator

LinearInterpolator
AccelerateInterpolator：开始慢，后面快
DecelerateInterpolator
AccelerateDecelerateInterpolator：开始慢，结束慢，中间快
AnticipateInterpolator
OvershootInterpolator
AnticipateOvershootInterpolator
BounceInterpolator
CycleInterpolator

PathInterpolator

```

安卓内置的几个估值器：
```java
TypeEvaluator

ArgbEvaluator
FloatEvaluator
FloatArrayEvaluator
IntEvaluator
IntArrayEvaluator
PointFEvaluator
RectEvaluator


```