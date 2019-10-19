package com.example.ui.retrofit;

import androidx.annotation.NonNull;

/**
 * Created by Carson_Ho on 17/3/20.
 */

public class Translation {

    private int status;

    private Content content;
    private static class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }




    //定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString() + "  status =  " +status +  " ;Content.from = "+ content.from + " ; Content.to = "+content.to + " ; Content.vendor ; "+ content.vendor +" ; Content.out "+content.out + ";Content.errNo  "+ content.errNo;
    }
}
