package com.example.avadla.swip_up;


import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    BottomSheetBehavior mBottomSheetBehavior;
    Boolean firsttime = true;
    List list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        View bottomSheet = findViewById( R.id.bottom_sheet );

        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.linear);
        final NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.bottom_sheet);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.upbar);
        final ImageView imageView = (ImageView) findViewById(R.id.back_arrow);
        final ImageView imageView1 = (ImageView) findViewById(R.id.checkbox);
        final ImageView imageView2 = (ImageView) findViewById(R.id.gallery);
        final ImageView imageView4 = (ImageView) findViewById(R.id.gallery1);
        final ImageView imageView5 = (ImageView) findViewById(R.id.gallery2);
        final ImageView imageView3 = (ImageView) findViewById(R.id.gallery_pics);

        imageView.setOnClickListener(this);
        firsttime = true;
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(400);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(400);
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING){

                }
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    firsttime = false;
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                float alpha = 255;

                if(firsttime){
                    list.add(slideOffset);
                }

                slideOffset = slideOffset*100;  // percentage bottomsheet occupy

                if(slideOffset >= 0) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.VISIBLE);
                    nestedScrollView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    relativeLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
                    alpha = (slideOffset) * 2.55f;  //2.25f as the maximum alpha is 255
                    imageView.setImageAlpha((int)alpha);
                    imageView1.setImageAlpha((int)alpha);
                    imageView2.setImageAlpha(255 - (int)alpha);
                    imageView4.setImageAlpha(255 - (int)alpha);
                    imageView5.setImageAlpha(255 - (int)alpha);
                    imageView3.setImageAlpha((int)alpha);
                    relativeLayout.getBackground().setAlpha((int)alpha);
                    nestedScrollView.getBackground().setAlpha((int)alpha);
                }

                Log.e("offset",slideOffset+"");

            }

        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_arrow:{
                mBottomSheetBehavior.setPeekHeight(400);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    }
}
