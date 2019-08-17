package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_tab_top, R.id.btn_tab_recyclerview, R.id.btn_vector, R.id.btn_circle_indicator, R.id.btn_okhttp, R.id.btn_bubble})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tab_top:
                startActivity(new Intent(this, TabLayoutTopActivity.class));
                break;
            case R.id.btn_tab_recyclerview:
                startActivity(new Intent(this, TablayoutRecyclerViewActivity.class));
                break;
            case R.id.btn_vector:
                startActivity(new Intent(this, VectorDemoActivity.class));
                break;
            case R.id.btn_circle_indicator:
                startActivity(new Intent(this, CircleIndicatorActivity.class));
                break;
            case R.id.btn_okhttp:
                startActivity(new Intent(this, OkhttpDemoActivity.class));
                break;
            case R.id.btn_bubble:
                startActivity(new Intent(this, BubbleLayoutActivity.class));
                break;
        }
    }
}
