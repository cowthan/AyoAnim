package org.ayo.robot.anim.propertyanim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.robot.anim.DemoBase;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class DemoScaleXY extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoScaleXY.class);
        a.startActivityForResult(intent, 205);
    }
    @Override
    protected String getType() {
        return "";
    }
    @Override
    protected float getFrom() {
        return 1;
    }

    @Override
    protected float getTo() {
        return 2;
    }
    @Override
    protected Animator createAnimator(View v){

        v.setPivotX(getCustomPivotX()/100 * v.getWidth());
        v.setPivotY(getCustomPivotY()/100 * v.getHeight());

        ViewGroup parent = (ViewGroup) v.getParent();
        int distance = parent.getWidth() - v.getLeft();
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "scaleX", 1, 2);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(v, "scaleY", 1, 2);
        o.setRepeatMode(getRepeatMode());
        o.setRepeatCount(getRepeatCount());
        o2.setRepeatMode(getRepeatMode());
        o2.setRepeatCount(getRepeatCount());
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(o, o2);
        return mAnimatorSet;
    }


    @Override
    protected View createTestView() {
        return null;
    }
}
