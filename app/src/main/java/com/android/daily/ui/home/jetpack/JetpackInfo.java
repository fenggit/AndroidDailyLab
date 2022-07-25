package com.android.daily.ui.home.jetpack;

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
public class JetpackInfo {
    public String title;
    public String imageUrl;
    public Class<?> target;

    public JetpackInfo(String title, String imageUrl, Class<?> target) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.target = target;
    }
}
