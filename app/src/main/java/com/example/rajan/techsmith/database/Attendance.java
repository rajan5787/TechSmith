package com.example.rajan.techsmith.database;

import com.orm.SugarRecord;

/**
 * Created by rajan on 21/2/16.
 */
public class Attendance extends SugarRecord {
    public long student_ID;
    public boolean isPresent;
    public String image;
    public Attendance() {

    }

    public Attendance(long ID, boolean isPresent,String image) {
        this.student_ID = ID;
        this.isPresent = isPresent;
        this.image = image;
    }
}
