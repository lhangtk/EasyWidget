package com.example.EasyWidget.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hangli2 on 2015/7/23.
 */
public class CircleProgressBar extends View {
    private RectF rectF;
    float progress=90f;//进度
    private boolean useCenter = false;//是否使用圆形，为true使用，false不使用
    private int style;
    public final static int FILL = 1;//填充模式1，充满，绘制区域
    public final static int STROKE = 0;//填充模式0，绘制边框
    //边框宽度，默认为4
    private int strokeWidth = 4;

    public CircleProgressBar(Context context) {
        super(context);
        paint = new Paint(Paint.DITHER_FLAG);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.DITHER_FLAG);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.DITHER_FLAG);
    }
    Paint paint;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectF = new RectF(2,2,MeasureSpec.getSize(widthMeasureSpec)-2,MeasureSpec.getSize(heightMeasureSpec)-2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(0x880000FF);
        paint.setDither(true);

        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    /**
     * 设置进度
     * @param progress 百分比
     */
    public void setProgress(float progress){
        this.progress = 360*progress;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF,-90,progress,useCenter,paint);
    }

    public boolean isUseCenter() {
        return useCenter;
    }

    /**
     * 设置是否使用圆心
     * @param useCenter
     */
    public void setUseCenter(boolean useCenter) {
        this.useCenter = useCenter;
    }

    public float getProgress() {
        return progress;
    }

    public int getStyle() {
        return style;
    }

    /**
     * 设置填充样式
     * @param style 参数为FILL和STROKE之一
     */
    public void setStyle(int style) {
        this.style = style;
        if (style == FILL){
            paint.setStyle(Paint.Style.FILL);
        }else if (style == STROKE){
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * 设置边框宽度
     * @param strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        paint.setStrokeWidth(strokeWidth);
    }
}
