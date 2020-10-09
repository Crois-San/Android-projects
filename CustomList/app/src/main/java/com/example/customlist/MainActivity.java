package com.example.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList(new String[]{"Item 1","Item 2","Item 3","Item 4","Item 5",
                "Item 6","Item 7","Item 8","Item 9",
                "Item 10","Item 11","Item 12","Item 13",
                "Item 14","Item 15","Item 16","Item 17",
                "Item 18","Item 19","Item 20","Item 21",});
    }

    void initList(String text[]){
        LinearLayout lay=findViewById(R.id.listLayout);

        for(int i=0;i<text.length;i++){
            LinearLayout sub=new LinearLayout(this);
            sub.addView(initCheckBox(text[i],32));
            sub.addView(initImage(R.drawable.pumpkin));
            lay.addView(sub);
        }
    }

    CheckBox initCheckBox(String text,float textSize){
        CheckBox a=new CheckBox(this);
        a.setText(text);
        a.setTextSize(textSize);
        a.setGravity(Gravity.LEFT);
        a.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1));
        return a;
    }

    ImageView initImage(int imgid){
        ImageView a=new ImageView(this);
        a.setImageResource(imgid);
        return a;
    }
}
