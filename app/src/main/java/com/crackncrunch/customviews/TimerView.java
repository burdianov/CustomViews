package com.crackncrunch.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lilian on 18-Feb-17.
 */

public class TimerView extends View {
    private Paint mBackgroundPaint;
    private TextPaint mNumberPaint;

    private long mStartTime;

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
        }
    };

    public TimerView(Context context) {
        super(context);
        init();
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.parseColor("#880E4F"));

        mNumberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mNumberPaint.setColor(ContextCompat.getColor(getContext(), android.R
                .color.white));
        mNumberPaint.setTextSize(64f * getResources().getDisplayMetrics().scaledDensity);
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        updateTimer();
    }

    public void stop() {
        mStartTime = 0;
        removeCallbacks(updateRunnable);
    }

    private void updateTimer() {
        invalidate();

        postDelayed(updateRunnable, 200L);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        float centerX = Math.round(canvasWidth * 0.5f);
        float centerY = Math.round(canvasHeight * 0.5f);

        float radius = (canvasWidth < canvasHeight ? canvasWidth :
                canvasHeight) * 0.5f;

        String number = String.valueOf((long) ((System.currentTimeMillis() -
                mStartTime) * 0.001));
        float textOffsetX = mNumberPaint.measureText(number) * 0.5f;
        float textOffsetY = mNumberPaint.getFontMetrics().ascent * -0.4f;

        canvas.drawCircle(centerX, centerY, radius, mBackgroundPaint);
        canvas.drawText(number, centerX - textOffsetX, centerY + textOffsetY,
                mNumberPaint);
    }
}
