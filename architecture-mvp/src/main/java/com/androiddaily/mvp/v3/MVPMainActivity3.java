package com.androiddaily.mvp.v3;

import android.os.Bundle;

import com.androiddaily.mvp.R;
import com.androiddaily.mvp.v3.annotation.InjectPresenter;
import com.androiddaily.mvp.v3.presenter.StarPresenter;
import com.androiddaily.mvp.v3.view.IStarView;

import java.util.List;

/**
 * 假如有多个Presenter该如何处理？
 */
public class MVPMainActivity3 extends BaseActivity<StarPresenter, IStarView> implements IStarView {

    @InjectPresenter
    StarPresenter mmStarPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mvpmain2);

        mPresenter.fetch();
    }

    @Override
    protected StarPresenter createPresenter() {
        return new StarPresenter();
    }

    @Override
    public void showErrorToast(String msg) {

    }

    @Override
    public void showStarList(List<String> list) {

    }


}