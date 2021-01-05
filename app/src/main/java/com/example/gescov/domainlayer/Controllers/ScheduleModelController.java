package com.example.gescov.domainlayer.Controllers;

import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.domainlayer.Singletons.DomainControlFactory;
import com.example.gescov.domainlayer.Singletons.ServicesFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public void getSchedule(String classID) {

        ServicesFactory.getClassroomService().getSchedule(classID);
    }

    public void sendResponseOfSchedule(JSONArray response) {
        List<Subject> l1,l2,l3,l4,l5;
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        l3 = new ArrayList<>();
        l4 = new ArrayList<>();
        l5 = new ArrayList<>();
        for(int i = 0; i < 9; ++i) {
            Subject empty = new Subject("EMPTY","EMPTY");
            l1.add(empty);
            l2.add(empty);
            l3.add(empty);
            l4.add(empty);
            l5.add(empty);
        }

        for (int i = 0; i < response.length(); ++i) {

            try {
                JSONObject object = response.getJSONObject(i);
                String subjectName = object.getString("first");
                JSONObject subjectInfo = object.getJSONObject("second");
                String subjectID = subjectInfo.getString("subjectID");
                String start = subjectInfo.getString("start");
                String day = subjectInfo.getString("day");
                int pos = Integer.parseInt(start.substring(0,2)) - 8;
                assignToList(pos, l1, l2 ,l3, l4, l5,day,subjectID,subjectName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        DomainControlFactory.getModelController().sendResponseOfSchedule(l1,l2,l3,l4,l5);


    }

    private void assignToList(int pos, List<Subject> l1, List<Subject> l2, List<Subject> l3, List<Subject> l4, List<Subject> l5,
                              String day,String subjectID, String subjectName) {
        Subject element = new Subject(subjectName,subjectID);
        switch (day) {
            case "MONDAY":
                l1.add(pos ,element);
                break;
            case "TUESDAY":
                l2.add(pos ,element);
                break;
            case "WEDNESDAY":
                l3.add(pos ,element);
                break;
            case "THURSDAY":
                l4.add(pos ,element);
                break;
            case "FRIDAY":
                l5.add(pos ,element);
                break;
            default:
                System.out.println("error");
        }

    }
}
