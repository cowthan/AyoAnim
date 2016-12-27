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

package org.ayo.animate.ease;


/**
 * Functions是各种不同的easing效果的枚举集合类
 */
public enum Functions {



    Linear(EasingFunction.Linear.class),
    Accelerate(EasingFunction.Accelerate.class),
    Decelerate(EasingFunction.Decelerate.class),
    AccelerateDecelerate(EasingFunction.AccelerateDecelerate.class),
    Anticipate(EasingFunction.Anticipate.class),
    Overshoot(EasingFunction.Overshoot.class),
    AnticipateOvershoot(EasingFunction.AnticipateOvershoot.class),
    Bounce(EasingFunction.Bounce.class),
    Cycle(EasingFunction.Cycle.class),

    BACK_IN(EasingFunction.BACK_IN.class),
    BACK_OUT(EasingFunction.BACK_OUT.class),
    BACK_INOUT(EasingFunction.BACK_INOUT.class),

    BOUNCE_IN(EasingFunction.BOUNCE_IN.class),
    BOUNCE_OUT(EasingFunction.BOUNCE_OUT.class),
    BOUNCE_INOUT(EasingFunction.BOUNCE_INOUT.class),

    CIRCULAR_IN(EasingFunction.CIRCULAR_IN.class),
    CIRCULAR_OUT(EasingFunction.CIRCULAR_OUT.class),
    CIRCULAR_INOUT(EasingFunction.CIRCULAR_INOUT.class),

    ELASTIC_IN(EasingFunction.ELASTIC_IN.class),
    ELASTIC_OUT(EasingFunction.ELASTIC_OUT.class),
    ELASTIC_INOUT(EasingFunction.ELASTIC_INOUT.class),

    EXPO_IN(EasingFunction.EXPO_IN.class),
    EXPO_OUT(EasingFunction.EXPO_OUT.class),
    EXPO_INOUT(EasingFunction.EXPO_INOUT.class),

    QUAD_IN(EasingFunction.QUAD_IN.class),
    QUAD_OUT(EasingFunction.QUAD_OUT.class),
    QUAD_INOUT(EasingFunction.QUAD_INOUT.class),

    CUBIC_IN(EasingFunction.CUBIC_IN.class),
    CUBIC_OUT(EasingFunction.CUBIC_OUT.class),
    CUBIC_INOUT(EasingFunction.CUBIC_INOUT.class),

    QUART_IN(EasingFunction.QUART_IN.class),
    QUART_OUT(EasingFunction.QUART_OUT.class),
    QUART_INOUT(EasingFunction.QUART_INOUT.class),

    QUINT_IN(EasingFunction.QUINT_IN.class),
    QUINT_OUT(EasingFunction.QUINT_OUT.class),
    QUINT_INOUT(EasingFunction.QUINT_INOUT.class),

    SINE_IN(EasingFunction.SINE_IN.class),
    SINE_OUT(EasingFunction.SINE_OUT.class),
    SINE_INOUT(EasingFunction.SINE_INOUT.class),
    BREATH(EasingFunction.BREATH.class);

    private Class functionClazz;

    Functions(Class clazz) {
        functionClazz = clazz;
    }

    public EasingFunction getEasingFunction() {
        try {
            return (EasingFunction) functionClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init EasingFunction instance");
        }
    }
}
