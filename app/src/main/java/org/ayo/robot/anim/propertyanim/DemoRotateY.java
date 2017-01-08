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

public class DemoRotateY extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoRotateY.class);
        a.startActivityForResult(intent, 205);
    }
    @Override
    protected String getType() {
        return "rotationY";
    }
    @Override
    protected float getMinValue() {
        return -360;
    }

    @Override
    protected float getMaxValue() {
        return 360;
    }

    @Override
    protected float getDefaultFrom() {
        return 360;
    }

    @Override
    protected float getDefaultTo() {
        return 720;
    }

    @Override
    protected Animator createAnimator(View v){

        ViewGroup parent = (ViewGroup) v.getParent();
        float from = getFrom() - 360;
        float to = getTo() - 360;
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "rotationY", from, to);
        return o;
    }
    @Override
    protected float parseProgress(int progress) {
        return progress - 360;
    }

    @Override
    protected View createTestView() {
        return null;
    }
}
