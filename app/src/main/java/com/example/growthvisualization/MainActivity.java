package com.example.growthvisualization;

import android.content.Intent;
import android.os.Bundle;

// import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.growthvisualization.Model.TaskModel;
import com.example.growthvisualization.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    // ホーム画面

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Realm realm;
    List<TaskModel> taskModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Realmを使う際、お呪いの２文
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        // 保存されている全てのタスクを抽出
        RealmQuery<TaskModel> query =realm.where(TaskModel.class);
        RealmResults<TaskModel> results = query.findAll();

        taskModelList = new ArrayList<TaskModel>();

        // 保存されている場合
        if (0 < results.size()){

            // ListLayoutにタスクを表示する処理
            for(TaskModel taskModel : results){
                taskModelList.add(taskModel);
            }
        }
        // 何も保存されていない場合
        else{
            // テストで数検のタスクを保存する
            realm.beginTransaction();

            for(int i = 1; i <= 3; i++){
                TaskModel taskModel = new TaskModel();

                taskModel.setId(i);
                taskModel.setTitle("testタイトル" + i);
                taskModel.setText("test本文" + i);

                TaskModel info = realm.copyToRealm(taskModel);

                taskModelList.add(taskModel);
            }

            realm.commitTransaction();
        }

        // リストビューを取得。アダプターをインスタンス生成。リストビューにアダプターをセット。
        ListView listView = findViewById(R.id.list_view);
        BaseAdapter listAdapter = new TaskListAdapter(this.getApplicationContext(), R.layout.list_layout, taskModelList);
        listView.setAdapter(listAdapter);

        // 「新規作成」を押下した場合
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make: Tomcatのようなもの。"text"が本文
                // setAction: ボタンを実装できる。"text"がボタンとして表示される。"listener"にボタンを押下した後の処理を実装できる。
                Snackbar.make(view, "新規作成画面", Snackbar.LENGTH_LONG)
                        .setAction("確認しました", null).show();

                // TODO:現状、遷移先の画面Activiteを作成していないためエラーが起こる
                Intent intent = new Intent(getApplication(), NewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        int id = item.getItemId();

        switch (id){
            // 設定ボタンを押した処理
            case R.id.action_settings:
                intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);

                return true;

            // タイムラインボタンを押して、タイムライン画面へ画面遷移する処理
            case R.id.action_timeline:
                intent = new Intent(getApplication(), TimelineActivity.class);
                startActivity(intent);

                return true;

            // メニューボタンを押して、メニュー欄を表示する処理
            case R.id.action_menu:
                intent = new Intent(getApplication(), null);
                startActivity(intent);

                return true;

            // グラフボタンを押して、グラフ画面へ画面遷移する処理
            case R.id.action_chart:
                intent = new Intent(getApplication(), ChartActivity.class);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}