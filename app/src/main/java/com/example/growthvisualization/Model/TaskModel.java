package com.example.growthvisualization.Model;

import io.realm.RealmObject;

public class TaskModel extends RealmObject {
    // タスクの名前
    String name;
    // 開始と終了時間
    long start;
    long end;
    // カテゴリ
    // TODO:カテゴリのまとめ方。「タスク名」でまとめられるようにする？
    String category;

    public TaskModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
