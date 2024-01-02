package com.color.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CustomSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {

    private Paint maskPaint;
    private boolean isSelected = false;

    public CustomSeekBar(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        maskPaint = new Paint();
        // 设置遮罩层颜色为激活状态的颜色
        maskPaint.setColor(Color.argb(80, 200, 200, 200));
    }

    private int getActivatedColor() {
        int[] attrs = new int[]{android.R.attr.colorActivatedHighlight};
        int colorResId = 0;

        if (getContext().getTheme().resolveAttribute(android.R.attr.colorActivatedHighlight, new android.util.TypedValue(), true)) {
            colorResId = getContext().getTheme().obtainStyledAttributes(new android.util.TypedValue().data, attrs).getResourceId(0, 0);
        }

        return getResources().getColor(colorResId);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, android.graphics.Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);

        isSelected = gainFocus;
        invalidate(); // 重新绘制SeekBar
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isSelected) {
            // 绘制遮罩层
            canvas.drawRect(0, 0, getWidth(), getHeight(), maskPaint);
        }
    }
}
