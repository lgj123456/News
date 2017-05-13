package ad0424.yls.example.com.news.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by yhdj on 2017/5/13.
 */

public class CollectBean extends BmobObject{
    private String imgUrl;
    private String title;
    private String collectTime;
private String contentUrl;

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }
}
