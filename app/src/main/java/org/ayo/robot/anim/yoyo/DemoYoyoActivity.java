package org.ayo.robot.anim.yoyo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ayo.animate.ease.EasingFunction;
import org.ayo.animate.ease.Functions;
import org.ayo.animate.yoyo.Techniques;
import org.ayo.animate.yoyo.YoYo;
import org.ayo.core.activity.AyoActivity;
import org.ayo.robot.anim.R;
import org.ayo.robot.anim.ease.EvaluatorActivity;

public class DemoYoyoActivity extends AyoActivity {

    private View mTarget, mTarget2;
    private ListView mListView;
    private EffectAdapter mAdapter;
    private YoYo.YoYoString rope, rope2;
    private TextView tv_ease;

    private TextView tv_duraion;
    private SeekBar sb_seekbar;

    private int duration = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_yoyo_demo_main);

        mTarget = findViewById(R.id.hello_world);
        mTarget2 = findViewById(R.id.hello_world2);
        mListView = (ListView)findViewById(R.id.list);
        mAdapter = new EffectAdapter(this);
        mListView.setAdapter(mAdapter);
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
                if (rope != null) {
                    rope.stop(true);
                } if (rope2 != null) {
                    rope2.stop(true);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                duration = seekBar.getProgress();
                tv_duraion.setText("duration: " + seekBar.getProgress());
            }
        });


        rope = YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTarget);// after start,just click mTarget view, rope is not init
        rope2 = YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTarget2);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Techniques technique = (Techniques)view.getTag();
                techniques = technique;
                startAnim();
            }
        });

        mTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rope != null) {
                    rope.stop(true);
                }

                if (rope2 != null) {
                    rope2.stop(true);
                }
            }
        });

        findViewById(R.id.wrapper_ease).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EvaluatorActivity.startForResult(getActivity());
            }
        });
    }

    private EasingFunction easingFunction = Functions.Linear.getEasingFunction();
    private Techniques techniques;

    private void startAnim(){
        if(techniques == null) return;
        rope =  YoYo.with(techniques).duration(duration)
                .interpolate(easingFunction)
                .listen(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Toast.makeText(DemoYoyoActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .playOn(mTarget);

        rope2 =  YoYo.with(techniques).duration(duration)
                .interpolate(easingFunction)
                .listen(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //Toast.makeText(DemoYoyoActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .playOn(mTarget2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 200){
            int pos = data.hasExtra("pos") ? data.getIntExtra("pos", -1) : -1;
            if(pos == -1){

            }else{
                EasingFunction easingFunction = Functions.values()[pos].getEasingFunction();
                this.easingFunction = easingFunction;
                tv_ease.setText("EasingFunction." + Functions.values()[pos].name());
                startAnim();
            }
        }
    }
}
