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


/**
 * Technique是各种不同的animator效果的枚举集合类 （形象地表示为采用哪种技术来操纵悠悠球）
 */
public enum Techniques {

    DropOut(YoYoAnimator.DropOut.class),
    Flash(YoYoAnimator.Flash.class),
    Hinge(YoYoAnimator.Hinge.class),  ///out
    Landing(YoYoAnimator.Landing.class),
    TakingOff(YoYoAnimator.TakingOff.class), ///out
    Linear(YoYoAnimator.Linear.class),
    Jelly(YoYoAnimator.Jelly.class),
    NewsPaperIn(YoYoAnimator.NewsPaperIn.class),

    Pulse(YoYoAnimator.Pulse.class),
    RubberBand(YoYoAnimator.RubberBand.class),
    Shake(YoYoAnimator.Shake.class),
    ShakeVertical(YoYoAnimator.ShakeVertical.class),
    Swing(YoYoAnimator.Swing.class),
    Wobble(YoYoAnimator.Wobble.class),
    Bounce(YoYoAnimator.Bounce.class),
    Tada(YoYoAnimator.Tada.class),
    StandUp(YoYoAnimator.StandUp.class),
    Wave(YoYoAnimator.Wave.class),

    FallIn(YoYoAnimator.FallIn.class),
    FallRotateIn(YoYoAnimator.FallRotateIn.class),

    RollIn(YoYoAnimator.RollIn.class),
    RollOut(YoYoAnimator.RollOut.class), ///out

    BounceIn(YoYoAnimator.BounceIn.class),
    BounceInDown(YoYoAnimator.BounceInDown.class),
    BounceInLeft(YoYoAnimator.BounceInLeft.class),
    BounceInRight(YoYoAnimator.BounceInRight.class),
    BounceInUp(YoYoAnimator.BounceInUp.class),

    FadeIn(YoYoAnimator.FadeIn.class),
    FadeInUp(YoYoAnimator.FadeInUp.class),
    FadeInDown(YoYoAnimator.FadeInDown.class),
    FadeInLeft(YoYoAnimator.FadeInLeft.class),
    FadeInRight(YoYoAnimator.FadeInRight.class),

    FadeOut(YoYoAnimator.FadeOut.class), ///out
    FadeOutDown(YoYoAnimator.FadeOutDown.class), ///out
    FadeOutLeft(YoYoAnimator.FadeOutLeft.class), ///out
    FadeOutRight(YoYoAnimator.FadeOutRight.class), ///out
    FadeOutUp(YoYoAnimator.FadeOutUp.class), ///out

    FlipInX(YoYoAnimator.FlipInX.class),
    FlipOutX(YoYoAnimator.FlipOutX.class), ///out
    FlipInY(YoYoAnimator.FlipInY.class),
    FlipOutY(YoYoAnimator.FlipOutY.class), ///out

    RotateIn(YoYoAnimator.RotateIn.class),
    RotateInDownLeft(YoYoAnimator.RotateInDownLeft.class),
    RotateInDownRight(YoYoAnimator.RotateInDownRight.class),
    RotateInUpLeft(YoYoAnimator.RotateInUpLeft.class),
    RotateInUpRight(YoYoAnimator.RotateInUpRight.class),

    RotateOut(YoYoAnimator.RotateInUpRight.class),
    RotateOutDownLeft(YoYoAnimator.RotateOutDownLeft.class), ///out
    RotateOutDownRight(YoYoAnimator.RotateOutDownRight.class), ///out
    RotateOutUpLeft(YoYoAnimator.RotateOutUpLeft.class), ///out
    RotateOutUpRight(YoYoAnimator.RotateOutUpRight.class), ///out

    Slide(YoYoAnimator.Slide.class),

    SlideInLeft(YoYoAnimator.SlideInLeft.class),
    SlideInRight(YoYoAnimator.SlideInRight.class),
    SlideInUp(YoYoAnimator.SlideInUp.class),
    SlideInDown(YoYoAnimator.SlideInDown.class),

    SlideOutLeft(YoYoAnimator.SlideOutLeft.class), ///out
    SlideOutRight(YoYoAnimator.SlideOutRight.class), ///out
    SlideOutUp(YoYoAnimator.SlideOutUp.class), ///out
    SlideOutDown(YoYoAnimator.SlideOutDown.class), ///out

    ZoomIn(YoYoAnimator.ZoomIn.class),
    ZoomInDown(YoYoAnimator.ZoomInDown.class),
    ZoomInLeft(YoYoAnimator.ZoomInLeft.class),
    ZoomInRight(YoYoAnimator.ZoomInRight.class),
    ZoomInUp(YoYoAnimator.ZoomInUp.class),

    ZoomOut(YoYoAnimator.ZoomOut.class), ///out
    ZoomOutDown(YoYoAnimator.ZoomOutDown.class), ///out
    ZoomOutLeft(YoYoAnimator.ZoomOutLeft.class), ///out
    ZoomOutRight(YoYoAnimator.ZoomOutRight.class), ///out
    ZoomOutUp(YoYoAnimator.ZoomOutUp.class); ///out

    private Class animatorClazz;

    Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public YoYoAnimator getAnimator() {
        try {
            return (YoYoAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
