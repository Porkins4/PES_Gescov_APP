package com.example.gescov;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.gridlayout.widget.GridLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link showDistributionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showDistributionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GridLayout gridLayout;
    private String [][] distr = {
            {"-1","Albert","-1","Ponç"},
            {"Pablo","-1","0","-1"},
            {"-1","Anas","-1","Isaac"},
    };

    public showDistributionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static showDistributionFragment newInstance(String param1, String param2) {
        showDistributionFragment fragment = new showDistributionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_show_distribution, container, false);
        showDistribution(thisView);
        return thisView;
    }

    private void showDistribution(View thisView) {
        gridLayout = (GridLayout) thisView.findViewById(R.id.ShowDistributionGrid);
        gridLayout.setRowCount(distr.length);//filas del grid
        gridLayout.setColumnCount(distr[0].length); //columnas del grid

        for(int i = 0; i < distr.length; ++i) {
            for (int j = 0; j < distr[0].length; ++j) {
                TableWidget y = new TableWidget(getLayoutInflater().inflate(R.layout.table_item,null,false));
                y.initTable(distr[i][j]);
                gridLayout.addView(y.getTableLayout());
            }
        }
    }
}