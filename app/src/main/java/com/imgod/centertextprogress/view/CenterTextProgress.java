package com.imgod.centertextprogress.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.imgod.centertextprogress.R;
import com.imgod.centertextprogress.utils.DisplayUtil;

/**
 * CenterTextProgress.java  是液总汇的$DES$类。
 *
 * @author kk
 * @version 2.0.0 2018/3/19 10:26
 * @update kk 2018/3/19 10:26
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class CenterTextProgress extends View {
    public CenterTextProgress(Context context) {
        this(context, null);
    }

    public CenterTextProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private String titleText;//中间文字
    private int titleTextColor;//中间文字颜色
    private int titleTextSize;//中间文字大小
    private int progressBgColor;//进度背景色
    private int progressColor;//进度颜色
    private int progressWidth;//进度圆环的宽度
    private int progress;


    private Rect textBound;

    public CenterTextProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CenterTextProgress);
        titleText = typedArray.getString(R.styleable.CenterTextProgress_titleText);
        titleTextColor = typedArray.getColor(R.styleable.CenterTextProgress_titleTextColor, Color.BLACK);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.CenterTextProgress_titleTextSize, 12);
        progressBgColor = typedArray.getColor(R.styleable.CenterTextProgress_progressBgColor, Color.GRAY);
        progressColor = typedArray.getColor(R.styleable.CenterTextProgress_progressColor, Color.YELLOW);
        progressWidth = typedArray.getDimensionPixelSize(R.styleable.CenterTextProgress_progressWidth, 6);
        typedArray.recycle();

        textBound = new Rect();
        paint.getTextBounds(titleText, 0, titleText.length(), textBound);
    }

    private Paint paint;

    private void initData() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private int height;//控件高度
    private int width;//控件宽度

    public static final int DEFAULT_WIDTH = 60;//默认高度

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //当在布局文件设置高度为wrap_content时，默认为60dp(如果不处理效果和math_parent效果一样)，宽度就不处理了
        if (widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) DisplayUtil.dp2px(DEFAULT_WIDTH, getContext()), MeasureSpec.EXACTLY);
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //当在布局文件设置高度为wrap_content时，默认为60dp(如果不处理效果和math_parent效果一样)，宽度就不处理了
        if (heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) DisplayUtil.dp2px(DEFAULT_WIDTH, getContext()), MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }

        if (width <= height) {//取两者中最短的那个
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }


        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(titleTextColor);
        paint.setTextSize(titleTextSize);
        paint.setStrokeWidth(0);
        canvas.drawText(titleText, width / 2 - textBound.width() / 2 - progressWidth, height / 2 + textBound.height() / 2, paint);

        paint.setColor(progressBgColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(progressWidth);

        int r = Math.min(width, height) / 2;
        //绘制圆环背景
        canvas.drawCircle(width / 2, height / 2, r - progressWidth, paint);

        //绘制进度
        int angle = (int) (360 * progress / 100.0);
        Log.e("test", "angle:" + angle);
        paint.setColor(progressColor);

        RectF rectF = new RectF(progressWidth, progressWidth, width - progressWidth, height - progressWidth);
        canvas.drawArc(rectF, 90, angle, false, paint);
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        invalidate();
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        invalidate();
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
        invalidate();
    }

    public int getProgressBgColor() {
        return progressBgColor;
    }

    public void setProgressBgColor(int progressBgColor) {
        this.progressBgColor = progressBgColor;
        invalidate();
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        invalidate();
    }

    public int getProgressWidth() {
        return progressWidth;
    }

    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress <= 0) {
            progress = 0;
        }

        if (progress >= 100) {
            progress = 100;
        }
        this.progress = progress;
        invalidate();
    }
}
