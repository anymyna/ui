package com.example.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.anzewei.parallaxbacklayout.ParallaxBack;


@ParallaxBack(edge = ParallaxBack.Edge.LEFT)
public class ParallaxBackLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallaxbacklayout);

    }

}
