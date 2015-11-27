package com.example.EasyWidget.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hangli2 on 2015/10/21.
 */
public class WaveView extends View {
    private Path foregroundPath;//前景色路径
    private Path backgroundPath; //背景色路径
    private int foregroundColor;
    private int backgroundColor;
    private Paint foregroundPaint;
    private Paint backgroundPaint;
    private float amplitude = 50.0f;//振幅
    private float period = 1280.0f;//周期
    private float X_SPACE = 20.0f;//绘图间隔
    private float B_OFFSET = 50.0f;//背景，移动间隔
    private float F_OFFSET = 25.0f;//前景，移动间隔
    private float M_OFFSET = 0;
    private float N_OFFSET = 0;
    private float progress = 0.5f;
    private float minPro =0.03f;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public WaveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPath();
        initPaint();
        startWave();
        period = getMeasuredWidth()/2;
        amplitude = period/50;
//        minPro = amplitude/getMeasuredWidth();
    }

    Handler refreshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float y_offset = getMeasuredHeight()*(1-progress/2);
            if (progress>0&&progress<minPro){
                progress = minPro;
            }
            calculatePath(M_OFFSET += B_OFFSET, N_OFFSET += F_OFFSET, y_offset);
            backgroundPaint.setStrokeWidth(getMeasuredHeight() * progress);
            foregroundPaint.setStrokeWidth(getMeasuredHeight() * progress);
            postInvalidate();
            if (M_OFFSET > Float.MAX_VALUE-100) {
                M_OFFSET = 0.0f;
            }
            if (N_OFFSET > Float.MAX_VALUE-100) {
                N_OFFSET = 0.0f;
            }
            sendEmptyMessageDelayed(1, 100);
        }
    };

    public void stopWave() {
        refreshHandler.removeMessages(1);
    }

    public void startWave() {
        refreshHandler.sendEmptyMessage(1);
    }

    /**
     * 设置进度
     *
     * @param progress 百分比
     */
    public void setProgress(float progress) {
        this.progress = progress;
        if (!refreshHandler.hasMessages(1)){
            startWave();
        }
    }

    public float getProgress() {
        return progress;
    }

    protected void calculatePath(float m_offset, float n_offset, float y_offset) {
        backgroundPath.reset();
        foregroundPath.reset();
        float w = (float) (2 * Math.PI / period);
        backgroundPath.moveTo(0, (float) (amplitude * (Math.sin(w * 0 + m_offset)) + y_offset));
        foregroundPath.moveTo(0, (float) (amplitude * (Math.cos(w * 0 + n_offset)) + y_offset));

        for (float x = 0; x <= getMeasuredWidth(); x += X_SPACE) {
            backgroundPath.lineTo(x, (float) (amplitude * (Math.sin(w * x + m_offset)) + y_offset));
        }
        backgroundPath.lineTo(getMeasuredWidth(), (float) (amplitude * (Math.sin(w * getMeasuredWidth() + m_offset)) + y_offset));

        for (float x = 0; x <= getMeasuredWidth(); x += X_SPACE) {
            foregroundPath.lineTo(x, (float) (amplitude * (Math.cos(w * x + n_offset)) + y_offset));
        }
        foregroundPath.lineTo(getMeasuredWidth(), (float) (amplitude * (Math.cos(w * getMeasuredWidth() + n_offset)) + y_offset));

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawPath(backgroundPath, backgroundPaint);
        canvas.drawPath(foregroundPath, foregroundPaint);
        canvas.drawRect(getLeft(),getMeasuredHeight()*(1-progress/4),getRight(),getBottom(),foregroundPaint);
        if (progress>=1){
            canvas.drawRect(getLeft(),getTop(),getRight(),getBottom(),foregroundPaint);
            stopWave();
        }
    }

    protected void initPaint() {
        foregroundPaint = new Paint(Paint.DITHER_FLAG);
        foregroundPaint.setAlpha(100);
        foregroundPaint.setAntiAlias(true);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setDither(true);
        foregroundPaint.setColor(Color.parseColor("#0080FF"));
        foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
        foregroundPaint.setStrokeJoin(Paint.Join.ROUND);
//        foregroundPaint.setStrokeWidth(10);

        backgroundPaint = new Paint();
        backgroundPaint.setAlpha(200);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setDither(true);
        backgroundPaint.setColor(Color.parseColor("#97CBFF"));
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        backgroundPaint.setStrokeJoin(Paint.Join.ROUND);
//        backgroundPaint.setStrokeWidth(10);
    }

    protected void initPath() {
        foregroundPath = new Path();
        backgroundPath = new Path();
    }


    public int getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(int foregroundColor) {
        this.foregroundColor = foregroundColor;
        foregroundPaint.setColor(foregroundColor);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        backgroundPaint.setColor(backgroundColor);
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint backgroundPaint) {
        this.backgroundPaint = backgroundPaint;
    }

    public Paint getForegroundPaint() {
        return foregroundPaint;
    }

    public void setForegroundPaint(Paint foregroundPaint) {
        this.foregroundPaint = foregroundPaint;
    }

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public float getX_SPACE() {
        return X_SPACE;
    }

    public void setX_SPACE(float x_SPACE) {
        X_SPACE = x_SPACE;
    }
}
