package org.ayo.animate.yoyo.animator;

import android.animation.ObjectAnimator;

import org.ayo.animate.ease.Functions;


public class TakingOffAnimator extends BaseViewAnimator {
    @Override
    protected void prepare() {
//        mAnimatorSet.playTogether(
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "scaleX", 1f, 1.5f)),
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "scaleY", 1f, 1.5f)),
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0))
//        );

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTarget, "scaleX", 1f, 1.5f);
        animator1.setInterpolator(Functions.QUINT_OUT.getEasingFunction());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTarget, "scaleY", 1f, 1.5f);
        animator2.setInterpolator(Functions.QUINT_OUT.getEasingFunction());
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0);
        animator3.setInterpolator(Functions.QUINT_OUT.getEasingFunction());

        mAnimatorSet.playTogether(animator1, animator2, animator3);
    }
}
