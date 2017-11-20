package libo.com.social.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import libo.com.social.R;

/**
 * Created by liaodp on 2017/11/14.
 */

public class WaterView extends View {

    private boolean isShowFrame;
    private int mWidth;
    private int mHeight;
    private float centerX;
    private float centerY;

    private Path canvasPath = new Path();
    private Path firstPath = new Path();
    private Path secondPath = new Path();
    private Paint firstPaint;
    private Paint secondPaint;
    private Paint framePaint;

    private float sin_cycle = 0.01f;//周期 ， 0.01f左右
    float sin_offset = 0.0f;//初项，偏移量
    float h = 0f;


    private int secondPaintColor = Color.parseColor("#9292DA");
    private int firstPaintColor = Color.parseColor("#5353C7");
    private int frameColor = Color.parseColor("#5353C7");
    private float frameWidth;
    private int sin_amplitude = 20;//振幅 ，10到100之间
    private float sin_offset_increment_value = 0.4f;//初项递增值，表示波浪的快慢
    private int sin_up_velocity = 5;//上升速度，参考值3
    private int sleep_time = 100; //休眠时间，参考值100

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        frameWidth = dip2px(context, 2);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterView);
        firstPaintColor = typedArray.getColor(R.styleable.WaterView_waterview_paint_color_first, firstPaintColor);
        secondPaintColor = typedArray.getColor(R.styleable.WaterView_waterview_paint_color_second, secondPaintColor);
        sin_amplitude = typedArray.getInt(R.styleable.WaterView_waterview_amplitude, sin_amplitude);
        sin_offset_increment_value = typedArray.getFloat(R.styleable.WaterView_waterview_offset_increment_value, sin_offset_increment_value);
        sin_up_velocity = typedArray.getInt(R.styleable.WaterView_waterview_up_velocity, sin_up_velocity);
        sleep_time = typedArray.getInt(R.styleable.WaterView_waterview_sleep_time, sleep_time);
        frameWidth = typedArray.getDimension(R.styleable.WaterView_waterview_frame_width, frameWidth);
        frameColor = typedArray.getColor(R.styleable.WaterView_waterview_frame_color, frameColor);
        isShowFrame = typedArray.getBoolean(R.styleable.WaterView_waterview_frame_color, false);
        typedArray.recycle();

        if (frameWidth == 0) isShowFrame = false;

        firstPaint = new Paint();
        firstPaint.setColor(firstPaintColor);
        firstPaint.setAlpha(70);
        firstPaint.setAntiAlias(true);
        secondPaint = new Paint();
        secondPaint.setColor(secondPaintColor);
        secondPaint.setAlpha(90);
        secondPaint.setAntiAlias(true);
        framePaint = new Paint();
        framePaint.setStrokeWidth(frameWidth);
        framePaint.setAntiAlias(true);
        framePaint.setColor(frameColor);
        framePaint.setStyle(Paint.Style.STROKE);//设置空心的
        framePaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置实心圆
    }

    /***
     * 测量view的长宽   1
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 设置宽度
         * 单位 px
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        } else {
            mWidth = dip2px(getContext(), 150);
        }

        /***
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else {
            mHeight = dip2px(getContext(), 150);
        }
        centerX = mWidth / 2;
        centerY = mHeight / 2;

        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.h = mHeight;
        canvasPath.addCircle(centerX, centerY, mHeight / 2, Path.Direction.CCW);
    }

    /**
     * window  中绘制 2
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(canvasPath, Region.Op.INTERSECT);
        //画圆
        if (isShowFrame) canvas.drawCircle(centerX, centerY, mHeight / 2, framePaint);
        canvas.drawPath(secondPath(), secondPaint);
        canvas.drawPath(firstPath(), firstPaint);
    }

    //y = Asin(wx+b)+h ，这个公式里：w影响周期，A影响振幅，h影响y位置，b为初相；
    private Path firstPath() {
        firstPath.reset();
        firstPath.moveTo(0, mHeight);// 移动到左下角的点

        for (float x = 0; x <= mWidth; x++) {
            float y = (float) (sin_amplitude * Math.sin(sin_cycle * x + sin_offset + 40)) + h;
            firstPath.lineTo(x, y);
        }
        firstPath.lineTo(mWidth, mHeight);
        firstPath.lineTo(0, mHeight);
        firstPath.close();
        return firstPath;
    }

    private Path secondPath() {
        secondPath.reset();//复位
        secondPath.moveTo(0, mHeight);// 移动到左下角的点

        for (float x = 0; x <= mWidth; x++) {
            float y = (float) (sin_amplitude * Math.sin(sin_cycle * x + sin_offset)) + h;
            secondPath.lineTo(x, y);
        }
        secondPath.lineTo(mWidth, mHeight);
        secondPath.lineTo(0, mHeight);
        secondPath.close();
        return secondPath;
    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private RunThread runThread;

    public void start() {
        if (runThread == null) {
            runThread = new RunThread();
        }
        runThread.start();
    }



    public void setH(float pro) {
        this.h = mHeight - (mHeight * pro / 100);
    }

    private class RunThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(sleep_time);
                    sin_offset += sin_offset_increment_value;
//                    Log.i("Water", "sin_offset:" + sin_offset);
                    postInvalidate();
                    if (h + sin_amplitude < 0) {
                        if (listener != null) {
                            WaterView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.finish();
                                }
                            });
                        }
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Listener listener;

    public interface Listener {
        void finish();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}