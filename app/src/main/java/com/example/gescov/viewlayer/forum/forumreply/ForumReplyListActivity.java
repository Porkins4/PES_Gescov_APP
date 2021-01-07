package com.example.gescov.viewlayer.forum.forumreply;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.WallEntry;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

public class ForumReplyListActivity extends AppCompatActivity {
    private ModelListViewAdapter adapter;
    private WallEntry wallEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String wallEntryId = getIntent().getStringExtra("wallEntry");

        setContentView(R.layout.activity_forum_entry_replies);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView repliesList = findViewById(R.id.classrooms_list);
        refreshActivity(wallEntryId, repliesList);

        EditText commentEditText = findViewById(R.id.create_wall_entry_reply_form_text);
        Button createCommentButton = findViewById(R.id.create_wall_entry_reply_form_button);

        createCommentButton.setOnClickListener(e-> {
            if (commentEditText.getText() == null || commentEditText.getText().toString().equals("")) {
                Toast.makeText(this, getResources().getText(R.string.create_forum_check_content), Toast.LENGTH_SHORT).show();
            } else {
                PresentationControlFactory.getForumController().createForumEntryReply(wallEntryId, commentEditText.getText().toString());
                commentEditText.setText("");
            }
        });

    }

    private void refreshActivity(String wallEntryId, ListView list) {
        WallEntry wallEntry = PresentationControlFactory.getForumController().getModelItemById(wallEntryId);
        if (wallEntry != null) {
            PresentationControlFactory.getForumRepliesController().setWallEntry(wallEntry);
            PresentationControlFactory.getForumRepliesController().refreshList();
            PresentationControlFactory.getForumRepliesController().setListViewAdapter(this);
            adapter = PresentationControlFactory.getForumRepliesController().getListViewAdapter();
            list.setAdapter(adapter);

            TextView weTitle = findViewById(R.id.wall_entry_list_item_title);
            TextView weContent = findViewById(R.id.wall_item_reply_list_item_content);
            TextView weHour = findViewById(R.id.wall_item_reply_list_item_date);
            TextView weSchool = findViewById(R.id.wall_entry_reply_list_item_author);

            weTitle.setText(wallEntry.getTitle());
            weContent.setText(wallEntry.getContent());
            weHour.setText(wallEntry.getTime());
            School school = PresentationControlFactory.getSchoolsCrontroller().getSchoolById(wallEntry.getSchoolId());
            weSchool.setText(school == null ? wallEntry.getSchoolId() : school.getName());
        }
    }
}
