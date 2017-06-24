package ad0424.yls.example.com.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyFragmentAdapter;

/**
 * Created by yhdj on 2017/5/25.
 */

public class Main2Fragment extends Fragment {
    private ViewPager mViewPager;
    private ArrayList<MyFragment> mMyFragmentArrayList = new ArrayList<MyFragment>();
    private ArrayList<String> mTitleList = new ArrayList<String>();
    private MyFragmentAdapter adapter;
    private final String NEWS_TYPE = "NEWS_TYPE";
    private final String NEWS_GUONEI = "国内";
    private final String NEWS_GUOJI = "国际";
    private final String NEWS_YULE = "娱乐";
    private final String NEWS_TIYU = "体育";
    private final String NEWS_TOP = "头条";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
        initFragment();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        if(adapter == null){
            adapter = new MyFragmentAdapter(getFragmentManager(), mMyFragmentArrayList, mTitleList);
        }

        mViewPager.setAdapter(adapter);

        //TabLayout

        mViewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }

    private void initFragment() {
        Bundle bundleTop = new Bundle();
        bundleTop.putString(NEWS_TYPE, NEWS_TOP);
        MyFragment f1 = new MyFragment();
        f1.setArguments(bundleTop);

        Bundle bundleGuonei = new Bundle();
        bundleGuonei.putString(NEWS_TYPE, NEWS_GUONEI);
        MyFragment f2 = new MyFragment();
        f2.setArguments(bundleGuonei);

        Bundle bundleGuoji = new Bundle();
        bundleGuoji.putString(NEWS_TYPE, NEWS_GUOJI);
        MyFragment f3 = new MyFragment();
        f3.setArguments(bundleGuoji);

        Bundle bundleTiyu = new Bundle();
        bundleTiyu.putString(NEWS_TYPE, NEWS_TIYU);
        MyFragment f4 = new MyFragment();
        f4.setArguments(bundleTiyu);

        Bundle bundleYule = new Bundle();
        bundleYule.putString(NEWS_TYPE, NEWS_YULE);
        MyFragment f5 = new MyFragment();
        f5.setArguments(bundleYule);

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

    }



}
