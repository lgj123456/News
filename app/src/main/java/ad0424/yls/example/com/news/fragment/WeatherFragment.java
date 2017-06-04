package ad0424.yls.example.com.news.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.adapter.MyWeatherAdapter;
import ad0424.yls.example.com.news.application.MyApplication;
import ad0424.yls.example.com.news.model.TemperatureBean;
import ad0424.yls.example.com.news.model.WeatherBean;
import ad0424.yls.example.com.news.model.WeatherForcast;
import ad0424.yls.example.com.news.utils.SPUtil;
import ad0424.yls.example.com.news.utils.WeatherSortUtil;
import ad0424.yls.example.com.news.view.TemperatureView;
import okhttp3.Call;

/**
 * Created by yhdj on 2017/5/25.
 */

public class WeatherFragment extends Fragment {
    //7f9ce4e1a10df8fce6be087ddbc636b6
    //http://v.juhe.cn/weather/uni?key=

    //http://v.juhe.cn/weather/geo?format=2&key=您申请的KEY&lon=116.39277&lat=39.933748
    //http://v.juhe.cn/weather/forecast3h.php?cityname=%E4%B8%8A%E6%B5%B7&key=申请的KEY
    private Gson mGson = new Gson();
    private WeatherBean mWeatherBean = new WeatherBean();
    private float longitude = SPUtil.getLongitude(MyApplication.getContext());
    private float latitude = SPUtil.getLatitude(MyApplication.getContext());
    //    private LineChart mChart;
//    private int[] mColors = new int[]{
//            Color.parseColor("#5abdfc"),    //蓝色
//            Color.parseColor("#eb73f6")    //紫色
//    };
//
//    protected String[] mMonths = new String[]{
//            "05:00-08:00", "08:00-11:00", "11:00-14:00", "14:00-17:00", "17:00-20:00", "20:00-23:00"
//    };
    private WeatherForcast mWeatherForcast = new WeatherForcast();
    private List<WeatherForcast.ResultBean> mResultBeen = new ArrayList<>();
    private Handler handler = new Handler();
    private ImageView ivWeatherIconToday;
    private TextView tvWeatherToday;
    private TextView tvTemperatureToday;
    private TextView tvWeekToday;
    private RecyclerView mRecyclerView;
    private MyWeatherAdapter mMyWeatherAdapter;
    private List<WeatherBean.ResultBean.FutureBean> mFutureBeen = new ArrayList<>();
    private TextView tvCityToday;
    private TextView tvWindToday;
    private TextView tvDressToday;
    private TextView tvDateToday;
    private ArrayList<String> temperatureList = new ArrayList<>();
    private String temperature = new String();
    private ArrayList<Integer> lowTemperatureList = new ArrayList<>();
    private ArrayList<Integer> highTemperatureList = new ArrayList<>();
    private TemperatureView mTemperatureView;
    private ArrayList<String> dateList = new ArrayList<>();
    private ArrayList<TemperatureBean> mTemperatureBeanArrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpUtils.get()
                .url("http://v.juhe.cn/weather/geo")
                .addParams("format", "2")
                .addParams("key", "7f9ce4e1a10df8fce6be087ddbc636b6")
                .addParams("lon", "" + longitude)
                .addParams("lat", "" + latitude)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaaaaaaaaaa", "onResponse: " + response.toString());
                        mFutureBeen = mGson.fromJson(response.toString(), WeatherBean.class).getResult().getFuture();
                        mMyWeatherAdapter.changeData(mFutureBeen);
                        mWeatherBean = mGson.fromJson(response.toString(), WeatherBean.class);
                        initTemperatureData(initTemperatureList(mFutureBeen));
                        initTemperatureBeanArrayList();
                    }
                });


        OkHttpUtils.get()
                .url("http://v.juhe.cn/weather/forecast3h.php")
                .addParams("cityname", "广州")
                .addParams("key", "7f9ce4e1a10df8fce6be087ddbc636b6")

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaaaaaaaaaa", "onResponse: bbbbbbbbbbb" + response.toString());
                        mResultBeen = mGson.fromJson(response.toString(), WeatherForcast.class).getResult();
                        Toast.makeText(MyApplication.getContext(), "size" + mResultBeen.size(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initTemperatureBeanArrayList() {
        for (int i = 0; i < highTemperatureList.size(); i++) {
            TemperatureBean temperatureBean = new TemperatureBean(lowTemperatureList.get(i), highTemperatureList.get(i));
            mTemperatureBeanArrayList.add(temperatureBean);
        }
    }

    private void initTemperatureData(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0 && i % 2 == 0) {
                lowTemperatureList.add(ints[i]);
            } else if (ints[i] != 0 && i % 2 != 0) {
                highTemperatureList.add(ints[i]);
            }
        }
    }

    public ArrayList<TemperatureBean> getTemp() {

        return mTemperatureBeanArrayList;

    }

    private int[] initTemperatureList(List<WeatherBean.ResultBean.FutureBean> futureBeen) {
        for (int i = 0; i < futureBeen.size(); i++) {
            temperature += futureBeen.get(i).getTemperature();
            dateList.add(futureBeen.get(i).getDate());
        }
        //  Log.e("aaaaaaaaaaa", "initTemperatureList: " + temperature);
        //26℃~33℃    26℃~34℃    27℃~34℃     27℃~35℃    27℃~35℃     27℃~35℃    27℃~34℃
        String temp = temperature.replaceAll("~", "");
        //   Toast.makeText(getContext(), temp, Toast.LENGTH_SHORT).show();
        // String[] a =  temperature.split("~");

        int str[] = new int[50];
        String str2 = new String();
        int index = 0;
        for (int i = 0; i < temp.length(); i++) {

            if (String.valueOf(temp.charAt(i)).equals("℃")) {
                str[index] = Integer.valueOf(str2);
                str2 = new String();
                index++;
                continue;
            }
            str2 += String.valueOf(temp.charAt(i));
        }

        return str;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);


        // mChart = (LineChart) view.findViewById(chart);
        ivWeatherIconToday = (ImageView) view.findViewById(R.id.iv_weather_icon_today);
        tvTemperatureToday = (TextView) view.findViewById(R.id.tv_temperature_today);
        tvWeekToday = (TextView) view.findViewById(R.id.tv_week_today);
        tvWeatherToday = (TextView) view.findViewById(R.id.tv_weather_today);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.weatherView);
        tvCityToday = (TextView) view.findViewById(R.id.tv_city_today);
        tvWindToday = (TextView) view.findViewById(R.id.tv_wind_today);
        tvDressToday = (TextView) view.findViewById(R.id.tv_dress_today);
        tvDateToday = (TextView) view.findViewById(R.id.tv_date_today);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mMyWeatherAdapter = new MyWeatherAdapter(mFutureBeen);
        mRecyclerView.setAdapter(mMyWeatherAdapter);
        mTemperatureView = (TemperatureView) view.findViewById(R.id.temperatureView);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                initChartView();
//
//                ArrayList<Entry> yVals = new ArrayList<Entry>();
//                for (int i = 0; i < 6; i++) {
//                    yVals.add(new Entry(i, Integer.valueOf(mResultBeen.get(i).getTemp1())));
//                }
//
//                ArrayList<Entry> yVals2 = new ArrayList<Entry>();
//                for (int i = 0; i < 6; i++) {
//                    yVals2.add(new Entry(i, Integer.valueOf(mResultBeen.get(i).getTemp2())));
//                }
//
//                addDataSet(yVals, "低温");
//                addDataSet(yVals2, "高温");


//图标的下边的指示块  图例
//                Legend l = mChart.getLegend();
//                l.setForm(Legend.LegendForm.LINE);
//                l.setXEntrySpace(40);
                mTemperatureView.drawWeather(mTemperatureBeanArrayList);
                mTemperatureView.drawDateData(dateList);
                tvWeatherToday.setText(mWeatherBean.getResult().getToday().getWeather());
                tvTemperatureToday.setText(mWeatherBean.getResult().getToday().getTemperature());
                tvWeekToday.setText(mWeatherBean.getResult().getToday().getWeek());
                ivWeatherIconToday.setImageResource(WeatherSortUtil.getWeatherIcon(Integer.valueOf(mWeatherBean.getResult().getToday().getWeather_id().getFa())));
                tvCityToday.setText(mWeatherBean.getResult().getToday().getCity());
                tvWindToday.setText("风向: " + mWeatherBean.getResult().getToday().getWind());
                tvDressToday.setText("穿衣建议: " + mWeatherBean.getResult().getToday().getDressing_advice());
                tvDateToday.setText(mWeatherBean.getResult().getToday().getDate_y());
            }
        }, 4000);


        return view;
    }

//    private void initChartView() {
//        mChart.setDrawGridBackground(false);
//        mChart.setDescription(null);    //右下角说明文字
//        mChart.setDrawBorders(true);    //四周是不是有边框
//        mChart.setBorderWidth(0.5f);
//        mChart.setBorderColor(Color.parseColor("#b3b3b3"));    //边框颜色，默认黑色？
////        mChart.setVisibleXRangeMaximum(4);
//
//        // enable touch gestures
//        mChart.setTouchEnabled(true);
//        // if disabled, scaling can be done on x- and y-axis separately
//        //禁止x轴y轴同时进行缩放
//        mChart.setPinchZoom(false);
//        // enable scaling and dragging
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);
//
//        //控制轴上的坐标绘制在什么地方 上边下边左边右边
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);    //x轴是在上边显示还是显示在下边
//        xAxis.enableGridDashedLine(10f, 10f, 0f);    //背景用虚线表格来绘制  给整成虚线
//        xAxis.setAxisMinimum(0f);//设置轴的最小值。这样设置将不会根据提供的数据自动计算。
//        xAxis.setGranularityEnabled(true);    //粒度
//        xAxis.setGranularity(1f);    //缩放的时候有用，比如放大的时候，我不想把横轴的月份再细分
//
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return mMonths[(int) value % mMonths.length];
//            }
//
//
//        });
////        xAxis.setAxisLineWidth(0f);    //设置坐标轴那条线的宽度
//        xAxis.setDrawAxisLine(false);    //是否显示坐标轴那条轴
//        xAxis.setDrawLabels(true);    //是不是显示轴上的刻度
//        xAxis.setLabelCount(mMonths.length);    //强制有多少个刻度
//        xAxis.setTextColor(Color.parseColor("#b3b3b3"));
//
//
//        //隐藏左侧坐标轴显示右侧坐标轴，并对右侧的轴进行配置
//        mChart.getAxisLeft().setEnabled(false);
//        YAxis leftAxis = mChart.getAxisRight();
//        leftAxis.setEnabled(true);
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
//        leftAxis.setAxisMinimum(0);
//        leftAxis.setDrawAxisLine(false);
//        //坐标轴绘制在图表的内侧
//        leftAxis.setPosition(INSIDE_CHART);
//        leftAxis.setTextColor(Color.parseColor("#b3b3b3"));
//        //确实没看懂这个是干嘛用的，默认是10f
//        //这个玩意好像有坐标轴enable的时候是不可用的
//        leftAxis.setSpaceBottom(10f);

    //一个chart中包含一个Data对象，一个Data对象包含多个DataSet对象，
    // 每个DataSet是对应一条线上的所有点(相对于折线图来说)
    //  mChart.setData(new LineData());

    // }


    /**
     * Author: liuqiang
     * Time: 2016-12-26 15:58
     * Description: 根据数据集合，动态构建DataSet，来绘制界面
     */
//    private void addDataSet(ArrayList<Entry> entryList, String dataSetName) {
//
//        LineData data = mChart.getData();
//
//        if (data != null) {
//            int count = data.getDataSetCount();
//
//            LineDataSet set = new LineDataSet(entryList, dataSetName);
//            set.setLineWidth(1.5f);
//            set.setCircleRadius(3.5f);
//
//            int color = mColors[count % mColors.length];
//
//            set.setColor(color);
//            set.setCircleColor(color);
//            set.setHighLightColor(color);
//            set.setValueTextSize(10f);
//            set.setDrawValues(false);    //节点不显示具体数值
//            set.setValueTextColor(color);
//            set.enableDashedHighlightLine(10f, 5f, 0f);    //选中某个点的时候高亮显示只是线
//            set.setDrawFilled(true);     //填充折线图折线和坐标轴之间
//            set.setFillColor(color);
//
////            set.setDrawVerticalHighlightIndicator(false);//取消纵向辅助线
//            set.setDrawHorizontalHighlightIndicator(false);//取消横向辅助线
//
//            data.addDataSet(set);
//            data.notifyDataChanged();
//            mChart.notifyDataSetChanged();
//            //这行代码必须放到这里，这里设置的是图表这个视窗能显示，x坐标轴，从最大值到最小值之间
//            //多少段，好像这个库没有办法设置x轴中的间隔的大小
//            mChart.setVisibleXRangeMaximum(6);
//            mChart.invalidate();
//        }
//  }


}
