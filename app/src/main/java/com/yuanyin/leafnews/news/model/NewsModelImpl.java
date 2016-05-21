package com.yuanyin.leafnews.news.model;

import com.yuanyin.leafnews.beans.NewsBean;
import com.yuanyin.leafnews.beans.NewsDetailBean;
import com.yuanyin.leafnews.commons.Urls;
import com.yuanyin.leafnews.news.NewsJsonUtils;
import com.yuanyin.leafnews.utils.OkHttpUtils;

import java.util.List;

/**
 * Description : 新闻业务处理类
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/20
 */
public class NewsModelImpl implements NewsModel {

    /**
     * 加载新闻列表
     *
     * @param url
     * @param listener
     */
    @Override
    public void loadNews(String url, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, Urls.TOP_ID);
                listener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 加载新闻详情
     *
     * @param docid
     * @param listener
     */
    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);

        void onFailure(String msg, Exception e);
    }

    public interface OnLoadNewsDetailListener {
        void onSuccess(NewsDetailBean newsDetailBean);

        void onFailure(String msg, Exception e);
    }

}
