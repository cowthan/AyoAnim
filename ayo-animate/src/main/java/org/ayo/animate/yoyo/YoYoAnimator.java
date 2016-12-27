/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.ayo.animate.yoyo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;

import org.ayo.animate.ease.Functions;

import java.util.List;

import static android.R.attr.duration;

/**
 * YoYoAnimator的抽象基类，不同动画效果之间的差异体现在它们在动画准备阶段加入到动画集合中的动画不同
 * animator包下的所有类都继承自YoYoAnimator，它们实现的就是不同效果的动画
 */
public abstract class YoYoAnimator {

    private int mRepeat;//动画重复的次数
    private boolean mRest;//动画结束之后是否恢复到原来的状态

    //子类可以直接访问
    protected View mTarget;//动画作用的对象
    protected AnimatorSet mAnimatorSet = new AnimatorSet();//动画集合

    protected abstract void prepare();//动画准备阶段

    /**
     * start to animate
     */
    public void start() {
        reset();
        prepare();
        if (mRepeat != 0) {
            for (Animator animator : mAnimatorSet.getChildAnimations()) {
                ((ValueAnimator) animator).setRepeatCount(mRepeat > 0 ? mRepeat - 1 : mRepeat);//区别无穷次
                //((ValueAnimator) animator).setRepeatMode(ValueAnimator.REVERSE);
            }
        }
        if (mRest) {
            mAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    reset();
                }
            });
        }
        mAnimatorSet.start();
    }

    /**
     * reset the view to default status
     */
    public void reset() {
        mTarget.setAlpha(1);
        mTarget.setScaleX(1);
        mTarget.setScaleY(1);
        mTarget.setTranslationX(0);
        mTarget.setTranslationY(0);
        mTarget.setRotation(0);
        mTarget.setRotationX(0);
        mTarget.setRotationY(0);
        mTarget.setPivotX(mTarget.getMeasuredWidth() / 2.0f);
        mTarget.setPivotY(mTarget.getMeasuredHeight() / 2.0f);
    }

    //动画过程的控制
    public void cancel() {
        mAnimatorSet.cancel();
    }

    public boolean isRunning() {
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return mAnimatorSet.isStarted();
    }

    //动画监听器的控制，监听器的方法会在AnimatorSet中被调用
    public YoYoAnimator addAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.addListener(l);
        return this;
    }

    public YoYoAnimator addAllListeners(List<Animator.AnimatorListener> ls) {
        for (Animator.AnimatorListener l : ls) {
            mAnimatorSet.addListener(l);
        }
        return this;
    }

    public void removeAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.removeListener(l);
    }

    public void removeAllListener() {
        mAnimatorSet.removeAllListeners();
    }

    //动画其他属性的控制 set
    public YoYoAnimator setTarget(View target) {//设置动画的作用对象
        mTarget = target;
        mAnimatorSet.setTarget(target);
        return this;
    }

    public YoYoAnimator setInterpolator(Interpolator interpolator) {//设置动画的插值器
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public YoYoAnimator setDuration(long duration) {
        mAnimatorSet.setDuration(duration);
        return this;
    }

    public YoYoAnimator setStartDelay(long delay) {
        mAnimatorSet.setStartDelay(delay);
        return this;
    }

    public YoYoAnimator setRest(boolean rest) {
        mRest = rest;
        return this;
    }

    public YoYoAnimator setRepeat(int repeat) {
        mRepeat = repeat;
        return this;
    }


    ///---------------------------子类----------------------------------
    public static class DropOut extends YoYoAnimator {
        @Override
        protected void prepare() {
            int distance = mTarget.getTop() + mTarget.getHeight();
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTarget, "translationY", -distance, 0);
            animator.setInterpolator(Functions.BOUNCE_OUT.getEasingFunction());
            mAnimatorSet.playTogether(ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1), animator);
        }
    }

    public static class Flash extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0, 1, 0, 1)
            );
        }
    }

    public static class Hinge extends YoYoAnimator {

        @Override
        public void prepare() {
            float x = mTarget.getPaddingLeft();
            float y = mTarget.getPaddingTop();
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTarget, "rotation", 0, 80, 60, 80, 60, 60);
            animator.setInterpolator(Functions.SINE_INOUT.getEasingFunction());
            mAnimatorSet.playTogether(
                    animator,
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, 0, 0, 0, 0, 700),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 1, 1, 1, 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x, x, x, x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y, y, y, y, y)
            );
        }
    }

    public static class Landing extends YoYoAnimator {
        @Override
        protected void prepare() {
//        mAnimatorSet.playTogether(
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "scaleX", 1.5f, 1f)),
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "scaleY", 1.5f, 1f)),
//                Glider.glide(Skill.QuintEaseOut, mAnimatorSet.getDuration(), ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1f))
//        );

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTarget, "scaleX", 1.5f, 1f);
            animator1.setInterpolator(Functions.QUINT_OUT.getEasingFunction());
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTarget, "scaleY", 1.5f, 1f);
            animator2.setInterpolator(Functions.QUINT_OUT.getEasingFunction());
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1f);
            animator3.setInterpolator(Functions.QUINT_OUT.getEasingFunction());
            mAnimatorSet.playTogether(animator1, animator2, animator3);
        }
    }

    public static class Pulse extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 1.1f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 1.1f, 1)
            );
        }
    }

    public static class RubberBand extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 1.25f, 0.75f, 1.15f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.75f, 1.25f, 0.85f, 1)
            );
        }
    }
    public static class Shake extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
            );
        }
    }

    public static class ShakeVertical extends YoYoAnimator {
        @Override
        public void prepare() {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mTarget, "translationY", -10, 10);
            animator.setInterpolator(new CycleInterpolator(5));
            mAnimatorSet.playTogether(animator);
        }
    }

    public static class Swing extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)
            );
        }
    }

    public static class Tada extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, -3, -3, 3, -3, 3, -3, 3, -3, 0)
            );
        }
    }

    public static class StandUp extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = (mTarget.getWidth() - mTarget.getPaddingLeft() - mTarget.getPaddingRight()) / 2
                    + mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x, x, x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y, y, y, y),
                    ObjectAnimator.ofFloat(mTarget, "rotationX", 55, -30, 15, -15, 0)
            );
        }
    }

    public static class TakingOff extends YoYoAnimator {
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

    public static class Wave extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = (mTarget.getWidth() - mTarget.getPaddingLeft() - mTarget.getPaddingRight()) / 2
                    + mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", 12, -12, 3, -3, 0),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x, x, x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y, y, y, y)
            );
        }
    }

    public static class Wobble extends YoYoAnimator {
        @Override
        public void prepare() {
            float width = mTarget.getWidth();
            float one = (float) (width / 100.0);
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0 * one, -25 * one, 20 * one, -15 * one, 10 * one, -5 * one, 0 * one, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, -5, 3, -3, 2, -1, 0)
            );
        }
    }

    public static class NewsPaperIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(//
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.1f, 0.5f, 1f), //
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.1f, 0.5f, 1f),//
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0f, 1f),//
                    ObjectAnimator.ofFloat(mTarget, "rotation", 1080, 720, 360, 0));
        }
    }

    public static class Jelly extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(//
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0.2f, 1));
        }
    }

    public static class Linear extends YoYoAnimator {

        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, 0, -30, 0, -15, 0, 0)
            );
        }
    }

    public static class Bounce extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, 0, -30, 0, -15, 0, 0)
            );
        }
    }

    public static class BounceIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.3f, 1.05f, 0.9f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.3f, 1.05f, 0.9f, 1)
            );
            /**
             * <pre>
             * 另一种弹性实现:依据sweet-alert-dialog布局文件实现
             * ObjectAnimator oa_alpha = ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1).setDuration(90);
             *
             * AnimatorSet as1 = new AnimatorSet();
             * as1.playTogether(oa_alpha, ObjectAnimator.ofFloat(view, "scaleX", 0.7f, 1.05f).setDuration(135),//
             * 		ObjectAnimator.ofFloat(view, "scaleY", 0.7f, 1.05f).setDuration(135));
             *
             * AnimatorSet as2 = new AnimatorSet();
             * as2.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 0.95f).setDuration(105), //
             * 		ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 0.95f).setDuration(105));
             *
             * AnimatorSet as3 = new AnimatorSet();
             * as3.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f).setDuration(60),//
             * 		ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f).setDuration(60));
             *
             * animatorSet.playSequentially(as1, as2, as3);
             * </pre>
             *
             */
        }
    }

    public static class BounceInDown extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", -mTarget.getHeight(), 30, -10, 0)
            );
        }
    }

    public static class BounceInLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationX", -mTarget.getWidth(), 30, -10, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1, 1)
            );
        }
    }

    public static class BounceInRight extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationX", mTarget.getMeasuredWidth() + mTarget.getWidth(), -30, 10, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1, 1)
            );
        }
    }

    public static class BounceInUp extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "translationY", mTarget.getMeasuredHeight(), -30, 10, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1, 1)
            );
        }

    }

    public static class FallIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(ObjectAnimator.ofFloat(mTarget, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1f).setDuration(duration));
        }

    }

    public static class FallRotateIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(ObjectAnimator.ofFloat(mTarget, "scaleX", 2, 1.5f, 1),//
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 2, 1.5f, 1),//
                    ObjectAnimator.ofFloat(mTarget, "rotation", 45, 0),//
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1));
        }

    }

    public static class SlideInDown extends YoYoAnimator {
        @Override
        public void prepare() {
            int distance = mTarget.getTop() + mTarget.getHeight();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", -distance, 0)
            );
        }
    }
    public static class SlideInLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getWidth() - mTarget.getLeft();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", -distance, 0)
            );
        }
    }

    public static class SlideInRight extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getWidth() - mTarget.getLeft();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", distance, 0)
            );
        }
    }
    public static class SlideInUp extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getHeight() - mTarget.getTop();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", distance, 0)
            );
        }
    }

    public static class SlideOutDown extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getHeight() - mTarget.getTop();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, distance)
            );
        }
    }
    public static class SlideOutLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, -mTarget.getRight())
            );
        }
    }


    public static class SlideOutRight extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getWidth() - mTarget.getLeft();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, distance)
            );
        }
    }

    public static class SlideOutUp extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, -mTarget.getBottom())
            );
        }
    }


    public static class ZoomIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.45f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.45f, 1),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1)
            );
        }
    }

    public static class ZoomInDown extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", -mTarget.getBottom(), 60, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1)
            );
        }
    }

    public static class ZoomInLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", -mTarget.getRight(), 48, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1)
            );
        }
    }

    public static class ZoomInRight extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", mTarget.getWidth() + mTarget.getPaddingRight(), -48, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1)
            );
        }
    }

    public static class ZoomInUp extends YoYoAnimator {
        @Override
        public void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getHeight() - mTarget.getTop();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 0.1f, 0.475f, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", distance, -60, 0)
            );
        }
    }

    public static class ZoomOut extends YoYoAnimator {
        @Override
        protected void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.3f, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.3f, 0)
            );
        }
    }

    public static class ZoomOutDown extends YoYoAnimator {
        @Override
        protected void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getHeight() - mTarget.getTop();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, -60, distance)
            );
        }
    }

    public static class ZoomOutLeft extends YoYoAnimator {
        @Override
        protected void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, 42, -mTarget.getRight())
            );
        }
    }
    public static class ZoomOutRight extends YoYoAnimator {
        @Override
        protected void prepare() {
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            int distance = parent.getWidth() - parent.getLeft();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, -42, distance)
            );
        }
    }

    public static class ZoomOutUp extends YoYoAnimator {
        @Override
        protected void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "scaleX", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "scaleY", 1, 0.475f, 0.1f),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, 60, -mTarget.getBottom())
            );
        }
    }

    public static class FadeIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1)
            );
        }
    }


    public static class FadeInDown extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", -mTarget.getHeight() / 4, 0)
            );
        }
    }

    public static class FadeInLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", -mTarget.getWidth() / 4, 0)
            );
        }
    }


    public static class FadeInRight extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", mTarget.getWidth() / 4, 0)
            );
        }
    }

    public static class FadeInUp extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationY", mTarget.getHeight() / 4, 0)
            );
        }
    }

    public static class FadeOut extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0)
            );
        }
    }
    public static class FadeOutDown extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, mTarget.getHeight() / 4)
            );
        }
    }

    public static class FadeOutLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, -mTarget.getWidth() / 4)
            );
        }
    }
    public static class FadeOutRight extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, mTarget.getWidth() / 4)
            );
        }
    }

    public static class FadeOutUp extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationY", 0, -mTarget.getHeight() / 4)
            );
        }
    }


    public static class FlipInX extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotationX", 90, -15, 15, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0.25f, 0.5f, 0.75f, 1)
            );
        }
    }

    public static class FlipInY extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotationY", 90, -15, 15, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0.25f, 0.5f, 0.75f, 1)
            );
        }
    }

    public static class FlipOutX extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotationX", 0, 90),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0)
            );
        }
    }

    public static class FlipOutY extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotationY", 0, 90),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0)
            );
        }
    }

    public static class RollIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "translationX", -(mTarget.getWidth() - mTarget.getPaddingLeft() - mTarget.getPaddingRight()), 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", -120, 0)
            );
        }
    }

    public static class RollOut extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "translationX", 0, mTarget.getWidth()),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, 120)
            );
        }
    }
    public static class RotateIn extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", -200, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1)
            );
        }
    }


    public static class RotateInDownLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", -90, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }


    public static class RotateInDownRight extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getWidth() - mTarget.getPaddingRight();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", 90, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateInUpLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", 90, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateInUpRight extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getWidth() - mTarget.getPaddingRight();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "rotation", -90, 0),
                    ObjectAnimator.ofFloat(mTarget, "alpha", 0, 1),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateOut extends YoYoAnimator {
        @Override
        public void prepare() {
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, 200)
            );
        }
    }

    public static class RotateOutDownLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, 90),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateOutDownRight extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getWidth() - mTarget.getPaddingRight();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, -90),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateOutUpLeft extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getPaddingLeft();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, -90),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }

    public static class RotateOutUpRight extends YoYoAnimator {
        @Override
        public void prepare() {
            float x = mTarget.getWidth() - mTarget.getPaddingRight();
            float y = mTarget.getHeight() - mTarget.getPaddingBottom();
            mAnimatorSet.playTogether(
                    ObjectAnimator.ofFloat(mTarget, "alpha", 1, 0),
                    ObjectAnimator.ofFloat(mTarget, "rotation", 0, 90),
                    ObjectAnimator.ofFloat(mTarget, "pivotX", x, x),
                    ObjectAnimator.ofFloat(mTarget, "pivotY", y, y)
            );
        }
    }



}
