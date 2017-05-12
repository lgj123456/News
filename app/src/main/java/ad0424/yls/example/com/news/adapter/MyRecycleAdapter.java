package ad0424.yls.example.com.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.application.MyApplication;
import ad0424.yls.example.com.news.model.GuoNeiBeanList;

/**
 * Created by yhdj on 2017/5/12.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> {
    private GuoNeiBeanList mGuoNeiBeanList;
    public MyRecycleAdapter(GuoNeiBeanList mGuoNeiBeanLists){
        this.mGuoNeiBeanList = mGuoNeiBeanLists;
    }
    @Override
    public MyRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecycleAdapter.ViewHolder holder, int position) {

        GuoNeiBeanList.ResultBean.DataBean dataBean = mGuoNeiBeanList.getResult().getData().get(position);
        holder.mTvContent.setText(dataBean.getTitle());
        Glide.with(MyApplication.getContext()).load(dataBean.getThumbnail_pic_s()).into(holder.mTitleImg);
    }

    @Override
    public int getItemCount() {
        if(mGuoNeiBeanList != null && mGuoNeiBeanList.getResult().getData() != null){
            return mGuoNeiBeanList.getResult().getData().size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mTitleImg;
        private TextView mTvContent;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitleImg = (ImageView) itemView.findViewById(R.id.iv_title);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
