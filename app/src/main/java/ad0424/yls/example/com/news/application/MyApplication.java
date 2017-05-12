package ad0424.yls.example.com.news.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by yhdj on 2017/5/12.
 */

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
