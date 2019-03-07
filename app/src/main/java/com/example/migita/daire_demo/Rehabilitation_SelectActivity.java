package com.example.migita.daire_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Rehabilitation_SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitation__select);
    }
    // ボタンクリック
    public void naiten(View v) {

        Intent intent1 = getIntent();
        int data=intent1.getIntExtra("KEY", 0);
        String username=intent1.getStringExtra("username");

        // 画面移行の準備
        Intent intent = new Intent(this, Rehabilitation_DougaActivity.class);
        // 移行後に再生する動画を指定
        intent.putExtra("mp4", R.raw.naiten);
        intent.putExtra("username",username);
        intent.putExtra("KEY",data);
        // 画面移行スタート
        startActivity(intent);
    }

    public void gaiten(View v) {
        Intent intent1 = getIntent();
        int data=intent1.getIntExtra("KEY", 0);
        String username=intent1.getStringExtra("username");

        Intent intent = new Intent(this, Rehabilitation_DougaActivity.class);
        intent.putExtra("mp4", R.raw.gaiten);
        intent.putExtra("KEY",data);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void kukkyoku(View v) {
        Intent intent1 = getIntent();
        int data=intent1.getIntExtra("KEY", 0);
        String username=intent1.getStringExtra("username");

        Intent intent = new Intent(this, Rehabilitation_DougaActivity.class);
        intent.putExtra("mp4", R.raw.kukkyoku);
        intent.putExtra("KEY",data);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void shinten(View v) {
        Intent intent1 = getIntent();
        int data=intent1.getIntExtra("KEY", 0);
        String username=intent1.getStringExtra("username");

        Intent intent = new Intent(this, Rehabilitation_DougaActivity.class);
        intent.putExtra("mp4",R.raw.shinten);
        intent.putExtra("KEY",data);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}

