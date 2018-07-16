package com.example.xkfeng.guidepage.Viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.xkfeng.guidepage.MainActivity;
import com.example.xkfeng.guidepage.R;


/**
 * Created by initializing on 2018/7/16.
 */

public class ContentFragemnt extends Fragment {


    private int[] images = new int[]{R.drawable.kiwi_fruit ,R.drawable.orange, R.drawable.watermelon} ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.guide_fragment_item , null) ;
        Button btn = (Button)view.findViewById(R.id.bt_joinBtn) ;
        RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.rl_fgLayout) ;
        int index = getArguments().getInt("index") ;
        rl.setBackgroundResource(images[index]);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , MainActivity.class));
            }
        });
        if (index != images.length-1)
        {
            btn.setVisibility(View.GONE);
        }

        return view;
    }
}
