package org.ayo.robot.anim.frame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class DemoFrameAnimate extends BasePage {

    private ImageView animationIV;
    private Button buttonA, buttonB, buttonC;
    private AnimationDrawable animationDrawable;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_demo_frame_anim;
    }

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {
        animationIV = (ImageView) findViewById(R.id.animationIV);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);

        buttonA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.anim_signal_1);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }

        });

        buttonB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.stop();
            }

        });

        buttonC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.anim_signal_2);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }
        });
    }

    @Override
    protected void onDestroy2() {

    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }
}
