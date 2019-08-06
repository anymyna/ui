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
import android.widget.TextView;

public class TablayoutRecyclerViewActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private TabRecyclerAdapter mAdapter;
    private String titles[] = new String[]{"TAB1", "TAB2", "TAB3", "TAB4", "TAB5", "TAB6", "TAB7", "TAB8", "TAB9", "TAB10"};
//    private String titles[] = new String[]{"TAB1", "TAB2", "TAB3", "TAB4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                //滑动RecyclerView list的时候，根据最上面一个Item的position来切换tab
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
                //点击tab的时候，RecyclerView自动滑到该tab对应的item位置
                //mManager.scrollToPositionWithOffset(tab.getPosition(), 0);

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

            }
        });

    }

    private void initTab() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]).setTag(Constants.TAG_ZERO));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]).setTag(Constants.TAG_ONE));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[2]).setTag(Constants.TAG_TWO));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[3]).setTag(Constants.TAG_THREE));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[4]).setTag(Constants.TAG_FOUR));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[5]).setTag(Constants.TAG_FIVE));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[6]).setTag(Constants.TAG_SIX));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[7]).setTag(Constants.TAG_SEVEN));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[8]).setTag(Constants.TAG_EIGHT));
//        mTabLayout.addTab(mTabLayout.newTab().setText(titles[9]).setTag(Constants.TAG_NINE));
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
                //Footer是最后留白的位置，以便最后一个item能够出发tab的切换
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
//                        mTitle.setText(titles[Constants.TAG_ZERO]);
                        //break;
                    case Constants.TAG_ONE:
//                        mTitle.setText(titles[Constants.TAG_ONE]);
                        //break;
                    case Constants.TAG_TWO:
//                        mTitle.setText(titles[Constants.TAG_TWO]);
                        //break;
                    case Constants.TAG_THREE:
//                        mTitle.setText(titles[Constants.TAG_THREE]);
                        //break;
                    case Constants.TAG_FOUR:
//                        mTitle.setText(titles[Constants.TAG_FOUR]);
                        //break;
                    case Constants.TAG_FIVE:
//                        mTitle.setText(titles[Constants.TAG_FIVE]);
                       //break;
                    case Constants.TAG_SIX:
//                        mTitle.setText(titles[Constants.TAG_SIX]);
                        //break;
                    case Constants.TAG_SEVEN:
//                        mTitle.setText(titles[Constants.TAG_SEVEN]);
                        //break;
                    case Constants.TAG_EIGHT:
//                        mTitle.setText(titles[Constants.TAG_EIGHT]);
//                        break;
                    case Constants.TAG_NINE:
//                        mTitle.setText(titles[Constants.TAG_NINE]);
//                        break;
                        mTitle.setText("Item " + position);
                        break;
                   default:
                       mTitle.setText("Item");



                }
            }
        }
    }
}
