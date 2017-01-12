package org.ayo.robot.anim;

import android.os.Bundle;

import org.ayo.robot.anim.ease.DemoEaseActivity;
import org.ayo.robot.anim.ease.EvaluatorActivity;
import org.ayo.robot.anim.ease.InterpolatorActivity;
import org.ayo.robot.anim.frame.DemoFrameAnimate;
import org.ayo.robot.anim.layout.DemoLayoutTransition;
import org.ayo.robot.anim.layout.DemoLayoutTransition2;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_fade_in;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_fade_out;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_scale_in;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_scale_out;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_slide_in;
import org.ayo.robot.anim.layout_tween.DemoLayoutAnimation_slide_out;
import org.ayo.robot.anim.material.DemoCircularReveal;
import org.ayo.robot.anim.material.DemoStateList;
import org.ayo.robot.anim.material.DemoTouchFeedback;
import org.ayo.robot.anim.path.PathAnimDemo1;
import org.ayo.robot.anim.path.PathAnimDemo2;
import org.ayo.robot.anim.propertyanim.AnimatorCreateActivity;
import org.ayo.robot.anim.propertyanim.DemoAlpha;
import org.ayo.robot.anim.propertyanim.DemoRotate;
import org.ayo.robot.anim.propertyanim.DemoRotateX;
import org.ayo.robot.anim.propertyanim.DemoRotateY;
import org.ayo.robot.anim.propertyanim.DemoScaleX;
import org.ayo.robot.anim.propertyanim.DemoScaleXY;
import org.ayo.robot.anim.propertyanim.DemoScaleY;
import org.ayo.robot.anim.propertyanim.DemoTranslateX;
import org.ayo.robot.anim.propertyanim.DemoTranslateY;
import org.ayo.robot.anim.svg.DemoSvgDraw;
import org.ayo.robot.anim.svg_vectordrawable.DemoVector;
import org.ayo.robot.anim.transition.ActivityTransition;
import org.ayo.robot.anim.transition.DemoTransitionPage1;
import org.ayo.robot.anim.transition.DemoTransitionPage3;
import org.ayo.robot.anim.viewanim.fade_in;
import org.ayo.robot.anim.viewanim.fade_out;
import org.ayo.robot.anim.viewanim.hold;
import org.ayo.robot.anim.viewanim.rotate;
import org.ayo.robot.anim.viewanim.rotate_3d;
import org.ayo.robot.anim.viewanim.scale_in;
import org.ayo.robot.anim.viewanim.scale_out;
import org.ayo.robot.anim.viewanim.slide_in_from_bottom;
import org.ayo.robot.anim.viewanim.slide_in_from_left;
import org.ayo.robot.anim.viewanim.slide_in_from_right;
import org.ayo.robot.anim.viewanim.slide_in_from_top;
import org.ayo.robot.anim.viewanim.slide_out_to_bottom;
import org.ayo.robot.anim.viewanim.slide_out_to_left;
import org.ayo.robot.anim.viewanim.slide_out_to_right;
import org.ayo.robot.anim.viewanim.slide_out_to_top;
import org.ayo.robot.anim.yoyo.DemoYoyoActivity;
import org.ayo.sample.menu.Leaf;
import org.ayo.sample.menu.MainPagerActivity;
import org.ayo.sample.menu.Menu;
import org.ayo.sample.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MainPagerActivity {

    private static List<Menu> menus;

    @Override
    public List<Menu> getMenus() {
        return menus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);

    }

    private void init(){
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    static{
        menus = new ArrayList<Menu>();

        ///--------------------------菜单1：属性动画
        Menu m3 = new Menu("基本概念", R.drawable.find_normal, R.drawable.find_pressed);
        menus.add(m3);
        {
            MenuItem menuItem = new MenuItem("插值器和估值器", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("插值器：TimeIntercepter", "", InterpolatorActivity.class, 1));
                menuItem.addLeaf(new Leaf("估值器：TypeEvaluator", "", EvaluatorActivity.class, 1));
                menuItem.addLeaf(new Leaf("缓动函数", "", EvaluatorActivity.class, 1));
            }

            menuItem = new MenuItem("库", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("ease库demo", "", DemoEaseActivity.class, 1));
                menuItem.addLeaf(new Leaf("animate库demo", "", DemoYoyoActivity.class, 1));
            }

        }


        ///--------------------------菜单1：View动画
        Menu m1 = new Menu("View动画", R.drawable.weixin_normal, R.drawable.weixin_pressed);
        menus.add(m1);
        {
            MenuItem menuItem1 = new MenuItem("入门", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem1);
            {
                menuItem1.addLeaf(new Leaf("帧动画", "", DemoFrameAnimate.class));
                menuItem1.addLeaf(new Leaf("fade_in", "", fade_in.class));
                menuItem1.addLeaf(new Leaf("fade_out", "", fade_out.class));
                menuItem1.addLeaf(new Leaf("scale_in", "", scale_in.class));
                menuItem1.addLeaf(new Leaf("scale_out", "", scale_out.class));
                menuItem1.addLeaf(new Leaf("rotate", "", rotate.class));
                menuItem1.addLeaf(new Leaf("slide_in_from_bottom", "", slide_in_from_bottom.class));
                menuItem1.addLeaf(new Leaf("slide_in_from_top", "", slide_in_from_top.class));
                menuItem1.addLeaf(new Leaf("slide_in_from_left", "", slide_in_from_left.class));
                menuItem1.addLeaf(new Leaf("slide_in_from_right", "", slide_in_from_right.class));
                menuItem1.addLeaf(new Leaf("slide_out_to_bottom", "", slide_out_to_bottom.class));
                menuItem1.addLeaf(new Leaf("slide_out_to_top", "", slide_out_to_top.class));
                menuItem1.addLeaf(new Leaf("slide_out_to_left", "", slide_out_to_left.class));
                menuItem1.addLeaf(new Leaf("slide_out_to_right", "", slide_out_to_right.class));
                menuItem1.addLeaf(new Leaf("hold--不动", "", hold.class));
                menuItem1.addLeaf(new Leaf("通过Matrix自定义动画--Rotate3D", "", rotate_3d.class));
                menuItem1.addLeaf(new Leaf("动画组合", "", null));
                menuItem1.addLeaf(new Leaf("动画编辑器", "", null));
            }

            MenuItem menuItem2 = new MenuItem("Layout动画", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("setLayoutAnimation", "", DemoLayoutAnimation_fade_in.class));
                menuItem2.addLeaf(new Leaf("fade_in", "", DemoLayoutAnimation_fade_in.class));
                menuItem2.addLeaf(new Leaf("fade_out", "", DemoLayoutAnimation_fade_out.class));
                menuItem2.addLeaf(new Leaf("scale_in", "", DemoLayoutAnimation_scale_in.class));
                menuItem2.addLeaf(new Leaf("scale_out", "", DemoLayoutAnimation_scale_out.class));
                menuItem2.addLeaf(new Leaf("slide_in", "", DemoLayoutAnimation_slide_in.class));
                menuItem2.addLeaf(new Leaf("slide_in", "", DemoLayoutAnimation_slide_out.class));
            }

            menuItem2 = new MenuItem("Activity切换", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("示例", "", null));
            }

            menuItem2 = new MenuItem("Fragment切换", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("有啥", "", null));
            }
        }

        ///--------------------------菜单1：属性动画
        m3 = new Menu("属性动画", R.drawable.find_normal, R.drawable.find_pressed);
        menus.add(m3);
        {
            MenuItem menuItem = new MenuItem("普通", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("平移X", "", DemoTranslateX.class));
                menuItem.addLeaf(new Leaf("平移Y", "", DemoTranslateY.class));
                menuItem.addLeaf(new Leaf("缩放", "", DemoScaleXY.class));
                menuItem.addLeaf(new Leaf("缩放X", "", DemoScaleX.class));
                menuItem.addLeaf(new Leaf("缩放Y", "", DemoScaleY.class));
                menuItem.addLeaf(new Leaf("旋转", "", DemoRotate.class));
                menuItem.addLeaf(new Leaf("旋转X", "", DemoRotateX.class));
                menuItem.addLeaf(new Leaf("旋转Y", "", DemoRotateY.class));
                menuItem.addLeaf(new Leaf("透明度", "", DemoAlpha.class));
                menuItem.addLeaf(new Leaf("动画编辑器", "", AnimatorCreateActivity.class));
                menuItem.addLeaf(new Leaf("ARGB", "", AnimatorCreateActivity.class));
                menuItem.addLeaf(new Leaf("Point", "", AnimatorCreateActivity.class));
            }

            menuItem = new MenuItem("Path动画", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("入门", "", PathAnimDemo1.class));
                menuItem.addLeaf(new Leaf("入门2", "", PathAnimDemo2.class));
                menuItem.addLeaf(new Leaf("Point的估值器", "", null));
            }
        }

        ///--------------------------菜单1：Transition
        m3 = new Menu("Material动画", R.drawable.find_normal, R.drawable.find_pressed);
        menus.add(m3);
        {
            MenuItem menuItem = new MenuItem("Transition", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("直接特效", "", DemoTransitionPage1.class, 1));
                menuItem.addLeaf(new Leaf("共享元素特效", "", DemoTransitionPage3.class, 1));
                menuItem.addLeaf(new Leaf("来自ApiDemo的demo", "", ActivityTransition.class, 1));
            }

            menuItem = new MenuItem("LayoutTransition", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("setTransition--demo 1", "", DemoLayoutTransition.class));
                menuItem.addLeaf(new Leaf("setTransition--demo 2", "", DemoLayoutTransition2.class));
            }

            menuItem = new MenuItem("SVG", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("绘制svg---非Material--svg to Path", "", DemoSvgDraw.class));
                menuItem.addLeaf(new Leaf("Animate Vector Drawables：可绘矢量动画", "", DemoVector.class, 1));
            }
            menuItem = new MenuItem("其他", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("触摸反馈：Touch feedback", "", DemoTouchFeedback.class));
                menuItem.addLeaf(new Leaf("Reveal effect", "", DemoCircularReveal.class));
                menuItem.addLeaf(new Leaf("Curved motion：曲线运动", "", PathAnimDemo2.class));
                menuItem.addLeaf(new Leaf("View state changes：视图状态改变", "", DemoStateList.class));
            }
        }


    }
}
