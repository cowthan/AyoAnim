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

public class DemoScaleX extends DemoBase{
    public static void startForResult(Activity a){
        Intent intent = new Intent(a, DemoScaleX.class);
        a.startActivityForResult(intent, 205);
    }
    @Override
    protected String getType() {
        return "scaleX";
    }
    @Override
    protected float getMinValue() {
        return -2;
    }

    @Override
    protected float getMaxValue() {
        return 2;
    }

    @Override
    protected float getDefaultFrom() {
        return 0.5f;
    }

    @Override
    protected float getDefaultTo() {
        return 2;
    }
    @Override
    protected Animator createAnimator(View v){

        ViewGroup parent = (ViewGroup) v.getParent();
        int distance = parent.getWidth() - v.getLeft();
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "scaleX", getFrom(), getTo());
        return o;
    }


    @Override
    protected View createTestView() {
        return null;
    }
}
