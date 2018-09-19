package com.m.liu.studyapp.haveclass;

/**
 * Created by luculent on 2018/9/13.
 */

public class HaveLessons {

    private String teacherName_over;
    private String studentName_over;
    private String classes_over;
    private String startTime_over;
    private String endTime_over;
    private String teachButton;

    public HaveLessons(String teacherName, String studentName, String classes, String startTime, String endTime){
        this.teacherName_over = teacherName;
        this.studentName_over = studentName;
        this.classes_over = classes;
        this.startTime_over = startTime;
        this.endTime_over = endTime;
    }
    public String getTeacherName(){
        return teacherName_over;
    }

    public String getStudentName() {
        return studentName_over;
    }

    public String getClasses() {
        return classes_over;
    }

    public String getStartTime() {
        return startTime_over;
    }

    public String getEndTime() {
        return endTime_over;
    }

    public String getTeachButton() {
        return teachButton;
    }
}
