package com.example.gescov.viewlayer.ranking;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.gescov.R;


public class RankingFragment extends Fragment {
    private View thisView;
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
        rankingViewModel.getRanking().observe(getActivity(), received -> {
            if ( received ) {
                listView.setAdapter(rankingViewModel.getAdapter(getActivity()));
            }
        });
        return thisView;
    }
}