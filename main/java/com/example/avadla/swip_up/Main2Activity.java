package com.example.avadla.swip_up;


import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    BottomSheetBehavior mBottomSheetBehavior;
    Boolean firsttime = true;
    Boolean onclick = true;
    RecyclerView recyclerView;
    List list = new ArrayList();
    NestedScrollView nestedScrollView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        View bottomSheet = findViewById( R.id.bottom_sheet );
        imageView =(ImageView) findViewById(R.id.up);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        final NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.bottom_sheet);

        imageView.setOnClickListener(this);

        firsttime = true;
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        String names[] = {"vicky","amar","akbar","anthony","kumble","anil","dhoni","michael","kamba","sudeep","royal","avenger","pulsar"};
        Adapter_class adapter_class = new Adapter_class(Main2Activity.this,names);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_class);
        recyclerView.setNestedScrollingEnabled(false);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {

                    if(onclick) {
                        imageView.setVisibility(View.INVISIBLE);
                    }
                    else {
                        imageView.setVisibility(View.VISIBLE);
                    }
                    mBottomSheetBehavior.setPeekHeight(0);
                    onclick = false;
                    firsttime = true;
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING){
                    imageView.setVisibility(View.INVISIBLE);

                }
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    imageView.setVisibility(View.INVISIBLE);
                    firsttime = false;
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                float alpha = 255;

                if(firsttime){
                    nestedScrollView.setBackgroundColor(getResources().getColor(R.color.semi_transparent));
                    list.add(slideOffset);
                }

                slideOffset = slideOffset*100;  // percentage bottomsheet occupy

                if(slideOffset < 0){
                    //alpha = Math.abs((Float) list.get(0));
                    nestedScrollView.setBackgroundColor(getResources().getColor(R.color.semi_transparent));
                    //nestedScrollView.getBackground().setAlpha((int)alpha);  // set alpha first time
                }

                else if(slideOffset > 0 && !firsttime) {
                    alpha = (slideOffset) * 2.55f;  //2.25f as the maximum alpha is 255
                    nestedScrollView.getBackground().setAlpha((int)alpha);
                }
                Log.e("offset",slideOffset+"");


               /* bottomSheet.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = MotionEventCompat.getActionMasked(event);
                        switch (action) {
                            case MotionEvent.ACTION_UP:
                                return true;
                            case MotionEvent.ACTION_DOWN:
                                return false;
                            default:
                                return true;
                        }
                    }
                });*/
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.up: {
                imageView =(ImageView) findViewById(R.id.up);
                nestedScrollView = (NestedScrollView) findViewById(R.id.bottom_sheet);
                imageView.setVisibility(View.INVISIBLE);
                mBottomSheetBehavior.setPeekHeight(400);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                nestedScrollView.scrollTo(0,0);
                onclick = true;
                break;
            }
        }
    }

}
