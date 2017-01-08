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

public class DemoAlpha extends DemoBase{

    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoAlpha.class);
        a.startActivityForResult(intent, 205);
    }


    @Override
    protected String getType() {
        return "alpha";
    }

    @Override
    protected float getMinValue() {
        return 0;
    }

    @Override
    protected float getMaxValue() {
        return 100;
    }

    @Override
    protected float getDefaultFrom() {
        return 100;
    }

    @Override
    protected float getDefaultTo() {
        return 0;
    }


    @Override
    protected Animator createAnimator(View v){

        ViewGroup parent = (ViewGroup) v.getParent();
        int distance = parent.getWidth() - v.getLeft();
        float from = getFrom()/100f;
        float to  = getTo() / 100f;
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "alpha", from, to);
        return o;
    }

    @Override
    protected float parseProgress(int progress) {
        return progress/100f;
    }

    @Override
    protected View createTestView() {
        return null;
    }
}
