package org.ayo.robot.anim.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PathFrameLayout extends FrameLayout {
    public PathFrameLayout(Context context) {
        super(context);
        init();
    }

    public PathFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    Paint mPaint;

    private void init(){
        path = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    public interface OnPathDrawFinished{
        void onPathCreated(Path path);
    }

    private OnPathDrawFinished onPathDrawFinished;

    public void setOnPathDrawFinished(OnPathDrawFinished onPathDrawFinished){
        this.onPathDrawFinished = onPathDrawFinished;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, mPaint);
    }


    protected void onFingerMove(int x, int y, int dx, int dy) {
        path.rLineTo(dx, dy);
        invalidate();
    }

    private Path path;

    protected void onFingerDown(int x, int y) {
        path.reset();
        path.moveTo(x, y);
        invalidate();
    }

    protected void onFingerUp(int x, int y) {
        //path.close();
        invalidate();
        if(onPathDrawFinished != null){
            onPathDrawFinished.onPathCreated(path);
        }
    }


    int mLastX = 0;
    int mLastY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        super.onTouchEvent(e);
        boolean consume = false;
        int x = (int)e.getX();
        int y = (int)e.getY();
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            consume = true;
            onFingerDown(x, y);
        }else if(e.getAction() == MotionEvent.ACTION_MOVE){
            int dx = x - mLastX;
            int dy = y - mLastY;
            consume = true;
            onFingerMove(x, y, dx, dy);
        }else if(e.getAction() == MotionEvent.ACTION_UP){
            consume = true;
            onFingerUp(x, y);
        }

        mLastX = x;
        mLastY = y;
        return consume;
    }
}
