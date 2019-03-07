package com.example.migita.daire_demo;
//sqlに保存できた、read消す

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class SokectActivity extends Activity {

    private EditText editTextKey, editTextValue;
    private TestOpenHelper helper;
    private SQLiteDatabase db;

    private TextView textView1;
    private Chronometer chronometer;
    Timer timer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokect);
        final String r;

        timer = new Timer();

        textView1 = findViewById(R.id.text_view);

        Intent intent = getIntent();
        final int mp4 = intent.getIntExtra("mp4", R.raw.video2);
        final String username=intent.getStringExtra("username");

        chronometer = findViewById(R.id.chronometer);
        final TextView textView = findViewById(R.id.deg);


        if(mp4==R.raw.naiten){
            r="naiten";
        }else if(mp4==R.raw.gaiten){
            r="gaiten";
        }else if(mp4==R.raw.shinten){
            r="shinten";
        }else{
            r="kukkyoku";
        }

        //値が送られてくる、タイマーストップ
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Runnable sender = new Runnable() {
                    @Override
                    public void run() {

                        //textView.setText("98");
                        chronometer.stop();

                        Socket socket = null;
                        BufferedReader reader = null;
                        final String address ="192.168.11.17";
                        final int port=811;

                        try {
                            socket = new Socket(address, port);
                            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            final PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                            final String sendTxt = "deg?";

                            pw.println(sendTxt);
                            String deg=reader.readLine();
                            textView.setText(deg);

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        if (socket != null||reader != null) {
                            try {
                                socket.close();
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread th = new Thread(sender);
                th.start();

            }
        });


        //開始ボタン
        //タイマーの開始
        ImageButton insertButton = findViewById(R.id.start);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

//                timer.cancel();
//                timer = new Timer();


            }
        });

        //ホームへ行く
        //ホームへ行く、データベースに書き込み
        ImageButton readButton = findViewById(R.id.return_home);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper == null){
                    helper = new TestOpenHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                String username1 = username;
                String angle = textView.getText().toString();
                String l_s = r;
                String time=chronometer.getText().toString();

                insertData(db, username1, l_s,angle,time);

                Intent intent1 = new Intent(SokectActivity.this, Mode_SelectActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);

            }
        });

    }

    private void insertData(SQLiteDatabase db, String username, String l_s,String angle,String time){

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("l_s", l_s);
        values.put("save_int", angle);
        values.put("time", time);

        db.insert("testdb", null, values);
    }


    private void readData(){
        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "testdb",
                new String[] { "username","l_s","save_int" ,"time"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(", ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append(", ");
            sbuilder.append(cursor.getString(2));
            sbuilder.append(", ");
            sbuilder.append(cursor.getString(3));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder.toString());
        textView1.setText(sbuilder.toString());


    }

}

