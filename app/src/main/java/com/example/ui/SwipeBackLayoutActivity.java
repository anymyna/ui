package com.example.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.anzewei.parallaxbacklayout.ParallaxBack;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class SwipeBackLayoutActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallaxbacklayout);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

}
