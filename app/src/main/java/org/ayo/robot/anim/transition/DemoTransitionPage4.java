package org.ayo.robot.anim.transition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;

import org.ayo.robot.anim.App;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/31.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DemoTransitionPage4 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.requestFeatureFromTransition(this);
        setContentView(R.layout.image_details4);

        if(App.supportMaterial()){
            getWindow().setReturnTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
        }
    }

    public void clicked(View v) {
        App.startActivityConsiderTransitionSupport(this, DemoTransitionPage2.class);
    }

}
