package org.ayo.robot.anim.propertyanim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.robot.anim.DemoBase;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class DemoTranslateY extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoTranslateY.class);
        a.startActivityForResult(intent, 205);
    }
    @Override
    protected String getType() {
        return "translationY";
    }
    @Override
    protected float getMinValue() {
        return -100;
    }

    @Override
    protected float getMaxValue() {
        return 100;
    }

    @Override
    protected float getDefaultFrom() {
        return 0;
    }

    @Override
    protected float getDefaultTo() {
        return 100;
    }
    int distance = 0;
    @Override
    protected Animator createAnimator(View v){


        ViewGroup parent = (ViewGroup) v.getParent();
        distance = parent.getHeight() - v.getTop();
        float from = v.getHeight() * (getFrom()-100)/100f;
        float to = v.getHeight() * (getTo()-100)/100f;
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "translationY", from , to);
        return o;
//        AnimatorSet mAnimatorSet = new AnimatorSet();
//        mAnimatorSet.playTogether(
//
//        );
    }


    @Override
    protected View createTestView() {
        return null;
    }
}
