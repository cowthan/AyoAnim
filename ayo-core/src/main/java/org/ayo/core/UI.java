package org.ayo.core;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.ayo.core.systembar.SystemBarTintManager;

/**
 * 控制通用UI：
 * systembar，navigationbar
 * clipToPadding，clipToChild   http://www.cnblogs.com/over140/p/3508335.html   http://www.cnblogs.com/xitang/p/3606578.html
 * 肯定会和一个activity绑定
 * 状态栏浅色支持：
 * ——安卓手机状态栏一般默认字体是浅色，背景是深色，非要反过来，不太好弄，就小米魅族公开支持，安卓6.0的手机原生系统支持，但三星的6.0又不支持
 * ——三星6.0的状态栏设置，还不能给设置成白色，设置成白色，它就变成灰条
 *
 *
 */

public class UI {

    /**
     * @param enable
     */
    public void enableSystemBarTakenByContent(Activity a, boolean enable){

        //checkLayout((ViewGroup) getActivity().getWindow().getDecorView(), "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup content = (ViewGroup) a.getWindow().getDecorView().findViewById(android.R.id.content);
            View contentRoot = content.getChildAt(0);
            //Log.i("111", contentRoot.getClass().getSimpleName());
            contentRoot.setFitsSystemWindows(!enable);

            /// 这俩的默认值本来就是true，具体什么意思，还没搞明白，只有用在可滚动的控件里时，才有区别
            ((ViewGroup) contentRoot).setClipChildren(true);
            ((ViewGroup) contentRoot).setClipToPadding(true);

        }else {
            //Toast.makeText(getActivity(), "doesn't support system bar controll", Toast.LENGTH_LONG).show();
        }
    }


//	public static void checkLayout(ViewGroup root, String prefix){
//		if(root == null) return;
//		for(int i = 0; i < root.getChildCount(); i++){
//			View v = root.getChildAt(i);
//			Log.i("111", prefix + ": " + v.getClass().getSimpleName());
//			if(v instanceof ViewGroup){
//				checkLayout(((ViewGroup) v), "    " + prefix);
//			}
//		}
//	}

    public void renderSystemBar(Activity a, int statusBarColor, int navigateBarColor){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Window win = a.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (true) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);


            SystemBarTintManager tintManager = new SystemBarTintManager(a);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(statusBarColor);

            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setNavigationBarTintColor(navigateBarColor);
        }else{

        }
    }


}
