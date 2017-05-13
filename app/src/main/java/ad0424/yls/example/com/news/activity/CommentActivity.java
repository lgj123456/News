package ad0424.yls.example.com.news.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyCommentAdapter;
import ad0424.yls.example.com.news.listener.UpdateCommentListener;
import ad0424.yls.example.com.news.model.UserBean;
import ad0424.yls.example.com.news.utils.BmobUtil;

public class CommentActivity extends AppCompatActivity implements UpdateCommentListener{

    private RecyclerView mCommentRecycle;
    private List<UserBean> mUserBeen = new ArrayList<>();
    private MyCommentAdapter mMyCommentAdapter;
    private Handler mHandler = new Handler();
    private android.view.animation.Animation animation;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initData();
        initViews();

    }

    private void initData() {

        mUserBeen = BmobUtil.query();

    }

    private void initViews() {
        mCommentRecycle = (RecyclerView) findViewById(R.id.comment_recycle);
        LinearLayoutManager manager = new LinearLayoutManager(CommentActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentRecycle.setLayoutManager(manager);
        mMyCommentAdapter = new MyCommentAdapter(mUserBeen,CommentActivity.this);
        mCommentRecycle.setAdapter(mMyCommentAdapter);
        mMyCommentAdapter.notifyDataSetChanged();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMyCommentAdapter.changeData(mUserBeen);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_back);
        }

    }


    @Override
    public void updatePraiseNum() {
        mUserBeen = BmobUtil.query();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyCommentAdapter.changeData(mUserBeen);
            }
        }, 1500);
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
