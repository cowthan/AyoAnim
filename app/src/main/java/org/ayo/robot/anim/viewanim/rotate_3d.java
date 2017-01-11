package org.ayo.robot.anim.viewanim;


import android.view.View;
import android.view.animation.Animation;

import org.ayo.robot.anim.DemoBaseForViewAnim;

public class rotate_3d extends DemoBaseForViewAnim {

    @Override
    protected String getType() {
        return "rotate_3d";
    }

    @Override
    protected float getMinValue() {
        return 0;
    }

    @Override
    protected float getMaxValue() {
        return 0;
    }

    @Override
    protected float getDefaultFrom() {
        return 0;
    }

    @Override
    protected float getDefaultTo() {
        return 0;
    }

    @Override
    protected float parseProgress(int progress) {
        return super.parseProgress(progress);
    }

    @Override
    protected Animation createAnimator(View target) {
        return new Rotate3dAnimation(0, 360, target.getWidth()/2, target.getHeight()/1, 1, true);
    }

    @Override
    protected View createTestView() {
        return null;
    }
}
