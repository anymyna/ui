package com.example.ui;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class TablayoutRecyclerViewActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private TabRecyclerAdapter mAdapter;
    private String titles[] = new String[]{"TAB1", "TAB2", "TAB3", "TAB4", "TAB5", "TAB6", "TAB7", "TAB8", "TAB9", "TAB10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tablayout_recyclerview);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initTab();
        mAdapter = new TabRecyclerAdapter();
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                mTabLayout.setScrollPosition(mManager.findFirstVisibleItemPosition(), 0, true);

                int pos = mManager.findFirstVisibleItemPosition();

                if(pos == 0)
                {
                    mTabLayout.setScrollPosition(0, 0, true);
                }
                else  if(pos == 1)
                {
                    mTabLayout.setScrollPosition(1, 0, true);

                }                else  if(pos == 5)
                {
                    mTabLayout.setScrollPosition(2, 0, true);
                }
                else  if(pos == 7)
                {
                    mTabLayout.setScrollPosition(3, 0, true);
                }
            }
        });
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorBlue));
        mTabLayout.setTabTextColors(Color.WHITE, ContextCompat.getColor(this, R.color.colorBlue));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //mManager.scrollToPositionWithOffset(tab.getPosition(), 0);
                //  坑 快速滑动底部，切换tab mRecyclerView 滑动指定位置失效
                int pos = tab.getPosition();
                if(pos == 0)
                {
                    mManager.scrollToPositionWithOffset(0, 0);
                }
                else  if(pos == 1)
                {
                    mManager.scrollToPositionWithOffset(1, 0);
                }
                else  if(pos == 2)
                {
                    mManager.scrollToPositionWithOffset(5, 0);
                }
                else  if(pos == 3)
                {
                    mManager.scrollToPositionWithOffset(7, 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //  坑 快速滑动底部，切换tab 失败
                //  mLinearSnapHelper.attachToRecyclerView(mRecyclerView);  // item滑动居中
                //  ru.noties:scrollable   scrollableLayout.animateScroll(1000).setDuration(250L).start();  Animate Scroll // collapsed  header

                //关于Android7.x系统Toast显示异常BadTokenException解决方案
                //调用Toast的show()方法后，将主线程阻塞5s，即可稳定复现这个异常
                //Toast.makeToast(context, "123", LENGTH_LONG).show();
                //Thread.sleep(5*1000);
                //出现这个异常的原因就是Toast显示时会有一个Token，这个Token是由WindowManager创建并管理的，重点是这个Token有时间限制，当超过了一个Toast的显示时长（LENGTH_LONG）后，会把这个Token置为无效。所以把Toast延迟显示后，使用的Token就已经过期了。

            }
        });

    }

    private void initTab() {
        //mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]).setTag(Constants.TAG_ZERO));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]).setTag(Constants.TAG_ONE));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[2]).setTag(Constants.TAG_TWO));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[3]).setTag(Constants.TAG_THREE));
    }

    private class Constants {
        public static final int TAG_ZERO = 0;
        public static final int TAG_ONE = 1;
        public static final int TAG_TWO = 2;
        public static final int TAG_THREE = 3;
        public static final int TAG_FOUR = 4;
        public static final int TAG_FIVE = 5;
        public static final int TAG_SIX = 6;
        public static final int TAG_SEVEN = 7;
        public static final int TAG_EIGHT = 8;
        public static final int TAG_NINE = 9;
    }

    public class TabRecyclerAdapter extends RecyclerView.Adapter {

        public static final int VIEW_TYPE_ITEM = 1;
        public static final int VIEW_TYPE_FOOTER = 2;
        public static final int VIEW_TYPE_BIG = 3;
        private int parentHeight;
        private int itemHeight;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        parentHeight = mRecyclerView.getHeight();
                        itemHeight = view.getHeight();
                    }
                });
                return new ItemViewHolder(view);
            }
            else {

                View view = new View(parent.getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parentHeight - itemHeight));
                return new FooterViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position != titles.length) {
                ((ItemViewHolder) holder).setData(position);
            }
        }

        @Override
        public int getItemCount() {
            return titles.length + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == titles.length) {
                return VIEW_TYPE_FOOTER;
            }

            else {
                return VIEW_TYPE_ITEM;
            }
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {

            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }



        class ItemViewHolder extends RecyclerView.ViewHolder {

            public TextView mTitle;

            public ItemViewHolder(View itemView) {
                super(itemView);
                mTitle = (TextView) itemView.findViewById(R.id.title);
            }

            public void setData(int position) {
                switch (position) {
                    case Constants.TAG_ZERO:
                    case Constants.TAG_ONE:
                    case Constants.TAG_TWO:
                    case Constants.TAG_THREE:
                    case Constants.TAG_FOUR:
                    case Constants.TAG_FIVE:
                    case Constants.TAG_SIX:
                    case Constants.TAG_SEVEN:
                    case Constants.TAG_EIGHT:
                    case Constants.TAG_NINE:
                        mTitle.setText("Item " + position);
                        break;
                   default:
                       mTitle.setText("Item");



                }
            }
        }
    }
}
