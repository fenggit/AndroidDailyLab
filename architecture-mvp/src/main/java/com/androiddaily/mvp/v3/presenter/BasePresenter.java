package com.androiddaily.mvp.v3.presenter;

import com.androiddaily.mvp.v3.view.IBaseView;
import com.androiddaily.mvp.v3.model.IBaseModel;
import com.androiddaily.mvp.v3.view.IStarView;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: ant
 * date: 2021/11/15
 * desc：
 */
public abstract class BasePresenter<T extends IBaseView, K extends IBaseModel> {
    protected WeakReference<IStarView> mViews;
    private K mModel;

    public BasePresenter()  {
        // 自定创建 Model
        // 获取，T 和 K
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            mModel = (K) ((Class)types[1]).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void onAttach(IBaseView view) {
        this.mViews = new WeakReference(view);
    }


    public void onDetach() {
        if (mViews == null) {
            return;
        }
        mViews.clear();
        mViews = null;
    }

}
