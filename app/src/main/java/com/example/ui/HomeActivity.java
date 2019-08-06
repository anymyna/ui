package com.example.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public void onClickTabLayoutTop(View v){
        startActivity(new Intent(this, TabLayoutTopActivity.class));
    }


    public void onClickTabLayout(View v){
        startActivity(new Intent(this, TablayoutRecyclerViewActivity.class));
    }
}
