package com.example.migita.daire_demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Rehabilitation_DougaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehabilitation__douga);

        // 元の画面で指定した動画を取得
        Intent intent = getIntent();
        final int mp4 = intent.getIntExtra("mp4", R.raw.video2);
        final int data=intent.getIntExtra("KEY", 0);
        final String username=intent.getStringExtra("username");


        // ID取得
        VideoView v = (VideoView) findViewById(R.id.video);

        // 動画の指定
        v.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + mp4));

        // 再生スタート
        v.start();

        // コントローラNO（動画をタップするとメニュー表示）
        v.setMediaController(new MediaController(this));

        v.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub

                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        //測定か練習かでページ遷移を変える
                        if(data==1){
                            Intent intent1 = new Intent(Rehabilitation_DougaActivity.this, SokectActivity.class);
                            intent1.putExtra("mp4", mp4);
                            intent1.putExtra("username",username);
                            startActivity(intent1);

                        }else{
//                            Intent intent1 = new Intent(Rehabilitation_DougaActivity.this, TimerActivity.class);
//                            intent1.putExtra("username",username);
//                            startActivity(intent1);

                        }
                        // アクティビティを終了させることで、スプラッシュ画面に戻ることを防ぐ。
                        //MainActivity.this.finish();
                    }
                }, 500);
            }
        });
    }
}
