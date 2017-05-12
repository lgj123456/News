package ad0424.yls.example.com.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyRecycleAdapter;
import ad0424.yls.example.com.news.listener.RefreshDataListener;
import ad0424.yls.example.com.news.model.GuoNeiBeanList;

/**
 * Created by yls on 2017/5/16.
 */

public class MyFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private GuoNeiBeanList mGuoNeiBeanLists ;
    private MyRecycleAdapter mMyRecycleAdapter;
    private PullToRefreshView mPullToRefreshView;
    private RefreshDataListener mRefreshDataListener;

    public MyFragment(RefreshDataListener mRefreshDataListener) {
        this.mRefreshDataListener = mRefreshDataListener;
    }

    public MyFragment(){

    }
//    public static final String ARGS_PAGE = "args_page";
//    private int mPage;
//
//    public static MyFragment newInstance(int page) {
//        Bundle args = new Bundle();
//
//        args.putInt(ARGS_PAGE, page);
//        MyFragment fragment = new MyFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARGS_PAGE);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMyRecycleAdapter = new MyRecycleAdapter(mGuoNeiBeanLists);
        mRecyclerView.setAdapter(mMyRecycleAdapter);
        mMyRecycleAdapter.notifyDataSetChanged();
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshDataListener.refreshData();

                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);

            }
        });
        return view;
    }

    public void setContent(GuoNeiBeanList guoNeiBeanLists) {
        this.mGuoNeiBeanLists = guoNeiBeanLists;
        mMyRecycleAdapter.notifyDataSetChanged();
    }

}
