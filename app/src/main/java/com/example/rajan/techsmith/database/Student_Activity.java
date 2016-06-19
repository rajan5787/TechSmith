package com.example.rajan.techsmith.database;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by rajan on 19/6/16.
 */
public class Student_Activity extends SugarRecord {

    public String student_PHOTO;
    public String time;
    public ArrayList<Long> students_ID;

    public Student_Activity() {
    }

    public Student_Activity(String student_PHOTO, String time,ArrayList<Long> students_ID) {
        this.student_PHOTO = student_PHOTO;
        this.time = time;
        this.students_ID = students_ID;
    }


}
