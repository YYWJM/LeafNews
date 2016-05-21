package com.yuanyin.leafnews.news.model;

/**
 * Description :新闻业务接口
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/20
 */
public interface NewsModel {

    void loadNews(String url, NewsModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);

}
