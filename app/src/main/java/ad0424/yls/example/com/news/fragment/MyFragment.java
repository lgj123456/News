package ad0424.yls.example.com.news.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yalantis.phoenix.PullToRefreshView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyRecycleAdapter;
import ad0424.yls.example.com.news.model.NewsBeanList;
import okhttp3.Call;

/**
 * Created by yls on 2017/5/16.
 */

public class MyFragment extends Fragment {
    private static final int NEWSDATA = 1001;
    private RecyclerView mRecyclerView;
    private NewsBeanList mNewsBeanLists;
    private MyRecycleAdapter mMyRecycleAdapter;
    private PullToRefreshView mPullToRefreshView;
    private Gson mGson = new Gson();
    private String newsType;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsType = getArguments().getString("NEWS_TYPE");
        initHandler();
        getContent(newsType);
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == NEWSDATA){
                    mMyRecycleAdapter.changeData(mNewsBeanLists);
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMyRecycleAdapter = new MyRecycleAdapter(mNewsBeanLists);
        mRecyclerView.setAdapter(mMyRecycleAdapter);
        return view;
    }


    private void getContent(String text) {
        if (text.equals("国内")) {
            getNews("guonei");
        } else if (text.equals("头条")) {

            getNews("toutiao");
        } else if (text.equals("国际")) {

            getNews("guoji");
        } else if (text.equals("体育")) {

            getNews("tiyu");
        } else {

            getNews("yule");
        }
    }


    private void getNews(String type) {
        OkHttpUtils.post().url("http://v.juhe.cn/toutiao/index?type=" + type + "&key=94a708ce50a14d68605a70670f8cd831")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("aaaaaa", "onResponse: " + response.toString());
                mNewsBeanLists = mGson.fromJson(response.toString(), NewsBeanList.class);
                mHandler.sendEmptyMessage(NEWSDATA);
            }
        });

    }
}

