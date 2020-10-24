package com.example.gescov.PresentationLayer.ContagionList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.PresentationLayer.ContagionList.Controllers.ContagionController;
import com.example.gescov.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContagionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContagionListFragment extends Fragment {
    ListView ListaCont;
    ContagionController controller;
    View thisview;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContagionListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ContagionListFragment newInstance(String param1, String param2) {
        ContagionListFragment fragment = new ContagionListFragment();
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
        controller = new ContagionController(this);
        thisview = inflater.inflate(R.layout.contagionlist, container, false);
        try {
            refresh();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thisview;
    }

    private void refresh() throws InterruptedException, ExecutionException, JSONException {
        controller.refresh();
    }
    public void updateData(String info) throws JSONException {

        JSONArray response = new JSONArray(info);
        List<String> Names = new ArrayList<>();
        List<String> ContagionDate = new ArrayList<>();
        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            String date = aux.getString("startContagion");
            String filter = "";
            int index = 0;
            while ( date.charAt(index) != 'T') {
                filter += date.charAt(index);
                ++index;
            }
            Names.add(aux.getString("nameInfected"));
            ContagionDate.add("FIB-Positiu des de " + filter);
        }
        ListaCont = (ListView) thisview.findViewById(R.id.ListaCont)  ;
        ContagionListAdapter adapter = new ContagionListAdapter(ListaCont.getContext(), Names,ContagionDate);
        ListaCont.setAdapter(adapter);
    }
}