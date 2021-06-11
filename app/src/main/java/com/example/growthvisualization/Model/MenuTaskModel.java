package com.example.growthvisualization.Model;

import io.realm.RealmObject;

public class MenuTaskModel extends RealmObject {
    // メニューに表示するタイトル
    String title;
    // メニューに表示するアイコン
    int icon;

    public MenuTaskModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
