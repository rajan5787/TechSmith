package com.example.rajan.techsmith.database;

import com.example.rajan.techsmith.adapter.Select_student_adapter;
import com.orm.SugarRecord;

/**
 * Created by rajan on 16/6/16.
 */
public class Studentlist extends SugarRecord {
    public long student_ID;
    public String student_name;
    public boolean student_select;
    public String student_image;
    public Studentlist() {

    }

    public Studentlist(long ID,String name,String image,Boolean select) {
        this.student_ID = ID;
        this.student_name = name;
        this.student_select = select;
        this.student_image = image;
    }
}
