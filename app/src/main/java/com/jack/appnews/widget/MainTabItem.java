package com.jack.appnews.widget;

public class MainTabItem {
    private int Image;
    private int Text;
    private Class Fragment;

    public MainTabItem(int image, int text, Class fragment) {
        Image = image;
        Text = text;
        Fragment = fragment;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getText() {
        return Text;
    }

    public void setText(int text) {
        Text = text;
    }

    public Class getFragment() {
        return Fragment;
    }

    public void setFragment(Class fragment) {
        Fragment = fragment;
    }
}