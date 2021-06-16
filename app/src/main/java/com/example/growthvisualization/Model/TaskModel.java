package com.example.growthvisualization.Model;

import io.realm.RealmObject;

public class TaskModel extends RealmObject {
    // TODO:主キーと他変数の初期値を設定してない

    // ID
    int id;
    // タイトル
    String title;
    // 本文
    String text;
    // 開始と終了時間
    long start;
    long end;
    // アイコン
    int icon;

    public TaskModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
