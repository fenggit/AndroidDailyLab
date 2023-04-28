package com.androiddaily.mvp.v3.model;

import java.util.List;

/**
 * author: ant
 * date: 2021/11/15
 * desc：
 */
public interface IBaseModel {
    void getStarList(OnLoaderListener listener);

    interface OnLoaderListener {
        void onSuccess(List<String> list);

        void onError(String msg);

    }
}
