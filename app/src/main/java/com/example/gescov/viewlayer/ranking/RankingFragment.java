package com.example.gescov.viewlayer.ranking;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.R;
import java.util.List;

public class RankingFragment extends Fragment {
    private View thisView;
    private RankingViewModel rankingViewModel;
    private ListView listView;
    private List<Pair<School,Integer>> schools;

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
                schools = rankingViewModel.getContagionperSchools();
                setListViewListener();
            }
        });

        return thisView;
    }

    private void setListViewListener() {
        // en esta función habra que hacer otro observer para obtener lo infectados por mes
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(),SchoolGraphActivity.class);
            intent.putExtra("name",schools.get(position).first.getName());
            intent.putExtra("numContagion",schools.get(position).second);
            startActivity(intent);
        });
    }


}