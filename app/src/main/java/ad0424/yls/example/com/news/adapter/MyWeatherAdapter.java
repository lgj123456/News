package ad0424.yls.example.com.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.model.WeatherBean;
import ad0424.yls.example.com.news.utils.WeatherSortUtil;

/**
 * Created by yhdj on 2017/5/26.
 */

public class MyWeatherAdapter extends RecyclerView.Adapter<MyWeatherAdapter.ViewHolder> {
    private List<WeatherBean.ResultBean.FutureBean> mFutureBeen = new ArrayList<>();

    public MyWeatherAdapter(List<WeatherBean.ResultBean.FutureBean> mFutureBeen) {
        this.mFutureBeen = mFutureBeen;
    }

    @Override
    public MyWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyWeatherAdapter.ViewHolder holder, int position) {
        WeatherBean.ResultBean.FutureBean futureBean = mFutureBeen.get(position);
        holder.ivWeatherIcon.setImageResource(WeatherSortUtil.getWeatherIcon(Integer.valueOf(futureBean.getWeather_id().getFa())));
        holder.tvTemperature.setText(futureBean.getTemperature());
        holder.tvWeather.setText(futureBean.getWeather());
        holder.tvWeek.setText(futureBean.getWeek());
    }

    @Override
    public int getItemCount() {
        return mFutureBeen.size();
    }

    public void changeData(List<WeatherBean.ResultBean.FutureBean> futureBeen) {
        this.mFutureBeen = futureBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivWeatherIcon;
        private TextView tvWeather;
        private TextView tvTemperature;
        private TextView tvWeek;

        public ViewHolder(View itemView) {
            super(itemView);
            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
            tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            tvWeek = (TextView) itemView.findViewById(R.id.tv_week);
            tvWeather = (TextView) itemView.findViewById(R.id.tv_weather);
        }
    }
}
