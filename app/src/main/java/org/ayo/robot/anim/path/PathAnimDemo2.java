package org.ayo.robot.anim.path;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import org.ayo.robot.anim.AyoActivityAttacher;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PathAnimDemo2 extends AyoActivityAttacher {

    private View ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demo_path_anim2);

        final PathFrameLayout pathLayout = (PathFrameLayout) findViewById(R.id.pathLayout);

        ball = pathLayout.getChildAt(0);

        pathLayout.setOnPathDrawFinished(new PathFrameLayout.OnPathDrawFinished() {
            @Override
            public void onPathCreated(Path path) {
                startAnim(pathLayout, path);
            }
        });
    }

    private static void startAnim(final ViewGroup v, Path path){
        final PathMeasure mPathMeasure = new PathMeasure(path, false);
        float step = mPathMeasure.getLength() / (v.getChildCount() - 1);
        for (int i = 0; i < v.getChildCount(); i++){
            View child = v.getChildAt(i);
            startAnim(child, path, i * step);
        }
    }

    private static void startAnim(final View v, Path path, float length){
        final PathMeasure mPathMeasure = new PathMeasure(path, false);
        final float[] mCurrentPosition = new float[2];

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, length);
        Log.e("aaaaa--11", mPathMeasure.getLength() + "");
        valueAnimator.setDuration(1000);
        // 减速插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                Log.e("aaaaa--22", value + "");
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                moveBallTo(v, mCurrentPosition[0], mCurrentPosition[1]);
            }
        });
        valueAnimator.start();
    }

    private static void moveBallTo(View v, float x, float y){
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) v.getLayoutParams();
        lp.topMargin = (int) y;
        lp.leftMargin = (int) x;
        v.setLayoutParams(lp);
    }

}
