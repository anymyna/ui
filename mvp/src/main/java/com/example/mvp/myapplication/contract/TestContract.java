package com.example.mvp.myapplication.contract;

import com.example.mvp.myapplication.presenter.TestPresenter;

public interface TestContract {
    interface Model {
        void jump(String name,TestPresenter testPresenter);
    }


    interface View {

        void onFail(String msg);

        void onSuccess();

        String getName();

    }


    interface Presenter {
        void onSuccess();
        void onFail(String msg);
    }
}
