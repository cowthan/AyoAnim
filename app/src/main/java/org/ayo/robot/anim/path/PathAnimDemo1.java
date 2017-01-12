package org.ayo.robot.anim.path;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import org.ayo.robot.anim.BasePage;
import org.ayo.robot.anim.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PathAnimDemo1 extends BasePage {

    View ball;

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

    @Override
    protected int getLayoutId() {
        return R.layout.ac_demo_path_anim;
    }

    @Override
    protected void onCreate2(View view, @Nullable Bundle bundle) {
        final PathFrameLayout pathLayout = (PathFrameLayout) findViewById(R.id.pathLayout);

        ball = findViewById(R.id.ball);

        pathLayout.setOnPathDrawFinished(new PathFrameLayout.OnPathDrawFinished() {
            @Override
            public void onPathCreated(Path path) {
                startAnim(ball, path);
            }
        });
    }

    @Override
    protected void onDestroy2() {

    }

    @Override
    protected void onPageVisibleChanged(boolean b, boolean b1, @Nullable Bundle bundle) {

    }
}
