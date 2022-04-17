package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton, button0, button1, button2, button3,PlayAgain;
    TextView resulttv, pointsTv,sumTv;
    ArrayList<Integer> jawaban = new ArrayList<Integer>();
    int lokasiJawabanBenar;
    int skor = 0;
    int banyakpertanyaan = 0;
    TextView timerTv;
    RelativeLayout gameRelativeLayout;

    public void  mainLagi(View view){

        skor = 0;
        banyakpertanyaan = 0;

        timerTv.setText("30s");
        pointsTv.setText("0/0");
        resulttv.setText("");
        PlayAgain.setVisibility(View.INVISIBLE);

        generatepertanyaan();

        new CountDownTimer(31000, 1000){

            @Override
            public void onTick(long miliSampeFinish) {
                timerTv.setText(String.valueOf(miliSampeFinish/1000+ "s"));
            }

            @Override
            public void onFinish() {

                PlayAgain.setVisibility(View.VISIBLE);
                timerTv.setText("0s");
                resulttv.setText("Skor Anda: "+ Integer.toString(skor)+ "/"+ Integer.toString(banyakpertanyaan));

            }
        }.start();

    }

    public void generatepertanyaan(){

        Random acak = new Random();

        int a = acak.nextInt(26);
        int b = acak.nextInt(26);

        sumTv.setText(Integer.toString(a) + " + "+ Integer.toString(b));

        lokasiJawabanBenar = acak.nextInt(4);

        jawaban.clear();

        int jawabanSalah;

        for (int i=0; i<4; i++){

            if (i == lokasiJawabanBenar){

                jawaban.add(a + b);
            }else {
                jawabanSalah = acak.nextInt(51);

                while (jawabanSalah == a + b){

                    jawabanSalah = acak.nextInt(51);
                }

                jawaban.add(jawabanSalah);

            }
        }

        button0.setText(Integer.toString(jawaban.get(0)));
        button1.setText(Integer.toString(jawaban.get(1)));
        button2.setText(Integer.toString(jawaban.get(2)));
        button3.setText(Integer.toString(jawaban.get(3)));

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        mainLagi(findViewById(R.id.btnMainLagi));
    }
    public void pilihJawaban(View view){

        if (view.getTag().toString().equals(Integer.toString(lokasiJawabanBenar))){

            skor++;
            resulttv.setText("Benar!");
        }else {
            resulttv.setText("Salah!");
        }

        banyakpertanyaan++;
        pointsTv.setText(Integer.toString(skor)+"/"+ Integer.toString(banyakpertanyaan));
        generatepertanyaan();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTv = findViewById(R.id.sumTv);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resulttv = findViewById(R.id.resultTv);
        pointsTv = findViewById(R.id.pointsTv);
        timerTv = findViewById(R.id.timerTv);
        PlayAgain = findViewById(R.id.btnMainLagi);
        gameRelativeLayout = findViewById(R.id.gameOn);
    }
}