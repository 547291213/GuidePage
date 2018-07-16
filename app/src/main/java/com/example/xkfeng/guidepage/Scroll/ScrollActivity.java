package com.example.xkfeng.guidepage.Scroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xkfeng.guidepage.MainActivity;
import com.example.xkfeng.guidepage.R;

/**
 * Created by initializing on 2018/7/16.
 */

public class ScrollActivity extends AppCompatActivity{


    private LinearLayout linearLayout ;
    private MyScrollView scrollView ;
    private Button button ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_main);

        scrollView = (MyScrollView)findViewById(R.id.msv_sView) ;
        linearLayout = (LinearLayout)findViewById(R.id.ll_layout) ;
        button = (Button)findViewById(R.id.bt_joinBtn) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScrollActivity.this , MainActivity.class));
            }
        });

        scrollView.setListener(new MyScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int top, int oldTop) {
                if (top > oldTop)
                {
                    //向下滑动
                    linearLayout.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(ScrollActivity.this , R.anim.scroll_show) ;
                    linearLayout.startAnimation(animation);
                }
                else if (top < oldTop)
                {
                    //向上滑动
                    linearLayout.setVisibility(View.INVISIBLE);
                    Animation animation =  AnimationUtils.loadAnimation(ScrollActivity.this ,R.anim.scroll_close) ;
                    linearLayout.startAnimation(animation);
                }
            }
        });
    }
}
