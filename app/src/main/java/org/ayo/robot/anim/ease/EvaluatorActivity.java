package org.ayo.robot.anim.ease;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.ayo.animate.ease.Functions;
import org.ayo.robot.anim.R;
import org.ayo.robot.anim.Toaster;
import org.ayo.robot.anim.ease.adapter.EvaluatorAdapter;
import org.ayo.robot.anim.ease.view.EvaluatorView;


/**
 * 演示TypeEvaluator
 */
public class EvaluatorActivity extends AppCompatActivity {

    public static void start(Activity a){
       Intent intent = new Intent(a, EvaluatorActivity.class);
        a.startActivity(intent);
    }

    public static void startForResult(Activity a){
        Intent intent = new Intent(a, EvaluatorActivity.class);
        intent.putExtra("forResult", true);
        a.startActivityForResult(intent, 100);
    }

    private ListView list;
    private EvaluatorAdapter adapter;

    private View circle;
    private EvaluatorView evaluatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator);

        adapter = new EvaluatorAdapter(this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        circle = findViewById(R.id.circle);
        evaluatorView = (EvaluatorView) findViewById(R.id.drawview);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(getIntent().hasExtra("forResult")){
                    Intent intent = new Intent();
                    intent.putExtra("pos", position);
                    setResult(200, intent);
                    finish();
                }else{
                    evaluatorView.clear();
                    circle.setTranslationX(0);
                    circle.setTranslationY(0);

                    ObjectAnimator animator = ObjectAnimator.ofFloat(circle, "translationY", 0, dipToPixels(EvaluatorActivity.this, -(160 - 3)));
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            evaluatorView.drawPoint(animation.getCurrentPlayTime(), animation.getDuration(), (float) animation.getAnimatedValue() - dipToPixels(EvaluatorActivity.this, 60));
                        }
                    });
                    animator.setEvaluator(Functions.values()[position].getEasingFunction());
                    animator.setDuration(1200);
                    animator.start();
                }


            }
        });

    }

    public void onSubmit(TypeEvaluator evaluator){
        Toaster.toastShort(evaluator.getClass().getSimpleName());
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

}
