package org.ayo.robot.anim.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/31.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DemoTransitionPage4 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置允许通过ActivityOptions.makeSceneTransitionAnimation发送或者接收Bundle
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //设置使用TransitionManager进行动画，不设置的话系统会使用一个默认的TransitionManager
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.image_details4);

        getWindow().setReturnTransition(new Explode());
        getWindow().setEnterTransition(new Explode());
    }

    public void clicked(View v) {
        Intent intent = new Intent(this, DemoTransitionPage2.class);
        ActivityOptions activityOptions  = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, activityOptions.toBundle());
    }

}
