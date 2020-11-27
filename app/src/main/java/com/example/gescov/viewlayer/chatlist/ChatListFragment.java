package com.example.gescov.ViewLayer.chatlist;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ChatListFragment extends Fragment {

    private ChatListViewModel mViewModel;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
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
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.create_new_chat_button);
        initStub();
        initAddCreateChatButton();
        setListViewItemsListener();
    }

    private void initAddCreateChatButton() {
        floatingActionButton.setOnClickListener(
                v -> System.out.println("stub: Creando nuevo chat...")
        );
    }

    private void setListViewItemsListener() {
        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.chat_options))
                            .setItems(R.array.chat_options_menu_items, (dialog, which) -> {
                                if (which == 0) confirmDeleteChatPrompt(position);
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
        );
    }

    private void confirmDeleteChatPrompt(int position) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) setDeleteChatListener(position);
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.confirm_delete_chat_message)).setPositiveButton(getString(R.string.yes_delete_chat), dialogClickListener)
                .setNegativeButton(getString(R.string.no_delete_chat), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setDeleteChatListener(int position) {
        mViewModel.deleteChat(position).observe(getViewLifecycleOwner(),
                error -> {
                    if (!error) mViewModel.deleteChatFromAdapter(position);
                });
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