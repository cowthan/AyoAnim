package org.ayo.animate.ease;

import android.animation.TypeEvaluator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;


/**
 * 常见的30个缓动函数的实现
 *
 * @author hujiawei 16/5/27
 */
public abstract class EasingFunction implements IFunction, Interpolator, TypeEvaluator<Float> {

    //如果这个function在求值的时候需要duration作为参数的话，那么可以通过setDuration来设置，否则使用默认值
    private float duration = 1000f;//目前只有ELASTIC***这三个是需要duration的，其他的都不需要

    public float getDuration() {
        return duration;
    }

    public EasingFunction setDuration(float duration) {
        this.duration = duration;
        return this;
    }

    //将Function当做Interpolator使用，默认的实现，不需要枚举元素去重新实现
    @Override
    public float getInterpolation(float input) {
        return getValue(input);
    }

    //将Function当做TypeEvaluator使用，默认的实现，不需要枚举元素去重新实现
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        return startValue + getValue(fraction) * (endValue - startValue);
    }

    public void init(){}

    public EasingFunction(){
        init();
    }

    //几个数学常量
    public static final float PI = (float) Math.PI;
    public static float DOUBLE_PI = PI * 2.0f;
    public static float HALF_PI = PI * 0.5f;

    public static EasingFunction with(final IFunction function) {
        return new EasingFunction() {
            @Override
            public float getValue(float input) {
                return function.getValue(input);
            }
        };
    }

    /* ------------------------------------------------------------------------------------------- */
    /* 内置的几个Interpolator的映射
    /* ------------------------------------------------------------------------------------------- */
//    Path{
//        PathInterpolator interpolator;
//        @Override
//        public void init() {
//            interpolator = new PathInterpolator();
//        }
//
//        @Override
//        public float getValue(float input) {
//            return interpolator.getInterpolation(input);
//        }
//    },
    public static class Linear extends EasingFunction {
        LinearInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new LinearInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }

    public static class Accelerate extends EasingFunction {
        AccelerateInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new AccelerateInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class Decelerate extends EasingFunction {
        DecelerateInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new DecelerateInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class AccelerateDecelerate extends EasingFunction {
        AccelerateDecelerateInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new AccelerateDecelerateInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class Anticipate extends EasingFunction {
        AnticipateInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new AnticipateInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class Overshoot extends EasingFunction {
        OvershootInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new OvershootInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class AnticipateOvershoot extends EasingFunction {
        AnticipateOvershootInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new AnticipateOvershootInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class Bounce extends EasingFunction {
        BounceInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new BounceInterpolator();
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }
    public static class Cycle extends EasingFunction {
        CycleInterpolator interpolator;
        @Override
        public void init() {
            interpolator = new CycleInterpolator(3);
        }

        @Override
        public float getValue(float input) {
            return interpolator.getInterpolation(input);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* BACK
    /* ------------------------------------------------------------------------------------------- */
    public static class BACK_IN  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return input * input * ((1.70158f + 1) * input - 1.70158f);
        }
    }
    public static class BACK_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            return ((input = input - 1) * input * ((1.70158f + 1) * input + 1.70158f) + 1);
        }
    }
    public static class BACK_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            float s = 1.70158f;
            if ((input *= 2) < 1) {
                return 0.5f * (input * input * (((s *= (1.525f)) + 1) * input - s));
            }
            return 0.5f * ((input -= 2) * input * (((s *= (1.525f)) + 1) * input + s) + 2);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* BOUNCE
    /* ------------------------------------------------------------------------------------------- */
    public static class BOUNCE_IN extends EasingFunction {
        @Override
        public float getValue(float input) {
            input = 1 - input;
            if (input < (1 / 2.75))
                return 1 - (7.5625f * input * input);
            else if (input < (2 / 2.75))
                return 1 - (7.5625f * (input -= (1.5f / 2.75f)) * input + 0.75f);
            else if (input < (2.5 / 2.75))
                return 1 - (7.5625f * (input -= (2.25f / 2.75f)) * input + 0.9375f);
            else
                return 1 - (7.5625f * (input -= (2.625f / 2.75f)) * input + 0.984375f);
        }
    }
    public static class BOUNCE_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if (input < (1 / 2.75))
                return (7.5625f * input * input);
            else if (input < (2 / 2.75))
                return (7.5625f * (input -= (1.5f / 2.75f)) * input + 0.75f);
            else if (input < (2.5 / 2.75))
                return (7.5625f * (input -= (2.25f / 2.75f)) * input + 0.9375f);
            else
                return (7.5625f * (input -= (2.625f / 2.75f)) * input + 0.984375f);
        }
    }
    public static class BOUNCE_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input) < 0.5f) {
                input = input * 2;
                input = 1 - input;
                if (input < (1 / 2.75))
                    return (1 - (7.5625f * input * input)) * 0.5f;
                else if (input < (2 / 2.75))
                    return (1 - (7.5625f * (input -= (1.5f / 2.75f)) * input + 0.75f)) * 0.5f;
                else if (input < (2.5 / 2.75))
                    return (1 - (7.5625f * (input -= (2.25f / 2.75f)) * input + 0.9375f)) * 0.5f;
                else
                    return (1 - (7.5625f * (input -= (2.625f / 2.75f)) * input + 0.984375f)) * 0.5f;
            } else {
                input = input * 2 - 1;
                if (input < (1 / 2.75))
                    return ((7.5625f * input * input)) * 0.5f + 1 * 0.5f;
                else if (input < (2 / 2.75))
                    return ((7.5625f * (input -= (1.5f / 2.75f)) * input + 0.75f)) * 0.5f + 1 * 0.5f;
                else if (input < (2.5 / 2.75))
                    return ((7.5625f * (input -= (2.25f / 2.75f)) * input + 0.9375f)) * 0.5f + 1 * 0.5f;
                else
                    return ((7.5625f * (input -= (2.625f / 2.75f)) * input + 0.984375f)) * 0.5f + 1 * 0.5f;
            }
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* CIRCULAR
    /* ------------------------------------------------------------------------------------------- */
    public static class CIRCULAR_IN  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) (-1 * (Math.sqrt(1 - input * input) - 1.0f));
        }
    }
    public static class CIRCULAR_OUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) (Math.sqrt(1 - (input - 1) * (input - 1)));
        }
    }
    public static class CIRCULAR_INOUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input /= 0.5) < 1) {
                return (float) (-1 * 0.5 * (Math.sqrt(1 - input * input) - 1));
            }
            return (float) (0.5 * (Math.sqrt(1 - (input -= 2) * input) + 1));
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* ELASTIC //todo duration 3
    /* ------------------------------------------------------------------------------------------- */
    public static class ELASTIC_IN  extends EasingFunction {
        @Override
        public float getValue(float input) {
            float mDuration = getDuration() / 1000f;
            float s;
            float p = 0.0f;
            float a = 0.0f;
            if (input == 0) {
                return 0;
            }
            if (input == 1) {
                return 1;
            }
            if (p == 0) {
                p = mDuration * 0.3f;
            }
            if (a == 0 || (1 > 0 && a < 1) || (1 < 0 /*&& a < -1*/)) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / DOUBLE_PI * Math.asin(1 / a));
            }
            return (float) (-(a * Math.pow(2, 10 * (input -= 1)) * Math.sin((input * mDuration - s) * DOUBLE_PI / p)) + 0);
        }
    }
    public static class ELASTIC_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            float mDuration = getDuration() / 1000f;
            float s;
            float p = 0.0f;
            float a = 0.0f;
            if (input == 0) {
                return 0;
            }
            if (input == 1) {
                return 1;
            }
            if (p == 0) {
                p = mDuration * 0.3f;
            }
            if (a == 0 || (1 > 0 && a < 1) || (1 < 0 /*&& a < -1*/)) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / DOUBLE_PI * Math.asin(1 / a));
            }
            return (float) (a * Math.pow(2, -10 * input) * Math.sin((input * mDuration - s) * DOUBLE_PI / p) + 1 + 0);
        }
    }
    public static class ELASTIC_INOUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            float mDuration = getDuration() / 1000f;
            float s;
            float p = 0.0f;
            float a = 0.0f;
            if (input == 0) {
                return 0;
            }
            if ((input /= 0.5) == 2) {
                return 1;
            }
            if (p == 0) {
                p = mDuration * (0.3f * 1.5f);
            }
            if (a == 0 || (1 > 0 && a < 1) || (1 < 0 /*&& a < -1*/)) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / DOUBLE_PI * Math.asin(1 / a));
            }
            if (input < 1) {
                return (float) (-0.5 * (a * Math.pow(2, 10 * (input -= 1)) * Math.sin((input * mDuration - s) * DOUBLE_PI / p)) + 0);
            }
            return (float) (a * Math.pow(2, -10 * (input -= 1)) * Math.sin((input * mDuration - s) * DOUBLE_PI / p) * .5 + 1 + 0);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* EXPO
    /* ------------------------------------------------------------------------------------------- */
    public static class EXPO_IN extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) ((input == 0) ? 0 : 1 * Math.pow(2, 10 * (input - 1)) - 0.001f);
        }
    }
    public static class EXPO_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) ((input == 1) ? 1 : (-Math.pow(2, -10 * input) + 1));
        }
    }
    public static class EXPO_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if (input == 0) {
                return 0;
            }
            if (input == 1) {
                return 1;
            }
            if ((input /= 0.5f) < 1) {
                return (float) (0.5f * Math.pow(2, 10 * (input - 1)));
            }
            return (float) (0.5f * (-Math.pow(2, -10 * --input) + 2));
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* QUAD
    /* ------------------------------------------------------------------------------------------- */
    public static class QUAD_IN  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return input * input;
        }
    }
    public static class QUAD_OUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return -input * (input - 2);
        }
    }
    public static class QUAD_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input /= 0.5f) < 1) {
                return 0.5f * input * input;
            }
            return -0.5f * ((--input) * (input - 2) - 1);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* CUBIC
    /* ------------------------------------------------------------------------------------------- */
    public static class CUBIC_IN extends EasingFunction {
        @Override
        public float getValue(float input) {
            return input * input * input;
        }
    }
    public static class CUBIC_OUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (input - 1) * (input - 1) * (input - 1) + 1;
        }
    }
    public static class CUBIC_INOUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input /= 0.5f) < 1) {
                return 0.5f * input * input * input;
            }
            return 0.5f * ((input -= 2) * input * input + 2);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* QUART
    /* ------------------------------------------------------------------------------------------- */
    public static class QUART_IN  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return input * input * input * input;
        }
    }
    public static class QUART_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            return 1 - (input - 1) * (input - 1) * (input - 1) * (input - 1);
        }
    }
    public static class QUART_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input /= 0.5f) < 1) {
                return 0.5f * input * input * input * input;
            }
            return -0.5f * ((input -= 2) * input * input * input - 2);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* QUINT
    /* ------------------------------------------------------------------------------------------- */
    public static class QUINT_IN extends EasingFunction {
        @Override
        public float getValue(float input) {
            return input * input * input * input * input;
        }
    }
    public static class QUINT_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (input - 1) * (input - 1) * (input - 1) * (input - 1) * (input - 1) + 1;
        }
    }
    public static class QUINT_INOUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            if ((input /= 0.5f) < 1) {
                return 0.5f * input * input * input * input * input;
            }
            return 0.5f * ((input -= 2) * input * input * input * input + 2);
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* SINE
    /* ------------------------------------------------------------------------------------------- */
    public static class SINE_IN extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) (-1 * Math.cos(input * HALF_PI) + 1);
        }
    }
    public static class SINE_OUT extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) Math.sin(input * HALF_PI);
        }
    }
    public static class SINE_INOUT  extends EasingFunction {
        @Override
        public float getValue(float input) {
            return (float) (-1 * 0.5f * (Math.cos(PI * input) - 1));
        }
    }

    /* ------------------------------------------------------------------------------------------- */
    /* BREATH
    /* ------------------------------------------------------------------------------------------- */
    public static class BREATH extends EasingFunction {
        @Override
        public float getValue(float input) {
            if (input < 0.333) {
                return (float) (0.5f + 0.5f * Math.sin(input * 3.0f * Math.PI - Math.PI * 0.5f));
            } else {
                return (float) Math.pow((0.5 * Math.sin(-3f * Math.PI * input * 0.5f + Math.PI) + 0.5f), 2);
            }
        }
    }


}
