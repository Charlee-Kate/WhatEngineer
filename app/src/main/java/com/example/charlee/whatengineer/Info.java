package com.example.charlee.whatengineer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Info extends MainActivity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.informationtemplate);

        final Button button = (Button) findViewById(R.id.infopage);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.informationtemplate);
            }
        });
    }
}
