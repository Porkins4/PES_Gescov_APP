package com.example.gescov.viewlayer.ranking;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Map.MapVIewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {
    private View thisView;
    private BarChart barChart;
    private RankingViewModel rankingViewModel;
    private ListView listView;


    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.fragment_ranking, container, false);
        listView = thisView.findViewById(R.id.ranking_list);
        rankingViewModel = new  ViewModelProvider(getActivity()).get(RankingViewModel.class);
        rankingViewModel.getRanking().observe(getActivity(), recieved -> {
            if ( recieved ) {
                listView.setAdapter(rankingViewModel.getAdapter(getActivity()));
            }
        });
        RankingAdapter rankingAdapter = rankingViewModel.getAdapter(getActivity());
        return thisView;
    }


}