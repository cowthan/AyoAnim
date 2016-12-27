package org.ayo.animate.yoyo.animator;

import android.animation.ObjectAnimator;

import org.ayo.animate.ease.Functions;


public class DropOutAnimator extends BaseViewAnimator {
    @Override
    protected void prepare() {
        int distance = mTarget.getTop() + mTarget.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTarget, "translationY", -distance, 0);
        animator.setInterpolator(Functions.BOUNCE_OUT.getEasingFunction());
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1), animator);
    }
}
