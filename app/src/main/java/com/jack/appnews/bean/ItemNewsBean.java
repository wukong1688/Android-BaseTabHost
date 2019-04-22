package com.jack.appnews.bean;

import java.util.List;

public class ItemNewsBean {
    public ItemNewsBean(String news_title, String source_name, List<String> img_list, int content_type) {
        this.news_title = news_title;
        this.source_name = source_name;
        this.img_list = img_list;
        this.content_type = content_type;
    }

    public ItemNewsBean(String news_title, String source_name, int content_type) {
        this.news_title = news_title;
        this.source_name = source_name;
        this.content_type = content_type;
    }

    /**
     * news_id : 1001
     * news_title : 手机充电速度到底取决于充电头还是数据线？以前一直搞错了
     * content_type : 1
     * web_url : https://m.toutiao.com/i6681254554287211015/?W2atIF=1
     * img_list : ["https://p3.pstatp.com/list/190x124/pgc-image/ROHTP7jD0TBlNZ"]
     * created : 1555878709
     * source_name : 凤凰新闻
     */

    private String news_title;
    private int content_type;
    private String source_name;
    private List<String> img_list;

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public int getContent_type() {
        return content_type;
    }

    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public List<String> getImg_list() {
        return img_list;
    }

    public void setImg_list(List<String> img_list) {
        this.img_list = img_list;
    }
}