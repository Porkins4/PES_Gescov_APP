package com.example.gescov.viewlayer.ranking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gescov.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SchoolGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_graph);
        LineChart lineChart = (LineChart) findViewById(R.id.line_chart);
        List<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0,10));
        dataVals.add(new Entry(1,12));
        dataVals.add(new Entry(2,5));
        LineDataSet lineDataSet = new LineDataSet(dataVals,"Data set");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.animateY(2000);
    }
}