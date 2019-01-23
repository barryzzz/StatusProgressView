package com.lsl.statusprogressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author barry
 * @version 1.0
 * @date 2019/1/23
 */
public class StatusProgressView extends View {


    private int mItemCount = 6;

    private int mCompleteState = 3;

    private int mItemWidth;
    private int mItemHeight;

    private Bitmap mCompleteBitmap;
    private Bitmap mUncompleteBitmap;

    private Bitmap mBitmap;

    private Paint mPaint;

    private TextPaint mTextPaint;

    private String[] strs = {"受理中", "审批中", "制证", "测试", "公测", "爬虫程序"};

    public StatusProgressView(Context context) {
        this(context, null);
    }

    public StatusProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StatusProgressView, defStyleAttr, 0);

        mItemCount = array.getInteger(R.styleable.StatusProgressView_itemCount, 1);

        array.recycle();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audit_complete);
        mUncompleteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.audit_uncomplete);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20f);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(sp2px(16f));
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mItemWidth = w / mItemCount;
        mItemHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mItemCount; i++) {
            canvas.drawBitmap(mBitmap, (mItemWidth * i + mItemWidth / 2) - mBitmap.getWidth() / 2, mItemHeight / 2 - mBitmap.getHeight() / 2, mPaint);
            if (i != 0) {
                canvas.drawLine(i * mItemWidth, mItemHeight / 2,
                        (i * mItemWidth + mItemWidth / 2) - mBitmap.getWidth() / 2, mItemHeight / 2, mPaint);
            }
            if (i != mItemCount - 1) {
                canvas.drawLine((mItemWidth * i + mItemWidth / 2) + mBitmap.getWidth() / 2, mItemHeight / 2,
                        (i + 1) * mItemWidth, mItemHeight / 2, mPaint);
            }

            canvas.drawText(strs[i], (mItemWidth * i + mItemWidth / 2), mItemHeight / 2 + mBitmap.getHeight(), mTextPaint);

        }
    }


    public int dp2px(float dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int px2dp(float px) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public float px2sp(float px) {
        return (int) (px / getContext().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public float sp2px(float sp) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }


}
