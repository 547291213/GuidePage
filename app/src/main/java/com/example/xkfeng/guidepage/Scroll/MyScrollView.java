package com.example.xkfeng.guidepage.Scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ScrollView;

import com.example.xkfeng.guidepage.Viewpager.ContentFragemnt;

/**
 * Created by initializing on 2018/7/16.
 */

public class MyScrollView extends ScrollView {


    public OnScrollChangedListener listener ;

    public MyScrollView(Context context) {
        this(context , null);
    }

    public MyScrollView(Context context , AttributeSet set)
    {
        super(context , set);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (listener != null)
        {
            listener.onScrollChanged(t , oldt);
        }


    }

    public void setListener(OnScrollChangedListener listener) {
        this.listener = listener;
    }

    public interface OnScrollChangedListener{
        public void onScrollChanged(int top , int oldTop) ;
    }
}
