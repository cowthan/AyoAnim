package org.ayo.robot.anim.transition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/31.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DemoTransitionPage3 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置允许通过ActivityOptions.makeSceneTransitionAnimation发送或者接收Bundle
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //设置使用TransitionManager进行动画，不设置的话系统会使用一个默认的TransitionManager
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.image_block3);

        getWindow().setExitTransition(new Explode());
        getWindow().setReenterTransition(new Slide());
    }

    public void clicked(View v) {

        ImageView iv = (ImageView) findViewById(R.id.ducky);
        Button btn = (Button) findViewById(R.id.btn);

        Intent intent = new Intent(this, DemoTransitionPage4.class);
        ActivityOptions activityOptions  = ActivityOptions.makeSceneTransitionAnimation(this,
                android.util.Pair.create((View)iv, "share_img"),
                android.util.Pair.create((View)btn, "share_btn"));
        startActivity(intent, activityOptions.toBundle());
    }

}
