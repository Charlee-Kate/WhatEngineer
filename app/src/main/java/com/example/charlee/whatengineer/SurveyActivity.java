package com.example.charlee.whatengineer;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.util.TypedValue;
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

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.FormBody;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.RequestBody;
        import okhttp3.Response;

        import static android.R.attr.name;
        import static com.example.charlee.whatengineer.R.id.add;

public class SurveyActivity extends MainActivity implements OnSeekBarChangeListener{
    private final OkHttpClient client = new OkHttpClient();
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
    int[] surveyResults = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int[][] top3 = new int[3][2];
    int userid = 1;
    int resultid = 1;
    int type1 = top3[0][0];
    int match1 =top3[0][1];
    int type2=top3[1][0];
    int match2=top3[1][1];
    int type3 = top3[2][0];
    int match3=top3[2][1];

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
                // store value from final value into array
                surveyResults[qid-1]=progressval;

               // Log.i("this is my array", "arr: " + Arrays.toString(surveyResults));
                if (qid < questionlist.size()) {
                    setQuestionView();


                }
                else {
                    //manipulate survey results here
                    SurveyAlgorithm();
                    try {
                        if (android.os.Build.VERSION.SDK_INT > 9)
                        {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }
                        postResult(userid, resultid, type1, match1, type2, match2, type3, match3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //  Intent intent = new Intent(context, ResultActivity.class);
                    //startActivity(intent);
                }
            }
        });

    }

    public void SurveyAlgorithm() {

        //iterate through array and add corresponding pairs of answers
        // store these 10 values in array
        // use algorithm to take highest numbers
        //use initial position to work out what scientists these are
        int val1 = 0;
        int val2 = 0;
        int addv = 0;
        int[] surveyAnalysis = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < surveyResults.length - 1; i += 2) {
            val1 = surveyResults[i];
            val2 = surveyResults[i + 1];
            addv = val1 + val2;
            if (addv != 0) {
                surveyAnalysis[i / 2] = addv;
            }
            addv = 0;
        }

        int[][] values = new int[10][2];
        for (int i = 0; i < 10; i++) {
            values[i][0] = surveyAnalysis[i];
        }
        for (int j = 0; j < 10; j++) {
            values[j][1] = j;
        }

        System.out.println(Arrays.deepToString(values));

//Arrays.sort(values);
        java.util.Arrays.sort(values, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });


        System.out.println(Arrays.deepToString(values));
        //System.out.println(values.length);
        // Arrays.sort(surveyAnalysis);
        int[][] top3 = new int[3][2];
        int k = 0;
        for (int j = 9; j > 6; j--) {

            //value of combined score returned as percentage /20 max score *100

            top3[k][0] = (values[j][0]) * 5;
            k++;

        }
        int m = 0;
        for (int j = 9; j > 6; j--) {


            //type_id, so need to add 1 works correctly
            top3[m][1] = (values[j][1]) + 1;
            m++;

        }
        type1 = top3[0][1];
        match1 =top3[0][0];
        type2=top3[1][1];
        match2=top3[1][0];
        type3 = top3[2][1];
        match3=top3[2][0];
        // System.out.println(Arrays.deepToString(top3));

        // top 3 array contains type 1, match %, type 2, match %, type 3, match %

        // to go into php script

        // userid, resultid (incremental number), top3(type 1, match %, type 2, match %, type 3, match %)

    }

    public void postResult(int userid, int resultid, int type1, int match1, int type2, int match2, int type3, int match3) throws Exception {
        //Adding a post form
        System.out.println(userid);
        System.out.println(resultid);
        System.out.println(type1);
        System.out.println(match1);
        RequestBody formBody = new FormBody.Builder()
                .add("user_ID", String.valueOf(userid))
                .add("result_ID", String.valueOf(resultid))
                .add("type_ID", String.valueOf(type1))
                .add("match1", String.valueOf(match1))
                .add("type_ID2", String.valueOf(type2))
                .add("match2", String.valueOf(match2))
                .add("type_ID3", String.valueOf(type3))
                .add("match3", String.valueOf(match3))
                .build();


        Request request = new Request.Builder()
                .url("http://192.168.0.15/test4.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

    }


            // Log.i("this is my new array ", "arr: " + Arrays.toString(surveyAnalysis));



    public int getCorrespondingIndex(int[] unsortedArray, int[] sortedArray, int index) {
        for (int i = 0; i < unsortedArray.length; i++) {
            if (sortedArray[index] == (unsortedArray[i])) {
                return i;

            }

        } return -1;
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




