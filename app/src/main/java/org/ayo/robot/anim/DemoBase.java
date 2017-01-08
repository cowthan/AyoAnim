package org.ayo.robot.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.ayo.animate.ease.EasingFunction;
import org.ayo.animate.ease.Functions;
import org.ayo.robot.anim.ease.EvaluatorActivity;
import org.ayo.robot.anim.propertyanim.DemoAlpha;
import org.ayo.robot.anim.propertyanim.DemoRotate;
import org.ayo.robot.anim.propertyanim.DemoRotateX;
import org.ayo.robot.anim.propertyanim.DemoRotateY;
import org.ayo.robot.anim.propertyanim.DemoScaleX;
import org.ayo.robot.anim.propertyanim.DemoScaleY;
import org.ayo.robot.anim.propertyanim.DemoTranslateX;
import org.ayo.robot.anim.propertyanim.DemoTranslateY;
import org.ayo.robot.anim.propertyanim.model.AnimatorInfo;

public abstract class DemoBase extends AyoActivity {

    public static void startForResult(Activity a, String type){
        if(type.equals(AnimatorInfo.TYPE_alpha)){
            DemoAlpha.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_rotate)){
            DemoRotate.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_rotateX)){
            DemoRotateX.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_rotateY)){
            DemoRotateY.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_scaleX)){
            DemoScaleX.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_scaleY)){
            DemoScaleY.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_translateX)){
            DemoTranslateX.startForResult(a);
        }else if(type.equals(AnimatorInfo.TYPE_translateY)){
            DemoTranslateY.startForResult(a);
        }
    }

    public static boolean isMyResult(int requestCode){
        return requestCode == 205;
    }

    public static AnimatorInfo getResult(Intent data){
        if(data != null && data.hasExtra("anim")){
            return (AnimatorInfo)data.getSerializableExtra("anim");
        }
        return null;
    }

    private View mTarget, mTarget2, mTarget3, mTarget4;
    private TextView tv_ease;

    private TextView tv_duraion;
    private SeekBar sb_seekbar;

    private int duration = 1000;

    public View getTarget1(){
        return mTarget;
    }
    public View getTarget2(){
        return mTarget2;
    }

    public View getTarget3(){
        return mTarget3;
    }
    public View getTarget4(){
        return mTarget4;
    }

    protected abstract String getType();
    final protected float getFrom(){
        return from;
    }
    final protected float getTo(){
        return to;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_base);

        mTarget = findViewById(R.id.hello_world);
        mTarget2 = findViewById(R.id.hello_world2);
        mTarget3 = findViewById(R.id.hello_world3);
        mTarget4 = findViewById(R.id.hello_world4);
        tv_ease = (TextView) findViewById(R.id.tv_ease);
        tv_ease.setText("EasingFunction.Linear");


        tv_duraion = (TextView) findViewById(R.id.tv_duraion);
        sb_seekbar = (SeekBar) findViewById(R.id.sb_seekbar);

        sb_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    duration = progress;
                    tv_duraion.setText("duration: " + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                stopAnim();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                duration = seekBar.getProgress();
                tv_duraion.setText("duration: " + seekBar.getProgress());
            }
        });


        mTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim();
            }
        });

        findViewById(R.id.wrapper_ease).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EvaluatorActivity.startForResult(getActivity());
            }
        });

        final TextView tv_pivot = (TextView) findViewById(R.id.tv_pivot);
        final SeekBar sb_pivot_x = (SeekBar) findViewById(R.id.sb_pivot_x);
        final SeekBar sb_pivot_y = (SeekBar) findViewById(R.id.sb_pivot_y);
        pivotX = getTarget1().getPivotX();
        pivotY = getTarget1().getPivotY();
        tv_pivot.setText("pivot(x, y)".replace("x", pivotX+"").replace("y", pivotY+""));
        sb_pivot_x.setProgress((int)pivotX);
        sb_pivot_y.setProgress((int)pivotY);

        sb_pivot_x.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    pivotX = progress;
                    tv_pivot.setText("pivot(x, y)".replace("x", pivotX+"").replace("y", pivotY+""));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                stopAnim();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                duration = seekBar.getProgress();
//                tv_duraion.setText("duration: " + seekBar.getProgress());
            }
        });
        sb_pivot_y.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    pivotY = progress;
                    tv_pivot.setText("pivot(x, y)".replace("x", pivotX+"").replace("y", pivotY+""));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                stopAnim();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                duration = seekBar.getProgress();
//                tv_duraion.setText("duration: " + seekBar.getProgress());
            }
        });


        RadioGroup rg_repeat_count = (RadioGroup) findViewById(R.id.rg_repeat_count);
        rg_repeat_count.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_1){
                    repeatCount = 1;
                }else if(checkedId == R.id.rb_3){
                    repeatCount = 3;
                }else if(checkedId == R.id.rb_infinite){
                    repeatCount = ValueAnimator.INFINITE;
                }
            }
        });
        RadioGroup rg_repeat_mode = (RadioGroup) findViewById(R.id.rg_repeat_mode);
        rg_repeat_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_restart){
                    repeatMode = ValueAnimator.RESTART;
                }else if(checkedId == R.id.rb_reverse){
                    repeatMode = ValueAnimator.REVERSE;
                }
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startAnim();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopAnim();
            }
        });

        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                org.ayo.sample.menu.notify.Toaster.toastShort("setting");
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        findViewById(R.id.btn_setresult).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AnimatorInfo animatorInfo = new AnimatorInfo();
                animatorInfo.duration = getDuration();
                animatorInfo.easingClassName = easeing.name();
                animatorInfo.from = getFrom();
                animatorInfo.to = getTo();
                animatorInfo.pivotX = getCustomPivotX();
                animatorInfo.pivotY = getCustomPivotY();
                animatorInfo.repeatCount = getRepeatCount();
                animatorInfo.repeatMode = getRepeatMode();
                animatorInfo.type = getType();
                Intent data = new Intent();
                data.putExtra("anim", animatorInfo);
                setResult(200, data);
             finish();
            }
        });

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final TextView tv_from_to = (TextView) findViewById(R.id.tv_from_to);
                SeekBar sb_from = (SeekBar) findViewById(R.id.sb_from);
                SeekBar sb_to = (SeekBar) findViewById(R.id.sb_to);
                sb_from.setMax((int) (getMaxValue() - getMinValue()));
                sb_to.setMax((int) (getMaxValue() - getMinValue()));
                from = getDefaultFrom();
                to = getDefaultTo();
                sb_from.setProgress((int) from);
                sb_to.setProgress((int) to);
                tv_from_to.setText("开始--结束(x, y)".replace("x", parseProgress((int) from)+"").replace("y", parseProgress((int) to)+""));
                sb_from.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        from = progress;
                        tv_from_to.setText("开始--结束(x, y)".replace("x", parseProgress((int) from)+"").replace("y", parseProgress((int) to)+""));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                sb_to.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        to = progress;
                        tv_from_to.setText("开始--结束(x, y)".replace("x", parseProgress((int) from)+"").replace("y", parseProgress((int) to)+""));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        }, 500);



        TextView tv_type = (TextView) findViewById(R.id.tv_type);
        tv_type.setText(getType());

        View v = createTestView();
        if(v != null){
            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            container.addView(v);
        }
    }
    private float from, to;
    protected abstract float getMinValue();
    protected abstract float getMaxValue();
    protected abstract float getDefaultFrom();
    protected abstract float getDefaultTo();
    protected float parseProgress(int progress){
        return progress;
    }

    private float pivotX, pivotY;

    protected float getCustomPivotX(){
        return pivotX;
    }

    protected float getCustomPivotY(){
        return pivotY;
    }

    private int repeatCount = 0;
    private int repeatMode = ValueAnimator.RESTART;

    protected int getRepeatCount(){
        return repeatCount;
    }

    protected int getRepeatMode(){
        return repeatMode;
    }

    private EasingFunction easingFunction = Functions.Linear.getEasingFunction();
    private Functions easeing = Functions.Linear;

    protected void startAnim() {
        o1 = apply(getTarget1());
        o2 = apply(getTarget2());
        o3 = apply(getTarget3());
        o4 = apply(getTarget4());
    }

    private Animator o1, o2, o3, o4;

    private Animator apply(View v){
        Animator o1 = createAnimator(v);
        v.setPivotX(getCustomPivotX()/100 * v.getWidth());
        v.setPivotY(getCustomPivotY()/100 * v.getHeight());
        o1.setDuration(getDuration());
        o1.setInterpolator(getEasingFunction());
        if(o1 instanceof ObjectAnimator){
            ObjectAnimator o = (ObjectAnimator) o1;
            o.setRepeatCount(repeatCount);
            o.setRepeatMode(repeatMode);
        }
        o1.start();
        return o1;
    }

    protected abstract Animator createAnimator(View target);
    protected void stopAnim() {
        if(o1 != null) o1.cancel();
        if(o2 != null) o2.cancel();
        if(o3 != null) o3.cancel();
        if(o4 != null) o4.cancel();
    }

    protected void reset(){
        if(o1 != null) {
            o1.cancel();
            resetAnim(getTarget1());
        }
        if(o2 != null) {
            o2.cancel();
            resetAnim(getTarget2());
        }
        if(o3 != null) {
            o3.cancel();
            resetAnim(getTarget3());
        }
        if(o4 != null) {
            o4.cancel();
            resetAnim(getTarget4());
        }
    }

    protected void resetAnim(View v){
        v.setPivotX(0);
        v.setPivotY(0);
        v.setRotation(0);
        v.setRotationX(0);
        v.setRotationY(0);
        v.setTranslationX(0);
        v.setTranslationY(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            v.setTranslationZ(0);
        }
        v.setAlpha(1);
        v.setScaleX(1);
        v.setScaleY(1);
    }

    protected abstract View createTestView();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 200){
            int pos = data.hasExtra("pos") ? data.getIntExtra("pos", -1) : -1;
            if(pos == -1){

            }else{
                easeing = Functions.values()[pos];
                EasingFunction easingFunction = Functions.values()[pos].getEasingFunction();
                this.easingFunction = easingFunction;
                tv_ease.setText("EasingFunction." + Functions.values()[pos].name());
                startAnim();
            }
        }
    }

    protected EasingFunction getEasingFunction(){
        return easingFunction;
    }

    protected int getDuration(){
        return duration;
    }
}
