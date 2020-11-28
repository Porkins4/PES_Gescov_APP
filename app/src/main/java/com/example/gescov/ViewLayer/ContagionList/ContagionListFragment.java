package com.example.gescov.ViewLayer.ContagionList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.Singletons.PresentationControlFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ContagionListFragment extends Fragment {
    private ListView contagionList;
    private ContagionController controller;
    private View thisView;
    private List<String> names;
    private List<String> contagionDate;
    private  ContagionListAdapter adapter;




    public ContagionListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        controller = PresentationControlFactory.getContagionController();
        controller.setContagionListFragment(this);

        thisView = inflater.inflate(R.layout.contagionlist, container, false);
        names = new ArrayList<>();
        contagionDate = new ArrayList<>();
        contagionList = thisView.findViewById(R.id.contagion_list);
        adapter = new ContagionListAdapter(contagionList.getContext(), names, contagionDate);

        contagionList.setAdapter(adapter);
        try {
            controller.refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thisView;
    }

    public void updateData(String info) throws JSONException {
        JSONArray response = new JSONArray(info);
        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            String nameInfected = aux.getString("first");
            String date = aux.getString("second");
            names.add(nameInfected);
            contagionDate.add("FIB-Positiu des de " + date);
        }
        adapter.notifyDataSetChanged();
    }
}