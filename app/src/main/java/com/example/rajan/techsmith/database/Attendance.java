package com.example.rajan.techsmith.database;

import com.orm.SugarRecord;

/**
 * Created by pradeet on 21/2/16.
 */
public class Attendance extends SugarRecord {
    public long student_ID;
    public boolean isPresent;
    public Attendance() {

    }

    public Attendance(long ID, boolean isPresent) {
        this.student_ID = ID;
        this.isPresent = isPresent;
    }
}
