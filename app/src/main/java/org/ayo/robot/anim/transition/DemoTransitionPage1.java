package org.ayo.robot.anim.transition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

import org.ayo.robot.anim.App;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/31.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DemoTransitionPage1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.requestFeatureFromTransition(this);
        setContentView(R.layout.image_block2);

        if(App.supportMaterial()){
            getWindow().setExitTransition(new Fade());
            getWindow().setReenterTransition(new Explode());
            getWindow().setEnterTransition(new Slide());
            getWindow().setReturnTransition(new Fade());
        }
    }

    public void clicked(View v) {
        App.startActivityConsiderTransitionSupport(this, DemoTransitionPage2.class);
    }

}
