package com.itmo.acounts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    String users[][]={
            {"user","123"},
            {"admin","1234"}
    };

    int itemCount[],userIndex=0;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        pref=getSharedPreferences("mydata",MODE_PRIVATE);
        itemCount=new int[users.length];
        itemCount[0]=pref.getInt(users[0][0],0);
        itemCount[1]=pref.getInt(users[1][0],0);

        final EditText l,p;
        l=initEdit(R.id.login,"Логин",20);
        p=initEdit(R.id.pass,"Пароль",20);

        initButton(R.id.ok,"OK",20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout aouth=findViewById(R.id.auth),
                        work=findViewById(R.id.work);
                work.setVisibility(View.GONE);

                boolean ok=false;
                if(l.getText().toString().length()>0 &&
                p.getText().toString().length()>0){

                    for(int i=0;i<users.length;i++)
                        if(l.getText().toString().equals(users[i][0]) &&
                            p.getText().toString().equals(users[i][1])){
                            work.setVisibility(View.VISIBLE);
                            aouth.setVisibility(View.GONE);
                            ok=true;
                            userIndex=i;
                            loadData();
                            break;
                        }

                        if(ok==false)showMessage("Внимание","Неправильный логин или пароль");

                }else
                    showMessage("Внимание","Поля не могут быть пустыми");
            }
        });

        initButton(R.id.add,"Дoбавить",30).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCount[userIndex]++;

                loadData();
                SharedPreferences.Editor ed=pref.edit();
                ed.putInt(users[userIndex][0],itemCount[userIndex]);
                ed.apply();
            }
        });
    }

    void loadData(){
        String itemsText[]=new String[itemCount[userIndex]];
        for(int i=0;i<itemsText.length;i++)
            itemsText[i]="Деталь "+(i+1);
        initList(itemsText);
    }
    void initList(String text[]){
        ListView lv=findViewById(R.id.itemslist);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,text));
    }

    void showMessage(String title,String text){
        AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
        a.setTitle(title);
        a.setMessage(text);
        a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        a.show();
    }

    EditText initEdit(int id,String text,int ts){
        EditText a=findViewById(id);
        a.setHint(text);
        a.setGravity(Gravity.CENTER);
        a.setTextSize(ts);
        return a;
    }

    Button initButton(int id, String text, int ts){
        Button a=findViewById(id);
        a.setGravity(Gravity.CENTER);
        a.setTextSize(ts);
        a.setText(text);
        return a;
    }
}
