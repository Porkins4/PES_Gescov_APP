package com.example.gescov.viewlayer.chat.chatlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.viewlayer.chat.chatview.ChatViewActivity;
import com.example.gescov.viewlayer.chat.createchat.CreateChatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ChatListFragment extends Fragment {

    private static final int SUCCESS_RESERVATION_REQUEST_CODE = 200;
    private ChatListViewModel mViewModel;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.chat_list_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewComponents();
    }

    private void initViewComponents() {
        listView = (ListView) root.findViewById(R.id.chat_fragment_list_view);
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.create_new_chat_button);
        floatingActionButton.setOnClickListener(
                v -> {
                    Intent i = new Intent(getActivity(), CreateChatActivity.class);
                    startActivity(i);
                }
        );
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Intent i = new Intent(getActivity(), ChatViewActivity.class);
                    i.putExtra("chatID",mViewModel.getChatID(position));
                    i.putExtra("targetName",mViewModel.getTargetName(position));
                    i.putExtra("targetPic",mViewModel.getTargetPic(position));
                    startActivity(i);
                }
        );

        //Code to delete chat if implemented
        /*listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.options))
                            .setItems(R.array.chat_options_menu_items, (dialog, which) -> {
                                if (which == 0) confirmDeleteChatPrompt(position);
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                }
        );*/
    }

    private void initViewModelData() {
        if (mViewModel == null) mViewModel = new ViewModelProvider(this).get(ChatListViewModel.class);
        mViewModel.getChatPreviewModels().observe(getActivity(), error -> {
            if (!error) {
                listView.setAdapter(mViewModel.getAdapter(this.getContext()));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewModelData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_RESERVATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //dummy
        }
    }

    //Code to delete chat if implemented
    /*private void confirmDeleteChatPrompt(int position) {
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
    }*/
}