package com.itmo.filework;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    char[] c={'A','A','A','A','A'};
    String text;
    String lines[]=new String[15];
    String words=
            "Look at them\n" +
                    "They come to this place\n" +
                    "when they know they are not pure.\n" +
                    "Tenno use the keys\n" +
                    "But they are mere trespassers.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.wrFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(words);
            }
        });

        findViewById(R.id.rdFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=charToString(readFile());
                lines=textToLines(text);
                for(int i=0;i<textToLines(text).length;i++)
                {
                    TextInit(lines[i],28);
                }
                Log.d("@@@@@@@@@@@@@@ ","Text read");
            }
        });

        text=charToString(c);
        Log.d("@@@@@@@@@@@@@@ ",text);
    }

    void writeFile(String text){
        File f=new File(Environment.getExternalStorageDirectory()+"/myfile.txt");

        try {
            FileWriter w=new FileWriter(f);
            w.append(text);
            Log.d("@@@@@@@@@@@@@@ ","Text written");
            w.flush();
            w.close();
        } catch (IOException e) {
            Log.d("@@@@@@@@@@@@@@ ","ERROR");
            e.printStackTrace();
    }
    }

    char[] readFile(){
        File f=new File(Environment.getExternalStorageDirectory()+"/myfile.txt");
        char buf[]=new char[10];
        try {
            FileReader r=new FileReader(f);
            r.read(buf);
            r.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf;
    }

    String charToString(char[] text)
    {
        String res="";
        for (int i=0;i<text.length;i++)
        {
            res+=text[i];
        }
        return res;
    }

    String[] textToLines(String text)
    {
        int linesNum=1;
        int buf=0;
        for (int i=0;i<text.length();i++)
        {

            if(text.charAt(i)=='\n')
                linesNum++;
        }
        String[] lines = new String[linesNum];
        for (int i=0;i<lines.length;i++)
            for (int j=buf;j<text.length();j++)
            {
                if(text.charAt(j)!='\n')
                {
                    lines[i]+=text.charAt(j);

                }
                else
                {
                    buf=i+1;
                    break;
                }
            }
        return lines;
    }
    TextView TextInit(String text, int size)
    {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(size);
        return tv;

    }
}
