package com.example.charlee.whatengineer;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WhatEngineer";
    private static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";

    static final String userTable ="Users";
    static final String colID = "User ID";
    static final String colName = "User name";

    static final String responseTable="Responses";
    static final String responseID ="Response ID";
    static final String userID ="User ID";
    static final String q1= "Question 1";
    static final String q2= "Question 2";
    static final String q3= "Question 3";
    static final String q4= "Question 4";
    static final String q5= "Question 5";
    static final String q6= "Question 6";
    static final String q7= "Question 7";
    static final String q8= "Question 8";
    static final String q9= "Question 9";
    static final String q10= "Question 10";
    static final String q11= "Question 11";
    static final String q12= "Question 12";
    static final String q13= "Question 13";
    static final String q14= "Question 14";
    static final String q15= "Question 15";
    static final String q16= "Question 16";
    static final String q17= "Question 17";
    static final String q18= "Question 18";
    static final String q19= "Question 19";
    static final String q20= "Question 20";
    static final String viewResult = "View Result";

    private SQLiteDatabase dbase;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST  +userTable +responseTable);
        onCreate(db);
    }

    //Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(KEY_QUES, quest.getQUESTION());
       // Inserting Row
        // dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
// return question list
        return quesList;
    }

    public int rowcount() {
        int row = 0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT))";
        db.execSQL(CREATE_QUES_TABLE);
        Question q1 = new Question("@String/statement1");
        this.addQuestion(q1);
        Question q2 = new Question("@String/statement2");
        this.addQuestion(q2);
        Question q3 = new Question("@String/statement3");
        this.addQuestion(q3);
        Question q4 = new Question("@String/statement4");
        this.addQuestion(q4);
        Question q5 = new Question("@String/statement5");
        this.addQuestion(q5);
        Question q6 = new Question("@String/statement6");
        this.addQuestion(q6);
        Question q7 = new Question("@String/statement7");
        this.addQuestion(q7);
        Question q8 = new Question("@String/statement8");
        this.addQuestion(q8);
        Question q9 = new Question("@String/statement9");
        this.addQuestion(q9);
        Question q10 = new Question("@String/statement10");
        this.addQuestion(q10);
        Question q11 = new Question("@String/statement11");
        this.addQuestion(q11);
        Question q12 = new Question("@String/statement12");
        this.addQuestion(q12);
        Question q13 = new Question("@String/statement13");
        this.addQuestion(q13);
        Question q14 = new Question("@String/statement14");
        this.addQuestion(q14);
        Question q15 = new Question("@String/statement15");
        this.addQuestion(q15);
        Question q16 = new Question("@String/statement16");
        this.addQuestion(q16);
        Question q17 = new Question("@String/statement17");
        this.addQuestion(q17);
        Question q18 = new Question("@String/statement18");
        this.addQuestion(q18);
        Question q19 = new Question("@String/statement19");
        this.addQuestion(q19);
        Question q20 = new Question("@String/statement20");
        this.addQuestion(q20);

        String CREATE_USER_TABLE = "CREATE TABLE" + userTable + "(" + colID + "INTEGER PRIMARY KEY," + colName + "TEXT)";
        db.execSQL(CREATE_USER_TABLE);


        String CREATE_RESPONSE_TABLE = " CREATE TABLE" + responseTable + "(" + responseID + "INTEGER PRIMARY KEY," + userID + "INTEGER," + q1 + "INTEGER," + q2 + "INTEGER," +q3 + "INTEGER," +q4 + "INTEGER," +q5 + "INTEGER," +q6 + "INTEGER," +q7 + "INTEGER," +q8 + "INTEGER," +q9 + "INTEGER," +q10 + "INTEGER," +q11 + "INTEGER," +q12 + "INTEGER," +q13 + "INTEGER," +q14 + "INTEGER," +q15 + "INTEGER," +q16 + "INTEGER," +q17 + "INTEGER," +q18 + "INTEGER," +q19 + "INTEGER," + q20 + "INTEGER)";
        db.execSQL(CREATE_RESPONSE_TABLE);


        db.close();
    }
}