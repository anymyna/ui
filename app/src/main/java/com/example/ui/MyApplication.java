package com.example.ui;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.ui.database.greenDao.db.DaoMaster;
import com.example.ui.database.greenDao.db.DaoSession;
import com.example.ui.dbflow.AppDatabase;
import com.facebook.stetho.Stetho;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());

        DoraemonKit.install(this);
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                //.showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                // .methodCount(2)         // (Optional) How many method line to show. Default 2
                //.methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("angel")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

//        Logger.addLogAdapter(new AndroidLogAdapter());


        FlowManager.init(new FlowConfig.Builder(this).build());

        initGreenDao();

    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }


}