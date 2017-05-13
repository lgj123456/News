package ad0424.yls.example.com.news.adapter;


import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.R;
import ad0424.yls.example.com.news.application.MyApplication;
import ad0424.yls.example.com.news.listener.UpdateCommentListener;
import ad0424.yls.example.com.news.model.UserBean;
import ad0424.yls.example.com.news.utils.BmobUtil;

/**
 * Created by yhdj on 2017/5/13.
 */

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHoder> {
    private List<UserBean> mUserBeen = new ArrayList<>();
    private final int UPDATE_PRAISE = 1001;
    private Handler handler = new Handler();
    UserBean userBean1;
    private UpdateCommentListener mUpdateCommentListener;
private Animation mAnimation = AnimationUtils.loadAnimation(MyApplication.getContext(),R.anim.add_score_anim);
    public MyCommentAdapter(List<UserBean> mUserBeen, UpdateCommentListener updateCommentListener) {
        this.mUserBeen = mUserBeen;
        this.mUpdateCommentListener = updateCommentListener;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, parent, false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(final ViewHoder holder, final int position) {
        final UserBean userBean = mUserBeen.get(position);
        Glide.with(MyApplication.getContext()).load(userBean.getImgUrl()).into(holder.mIvComment);
        holder.mTvComment.setText(userBean.getComment());
        holder.mTvProvince.setText(userBean.getProvince());
        holder.mTvCreateTime.setText(userBean.getCreateTime());
        holder.mTvUserName.setText(userBean.getUserName());
        holder.mTvPraiseNum.setText(String.valueOf(userBean.getPraiseNum()));
        holder.mIvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobUtil.update(userBean);
                Log.e("bbbbbbbbbb", "onClick: " + userBean.getObjectId());

//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                         userBean1 = BmobUtil.queryById(userBean.getObjectId());
//                    }
//                }, 1000);

                holder.mAddOne.setAlpha(1);
                holder.mAddOne.startAnimation(mAnimation);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUpdateCommentListener.updatePraiseNum();
                        holder.mAddOne.setAlpha(0);
                    }
                }, 1000);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserBeen.size();
    }

    public void changeData(List<UserBean> userBeen) {
        this.mUserBeen = userBeen;
        notifyDataSetChanged();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView mIvComment;
        private TextView mTvUserName;
        private TextView mTvCreateTime;
        private TextView mTvProvince;
        private TextView mTvComment;
        private ImageView mIvPraise;
        private TextView mTvPraiseNum;
        private TextView mAddOne;

        public ViewHoder(View itemView) {
            super(itemView);
            mIvComment = (ImageView) itemView.findViewById(R.id.iv_comment);
            mTvUserName = (TextView) itemView.findViewById(R.id.tv_userName);
            mTvCreateTime = (TextView) itemView.findViewById(R.id.tv_createTime);
            mTvProvince = (TextView) itemView.findViewById(R.id.tv_province);
            mTvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            mIvPraise = (ImageView) itemView.findViewById(R.id.iv_praise);
            mTvPraiseNum = (TextView) itemView.findViewById(R.id.iv_praise_num);
            mAddOne = (TextView) itemView.findViewById(R.id.addOne_tv);
        }
    }
}
