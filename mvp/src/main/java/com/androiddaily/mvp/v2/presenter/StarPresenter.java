package com.androiddaily.mvp.v2.presenter;

import com.androiddaily.mvp.v2.model.IBaseModel;
import com.androiddaily.mvp.v2.model.StarModel;
import com.androiddaily.mvp.v2.view.IStarView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: ant
 * date: 2021/11/15
 * desc： 解决循环引用，内存泄漏
 */
public class StarPresenter {
    private WeakReference<IStarView> mViews;
    private IBaseModel mStarModel;

    public StarPresenter() {
        this.mStarModel = new StarModel();

    }

    public void onAttach(IStarView starView) {
        this.mViews = new WeakReference<>(starView);
    }

    public void onDetach() {
        if (mViews == null) {
            return;
        }
        mViews.clear();
        mViews = null;
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
