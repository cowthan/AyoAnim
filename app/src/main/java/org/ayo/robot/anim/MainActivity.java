package org.ayo.robot.anim;

import android.os.Bundle;

import org.ayo.robot.anim.ease.DemoEaseActivity;
import org.ayo.robot.anim.ease.EvaluatorActivity;
import org.ayo.robot.anim.ease.InterpolatorActivity;
import org.ayo.robot.anim.frame.DemoFrameAnimate;
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
import org.ayo.robot.anim.transition.ActivityTransition;
import org.ayo.robot.anim.transition.DemoTransitionPage1;
import org.ayo.robot.anim.transition.DemoTransitionPage3;
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
                menuItem1.addLeaf(new Leaf("Translate", "", null));
                menuItem1.addLeaf(new Leaf("Scale", "", null));
                menuItem1.addLeaf(new Leaf("Rotate", "", null));
                menuItem1.addLeaf(new Leaf("Alpha", "", null));
                menuItem1.addLeaf(new Leaf("Rotate3D", "", null));
                menuItem1.addLeaf(new Leaf("通过Matrix自定义动画", "", null));
                menuItem1.addLeaf(new Leaf("动画组合", "", null));
                menuItem1.addLeaf(new Leaf("动画编辑器", "", null));
            }

            MenuItem menuItem2 = new MenuItem("Layout动画", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("示例", "", null));
                menuItem2.addLeaf(new Leaf("ListView", "", null));
                menuItem2.addLeaf(new Leaf("RecyclerView", "", null));
                menuItem2.addLeaf(new Leaf("Framelayout", "", null));
            }

            menuItem2 = new MenuItem("Activity切换", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("示例", "", null));
            }

            menuItem2 = new MenuItem("Fragment切换", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m1.addMenuItem(menuItem2);
            {
                menuItem2.addLeaf(new Leaf("clip rect", "", null));
                menuItem2.addLeaf(new Leaf("clip path", "", null));
                menuItem2.addLeaf(new Leaf("clip region：已被废弃，因为不支持matrix", "", null));
                menuItem2.addLeaf(new Leaf("clip region op：两个剪切区域叠加的不同效果", "", null));
            }
        }

        ///--------------------------菜单1：属性动画
        m3 = new Menu("属性动画", R.drawable.find_normal, R.drawable.find_pressed);
        menus.add(m3);
        {
            MenuItem menuItem = new MenuItem("普通", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("平移X", "", DemoTranslateX.class, 1));
                menuItem.addLeaf(new Leaf("平移Y", "", DemoTranslateY.class, 1));
                menuItem.addLeaf(new Leaf("缩放", "", DemoScaleXY.class, 1));
                menuItem.addLeaf(new Leaf("缩放X", "", DemoScaleX.class, 1));
                menuItem.addLeaf(new Leaf("缩放Y", "", DemoScaleY.class, 1));
                menuItem.addLeaf(new Leaf("旋转", "", DemoRotate.class, 1));
                menuItem.addLeaf(new Leaf("旋转X", "", DemoRotateX.class, 1));
                menuItem.addLeaf(new Leaf("旋转Y", "", DemoRotateY.class, 1));
                menuItem.addLeaf(new Leaf("透明度", "", DemoAlpha.class, 1));
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
        m3 = new Menu("Transition动画", R.drawable.find_normal, R.drawable.find_pressed);
        menus.add(m3);
        {
            MenuItem menuItem = new MenuItem("Transition", R.drawable.weixin_normal, R.drawable.weixin_pressed);
            m3.addMenuItem(menuItem);
            {
                menuItem.addLeaf(new Leaf("直接特效", "", DemoTransitionPage1.class, 1));
                menuItem.addLeaf(new Leaf("共享元素特效", "", DemoTransitionPage3.class, 1));
                menuItem.addLeaf(new Leaf("来自ApiDemo的demo", "", ActivityTransition.class, 1));
            }

        }


    }
}
