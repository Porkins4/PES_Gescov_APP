package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;
import com.example.gescov.viewlayer.tracingTestResult.TracingTestResultActivity;

import java.util.List;

public class SchoolUsersListViewAdapter extends ModelListViewAdapter {

    public SchoolUsersListViewAdapter(Context context, List list) {
        super(context, list);
    }
    private User loggedUser;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        View v = inflater.inflate(R.layout.user_list_item, null);
        TextView userName = v.findViewById(R.id.wall_entry_list_item_title);
        TextView userProfile = v.findViewById(R.id.wall_item_reply_list_item_content);
        User user = (User) modelList.get(position);
        School currentSchool = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        userName.setText(user.getName());
        if (user.getProfileType() == User.UserProfileType.STUDENT) {
            userProfile.setText(v.getResources().getText(R.string.school_user_list_student));
        } else if (currentSchool.getCreator().equals(user.getId())) {
            userProfile.setText(v.getResources().getText(R.string.school_user_list_creator));
        } else if (currentSchool.getAdministratorsList().contains(user.getId())) {
            userProfile.setText(v.getResources().getText(R.string.school_user_list_admin));
        } else {
            userProfile.setText(v.getResources().getText(R.string.school_user_list_teacher));
        }

        if (loggedUser.getProfileType() == User.UserProfileType.TEACHER)  {
            v.setOnLongClickListener(e -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                        builder.setTitle(e.getResources().getString(R.string.options))
                                .setItems( R.array.teacher_option_menu_items , (dialog, which) -> {
                                    if (which == 0) getResultsOfTests(user,v);
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
            );
        }

        if (isLoggedUserCreator() && user.getProfileType().equals(User.UserProfileType.TEACHER) && !user.getId().equals(currentSchool.getCreator())) {
            v.setOnLongClickListener(e -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                        builder.setTitle(e.getResources().getString(R.string.options))
                                .setItems(isUserAdmin(user) ? R.array.admin_option_menu_items : R.array.user_option_menu_items, (dialog, which) -> {
                                    if (which == 0) confirmSetAdministratorPrompt(e, user, isUserAdmin(user));
                                    else if (which == 1) getResultsOfTests(user,v);
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
            );
        }

        return v;
    }

    private void getResultsOfTests(User user, View v) {
        Intent intent = new Intent(v.getContext(), TracingTestResultActivity.class);
        intent.putExtra("name", user.getName());
        intent.putExtra("userID", user.getId());
        intent.putExtra("picture",user.getPic());
        v.getContext().startActivity(intent);
    }


    private boolean isUserAdmin(User user) {
        School school = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        return school.getAdministratorsList().contains(user.getId());
    }

    private boolean isLoggedUserCreator() {
        School school = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        return school.getCreator().equals(loggedUser.getId());
    }

    private void confirmSetAdministratorPrompt(View v, User user, boolean isUserAdmin) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!isUserAdmin) {
                    PresentationControlFactory.getSchoolsCrontroller().addNewAdminToSchool(user.getId());
                } else {
                    PresentationControlFactory.getSchoolsCrontroller().deleteSchoolAdmin(user.getId());
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        String question;
        if (!isUserAdmin)
            question = v.getResources().getString(R.string.user_school_make_admin_message_1) +" " + user.getName() + " " +  v.getResources().getString(R.string.user_school_make_admin_message_2);
        else
            question = v.getResources().getString(R.string.user_school_unmake_admin_message_1) +" " + user.getName() + " " +  v.getResources().getString(R.string.user_school_unmake_admin_message_2);
        builder.setMessage(question)
                .setPositiveButton(v.getResources().getString(R.string.apply), dialogClickListener)
                .setNegativeButton(v.getResources().getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
