package com.example.FirstGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;

public class StartScreen extends AppCompatActivity {
    Button buttonStart;

    List<WordsObject> wordsObjectList=new ArrayList<>();
    List<String> mainWordsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        //uygulamaya ilk ve ikinci girişteki kveri kontrolleri sağlama
        // girişte , shared prefe-> küçük verileri teleofunun hafızasında saklar, uygulamayı silmedikçe saklanır,

        SharedPreferences sharedPreferences=getSharedPreferences("MySharedPre",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        //uygulamaya ilk kez mi girildi sorgusu
        boolean firstTime=sharedPreferences.getBoolean("firstTime",true);

        if (firstTime){
            editor.putBoolean("firstTime",false);
            editor.apply();
        }



        buttonStart= findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoChoosing= new Intent(StartScreen.this,ChoosingScreen.class);
                startActivity(intentGoChoosing);
            }
        });

        // example object
        /*
        WordsObject object1= new WordsObject("TURKIYE","Baklava");
        WordsObject object2= new WordsObject("ISTANBUL","Uskudar");
        WordsObject object3= new WordsObject("IZMIR","Saat Kulesi");

        wordsObjectList.add(object1);
         wordsObjectList.add(object2);
         wordsObjectList.add(object3);

        for (int i = 0; i < wordsObjectList.size(); i++) {
            mainWordsList.add(wordsObjectList.get(i).getMainWord());
        }
*/

    }
}