package org.ayo.sample.menu.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 */
public class TouchBoard extends View {

    public interface Callback{
        void onDown(int x, int y);
        void onUp(int x, int y);
        void onClick();
        void onMove(int x, int y, int dx, int dy);
    }


    public TouchBoard(Context context) {
        super(context);
        initttt();
    }

    public TouchBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initttt();
    }

    public TouchBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initttt();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchBoard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initttt();
    }

    private int mColor = Color.parseColor("#55000000");
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);



    private void initttt(){
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, getMeasuredWidth()/2, mPaint);
    }

    //===========================================
    //为了让控件支持wrap_content时，内容尺寸取200px，需要我们重写measure过程
    //===========================================
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                mearureWidth(widthMeasureSpec),
                mearureHeight(heightMeasureSpec));
    }

    private int mearureWidth(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = calculateContentWidth();
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int mearureHeight(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = calculateContentHeight();
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int calculateContentWidth(){
        return 200;
    }

    private int calculateContentHeight(){
        return 200;
    }

    int mLastX = 0;
    int mLastY = 0;

    long downTime = 0;
    private boolean isValidClick = false;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        super.onTouchEvent(e);
        boolean consume = false;
        int x = (int)e.getX();
        int y = (int)e.getY();
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            consume = true;
            downTime = System.currentTimeMillis();
            if(callback != null) callback.onDown(x, y);
        }else if(e.getAction() == MotionEvent.ACTION_MOVE){
            int dx = x - mLastX;
            int dy = y - mLastY;
            consume = true;
            if(callback != null) callback.onMove(x, y, dx, dy);
        }else if(e.getAction() == MotionEvent.ACTION_UP){
            consume = true;
            if(callback != null) callback.onUp(x, y);
            if(System.currentTimeMillis() - downTime <= 300){
                isValidClick = true;
                if(callback != null) callback.onClick();
            }else{
                isValidClick = false;
            }
        }

        mLastX = x;
        mLastY = y;
        return consume;
    }

    private Callback callback;

    public void setCallback(TouchBoard.Callback callback){
        this.callback = callback;
    }


}