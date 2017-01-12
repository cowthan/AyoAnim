package org.ayo.robot.anim.propertyanim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.robot.anim.DemoBase;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class DemoTranslateX extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoTranslateX.class);
        a.startActivityForResult(intent, 205);
    }

    @Override
    protected String getType() {
        return "translationX";
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
    boolean isInited  = false;
    @Override
    protected Animator createAnimator(View v){

        ViewGroup parent = (ViewGroup) v.getParent();
        distance = parent.getWidth() - v.getLeft();
        float from = v.getWidth() * (getFrom()-100)/100f;
        float to = v.getWidth() * (getTo()-100)/100f;
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "translationX", from , to);
        return o;
//        AnimatorSet mAnimatorSet = new AnimatorSet();
//        mAnimatorSet.playTogether(
//
//        );
    }
    @Override
    protected float parseProgress(int progress) {
        return (progress-100)/100f;
    }

    @Override
    protected View createTestView() {
        return null;
    }


}
