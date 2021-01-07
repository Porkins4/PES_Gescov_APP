package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.os.Bundle;
import android.util.Pair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.gescov.R;

import java.util.ArrayList;
import java.util.List;

public class SchoolGraphActivity extends AppCompatActivity {

    private List<Pair<String,Integer>> contagionPerMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_graph);
        contagionPerMonth = new ArrayList<>();
        String schoolId = getIntent().getStringExtra("schoolId");
        String schoolName = getIntent().getStringExtra("nameSchool");
        setGraph(schoolId,schoolName);
    }


    public void setGraph(String schoolId, String schoolName) {

        SchoolGraphViewModel schoolGraphModel = new ViewModelProvider(this).get(SchoolGraphViewModel.class);
        schoolGraphModel.getGraphInfo(schoolId).observe(this, received -> {
            if ( received ) {
                Cartesian cartesian = AnyChart.column();
                List<DataEntry> data = new ArrayList<>();
                AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                contagionPerMonth = schoolGraphModel.getContagionPerMonth();
                if ( contagionPerMonth.size() == 0 ) data.add(new ValueDataEntry(getString(R.string.no_contagion), 0));
                else {
                    for (int i = 0; i < contagionPerMonth.size(); ++i) {
                        data.add(new ValueDataEntry(getNameOfMonth(i+1), contagionPerMonth.get(i).second));
                    }
                }

                Column column = cartesian.column(data);
                column.color("#06575B");
                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(3d)
                        .format("{%Value}{groupsSeparator: }");

                cartesian.animation(true);
                cartesian.title(getString(R.string.evolution)+ schoolName);
                cartesian.yScale().minimum(0d);

                cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                cartesian.interactivity().hoverMode(HoverMode.BY_X);

                cartesian.xAxis(0).title(getString(R.string.months));
                cartesian.yAxis(0).title(getString(R.string.axeYContagions));
                anyChartView.setChart(cartesian);
            }
        });
    }


    public String getNameOfMonth(int i) {
        String month;
        switch (i) {
            case 1: month = getString(R.string.first_month);
                break;
            case 2: month = getString(R.string.second_month);
                break;
            case 3: month = getString(R.string.third_month);
                break;
            case 4: month = getString(R.string.fourth_month);
                break;
            case 5: month = getString(R.string.fifth_month);
                break;
            case 6: month = getString(R.string.sixth_month);
                break;
            case 7: month = getString(R.string.seventh_month);
                break;
            case 8: month = getString(R.string.eighth_month);
                break;
            case 9: month = getString(R.string.nineth_month);
                break;
            case 10: month = getString(R.string.tenth_month);
                break;
            case 11: month = getString(R.string.elventh_month);
                break;
            case 12: month = getString(R.string.twelvth_month);
                break;
            default: month = "Invalid";
                break;
        }
        return month;
    }
}