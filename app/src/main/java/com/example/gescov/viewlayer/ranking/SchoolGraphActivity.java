package com.example.gescov.viewlayer.ranking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.gescov.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SchoolGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_graph);
        LineChart linechart = findViewById(R.id.line_chart);

        List<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(1,5));
        dataVals.add(new Entry(2,10));
        dataVals.add(new Entry(3,15));
        dataVals.add(new Entry(4,3));
        LineDataSet lineDataSet = new LineDataSet(dataVals,"Data set");
        lineDataSet.setFillAlpha(110);
        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextColor(Color.WHITE);
        linechart.setData(lineData);
        linechart.getDescription().setEnabled(false);
        linechart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        linechart.getXAxis().setLabelCount(12 );
        linechart.animateY(2000);
    }
}