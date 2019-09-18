package com.example.mvp.myapplication.model;

import com.example.mvp.myapplication.contract.TestContract;
import com.example.mvp.myapplication.presenter.TestPresenter;

public class TestModel implements TestContract.Model {


    @Override
    public void jump(String name, TestPresenter testPresenter) {
        if (name.equals("empty")){
            testPresenter.onFail("Empty");
        }else{
            testPresenter.onSuccess();
        }
    }
}
