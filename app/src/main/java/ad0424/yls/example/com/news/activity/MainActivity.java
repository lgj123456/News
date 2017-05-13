package ad0424.yls.example.com.news.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyFragmentAdapter;
import ad0424.yls.example.com.news.fragment.MyFragment;
import ad0424.yls.example.com.news.utils.SPUtil;
import cn.bmob.v3.BmobUser;


public class MainActivity extends AppCompatActivity {
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
    private ResideMenu resideMenu;
    private Toolbar mToolbar;
    private boolean isNight;
    private String[] items = {"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
    private int textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        initTitle();
        initFragment();
        initViews();


    }

    private void initChooseTextSize() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("设置字体");

        builder.setSingleChoiceItems(items, SPUtil.getTextSize(MainActivity.this), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textSize = which;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtil.setTestSize(MainActivity.this, textSize);
            }
        });

        builder.setNegativeButton("取消", null);
        builder.show();
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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), mMyFragmentArrayList, mTitleList);
        mViewPager.setAdapter(adapter);

        //TabLayout

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        initResideMenu();
        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.title_bar_menu_on);
        }


    }

    private void initResideMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.mipmap.bg_love);
        resideMenu.attachToActivity(this);
        final String titles[] = {"个人中心", "收藏", "夜间模式", "设置"};
        int icon[] = {R.mipmap.persion, R.drawable.collect, R.mipmap.night, R.mipmap.setting};

        ResideMenuItem personItem = new ResideMenuItem(this, icon[0], titles[0]);
        personItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.getCurrentUser() == null) {
                    Toast.makeText(MainActivity.this, "亲，您还没登录，请先登录！！！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {

                }
            }
        });
        resideMenu.addMenuItem(personItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem collectItem = new ResideMenuItem(this, icon[1], titles[1]);
        collectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        resideMenu.addMenuItem(collectItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem nightItem = new ResideMenuItem(this, icon[2], titles[2]);
        nightItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNight = SPUtil.getIsNight(MainActivity.this);
                Toast.makeText(MainActivity.this, isNight + "", Toast.LENGTH_SHORT).show();
                if (isNight) {
                    SPUtil.updateIsNight(MainActivity.this, false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //  recreate();
                } else {
                    SPUtil.updateIsNight(MainActivity.this, true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //recreate();
                }


            }
        });
        resideMenu.addMenuItem(nightItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem settingItem = new ResideMenuItem(this, icon[3], titles[3]);
        settingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChooseTextSize();
            }
        });

        resideMenu.addMenuItem(settingItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addIgnoredView(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                break;
            case R.id.search:

                break;

        }
        return true;
    }


}



