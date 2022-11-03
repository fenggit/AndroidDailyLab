package com.androiddaily.mvp.v1.presenter;

import com.androiddaily.mvp.v1.model.IBaseModel;
import com.androiddaily.mvp.v1.model.StarModel;
import com.androiddaily.mvp.v1.view.IStarView;

import java.util.List;

/**
 * author: ant
 * date: 2021/11/15
 * desc： 这样写：互相引用，导致内存泄漏
 */
public class StarPresenter {
    IStarView starView;
    IBaseModel starModel;

    public StarPresenter(IStarView starView) {
        starModel = new StarModel();
        this.starView = starView;
    }

    public void fetch() {
        starModel.getStarList(new IBaseModel.OnLoaderListener() {
            @Override
            public void onSuccess(List<String> list) {
                starView.showStarList(list);
            }

            @Override
            public void onError(String msg) {
                starView.showErrorToast(msg);
            }
        });
    }
}
