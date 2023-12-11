package com.example.FirstGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    TextView teamNameText;
    Button playAgainButton;

    int skor1;
    int skor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        teamNameText=findViewById(R.id.textTeamName);
        playAgainButton=findViewById(R.id.TekrarOynaButonu);

        skor1=DataSingleton.getInstance().getSkor1();
        skor2=DataSingleton.getInstance().getSkor2();

        if (skor1>=10){
            teamNameText.setText(getResources().getString(R.string.kazananTeam1));
        } else if (skor2>=10) {
            teamNameText.setText(getResources().getString(R.string.kazananTeam2));
        }

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSingleton.getInstance().setSkor1(0);
                DataSingleton.getInstance().setSkor2(0);
                DataSingleton.getInstance().setTakim("1");

                Intent intent= new Intent(ResultScreen.this,ChoosingScreen.class);
                startActivity(intent);
            }
        });

    }
}