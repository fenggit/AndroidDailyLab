package com.androiddaily.mvp.v2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.androiddaily.mvp.R;
import com.androiddaily.mvp.v2.presenter.StarPresenter;
import com.androiddaily.mvp.v2.view.IStarView;


import java.util.List;

public class MVPMainActivity2 extends AppCompatActivity implements IStarView {
    private StarPresenter mStarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mvpmain2);

        mStarPresenter = new StarPresenter();
        mStarPresenter.onAttach(this);
        mStarPresenter.fetch();
    }

    @Override
    public void showErrorToast(String msg) {

    }

    @Override
    public void showStarList(List<String> list) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStarPresenter.onDetach();
    }
}