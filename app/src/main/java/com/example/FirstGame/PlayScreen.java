package com.example.FirstGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayScreen extends AppCompatActivity {

    //kullanıcı girişi ya da güvenlik gerektiren durumlarda shared preferences yerine
    // daha farkllı uuid gibi sistemler kullanılabilir, shared pre. bilgileri tele kaydettiği için.
    // Gorsel ogeler
    TextView textSure;
    TextView textTakimAdi;
    ImageView imageDurdur;
    TextView textSkor;
    int skor=0;
    TextView textAnaKelime;
    TextView textKelime1;
    TextView textKelime2;
    TextView textKelime3;
    TextView textKelime4;
    TextView textKelime5;
    ImageView imagePas;
    ImageView imageTabu;
    ImageView imageDogru;
    TextView textKalanPas;

    int kalanPas=3;

    //Countdown timer için ogeler

    CountDownTimer countDownTimer;

    long timerLeftInMilles=10000;

    List<WordsObject> wordsList=new ArrayList<>();
    SQLiteDatabase db;

    String takim= "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        db =databaseHelper.getWritableDatabase();

        // activitye bağlama
        textSure=findViewById(R.id.textSure);
        textTakimAdi=findViewById(R.id.textTakimAdi);
        imageDurdur=findViewById(R.id.ImageDurdur);
        textSkor=findViewById(R.id.textSkor);
        textAnaKelime=findViewById(R.id.textAnaKelime);
        textKelime1=findViewById(R.id.textKelime1);
        textKelime2=findViewById(R.id.textKelime2);
        textKelime3=findViewById(R.id.textKelime3);
        textKelime4=findViewById(R.id.textKelime4);
        textKelime5=findViewById(R.id.textKelime5);
        imagePas=findViewById(R.id.ImagePas);
        imageTabu=findViewById(R.id.ImageTabu);
        imageDogru=findViewById(R.id.ImageDogru);
        textKalanPas=findViewById(R.id.textKalanPas);


        //singleton veri alma

       /* if (DataSingleton.getInstance().getTakim().equals("1") ){
            takim=DataSingleton.getInstance().getTakim();
            textTakimAdi.setText( textTakimAdi.getText()+"TAKIM 2");

        }
        else {
            takim="1";
            textTakimAdi.setText( textTakimAdi.getText()+"TAKIM 1");
        }*/

        if (DataSingleton.getInstance().getTakim()==null){
            takim="1";
            textTakimAdi.setText( textTakimAdi.getText()+"TAKIM 1");

        }
        else {
            takim=DataSingleton.getInstance().getTakim();
            textTakimAdi.setText( textTakimAdi.getText()+"TAKIM 2");
        }

        WordsObject word1=new WordsObject("İZMİR","Gevrek");
        WordsObject word2=new WordsObject("MANİSA","Şehzade");
        WordsObject word3=new WordsObject("KONYA","Mevlana");
        WordsObject word4=new WordsObject("İSTANBUL","Kız Kulesi");
        WordsObject word5=new WordsObject("SAMSUN","Pide");
        WordsObject word6=new WordsObject("AFYON","BÜKME");
        WordsObject word7=new WordsObject("ISPARTA","GÜL");
        WordsObject word8=new WordsObject("ERZURUM","Cağ Kebabı");

        wordsList.add(word1);
        wordsList.add(word2);
        wordsList.add(word3);
        wordsList.add(word4);
        wordsList.add(word5);
        wordsList.add(word6);
        wordsList.add(word7);
        wordsList.add(word8);

       /* long insertID= addWords(word1);
        if (insertID!=-1){
            //ekleme başarılı
            Toast.makeText(getApplicationContext(),"Kelime Ekleme Başarılı.",Toast.LENGTH_SHORT).show();
        }else {
            //ekleme başarısız
            Toast.makeText(getApplicationContext(),"Kelime Ekleme Başarısız.",Toast.LENGTH_SHORT).show();
        }*/

        //VERİ ALMA

        String query = "SELECT * FROM WordsObject";
        Cursor cursor= db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                //verileri cursor dan al
                String mainWord = cursor.getString(cursor.getColumnIndex("mainWord"));
                String tabooWord1 = cursor.getString(cursor.getColumnIndex("tabooWord1"));

                //kaydedilenleri alma

                WordsObject object = new WordsObject(mainWord,tabooWord1);
                wordsList.add(object);

            }while (cursor.moveToNext());
        }

        System.out.println("Liste Dolu!");




        textSkor.setText(String.valueOf(skor));
        textKalanPas.setText("Kalan Pas Sayısı : "+String.valueOf(kalanPas));

        //Countdown timer

        countDownTimer=new CountDownTimer(timerLeftInMilles,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int saniye=(int)(millisUntilFinished/1000)%60;
                textSure.setText(String.valueOf(saniye));

            }

            @Override
            public void onFinish() {

                //Singleton a veri alma

                if (takim.equals("1")){
                    DataSingleton.getInstance().setTakim("2");
                    int skor1Pre=DataSingleton.getInstance().getSkor1();
                    DataSingleton.getInstance().setSkor1(skor+skor1Pre);
                }
                else
                {
                    DataSingleton.getInstance().setTakim("1");
                    int skor2Pre=DataSingleton.getInstance().getSkor2();
                    DataSingleton.getInstance().setSkor2(skor+skor2Pre);
                }


              // onBackPressed();//onceki ekrana doner
                Intent intent=new Intent(PlayScreen.this,ChoosingScreen.class);
                startActivity(intent);
            }
        }.start();

        imageDogru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skor ++;
                textSkor.setText(String.valueOf(skor));

                //ana kelime ve tabu kelimlerini değiştirme
                textAnaKelime.setText(wordsList.get(1).getMainWord());
                textKelime1.setText(wordsList.get(1).getTabooWord1());

            }


        });

        imageTabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skor--;
                textSkor.setText(String.valueOf(skor));

                //ana kelime ve tabu kelimlerini değiştirme

                textAnaKelime.setText(wordsList.get(2).getMainWord());
                textKelime1.setText(wordsList.get(2).getTabooWord1());
            }
        });

        imagePas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kalanPas==0){
                    Toast.makeText(getApplicationContext(),"Pas Hakkınız Bitti!!!",Toast.LENGTH_LONG).show();
                } else if (kalanPas>0) {
                    kalanPas--;
                    textKalanPas.setText(String.valueOf("Kalan Pas Sayısı : "+kalanPas));
                    //ana kelime ve tabu kelimlerini değiştirme
                    textAnaKelime.setText(wordsList.get(0).getMainWord());
                    textKelime1.setText(wordsList.get(0).getTabooWord1());

                }
            }
        });

        imageDurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

    }

    //veri kaydı methodu

    public long addWords(WordsObject word){

        ContentValues contentValues=new ContentValues();
        contentValues.put("mainWord",word.getMainWord());
        contentValues.put("tabooWord1",word.getTabooWord1());

        long id  = db.insert("WordsObject",null,contentValues);

        return id;// ekleme işleminin olup olmadığını anlamak için.
    }
}