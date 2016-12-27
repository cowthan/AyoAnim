package org.ayo.robot.anim.path;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import org.ayo.core.activity.AyoActivityAttacher;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PathAnimDemo1 extends AyoActivityAttacher {

    View ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_demo_path_anim);

        final PathFrameLayout pathLayout = (PathFrameLayout) findViewById(R.id.pathLayout);

        ball = findViewById(R.id.ball);

        pathLayout.setOnPathDrawFinished(new PathFrameLayout.OnPathDrawFinished() {
            @Override
            public void onPathCreated(Path path) {
                startAnim(ball, path);
            }
        });
    }

    private static void startAnim(final View v, Path path){
        final PathMeasure mPathMeasure = new PathMeasure(path, false);
        final float[] mCurrentPosition = new float[2];

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 减速插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
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
