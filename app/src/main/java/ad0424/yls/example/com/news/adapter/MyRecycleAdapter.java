package ad0424.yls.example.com.news.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.activity.BrowerNewsActivity;
import ad0424.yls.example.com.news.application.MyApplication;
import ad0424.yls.example.com.news.model.NewsBeanList;

/**
 * Created by yhdj on 2017/5/12.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> {
    private NewsBeanList mNewsBeanList;
    public MyRecycleAdapter(NewsBeanList mNewsBeanLists){
        this.mNewsBeanList = mNewsBeanLists;
    }
    @Override
    public MyRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyRecycleAdapter.ViewHolder holder, int position) {

        final NewsBeanList.ResultBean.DataBean dataBean = mNewsBeanList.getResult().getData().get(position);
        holder.mTvContent.setText(dataBean.getTitle());
        holder.mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = dataBean.getUrl();
                Intent intent = new Intent(MyApplication.getContext(), BrowerNewsActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",dataBean.getTitle());
                intent.putExtra("imgUrl",dataBean.getThumbnail_pic_s());
                holder.mTitleImg.getContext().startActivity(intent);
            }
        });
        Glide.with(MyApplication.getContext()).load(dataBean.getThumbnail_pic_s()).into(holder.mTitleImg);
    }

    @Override
    public int getItemCount() {
        if(mNewsBeanList != null && mNewsBeanList.getResult().getData() != null){
            return mNewsBeanList.getResult().getData().size();
        }
        return 0;
    }

    public void changeData(NewsBeanList newsBeanLists) {
        mNewsBeanList = newsBeanLists;
        notifyDataSetChanged();
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
