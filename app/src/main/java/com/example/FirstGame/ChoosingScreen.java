package com.example.FirstGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosingScreen extends AppCompatActivity {

    Button butonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_screen);

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