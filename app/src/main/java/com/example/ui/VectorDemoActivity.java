package com.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;


@Route(path = "/test/activity")
public class VectorDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_vector_demo);

        View decor = getWindow().getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);
        //testQueue();

        //testStack();


        //testVector();

    }

    private void testVector() {
        Vector<String> hs = new Vector<String>();
        hs.add("aa");
        hs.add("bb");
        hs.add("aa");
        hs.add("cc");
        hs.add("aa");
        hs.add("dd");
        Iterator iterator = hs.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void testStack() {
        Stack stack=new Stack();
        //1.empty()栈是否为空
        System.out.println(stack.empty());
        //2.peek()栈顶值    3.进栈push()
        stack.push(new Integer(1));
        stack.push("b");
        System.out.println(stack.peek());
        //4.pop()出栈
        stack.pop();
        System.out.println(stack.peek());
    }

    private void testQueue() {
        //队列（Queue）用法
        Queue<String> queue = new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("poll="+queue.poll()); //返回第一个元素，并在队列中删除
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element="+queue.element()); //返回第一个元素
        for(String q : queue){
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("peek="+queue.peek()); //返回第一个元素
        for(String q : queue){
            System.out.println(q);
        }
    }

}
