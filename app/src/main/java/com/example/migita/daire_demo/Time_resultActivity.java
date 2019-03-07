package com.example.migita.daire_demo;//最終盤

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Time_resultActivity extends AppCompatActivity {

    PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_result);
        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String test1 = intent.getStringExtra("test");
        final String username = intent.getStringExtra("username");
        String graph = intent.getStringExtra("graph");
        mPieChart = (PieChart) findViewById(R.id.pie_chart);

        //String test1 = intent.getStringExtra("test2");
        String[] line = test1.split(",", 0);

        int[] data = new int[2];
        int[] sum = new int[line.length];
        int ts = 0;
        for (int i = 0; i < line.length; i++) {
            String[] line1 = line[i].split(":", 0);
            for (int j = 0; j < line1.length; j++) {
                data[j] = Integer.parseInt(line1[j]);

            }
            sum[i] = data[0] * 60 + data[1];
            ts = ts + sum[i];
            //textView.setText(sum[0]);
        }
        double n = ts / 300.00 * 100;
        textView.setText(String.valueOf((int)n) + "%");

        setupPieChartView((float)n);

        ImageButton button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Time_resultActivity.this, Mode_SelectActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }
    private void setupPieChartView(float n) {
        mPieChart.setUsePercentValues(true);
        int[] color = new int[3];
//        mPieChart.setDescription("aa");

        Legend legend = mPieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        List<Float> values = Arrays.asList(n,100f-n);
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            entries.add(new PieEntry(values.get(i), i));
        }

        PieDataSet dataSet = new PieDataSet(entries,"達成率");
        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        color[1] = ColorTemplate.VORDIPLOM_COLORS[2];
        color[0] = ColorTemplate.COLORFUL_COLORS[1];
        //color[2] = ColorTemplate.COLORFUL_COLORS[2];

        dataSet.setColors(color);
        dataSet.setDrawValues(true);

        List<String> labels = Arrays.asList("達成度","未達成");
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.WHITE);

        mPieChart.setData(pieData);
    }
}
