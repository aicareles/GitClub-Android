package com.i502tech.gitclub.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public abstract class BaseFrameLayout extends FrameLayout {

    public BaseFrameLayout(@NonNull Context context) {
        super(context);
        bindLayout(context, null, 0);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bindLayout(context, attrs, 0);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindLayout(context, attrs, defStyleAttr);
    }

    protected abstract void bindLayout(Context context, AttributeSet attrs, int defStyleAttr);

}
