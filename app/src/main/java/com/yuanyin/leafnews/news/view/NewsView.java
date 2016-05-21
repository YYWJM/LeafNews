package com.yuanyin.leafnews.news.view;

import com.yuanyin.leafnews.beans.NewsBean;

import java.util.List;

/**
 * Description :
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/20
 */
public interface NewsView {

    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
