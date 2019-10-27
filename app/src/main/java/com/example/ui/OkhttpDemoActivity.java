package com.example.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.ui.aspect.DoubleClickAnnotation;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkhttpDemoActivity extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    String Tag = "OkhttpDemoActivity";
    @Bind(R.id.img)
    ImageView img;


    @Bind(R.id.big_img)
    ImageView bigImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);
        ButterKnife.bind(this);


        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sugar, options);
        bigImg.setImageBitmap(bitmap);
        //bigImg.setBackgroundResource(R.drawable.sugar);

        OkHttpUtils
                .get()//
                .url("http://images.csdn.net/20150817/1.jpg")//
                .build()//
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(Tag, "onError ");
                    }
                    @Override
                    public void onResponse(Bitmap response, int id) {
                        Log.i(Tag, "onResponse ");
                        img.setImageBitmap(response);
                    }
                });
    }





    public void onClickPost(View v) {

        Log.i(Tag, "onClickPost");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .post(formBody)
                .url("http://httpbin.org/post")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i(Tag, "response：" + response.body().string());

            }
        });

    }

    @DoubleClickAnnotation
    public void onClickGet(View v) {
        Log.i(Tag, "onClickGet");

        getName("Jake","Wharton");

        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url("https://api.github.com/events")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i(Tag, "response：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    @DebugLog
    public String getName(String first, String last) {
        SystemClock.sleep(15); // Don't ever really do this!
        return first + " " + last;
    }


}
