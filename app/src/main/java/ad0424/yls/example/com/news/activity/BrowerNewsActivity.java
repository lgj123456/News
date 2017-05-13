package ad0424.yls.example.com.news.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.utils.BmobUtil;
import ad0424.yls.example.com.news.utils.SPUtil;

public class BrowerNewsActivity extends AppCompatActivity {
    private WebView mWebView;
    private String url;
    private Toolbar mToolbar;
    private String title;
    private EditText mEdtComment;
    private Button mBtnComments;
    private Button mBtnWriteComment;
    private String mComment;
    private String mProvince;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器

    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;
    private String mImgUrl = "http://p4.so.qhmsg.com/bdr/_240_/t01b4edc8c60e7ded99.jpg";
    private boolean isNight;
    private String imgUrl;
    private WebSettings settings;
    private int textSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brower_news);


        initUrl();
        applyPermissions();
        initLocation();
        isNight = SPUtil.getIsNight(BrowerNewsActivity.this);
        initViews();

    }

    private void initUrl() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        imgUrl = intent.getStringExtra("imgUrl");
    }

    private void initViews() {
        mWebView = (WebView) findViewById(R.id.forum_context);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient());
        Toast.makeText(this, "" + isNight, Toast.LENGTH_SHORT).show();
        mWebView.setDayOrNight(isNight);
        mWebView.loadUrl(url);
         settings = mWebView.getSettings();
        mToolbar = (Toolbar) findViewById(R.id.news_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_back);
        }

        mEdtComment = (EditText) findViewById(R.id.edt_commnet);
        mBtnComments = (Button) findViewById(R.id.btn_commnet);
        mBtnWriteComment = (Button) findViewById(R.id.btn_send_comment);
        mBtnWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdtComment.getText().toString() != null) {
                    mComment = mEdtComment.getText().toString().trim();
                }
                sendComment();
            }
        });

        mBtnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrowerNewsActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });

        setTextSize();
    }

    private void setTextSize() {
        textSize = SPUtil.getTextSize(BrowerNewsActivity.this);
        switch (textSize){
            case 0:
                settings.setTextSize(WebSettings.TextSize.LARGEST);
            break;
            case 1:
                settings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 2:
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                settings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 4:
                settings.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
        }
    }

    private void sendComment() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = simpleDateFormat.format(date);
        boolean isInsert = BmobUtil.insert(mImgUrl, "张三", mProvince, time, mComment);
        Toast.makeText(this, "发表评论成功！！！", Toast.LENGTH_SHORT).show();
        mEdtComment.setText("");
    }

    private void applyPermissions() {
        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                } else {
                    applyPermissions();
                }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16)

                    return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.brower_toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mWebView != null && mWebView.canGoBack()) {
                    mWebView.goBack();
                }else{
                    finish();
                }
                break;
            case R.id.collect:
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String collectionTime = simpleDateFormat.format(date);
                BmobUtil.insertCollection(imgUrl, title, collectionTime, url);
                Toast.makeText(this, "收藏成功！！！", Toast.LENGTH_SHORT).show();


                break;
            //分享
            case R.id.share:
                shareNews();
                break;
        }
        return true;
    }

    private void shareNews() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_TEXT, url + "\n" + title);
        startActivity(Intent.createChooser(intent, "分享"));
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);


//        mLocationListener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//
//            }
//        };
//        //启动定位
        mLocationClient.startLocation();
        //   mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        //  mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    Log.i("kkkkkkkkkkk", "aMapLocation.getAdCode() : =  " + aMapLocation.getAdCode() + "\n"
                            + " aMapLocation.getAddress() = " + aMapLocation.getAddress() + "\n"
                            + "aMapLocation.getAoiName()  = " + aMapLocation.getAoiName() + "\n"
                            + "aMapLocation.getBuildingId() = " + aMapLocation.getBuildingId() + "\n"
                            + "aMapLocation.getCity() = " + aMapLocation.getCity() + "\n"
                            + "aMapLocation.getProvince() = " + aMapLocation.getProvince() + "\n"
                            + "aMapLocation.getGpsAccuracyStatus() = " + aMapLocation.getGpsAccuracyStatus() + "\n"
                            + "aMapLocation.getLocationDetail() = " + aMapLocation.getLocationDetail() + "\n"
                            + "aMapLocation.getLatitude() = " + aMapLocation.getLatitude() + "\n"
                            + "aMapLocation.getLongitude() = " + aMapLocation.getLongitude() + "\n"
                    );
                    mProvince = aMapLocation.getProvince() + aMapLocation.getCity();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }


        }
    };
}
