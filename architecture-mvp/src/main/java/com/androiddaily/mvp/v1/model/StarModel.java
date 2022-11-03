package com.androiddaily.mvp.v1.model;

import java.util.ArrayList;

/**
 * author: ant
 * date: 2021/11/15
 * desc：
 */
public class StarModel implements IBaseModel {
    @Override
    public void getStarList(OnLoaderListener listener) {
        // 模拟网络请求
        listener.onSuccess(new ArrayList<>());
        listener.onError("网络请求失败");
    }
}
