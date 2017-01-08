package org.ayo.robot.anim.propertyanim.model;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import org.ayo.animate.ease.EasingFunction;
import org.ayo.animate.ease.Functions;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/8 0008.
 */

public class AnimatorInfo implements Serializable{

    public static final String TYPE_alpha = "alpha";
    public static final String TYPE_rotate = "rotate";
    public static final String TYPE_rotateX = "rotateX";
    public static final String TYPE_rotateY = "rotateY";
    public static final String TYPE_scaleX = "scaleX";
    public static final String TYPE_scaleY = "scaleY";
    public static final String TYPE_translateX = "translateX";
    public static final String TYPE_translateY = "translateY";

    public String type; //appha, rotate, rotateX, rotateY, scaleX, scaleY, translateX, translateY
    public float from;
    public float to;
    public float pivotX, pivotY;
    public int repeatCount = 0;
    public int repeatMode = ValueAnimator.RESTART;
    public int duration = 1000;
    public String easingClassName;

    public ObjectAnimator parse(View v){
        ObjectAnimator o = ObjectAnimator.ofFloat(v, type, from, to);
        v.setPivotX(pivotX);
        v.setPivotY(pivotY);
        o.setRepeatCount(repeatCount);
        o.setRepeatMode(repeatMode);
        o.setDuration(duration);
        EasingFunction fun = Functions.valueOf(easingClassName).getEasingFunction();
        o.setInterpolator(fun);
        return o;
    }

    @Override
    public String toString() {
        return type + ", " + from + ", " + to + ", " + pivotX + ", " + pivotY;
    }
}
