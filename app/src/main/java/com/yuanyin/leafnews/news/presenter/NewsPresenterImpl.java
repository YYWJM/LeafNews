package com.yuanyin.leafnews.news.presenter;

import com.yuanyin.leafnews.beans.NewsBean;
import com.yuanyin.leafnews.commons.Urls;
import com.yuanyin.leafnews.news.model.NewsModel;
import com.yuanyin.leafnews.news.model.NewsModelImpl;
import com.yuanyin.leafnews.news.view.NewsView;
import com.yuanyin.leafnews.utils.LogUtils;

import java.util.List;

/**
 * Description :
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/20
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoadNewsListListener {

    private static final String TAG = "NewsPresenterImpl";

    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(final int pageIndex) {
        String url = getUrl(pageIndex);
        LogUtils.d(TAG, url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if (pageIndex == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(url, this);
    }

    /**
     * 根据类别和页面索引创建url
     *
     * @param pageIndex
     * @return
     */
    private String getUrl(int pageIndex) {
        StringBuffer sb = new StringBuffer();
        sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }

}
