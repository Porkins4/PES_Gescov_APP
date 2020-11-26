package com.example.gescov.ViewLayer.chatlist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.DomainLayer.Classmodels.ChatViewerModel;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListFragment extends Fragment {

    private ChatListViewModel mViewModel;
    private ListView listView;
    private View root;

    public static ChatListFragment newInstance() {
        return new ChatListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.chat_list_fragment, container, false);
        return root;
    }

    private void initViewComponents() {
        listView = (ListView) root.findViewById(R.id.chat_fragment_list_view);
        initStub();
    }

    private void initStub() {
        listView.setAdapter(mViewModel.getAdapter(this.getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChatListViewModel.class);
        initViewComponents();
    }
}