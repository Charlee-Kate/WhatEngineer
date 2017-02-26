package com.example.charlee.whatengineer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TypeInformation extends MainActivity {
    //OkHttpClient client, we will use this to make our HTTP requests.
    private final OkHttpClient client = new OkHttpClient();


    public void setType(int position) throws Exception {
        //Adding a post form

        RequestBody formBody = new FormBody.Builder()
                .add("position", String.valueOf(position))
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.0.20/infotype.php")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                String responseString = response.body().string();
                JSONArray jsonarray = null;
                try {
                    jsonarray = new JSONArray(responseString);
                    JSONObject firstRow = jsonarray.getJSONObject(0);
                    final String keySkill = firstRow.getString("key_skill");
                    //Running on UI thread so we can change the text view.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView myTextView = (TextView) findViewById(R.id.infohead);
                            myTextView.setText(keySkill);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informationtemplate);
        ListView sciTypeList = (ListView) findViewById(R.id.scitypelist);
        sciTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                setContentView(R.layout.informationpage);
                try {
                    setType(position + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

