package com.example.charlee.whatengineer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

public class Question extends MainActivity implements View.OnClickListener {
    private int ID;
    private String QUESTION;
    private String ANSWER;
    public Question(String s)
    {
        ID=0;
        QUESTION="";
        ANSWER="";
    }
    public Question(String qUESTION, String aNSWER) {

        QUESTION = qUESTION;
        ANSWER = aNSWER;
    }

    public Question() {

    }

    public int getID()
    {
        return ID;
    }
    public String getQUESTION() {
        return QUESTION;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id)
    {
        ID=id;
    }
    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }
    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);

        //* *EDIT* *
        ListView listview = (ListView) findViewById(R.id.scitypelist);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, TypeInformation.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }







    @Override
    public void onClick(View v) {

    }
}

