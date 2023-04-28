package com.androiddaily.mvp.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androiddaily.mvp.R;
import com.androiddaily.mvp.v1.presenter.StarPresenter;
import com.androiddaily.mvp.v1.view.IStarView;

import java.util.List;

public class MVPMainActivity1 extends AppCompatActivity implements IStarView {
    private StarPresenter mStarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mvpmain2);

        mStarPresenter = new StarPresenter(this);
        mStarPresenter.fetch();
    }

    @Override
    public void showErrorToast(String msg) {

    }

    @Override
    public void showStarList(List<String> list) {

    }
}