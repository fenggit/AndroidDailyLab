package com.androiddaily.mvp.v3;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.androiddaily.mvp.v3.annotation.InjectPresenter;
import com.androiddaily.mvp.v3.presenter.BasePresenter;
import com.androiddaily.mvp.v3.view.IBaseView;
import com.androiddaily.mvp.v3.view.IStarView;

import java.lang.reflect.Field;

/**
 * author: ant
 * date: 2021/11/15
 * desc：
 */
public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity implements IBaseView {
    protected T mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.onAttach((V) this);


        // 通过注解的方式生成Presenter
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            InjectPresenter[] injectPresenters = field.getAnnotationsByType(InjectPresenter.class);
            Class<? extends BasePresenter> presenterClazz = null;
            if (injectPresenters != null) {
                try {
                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            BasePresenter basePresenter = null;
            try {
                basePresenter = presenterClazz.newInstance();
                basePresenter.onAttach(this);

                field.setAccessible(true);
                field.set(this, basePresenter);
                basePresenter.onAttach(this);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
