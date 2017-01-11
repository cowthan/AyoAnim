package org.ayo.robot.anim;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.view.Window;

import org.ayo.animate.AyoAnim;

/**
 * Created by cowthan on 2016/1/24.
 */
public class App extends Application{

    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AyoAnim.init(this);
        org.ayo.sample.menu.notify.Toaster.init(this);
    }

    public static boolean supportMaterial(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void startActivityConsiderTransitionSupport(Activity from, Class to){
        if(supportMaterial()){
            Intent intent = new Intent(from, to);
            ActivityOptions activityOptions  = ActivityOptions.makeSceneTransitionAnimation(from);
            from.startActivity(intent, activityOptions.toBundle());
        }else{
            org.ayo.sample.menu.notify.Toaster.toastShort("系统版本低于5.0，不支持Transition");
            Intent intent = new Intent(from, to);
            from.startActivity(intent);
        }

    }

    public static void requestFeatureFromTransition(Activity a){
        if(App.supportMaterial()){
            //设置允许通过ActivityOptions.makeSceneTransitionAnimation发送或者接收Bundle
            a.getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            //设置使用TransitionManager进行动画，不设置的话系统会使用一个默认的TransitionManager
            a.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
    }
}
