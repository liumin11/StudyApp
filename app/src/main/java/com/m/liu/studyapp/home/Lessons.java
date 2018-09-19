package com.m.liu.studyapp.home;

/**
 * Created by luculent on 2018/9/13.
 */

public class Lessons {

    private String teacherName;
    private String studentName;
    private String classes;
    private String startTime;
    private String endTime;
    private String teachButton;

    public Lessons(String teacherName,String studentName,String classes,String startTime, String endTime){
        this.teacherName = teacherName;
        this.studentName = studentName;
        this.classes = classes;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getTeacherName(){
        return teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClasses() {
        return classes;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTeachButton() {
        return teachButton;
    }
}
