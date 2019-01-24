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

import java.util.List;

/**
 * status progress view
 *
 * @author barry
 * @version 1.0
 * @date 2019/1/23
 */
public class StatusProgressView extends View {


    private int mItemCount = 0;

    private int mCompleteState = 3;

    private int mCompleteColor;
    private int mUnCompleteColor;


    private Bitmap mCompleteBitmap;
    private Bitmap mUncompletedBitmap;


    private int mItemWidth;
    private int mItemHeight;

    private Paint mPaint;

    private TextPaint mTextPaint;

    private CharSequence[] mStatuValues;

    private float mLineSize;

    private boolean mHalfLine = false;

    public StatusProgressView(Context context) {
        this(context, null);
    }

    public StatusProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StatusProgressView, defStyleAttr, 0);

        mCompleteState = array.getInt(R.styleable.StatusProgressView_completeState, 1);
        mLineSize = array.getDimension(R.styleable.StatusProgressView_lineSize, dp2px(5f));
        int complete = array.getResourceId(R.styleable.StatusProgressView_completeBackgroud, R.drawable.jd_step_01);
        int uncompete = array.getResourceId(R.styleable.StatusProgressView_uncompleteBackgroud, R.drawable.jd_step_02);
        String[] coloes = {"#168FF7", "#C1C1C1"};
        mCompleteColor = array.getColor(R.styleable.StatusProgressView_completeColor, Color.parseColor(coloes[0]));
        mUnCompleteColor = array.getColor(R.styleable.StatusProgressView_uncompleteColor, Color.parseColor(coloes[1]));

        float textSize = array.getDimension(R.styleable.StatusProgressView_itemTextSize, sp2px(16));

        mStatuValues = array.getTextArray(R.styleable.StatusProgressView_itemValues);
        if (mStatuValues != null)
            mItemCount = mStatuValues.length;

        mHalfLine = array.getBoolean(R.styleable.StatusProgressView_itemHalfLine, false);
        array.recycle();

        mCompleteBitmap = BitmapFactory.decodeResource(getResources(), complete);
        mUncompletedBitmap = BitmapFactory.decodeResource(getResources(), uncompete);

        mPaint = new Paint();
        mPaint.setStrokeWidth(mLineSize);

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(textSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mItemCount != 0)
            mItemWidth = w / mItemCount;
        else
            mItemWidth = w;
        mItemHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mItemCount; i++) {

            Bitmap bitmap;
            if (i < mCompleteState) {
                bitmap = mCompleteBitmap;
                mPaint.setColor(mCompleteColor);
                mTextPaint.setColor(mCompleteColor);
            } else {
                bitmap = mUncompletedBitmap;
                mPaint.setColor(mUnCompleteColor);
                mTextPaint.setColor(mUnCompleteColor);
            }

            canvas.drawBitmap(bitmap, (mItemWidth * i + mItemWidth / 2) - bitmap.getWidth() / 2, mItemHeight / 2 - bitmap.getHeight() / 2, mPaint);

            if (i != 0) {
                canvas.drawLine(i * mItemWidth, mItemHeight / 2,
                        (i * mItemWidth + mItemWidth / 2) - bitmap.getWidth() / 2, mItemHeight / 2, mPaint);
            }
            if (mStatuValues != null)
                canvas.drawText((String) mStatuValues[i], (mItemWidth * i + mItemWidth / 2), mItemHeight / 2 + bitmap.getHeight(), mTextPaint);

            if (i == mCompleteState - 1 && !mHalfLine) {
                mPaint.setColor(mUnCompleteColor);
            }

            if (i != mItemCount - 1) {
                canvas.drawLine((mItemWidth * i + mItemWidth / 2) + bitmap.getWidth() / 2, mItemHeight / 2,
                        (i + 1) * mItemWidth, mItemHeight / 2, mPaint);
            }


        }
    }

    /**
     * 获取线条size
     *
     * @return
     */
    public float getLineSize() {
        return mLineSize;
    }

    /**
     * 设置线条size
     *
     * @param line
     */
    public void setLineSize(float line) {
        mLineSize = line;
        mPaint.setStrokeWidth(dp2px(line));
        invalidate();
    }

    /**
     * 设置完成状态的图标
     *
     * @param completeBitmap
     */
    public void setCompleteBitmap(Bitmap completeBitmap) {
        mCompleteBitmap = completeBitmap;
        invalidate();
    }

    /**
     * 设置未完成状态图标
     *
     * @param uncompletedBitmap
     */
    public void setUncompletedBitmap(Bitmap uncompletedBitmap) {
        mUncompletedBitmap = uncompletedBitmap;
        invalidate();
    }

    /**
     * 设置完成状态颜色
     *
     * @param completeColor
     */
    public void setCompleteColor(int completeColor) {
        mCompleteColor = completeColor;
        invalidate();
    }

    /**
     * 设置未完成状态颜色
     *
     * @param unCompleteColor
     */
    public void setUnCompleteColor(int unCompleteColor) {
        mUnCompleteColor = unCompleteColor;
        invalidate();
    }

    /**
     * 设置statuValues
     *
     * @param statuValues
     */
    public void setStatuValues(String[] statuValues) {
        mStatuValues = statuValues;
        mItemCount = mStatuValues.length;
        invalidate();
    }

    /**
     * 设置statuValues
     *
     * @param statuValues
     */
    public void setStatuValues(List<String> statuValues) {
        setStatuValues((String[]) statuValues.toArray());
    }

    /**
     * 设置完成进度
     *
     * @param state
     */
    public void setCompleteState(int state) {
        if (state > mItemCount) {
            throw new IndexOutOfBoundsException("state index out of itemCount!");
        }
        mCompleteState = state;
        invalidate();
    }

    /**
     * 获取完成进度
     *
     * @return
     */
    public int getCompleteState() {
        return mCompleteState;
    }


    private int dp2px(float dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int px2dp(float px) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    private float px2sp(float px) {
        return (int) (px / getContext().getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    private float sp2px(float sp) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }


}
