package com.example.mvp.myapplication.presenter;

import com.example.mvp.myapplication.contract.TestContract;
import com.example.mvp.myapplication.model.TestModel;

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View view;
    private TestModel testModel;

    public TestPresenter(TestContract.View view) {
        this.view = view;
        testModel=new TestModel();
    }

    @Override
    public void onFail(String msg) {
        view.onFail(msg);
    }


    @Override
    public void onSuccess() {
        view.onSuccess();
    }


    public void jump(){

        testModel.jump("empty",this);
        //testModel.jump("test",this);
    }
}
