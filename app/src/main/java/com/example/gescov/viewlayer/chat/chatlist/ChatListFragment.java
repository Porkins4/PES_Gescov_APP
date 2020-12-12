package com.example.gescov.viewlayer.chat.chatlist;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gescov.R;
import com.example.gescov.viewlayer.chat.createchat.CreateChatActivity;
import com.example.gescov.viewlayer.chat.chatview.ChatViewActivity;
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
        setGetChatsListener();
        initAddCreateChatButton();
        setListViewItemsListener();//ojo que a lo mejor peta por hacerlo al principio del todo
    }

    private void setGetChatsListener() {
        mViewModel.getChatPreviewModels().observe(getActivity(), error -> {
            if (!error) initListView();
        });
    }

    private void initListView() {
        listView.setAdapter(mViewModel.getAdapter(this.getContext()));
    }

    private void initAddCreateChatButton() {
        floatingActionButton.setOnClickListener(
                v -> startCreateChatActivity()
        );
    }

    private void startCreateChatActivity() {
        Intent i = new Intent(getActivity(), CreateChatActivity.class);
        startActivity(i);
    }

    private void setListViewItemsListener() {
        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.options))
                            .setItems(R.array.chat_options_menu_items, (dialog, which) -> {
                                if (which == 0) confirmDeleteChatPrompt(position);
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getActivity(), ChatViewActivity.class);
                        i.putExtra("chatID",mViewModel.getChatID(position));
                        i.putExtra("targetName",mViewModel.getTargetName(position));
                        i.putExtra("targetPic",mViewModel.getTargetPic(position));
                        startActivity(i);
                    }
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



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChatListViewModel.class);
        initViewComponents();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}