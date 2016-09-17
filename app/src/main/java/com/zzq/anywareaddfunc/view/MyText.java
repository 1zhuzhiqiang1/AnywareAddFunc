package com.zzq.anywareaddfunc.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class MyText extends TextView {
    public MyText(Context context) {
        super(context);
        init();
    }

    public MyText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setFocusable(true);
    }



    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            this.setTextColor(Color.parseColor("#f00"));
        } else {
            this.setTextColor(Color.parseColor("#fff"));
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
