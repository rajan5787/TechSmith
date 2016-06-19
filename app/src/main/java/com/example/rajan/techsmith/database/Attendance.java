package com.example.rajan.techsmith.database;

import com.orm.SugarRecord;

/**
 * Created by rajan on 21/2/16.
 */
public class Attendance extends SugarRecord {
    public long student_ID;
    public String student_name;
    public boolean isPresent;
    public String image;
    public boolean isCheckout;

    public Attendance() {

    }

    public Attendance(long ID, String name,boolean isPresent,String image,boolean isCheckout) {
        this.student_ID = ID;
        this.student_name  = name;
        this.isPresent = isPresent;
        this.image = image;
        this.isCheckout = isCheckout;
    }
}
