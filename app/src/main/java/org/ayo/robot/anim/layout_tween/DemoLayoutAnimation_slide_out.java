package org.ayo.robot.anim.layout_tween;

import android.view.animation.Animation;

import org.ayo.animate.TweenAnimation;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class DemoLayoutAnimation_slide_out extends BaseDemoLayoutAnimation {

    @Override
    public Animation getLayoutAnimation() {
        return TweenAnimation.slide_out_to_top();
    }
}
