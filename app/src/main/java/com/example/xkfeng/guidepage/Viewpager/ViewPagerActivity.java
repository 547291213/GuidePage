package com.example.xkfeng.guidepage.Viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xkfeng.guidepage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by initializing on 2018/7/16.
 */

public class ViewPagerActivity extends AppCompatActivity {


    private ViewPager viewPager ;
    private LinearLayout linearLayout ;
    private PagerAdapter pagerAdapter ;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);
        viewPager = (ViewPager)findViewById(R.id.vp_viewGuide) ;
        linearLayout = (LinearLayout)findViewById(R.id.ll_indicator) ;

        init();
    }

    /*
      初始化
     */
    private void init()
    {

        /*
        完成Fragment列表的创建
         */
        for (int i = 0 ;i < 3 ; i++)
        {
            ContentFragemnt fragemnt = new ContentFragemnt() ;
            Bundle bundle = new Bundle() ;
            bundle.putInt("index" , i);
            fragemnt.setArguments(bundle);
            fragments.add(fragemnt) ;

        }
        //Adapter的初始化
        pagerAdapter = new GuideAdapger( getSupportFragmentManager(),fragments) ;
        viewPager.setAdapter(pagerAdapter);

        //添加page滑动的监听事件，用于同步dot的变化
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0 ; i < 3 ; i++)
                {
                    linearLayout.getChildAt(i).setBackgroundResource(i==position ? R.drawable.dot_selected : R.drawable.dot_normal);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*
           初始化dot
         */
        initDot() ;

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
                    viewPager.setCurrentItem(v.getId());

                }
            });
            linearLayout.addView(view);
        }
    }
}
