package ad0424.yls.example.com.news.utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ad0424.yls.example.com.news.model.CollectBean;
import ad0424.yls.example.com.news.model.UserBean;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by yhdj on 2017/5/6.
 */

public class BmobUtil {
    private static String url = "";
    private static boolean isInsert = false;
    private static final List<UserBean> users = new ArrayList<>();
    static boolean isUpdate = false;
    static UserBean mUserBean = new UserBean();
    private static final List<CollectBean> COLLECT_BEEN = new ArrayList<>();
    public static boolean insert(String imgUrl, String userName, String province, String createTime, String comment) {
        isInsert = false;
        UserBean userBean = new UserBean();
        userBean.setImgUrl(imgUrl);
        userBean.setUserName(userName);
        userBean.setProvince(province);
        userBean.setCreateTime(createTime);
        userBean.setComment(comment);
        userBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
//                if (e == null) {
                    isInsert = true;
//                } else {
//                    Log.i("AAAA", "done: " + s);
//                }
            }
        });
        return isInsert;
    }


    public static List<UserBean> query() {
        users.clear();
        BmobQuery<UserBean> query = new BmobQuery<UserBean>();
        query.setLimit(50);
//执行查询方法
        query.findObjects(new FindListener<UserBean>() {
            @Override
            public void done(List<UserBean> object, BmobException e) {
                if (e == null) {
                    for (UserBean user : object) {
                        UserBean userBean = new UserBean();
                        userBean.setImgUrl(user.getImgUrl());
                        userBean.setProvince(user.getProvince());
                        userBean.setComment(user.getComment());
                        userBean.setCreateTime(user.getCreateTime());
                        userBean.setUserName(user.getUserName());
                        userBean.setPraiseNum(user.getPraiseNum());
                        userBean.setObjectId(user.getObjectId());
                        users.add(userBean);
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }


            }
        });

        return users;
    }

    public static UserBean queryById(String objectId){
        BmobQuery<UserBean> query = new BmobQuery<UserBean>();
        query.getObject(objectId, new QueryListener<UserBean>() {
            @Override
            public void done(UserBean userBean, BmobException e) {
                if(e == null){
                  mUserBean = userBean;
                }
            }
        });
        return mUserBean;
    }

    public static boolean update(UserBean userBean) {
        isUpdate = false;

        userBean.increment("praiseNum");
        userBean.update(userBean.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    isUpdate = true;
                } else {
                    Log.e("bbbbbbbbbbb", "done: " + e.getMessage());
                }
            }
        });
        return isUpdate;
    }

    public static String uploadFile(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final BmobFile bmobFile = new BmobFile(new File(path));
                bmobFile.uploadblock(new UploadFileListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            //bmobFile.getFileUrl()--返回的上传文件的完整地址
                            url = bmobFile.getFileUrl();
                        } else {
                        }
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                    }
                });
            }
        }).start();

        return url;
    }

    public static boolean insertCollection(String imgUrl, String title, String createTime,String contentUrl) {
        isInsert = false;
        CollectBean collectBean = new CollectBean();
        collectBean.setImgUrl(imgUrl);
        collectBean.setCollectTime(createTime);
        collectBean.setTitle(title);
        collectBean.setContentUrl(contentUrl);
        collectBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
//                if (e == null) {
                    isInsert = true;
//                } else {
//                    Log.i("AAAA", "done: " + s);
//                }
            }
        });
        return isInsert;
    }

    public static List<CollectBean> queryCollection() {
        COLLECT_BEEN.clear();
        BmobQuery<CollectBean> query = new BmobQuery<CollectBean>();
        query.setLimit(50);
//执行查询方法
        query.findObjects(new FindListener<CollectBean>() {
            @Override
            public void done(List<CollectBean> object, BmobException e) {

                if (e == null) {
                    for (CollectBean coll : object) {
                        CollectBean collectBean = new CollectBean();
                        collectBean.setImgUrl(coll.getImgUrl());
                        collectBean.setTitle(coll.getTitle());
                        collectBean.setCollectTime(coll.getCollectTime());
                        collectBean.setContentUrl(coll.getContentUrl());
                        collectBean.setObjectId(coll.getObjectId());
                        COLLECT_BEEN.add(collectBean);
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

        return COLLECT_BEEN;
    }


    public static boolean delCollection(CollectBean collectBean){
        collectBean.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Log.i("aaaaa", "done: " + "删除成功！！！");
                }else{
                    Log.i("aaaaa", "done: " + "删除失败！！！");
                }
            }
        });
        return true;
    }
}
