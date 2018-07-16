package com.example.xkfeng.guidepage.ViewFilpper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.xkfeng.guidepage.MainActivity;
import com.example.xkfeng.guidepage.R;

/**
 * Created by initializing on 2018/7/16.
 */

//        setInAnimation 设置View进入屏幕时候使用的动画
//        setOutAnimation 设置View退出屏幕时候使用的动画
//        showPrevious 显示ViewFlipper里面的上一个View
//        showNext 显示ViewFlipper里面的下一个View
//        setFlipInterval 设置View之间切换的时间间隔
//        startFlipping 使用setFlipInterval方法设置的时间间隔来开始切换所有的View,切换会循环进行
//        stopFlipping 停止View切换 isFlipping 用来判断View切换是否正在进行
//        setDisplayedChild 切换到指定子View

public class ViewFlipperActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{


    private ViewFlipper viewFlipper ;
    private LinearLayout linearLayout ;
    private Button button ;
    private final static String TAG =  "ViewFlipperActivity" ;
    private GestureDetector detector ;
    private static int currentIndex = 0 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper_main);
        viewFlipper = (ViewFlipper)findViewById(R.id.vf_vflipper) ;
        linearLayout = (LinearLayout)findViewById(R.id.ll_indicator) ;
        button = (Button)findViewById(R.id.bt_joinBtn) ;


        init();
        initDot();
    }

    private void init()
    {
        //点击事件的实现
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弯沉个页面的切换
                startActivity(new Intent(ViewFlipperActivity.this , MainActivity.class));
            }
        });

        //给Flipper设置显示和退出的动画
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this ,R.anim.flipper_in ));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this ,R.anim.flipper_out));

        //完成手势的初始化和监听器绑定
        detector = new GestureDetector(this) ;

    }
    /*
      初始化dot
    */
    private void initDot()
    {
        int width = 30 ;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width ,width) ;
        lp.setMargins(0,0,2*width,0);

        for (int i = 0 ; i < 3 ; i++)
        {
            View view = new View(this) ;
            view.setId(i);
            view.setBackgroundResource(i==0 ? R.drawable.dot_selected : R.drawable.dot_normal);
            view.setLayoutParams(lp);
            /*
              实现对底部dot的点击也可以切换页面
             */
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //更新viewFileper的页面
                    viewFlipper.setDisplayedChild(v.getId());
                    //更新当前页面索引
                    currentIndex = v.getId() ;
                    //更新圆点位置
                    setIndicator();
                    Log.i(TAG , "View ONclick") ;
                }
            });
            linearLayout.addView(view);
        }
    }

    private void setIndicator()
    {
        for (int i = 0 ; i< 3 ; i++)
        {
            linearLayout.getChildAt(i).setBackgroundResource(i == currentIndex ? R.drawable.dot_selected : R.drawable.dot_normal);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //向左滑动  展现下一页
        if (e1.getX() > e2.getX())
        {
            //确保不会循环启动页
            if (currentIndex!=2)
                viewFlipper.showNext();
            //更新当前页面索引
            currentIndex = currentIndex < 2 ? ++currentIndex : currentIndex ;


        }
        //向右滑动  展示上一页
//        else if (e1.getX() < e2.getX())
//        {
//            //确保不会循环启动页
//            if (currentIndex!=0)
//                viewFlipper.showPrevious();
//            //更新当前页面索引
//            currentIndex = currentIndex > 0 ? --currentIndex : currentIndex ;
//
//
//        }
        else {
            return  false ;
        }
        //更新圆点位置
        setIndicator();
        return true;
    }
}
