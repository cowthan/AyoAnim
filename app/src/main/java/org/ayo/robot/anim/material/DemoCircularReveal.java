package org.ayo.robot.anim.material;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;
import org.ayo.sample.menu.notify.ToasterDebug;

/**
 */

public class DemoCircularReveal extends BasePage {


    @Override
    protected int getLayoutId() {
        return R.layout.ac_demo_l_circular_reveal;
    }

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {

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
                    ToasterDebug.toastShort("只支持5.0及其以上系统");
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
                    ToasterDebug.toastShort("只支持5.0及其以上系统");
                }

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
