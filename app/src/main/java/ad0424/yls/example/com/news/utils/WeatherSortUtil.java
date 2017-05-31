package ad0424.yls.example.com.news.utils;

import ad0424.yls.example.com.news.R;

/**
 * Created by yhdj on 2017/5/25.
 */

public class WeatherSortUtil {
    /*
    wid":"00","weather":"晴"}," +
            "{"wid":"01","weather":"多云"}" +
            ",{"wid":"02","weather":"阴"}," +
            "{"wid":"03","weather":"阵雨"}," +
            "{"wid":"04","weather":"雷阵雨"}," +
            "{"wid":"05","weather":"雷阵雨伴有冰雹"}," +
            "{"wid":"06","weather":"雨夹雪"}," +
            "{"wid":"07","weather":"小雨"}," +
            "{"wid":"08","weather":"中雨"}" +
            ",{"wid":"09","weather":"大雨"}," +
            "{"wid":"10","weather":"暴雨"}," +
            "{"wid":"11","weather":"大暴雨"}" +
            ",{"wid":"12","weather":"特大暴雨"}" +
            ",{"wid":"13","weather":"阵雪"}," +
            "{"wid":"14","weather":"小雪"}," +
            "{"wid":"15","weather":"中雪"}," +
            "{"wid":"16","weather":"大雪"}" +
            ",{"wid":"17","weather":"暴雪"}," +
            "{"wid":"18","weather":"雾"}" +
            ",{"wid":"19","weather":"冻雨"}," +
            "{"wid":"20","weather":"沙尘暴"}," +
            "{"wid":"21","weather":"小雨-中雨"}," +
            "{"wid":"22","weather":"中雨-大雨"}," +
            "{"wid":"23","weather":"大雨-暴雨"}," +
            "{"wid":"24","weather":"暴雨-大暴雨"}," +
            "{"wid":"25","weather":"大暴雨-特大暴雨"}" +
            ",{"wid":"26","weather":"小雪-中雪"}" +
            ",{"wid":"27","weather":"中雪-大雪"}," +
            "{"wid":"28","weather":"大雪-暴雪"}" +
            "{"wid":"29","weather":"浮尘"}" +
            ",{"wid":"30","weather":"扬沙"}," +
            "{"wid":"31","weather":"强沙尘暴"}," +
            "{"wid":"53","weather":"霾"}]
     */
    public static String getWeather(int num) {

        if (num == 00) {
            return "晴";
        } else if (num == 1) {
            return "多云";
        } else if (num == 2) {
            return "阴";
        } else if (num == 3) {
            return "阵雨";
        } else if (num == 4) {
            return "雷阵雨";
        } else if (num == 5) {
            return "雷阵雨伴有冰雹";
        } else if (num == 6) {
            return "雨夹雪";
        } else if (num == 7) {
            return "小雨";
        } else if (num == 8) {
            return "中雨";
        } else if (num == 9) {
            return "大雨";
        } else if (num == 10) {
            return "暴雨";
        } else if (num == 11) {
            return "大暴雨";
        } else if (num == 12) {
            return "特大暴雨";
        } else if (num == 13) {
            return "阵雪";
        } else if (num == 14) {
            return "小雪";
        } else if (num == 15) {
            return "中雪";
        } else if (num == 16) {
            return "大雪";
        } else if (num == 17) {
            return "暴雪";
        } else if (num == 18) {
            return "雾";
        } else if (num == 19) {
            return "冻雨";
        } else if (num == 20) {
            return "沙尘暴";
        } else if (num == 21) {
            return "小雨-中雨";
        } else if (num == 22) {
            return "中雨-大雨";
        } else if (num == 23) {
            return "大雨-暴雨";
        } else if (num == 24) {
            return "暴雨-大暴雨";
        } else if (num == 25) {
            return "大暴雨-特大暴雨";
        } else if (num == 26) {
            return "小雪-中雪";
        } else if (num == 27) {
            return "中雪-大雪";
        } else if (num == 28) {
            return "大雪-暴雪";
        } else if (num == 29) {
            return "浮尘";
        } else if (num == 30) {
            return "扬沙";
        } else if (num == 31) {
            return "强沙尘暴";
        } else if (num == 53) {
            return "霾";
        }
        return "";
    }

    public static int getWeatherIcon(int num) {
        if (num == 00) {
            return R.drawable.d00;
        } else if (num == 1) {
            return R.drawable.d01;
        } else if (num == 2) {
            return R.drawable.d02;
        } else if (num == 3) {
            return R.drawable.d03;
        } else if (num == 4) {
            return R.drawable.d04;
        } else if (num == 5) {
            return R.drawable.d05;
        } else if (num == 6) {
            return R.drawable.d06;
        } else if (num == 7) {
            return R.drawable.d07;
        } else if (num == 8) {
            return R.drawable.d08;
        } else if (num == 9) {
            return R.drawable.d09;
        } else if (num == 10) {
            return R.drawable.d10;
        } else if (num == 11) {
            return R.drawable.d11;
        } else if (num == 12) {
            return R.drawable.d12;
        } else if (num == 13) {
            return R.drawable.d13;
        } else if (num == 14) {
            return R.drawable.d14;
        } else if (num == 15) {
            return R.drawable.d15;
        } else if (num == 16) {
            return R.drawable.d16;
        } else if (num == 17) {
            return R.drawable.d17;
        } else if (num == 18) {
            return R.drawable.d18;
        } else if (num == 19) {
            return R.drawable.d19;
        } else if (num == 20) {
            return R.drawable.d20;
        } else if (num == 21) {
            return R.drawable.d21;
        } else if (num == 22) {
            return R.drawable.d22;
        } else if (num == 23) {
            return R.drawable.d23;
        } else if (num == 24) {
            return R.drawable.d24;
        } else if (num == 25) {
            return R.drawable.d25;
        } else if (num == 26) {
            return R.drawable.d26;
        } else if (num == 27) {
            return R.drawable.d27;
        } else if (num == 28) {
            return R.drawable.d28;
        } else if (num == 29) {
            return R.drawable.d29;
        } else if (num == 30) {
            return R.drawable.d30;
        } else if (num == 31) {
            return R.drawable.d31;
        } else if (num == 53) {
            return R.drawable.d53;
        }
        return R.drawable.d00;
    }

}
