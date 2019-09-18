package com.example.mvp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvp.myapplication.contract.TestContract;
import com.example.mvp.myapplication.presenter.TestPresenter;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements TestContract.View, View.OnClickListener {

    private TestPresenter testPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(this);
        testPresenter=new TestPresenter(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("duck"," onClick ");
        testPresenter.jump();
    }


    @Override
    public void onFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "onSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        Toast.makeText(this, "getName", Toast.LENGTH_SHORT).show();
        return "name";
    }
}
