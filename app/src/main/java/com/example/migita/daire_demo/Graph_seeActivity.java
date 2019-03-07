package com.example.migita.daire_demo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

public class Graph_seeActivity extends AppCompatActivity {

    private TestOpenHelper helper;
    private SQLiteDatabase db;
    //private String a="username";
    private TextView textView;
    int a[];
    private LineChart mChart;
    String time="time";
    String naiten="naiten";
    String gaiten="gaiten";
    String kukkyoku="kukkyoku";
    String sinten="sinten";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_see);

        textView = findViewById(R.id.text_view);
        Intent intent1 = getIntent();
        final String username=intent1.getStringExtra("username");
        final String graph=intent1.getStringExtra("graph");


        if(helper == null){
            helper = new TestOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        if(graph.equals(time)){
            Cursor cursor = db.query(
                    "testdb",
                    new String[] {  "time" },
                    "username=?",
                    new String[]{username},
                    null,
                    null,
                    null
            );
            String a[] = new String[0];
            int j=0;
            cursor.moveToFirst();

            StringBuilder sbuilder = new StringBuilder();

            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor.getString(0)==null){
                    cursor.moveToNext();

                }else{
                    sbuilder.append(cursor.getString(0));
                    sbuilder.append(",");
                    cursor.moveToNext();

                }
            }
            // 忘れずに！
            cursor.close();
            Intent intent = new Intent(Graph_seeActivity.this, Time_resultActivity.class);
            intent.putExtra("test", sbuilder.toString());
            intent.putExtra("username", username);
            intent.putExtra("graph", graph);
            startActivity(intent);

        }else {

            Cursor cursor = db.query(
                    "testdb",
                    new String[]{"save_int"},
                    "l_s=? AND username=?",
                    new String[]{graph, username},
                    null,
                    null,
                    null
            );
            String a[] = new String[0];
            int j=0;
            cursor.moveToFirst();

            StringBuilder sbuilder = new StringBuilder();

            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor.getString(0)==null){
                    cursor.moveToNext();

                }else{
                    sbuilder.append(cursor.getString(0));
                    sbuilder.append(",");
                    cursor.moveToNext();

                }
            }
            // 忘れずに！
            cursor.close();
            Intent intent = new Intent(Graph_seeActivity.this, Graph2Activity.class);
            intent.putExtra("test", sbuilder.toString());
            intent.putExtra("username", username);
            intent.putExtra("graph", graph);
            startActivity(intent);
        }



//        Log.d("debug","**********"+sbuilder.toString());
//        textView.setText(sbuilder.toString());
//        textView.setText(a);


//


    }

//    private void readData(String username,String graph){
//
//        if(helper == null){
//            helper = new TestOpenHelper(getApplicationContext());
//        }
//
//        if(db == null){
//            db = helper.getReadableDatabase();
//        }
//        Log.d("debug","**********Cursor");
//
//        Cursor cursor = db.query(
//                "testdb",
//                new String[] {  "save_int" },
//                "l_s=? AND username=?",
//                new String[]{graph,username},
//                null,
//                null,
//                null
//        );
//
//        cursor.moveToFirst();
//
//       StringBuilder sbuilder = new StringBuilder();
//
//        for (int i = 0; i < cursor.getCount(); i++) {
//            //a[i]=Integer.parseInt(cursor.getString(0));
//            sbuilder.append(cursor.getString(0));
//            sbuilder.append("\n");
//            cursor.moveToNext();
//        }
//
//        // 忘れずに！
//        cursor.close();
//
//        Log.d("debug","**********"+sbuilder.toString());
//        textView.setText(sbuilder.toString());
//
//
//    }
//    private void readData1(String username){
//
//        if(helper == null){
//            helper = new TestOpenHelper(getApplicationContext());
//        }
//
//        if(db == null){
//            db = helper.getReadableDatabase();
//        }
//        Log.d("debug","**********Cursor");
//
//        Cursor cursor = db.query(
//                "testdb",
//                new String[] {  "time" },
//                "username=?",
//                new String[]{username},
//                null,
//                null,
//                null
//        );
//
//        cursor.moveToFirst();
//
//        StringBuilder sbuilder = new StringBuilder();
//
//        for (int i = 0; i < cursor.getCount(); i++) {
//            //a[i]=Integer.parseInt(cursor.getString(0));
//            sbuilder.append(cursor.getString(0));
//            sbuilder.append("\n");
//            cursor.moveToNext();
//        }
//
//        // 忘れずに！
//        cursor.close();
//
//        Log.d("debug","**********"+sbuilder.toString());
//        textView.setText(sbuilder.toString());
//
//
//    }
}
