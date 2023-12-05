package com.example.FirstGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoosingScreen extends AppCompatActivity {

    Button butonStart;
    String takim = "1";

    TextView textTakim1Skor;
    TextView textTakim2Skor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_screen);

        textTakim1Skor=findViewById(R.id.textTakim1Skor);
        textTakim2Skor=findViewById(R.id.textTakim2Skor);

        //singleton deger alma

        if (DataSingleton.getInstance().getTakim()==null){
            takim="1";
        }else {
            takim=DataSingleton.getInstance().getTakim();
        }


            textTakim1Skor.setText(String.valueOf(DataSingleton.getInstance().getSkor1()));
            textTakim2Skor.setText(String.valueOf(DataSingleton.getInstance().getSkor2()));


        butonStart=findViewById(R.id.ButonStart);
        butonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChoosingScreen.this,PlayScreen.class);
                startActivity(intent);
            }

        });
    }
}