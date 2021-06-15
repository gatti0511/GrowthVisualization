package com.example.growthvisualization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.growthvisualization.Model.TaskModel;

import java.util.List;

public class TaskListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int layoutId;
    private List<TaskModel> taskModelList;

    public TaskListAdapter(Context context, int layoutId, List<TaskModel> taskModelList) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.taskModelList = taskModelList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(layoutId, null);
        ImageView list_icon = (ImageView)convertView.findViewById(R.id.list_icon);
        TextView list_tile = (TextView)convertView.findViewById(R.id.list_title);
        TextView list_starttime = (TextView)convertView.findViewById(R.id.list_starttime);
        TextView list_endtime = (TextView)convertView.findViewById(R.id.list_endtime);

        TaskModel taskModel = taskModelList.get(position);

        // TODO:listに表示するICON、の実装
        list_tile.setText(taskModel.getTitle());
        // TODO:listに表示する開始時間、の実装
        // TODO:listに表示する終了時間、の実装

        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
