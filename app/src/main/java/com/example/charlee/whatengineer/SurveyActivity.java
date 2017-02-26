package com.example.charlee.whatengineer;

        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import android.widget.Button;
        import android.view.View.OnClickListener;
        import android.widget.Toast;
        import android.widget.SeekBar;
        import android.widget.SeekBar.OnSeekBarChangeListener;
        import android.widget.Toast;

public class SurveyActivity extends MainActivity implements OnSeekBarChangeListener{
    public ArrayList<Integer> questionlist = new ArrayList<>();
    public ArrayList<Integer> quesnum = new ArrayList<>();
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion;
    TextView quesnumber;
    TextView finalanswer;
    Button butNext;

    int progressval = 0;

    Button button;
    SeekBar seekBar1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questiontemplate);
        questionlist.add(R.string.statement1);
        questionlist.add(R.string.statement2);
        questionlist.add(R.string.statement3);
        questionlist.add(R.string.statement4);
        questionlist.add(R.string.statement5);
        questionlist.add(R.string.statement6);
        questionlist.add(R.string.statement7);
        questionlist.add(R.string.statement8);
        questionlist.add(R.string.statement9);
        questionlist.add(R.string.statement10);
        questionlist.add(R.string.statement11);
        questionlist.add(R.string.statement12);
        questionlist.add(R.string.statement13);
        questionlist.add(R.string.statement14);
        questionlist.add(R.string.statement15);
        questionlist.add(R.string.statement16);
        questionlist.add(R.string.statement17);
        questionlist.add(R.string.statement18);
        questionlist.add(R.string.statement19);
        questionlist.add(R.string.statement20);
        quesnum.add(R.string.question_1_20);
        quesnum.add(R.string.question_2_20);
        quesnum.add(R.string.question_3_20);
        quesnum.add(R.string.question_4_20);
        quesnum.add(R.string.question_5_20);
        quesnum.add(R.string.question_6_20);
        quesnum.add(R.string.question_7_20);
        quesnum.add(R.string.question_8_20);
        quesnum.add(R.string.question_9_20);
        quesnum.add(R.string.question_10_20);
        quesnum.add(R.string.question_11_20);
        quesnum.add(R.string.question_12_20);
        quesnum.add(R.string.question_13_20);
        quesnum.add(R.string.question_14_20);
        quesnum.add(R.string.question_15_20);
        quesnum.add(R.string.question_16_20);
        quesnum.add(R.string.question_17_20);
        quesnum.add(R.string.question_18_20);
        quesnum.add(R.string.question_19_20);
        quesnum.add(R.string.question_20_20);

        quesnumber= (TextView)findViewById(R.id.question1number) ;
        txtQuestion=(TextView)findViewById(R.id.question1text);
        finalanswer=(TextView)findViewById(R.id.finalanswer);
        butNext=(Button)findViewById(R.id.nextq);

        seekBar1=(SeekBar)findViewById(R.id.seekBar1);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar1.setMax(10);
        seekBar1.setProgress(5);


        setQuestionView();
        listenbutton();

    }
    private void setQuestionView() {

        txtQuestion.setText(questionlist.get(qid));
        quesnumber.setText(quesnum.get(qid));
        qid++;
    }

    public void listenbutton() {

        final Context context = SurveyActivity.this;

     // button = (Button) findViewById(R.id.nextq);

        butNext.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0)
            {
                if (qid < questionlist.size()) {
                    setQuestionView();
                }
                else {
                    Intent intent = new Intent(context, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
      // finalanswer=(TextView)findViewById(R.id.finalanswer);
        progressval = progress;
        finalanswer.setText("" + progress);

  //  Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
       // Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
      //  Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();


        //int seekBarValue= seekBar1.getProgress();

       // finalanswer.setText(seekBarValue);
    }
                       /* public void onClick(View v) {
                if (qid < questionlist.size()) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(SurveyActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    startActivity(intent);
                    finish();
                }
            }*/



    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        questionlist.add(R.string.statement1);
        questionlist.add(R.string.statement2);
        questionlist.add(R.string.statement3);
        txtQuestion=(TextView)findViewById(R.id.question1text);
        fa=(Button)findViewById(R.id.finalanswer);
        butNext=(Button)findViewById(R.id.nextq);

        setQuestionView();
        //(R.string.statement1);

        //[R.string.statement1,R.string.statement2,R.string.statement3]
        /*setContentView(R.layout.questiontemplate);
        DBHelper db=new DBHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.question1text);
        fa=(Button)findViewById(R.id.finalanswer);
        butNext=(Button)findViewById(R.id.nextq);
        setQuestionView();


    }

    private void setQuestionView() {

        txtQuestion.setText(questionlist.get(qid));
        qid++;
    }
    //private void setQuestionView()
    //{
    //txtQuestion.setText(currentQ.getQUESTION());

    //  qid++;
    //}


    public void onClick(View v) {
        if (qid < 20) {
            currentQ = quesList.get(qid);
            setQuestionView();
        } else {
            Intent intent = new Intent(SurveyActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            startActivity(intent);
            finish();
        }
    }

*/


}




