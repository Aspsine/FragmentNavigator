package com.aspsine.fragmentnavigator.demo.ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.fragmentnavigator.demo.R;

/**
 * Created by aspsine on 16/3/31.
 */
public class BottomNavigatorView extends LinearLayoutCompat {

    OnBottomNavigatorViewItemClickListener mOnBottomNavigatorViewItemClickListener;

    public interface OnBottomNavigatorViewItemClickListener {
        void onBottomNavigatorViewItemClick(int position, View view);
    }

    public BottomNavigatorView(Context context) {
        this(context, null);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);
        inflate(context, R.layout.layout_bottom_navigator, this);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnBottomNavigatorViewItemClickListener.onBottomNavigatorViewItemClick(finalI, v);
                }
            });
        }
    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == position) {
                selectChild(child, true);
            } else {
                selectChild(child, false);
            }
        }
    }

    private void selectChild(View child, boolean select) {
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            group.setSelected(select);
            for (int i = 0; i < group.getChildCount(); i++) {
                selectChild(group.getChildAt(i), select);
            }
        } else {
            child.setSelected(select);
            if (child instanceof ImageView) {
                ImageView iv = (ImageView) child;
                Drawable drawable = iv.getDrawable().mutate();
                if (select) {
                    drawable.setColorFilter(getResources().getColor(R.color.colorTabSelected), PorterDuff.Mode.SRC_ATOP);
                } else {
                    drawable.setColorFilter(getResources().getColor(R.color.colorTabNormal), PorterDuff.Mode.SRC_ATOP);
                }
            }
        }
    }

    public void setOnBottomNavigatorViewItemClickListener(OnBottomNavigatorViewItemClickListener listener) {
        this.mOnBottomNavigatorViewItemClickListener = listener;
    }
}
