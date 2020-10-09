package com.itmo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int cookie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView m=findViewById(R.id.counter);
        m.setTextSize(28);
        m.setText("Всего печенек: "+cookie);

        Button btn=findViewById(R.id.cookie);
        btn.setBackgroundResource(R.drawable.cookie);
        btn.setGravity(Gravity.CENTER);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookie++;
                m.setText("Всего печенек: "+cookie+"$");
            }
        });

        Button buy =

        Timer tm=new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                cookie-=new Random().nextInt(100)+1;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m.setText("Всего заработано: "+cookie+"$");
                    }
                });

            }
        },500,500);
    }
}
