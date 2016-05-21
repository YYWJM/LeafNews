package com.yuanyin.leafnews.news.presenter;

import android.content.Context;

import com.yuanyin.leafnews.beans.NewsDetailBean;
import com.yuanyin.leafnews.news.model.NewsModel;
import com.yuanyin.leafnews.news.model.NewsModelImpl;
import com.yuanyin.leafnews.news.view.NewsDetailView;

/**
 * Description :
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/20
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }


    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if(newsDetailBean != null) {
            mNewsDetailView.showNewsDetailContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
