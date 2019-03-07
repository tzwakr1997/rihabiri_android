package com.example.migita.daire_demo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class GraphActivity extends Activity {

    private TestOpenHelper mExampleOpenHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph);
    }

    // ボタンクリック
    public void naiten(View v) {
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Graph_seeActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("graph","naiten");
        intent.putExtra("username",username);
        // 画面移行スタート
        startActivity(intent);
    }

    public void gaiten(View v) {
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Graph_seeActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("graph","gaiten");
        intent.putExtra("username",username);
        // 画面移行スタート
        startActivity(intent);
    }

    public void kukkyoku(View v) {
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Graph_seeActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("graph","kukkyoku");
        intent.putExtra("username",username);
        // 画面移行スタート
        startActivity(intent);
    }

    public void sinten(View v) {
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Graph_seeActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("graph","sinten");
        intent.putExtra("username",username);
        // 画面移行スタート
        startActivity(intent);
    }
    public void time(View v) {
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Graph_seeActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("graph","time");
        intent.putExtra("username",username);
        // 画面移行スタート
        startActivity(intent);
    }
}

