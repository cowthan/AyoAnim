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

public class DemoScaleY extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoScaleY.class);
        a.startActivityForResult(intent, 205);
    }
    @Override
    protected String getType() {
        return "scaleY";
    }
    @Override
    protected float getMinValue() {
        return -100;
    }

    @Override
    protected float getMaxValue() {
        return 200;
    }
    //200对应  -2到2
    @Override
    protected float getDefaultFrom() {
        return 100;
    }

    @Override
    protected float getDefaultTo() {
        return 200;
    }
    @Override
    protected Animator createAnimator(View v){

        ViewGroup parent = (ViewGroup) v.getParent();
        float from = getFrom()/100f;
        float to = getTo()/100f;
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "scaleY", from , to);
        return o;
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
