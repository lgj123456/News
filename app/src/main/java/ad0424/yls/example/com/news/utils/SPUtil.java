package ad0424.yls.example.com.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yhdj on 2017/5/12.
 */

public class SPUtil {
    private static final String FIRST_SP = "FIRST_SP";
    private static final String FIRST_RUN = "FIRST_RUN";

    public static boolean getIsFirstRun(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FIRST_RUN,true);
    }

    public static void setIsFirstRun(Context context, boolean isRuned){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN,isRuned);
        editor.commit();
    }
}
