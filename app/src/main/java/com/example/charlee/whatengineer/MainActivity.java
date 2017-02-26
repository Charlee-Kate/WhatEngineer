package com.example.charlee.whatengineer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;



public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.charlee.whatengineer.MESSAGE";



    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.quizpage);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, SurveyActivity.class);
                startActivity(intent);

            }

        });
    }
    /*  public void onItemClick(AdapterView<?> l, View v, int position, long id) {
          Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
          // Then you start a new Activity via Intent
          Intent intent = new Intent();
          intent.setClass(this, TypeInformation.class);
          intent.putExtra("position", position);
          intent.putExtra("id", id);
          startActivity(intent);
      }*/
    public void viewInfo(View view) {
        setContentView(R.layout.informationtemplate);
        Intent intent = new Intent(this, TypeInformation.class);
        startActivity(intent);
       // ListView listview = (ListView) findViewById(R.id.listView1);
        //listview.setOnItemClickListener((AdapterView.OnItemClickListener) this);

    }

    public void startQuiz(View view) {
        setContentView(R.layout.questiontemplate);
       // Intent intent = new Intent();
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);

       }

    public void mainMenu(View view) {
        setContentView(R.layout.menu);
    }








}



