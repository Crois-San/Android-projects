package com.itmo.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String login;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView(R.id.login_label,"Логин",28);
       /* EditText log=*/initEditText(R.id.login,"",28);
        initTextView(R.id.pass_label,"Пароль",28);
        /*EditText pass*/initEditText(R.id.pass,"",28);
        initButton(R.id.ok,"Ok",28).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String login,password;
                EditText log =findViewById(R.id.login);
                EditText pass =findViewById(R.id.pass);
                login=log.getText().toString();
                password=pass.getText().toString();

                if(login.equals("Ortyom") && password.equals("1234567890"))
                {
                    findViewById(R.id.ava1).setBackgroundResource(R.drawable.ava1);
                    initTextView(R.id.label1,"Ортем Голинский",28);
                }
                else if(login.equals("Crois-San") && password.equals("bitnotmaker"))
                {
                    findViewById(R.id.ava1).setBackgroundResource(R.drawable.ava2);
                    initTextView(R.id.label1,"Ортем(нет)",28);
                }
                else
                {
                    initTextView(R.id.error,"Пользователь не найден.",26);
                }
            }
        });
        }



    TextView initTextView(int id,String text,float size){
        TextView tv=findViewById(id);
        tv.setTextSize(size);
        tv.setText(text);
        tv.setGravity(Gravity.LEFT);
        tv.getEditableText();
        return tv;
    }

    Button initButton(int id,String text,float size){
        Button btn=findViewById(id);
        btn.setTextSize(size);
        btn.setText(text);
        return btn;
    }

    EditText initEditText(int id, String text, float size){
        EditText ed=findViewById(id);
        ed.setTextSize(size);
        ed.setText(text);
        return ed;
    }
}
