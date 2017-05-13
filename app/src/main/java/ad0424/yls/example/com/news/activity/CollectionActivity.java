package ad0424.yls.example.com.news.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyCollectionAdapter;
import ad0424.yls.example.com.news.model.CollectBean;
import ad0424.yls.example.com.news.utils.BmobUtil;

public class CollectionActivity extends AppCompatActivity {
    private SlideAndDragListView mSlideAndDragListView;
    private Menu menu = new Menu(true, 0);
    private MyCollectionAdapter mMyCollectionAdapter;
    private List<CollectBean> mCollectBeen = new ArrayList<>();
    private Handler mHandler = new Handler();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initData();
        initViews();
    }

    private void initData() {
        mCollectBeen = BmobUtil.queryCollection();
    }

    private void initViews() {
        mSlideAndDragListView = (SlideAndDragListView) findViewById(R.id.collectionListView);
        menu.addItem(new MenuItem.Builder().setWidth(120)
                .setBackground(new ColorDrawable(Color.RED))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setIcon(getResources().getDrawable(R.mipmap.ic_menu_delete))
                .build());

        mSlideAndDragListView.setMenu(menu);
        mSlideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
                switch (direction) {

                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) {
                            case 0://icon
                                BmobUtil.delCollection(mCollectBeen.get(itemPosition));
                                mCollectBeen = BmobUtil.queryCollection();
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mMyCollectionAdapter.changeData(mCollectBeen);
                                    }
                                }, 1500);
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                        }
                        break;
                    default:
                        return Menu.ITEM_NOTHING;
                }
                return itemPosition;
            }
        });


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyCollectionAdapter = new MyCollectionAdapter(mCollectBeen, CollectionActivity.this);
                mSlideAndDragListView.setAdapter(mMyCollectionAdapter);
                mMyCollectionAdapter.changeData(mCollectBeen);
            }
        }, 1500);

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_back);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.search:

                break;

        }
        return true;
    }
}
