package org.ayo.animate;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class TweenAnimation {

    public static final int fade_in = R.anim.ayoa_fade_in;
    public static final int fade_out = R.anim.ayoa_fade_out;
    public static final int rotate = R.anim.ayoa_rotate;
    public static final int scale_in = R.anim.ayoa_scale_in;
    public static final int scale_out = R.anim.ayoa_scale_out;
    public static final int slide_in_from_bottom = R.anim.ayoa_slide_in_from_bottom;
    public static final int slide_in_from_left = R.anim.ayoa_slide_in_from_left;
    public static final int slide_in_from_top = R.anim.ayoa_slide_in_from_top;
    public static final int slide_in_from_right = R.anim.ayoa_slide_in_from_right;
    public static final int slide_out_to_bottom = R.anim.ayoa_slide_out_to_bottom;
    public static final int slide_out_to_left = R.anim.ayoa_slide_out_to_left;
    public static final int slide_out_to_top = R.anim.ayoa_slide_out_to_top;
    public static final int slide_out_to_right = R.anim.ayoa_slide_out_to_right;
    public static final int hold = R.anim.ayoa_hold;


    public static void apply(Activity a, int enterAnim, int exitAnim){
        a.overridePendingTransition(enterAnim, exitAnim);
    }


    public static AnimationSet animset(Animation...animations){
        AnimationSet as = new AnimationSet(false);
        for(Animation a: animations){
            as.addAnimation(a);
        }
        return as;
    }

    public static Animation fade_in(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), fade_in);
    }
    public static Animation fade_out(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), fade_out);
    }
    public static Animation rotate(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), rotate);
    }
    public static Animation scale_in(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), scale_in);
    }
    public static Animation scale_out(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), scale_out);
    }
    public static Animation slide_in_from_bottom(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_in_from_bottom);
    }
    public static Animation slide_in_from_left(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_in_from_left);
    }
    public static Animation slide_in_from_top(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_in_from_top);
    }
    public static Animation slide_in_from_right(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_in_from_right);
    }
    public static Animation slide_out_to_bottom(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_out_to_bottom);
    }
    public static Animation slide_out_to_left(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_out_to_left);
    }
    public static Animation slide_out_to_top(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_out_to_top);
    }
    public static Animation slide_out_to_right(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), slide_out_to_right);
    }
    public static Animation hold(){
        return AnimationUtils.loadAnimation(AyoAnim.app(), hold);
    }


}
