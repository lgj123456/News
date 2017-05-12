package ad0424.yls.example.com.news.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyFragmentAdapter;
import ad0424.yls.example.com.news.fragment.MyFragment;
import ad0424.yls.example.com.news.listener.RefreshDataListener;
import ad0424.yls.example.com.news.model.GuoNeiBeanList;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements RefreshDataListener{
    private ViewPager mViewPager;
    private ArrayList<MyFragment> mMyFragmentArrayList = new ArrayList<MyFragment>();
    private ArrayList<String> mTitleList = new ArrayList<String>();
    private Gson mGson = new Gson();
    private GuoNeiBeanList list = new GuoNeiBeanList();
    private ProgressDialog mProgressDialog;
    private MyFragmentAdapter adapter;
    private String newsType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initFragment();
        initViews();


    }

    private void initFragment() {
        MyFragment f1 = new MyFragment(MainActivity.this);
        //  f1.setTitle("头条页面");

        MyFragment f2 = new MyFragment(MainActivity.this);
        // f2.setTitle("国内页面");

        MyFragment f3 = new MyFragment(MainActivity.this);
        //  f3.setTitle("国际页面");

        MyFragment f4 = new MyFragment(MainActivity.this);
        //  f4.setTitle("体育页面");

        MyFragment f5 = new MyFragment(MainActivity.this);
        //  f5.setTitle("娱乐页面");
        mMyFragmentArrayList.add(f1);
        mMyFragmentArrayList.add(f2);
        mMyFragmentArrayList.add(f3);
        mMyFragmentArrayList.add(f4);
        mMyFragmentArrayList.add(f5);
    }

    private void initTitle() {
        mTitleList.add("头条");
        mTitleList.add("国内");
        mTitleList.add("国际");
        mTitleList.add("体育");
        mTitleList.add("娱乐");
    }

    private void initViews() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), mMyFragmentArrayList, mTitleList);
        mViewPager.setAdapter(adapter);

        //TabLayout

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("温馨提示");
        mProgressDialog.setMessage("数据加载中······");
        mProgressDialog.setCancelable(false);
        getContent("头条");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /**
                 * 当tab第一次选择时候调用
                 * @param tab
                 */
                newsType = tab.getText().toString();

                //  Toast.makeText(MainActivity.this, "当tab第一次选择时候调用", Toast.LENGTH_SHORT).show();
                 Log.i("aaaaaaaaaa", "onTabSelected: " + tab.getText());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /**
                 * 当tab从 选择 ->未选择
                 * @param tab
                 */
//                Toast.makeText(MainActivity.this, "当tab从 选择 ->未选择", Toast.LENGTH_SHORT).show();
//                Log.i("aaaaaaaaaa", "onTabUnselected: " + tab.getText());
                getContent(tab.getText().toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /**
                 * 当tab已是选择状态时，继续点击调用此方法
                 * @param tab
                 */
//                Toast.makeText(MainActivity.this, "当tab已是选择状态时，继续点击调用此方法", Toast.LENGTH_SHORT).show();
//                Log.i("aaaaaaaaaa", "onTabReselected: " + tab.getText());

            }
        });
    }

    private void getContent(String text) {
        if (text.equals("国内")) {
            mProgressDialog.show();
            getNews("guonei", 1);
        } else if (text.equals("头条")) {
            mProgressDialog.show();
            getNews("toutiao", 0);
        } else if (text.equals("国际")) {
            mProgressDialog.show();
            getNews("guoji", 2);
        } else if (text.equals("体育")) {
            mProgressDialog.show();
            getNews("tiyu", 3);
        } else {
            mProgressDialog.show();
            getNews("yule", 4);
        }
    }

    private void getNews(String type, final int index) {
        OkHttpUtils.post().url("http://v.juhe.cn/toutiao/index?type=" + type + "&key=94a708ce50a14d68605a70670f8cd831")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("aaaaaa", "onResponse: " + response.toString());
                list = mGson.fromJson(response.toString(), GuoNeiBeanList.class);
                mMyFragmentArrayList.get(index).setContent(list);
                adapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            }
        });
    }

    @Override
    public void refreshData() {
        getContent(newsType);
    }
}
