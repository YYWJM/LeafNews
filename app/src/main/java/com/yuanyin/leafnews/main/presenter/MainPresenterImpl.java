package com.yuanyin.leafnews.main.presenter;

import com.yuanyin.leafnews.R;
import com.yuanyin.leafnews.main.view.MainView;

/**
 * Description :
 * Author : yuanyin
 * Email  : 2212079150@qq.com
 * Date   : 16/5/21
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.nav_news:
                mMainView.switch2News();
                break;
            case R.id.nav_about:
                mMainView.switch2About();
                break;
            default:
                mMainView.switch2News();
                break;
        }
    }
}
