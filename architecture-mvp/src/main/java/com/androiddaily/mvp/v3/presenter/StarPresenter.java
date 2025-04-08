package com.androiddaily.mvp.v3.presenter;

import com.androiddaily.mvp.v3.model.IBaseModel;
import com.androiddaily.mvp.v3.model.StarModel;
import com.androiddaily.mvp.v3.view.IStarView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: ant
 * date: 2021/11/15
 * desc： 抽取
 */
public class StarPresenter extends BasePresenter<IStarView,StarModel>{
    private IBaseModel mStarModel;

    public StarPresenter() {
        //this.mStarModel = new StarModel();
    }


    public void fetch() {
        mStarModel.getStarList(new IBaseModel.OnLoaderListener() {
            @Override
            public void onSuccess(List<String> list) {
                if (mViews.get() != null) {
                    mViews.get().showStarList(list);
                }
            }

            @Override
            public void onError(String msg) {
                if (mViews.get() != null) {
                    mViews.get().showErrorToast(msg);
                }
            }
        });
    }
}
