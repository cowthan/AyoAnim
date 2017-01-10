package org.ayo.robot.anim.material;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import org.ayo.robot.anim.AyoActivityAttacher;
import org.ayo.robot.anim.R;
import org.ayo.sample.menu.notify.Toaster;

/**
 */

public class DemoCircularReveal extends AyoActivityAttacher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demo_l_circular_reveal);

        final View btn1 = findViewById(R.id.btn1);
        final View btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = null;
                    animator = ViewAnimationUtils.createCircularReveal(
                            btn1,
                            btn1.getWidth()/2,
                            btn1.getHeight()/2,
                            btn1.getWidth(),
                            0);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(2000);
                    animator.start();
                }else{
                    Toaster.toastShort("只支持5.0及其以上系统");
                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = null;
                    animator = ViewAnimationUtils.createCircularReveal(
                            btn2,
                            0,
                            0,
                            0,
                            (float) Math.hypot(btn2.getWidth(), btn2.getHeight()));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(2000);
                    animator.start();
                }else{
                    Toaster.toastShort("只支持5.0及其以上系统");
                }

            }
        });
    }
}
