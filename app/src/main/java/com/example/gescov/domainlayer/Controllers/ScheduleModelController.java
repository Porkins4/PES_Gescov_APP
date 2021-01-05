package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ScheduleModelController {
    public void setSchedule(String classID, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5) {
        JSONObject classSchedule = new JSONObject();
        JSONArray scheduleList = new JSONArray();
        for (int i = 0; i < 5; ++i ) {
            switch (i) {
                case 0:
                    ConvertToJson(l1,"MONDAY",scheduleList);
                    break;
                case 1:
                    ConvertToJson(l2,"TUESDAY", scheduleList);
                    break;
                case 2:
                    ConvertToJson(l3,"WEDNESDAY", scheduleList);
                    break;
                case 3:
                    ConvertToJson(l4,"THURSDAY", scheduleList);
                    break;
                case 4:
                    ConvertToJson(l5,"FRIDAY", scheduleList);
                    break;
                default:
                    System.out.println("error");
            }
        }


        try {
            classSchedule.put("schedule",scheduleList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ServicesFactory.getClassroomService().setSchedule(scheduleList,classID);

    }

    private void ConvertToJson(List<Subject> l1, String day, JSONArray scheduleList) {
        try {
            for (int i = 0; i < l1.size(); ++i) {
                if (l1.get(i).getName() != "EMPTY") {
                    JSONObject subjectSchedule = new JSONObject();
                    String start;
                    String end;
                    Integer aux = i + 8;
                    if (aux < 10) start = "0" + aux.toString() + ":00";
                    else start = aux.toString() + ":00";
                    aux = i + 9;
                    if (aux < 10) end = "0" + aux.toString() + ":00";
                    else end = aux.toString() + ":00";
                    subjectSchedule.put("subjectID", l1.get(i).getId());
                    subjectSchedule.put("day", day);
                    subjectSchedule.put("start", start);
                    subjectSchedule.put("end", end);
                    scheduleList.put(subjectSchedule);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
