package org.ayo.robot.anim.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.ayo.robot.anim.App;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/31.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DemoTransitionPage3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.requestFeatureFromTransition(this);
        setContentView(R.layout.image_block3);

        if(App.supportMaterial()){
            getWindow().setExitTransition(new Explode());
            getWindow().setReenterTransition(new Slide());
        }
    }

    public void clicked(View v) {

        ImageView iv = (ImageView) findViewById(R.id.ducky);
        Button btn = (Button) findViewById(R.id.btn);

        if(App.supportMaterial()){
            Intent intent = new Intent(this, DemoTransitionPage4.class);
            ActivityOptions activityOptions  = ActivityOptions.makeSceneTransitionAnimation(this,
                    android.util.Pair.create((View)iv, "share_img"),
                    android.util.Pair.create((View)btn, "share_btn"));
            startActivity(intent, activityOptions.toBundle());
        }else{
            Intent intent = new Intent(this, DemoTransitionPage4.class);
            startActivity(intent);
        }
    }

}
