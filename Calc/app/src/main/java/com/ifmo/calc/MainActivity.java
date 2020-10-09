package com.ifmo.calc;

import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float Results[]=new float[4];
    EditText first, second;
    float a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView(R.id.Label1,"Первое число",16);
        first =initEditText(R.id.FirstDigit,"",16);
         first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                a=Float.parseFloat(first.getText().toString());
            }
        });
        initTextView(R.id.Label2,"Второк число",16);
        second =initEditText(R.id.SecondDigit,"",16);
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                b=Float.parseFloat(second.getText().toString());
                Results[0]=a+b;
                Results[1]=a-b;
                Results[2]=a*b;
                Results[3]=a/b;
            }
        });
        for(int i=0;i<4;i++)
            initListView(R.id.Result,i);
    }
    TextView initTextView(int id, String text, float size){
        TextView tv=findViewById(id);
        tv.setTextSize(size);
        tv.setText(text);
        tv.setGravity(Gravity.LEFT);
        tv.getEditableText();
        return tv;
    }

    EditText initEditText(int id, String text, float size){
        EditText ed=findViewById(id);
        ed.setTextSize(size);
        ed.setText(text);
        return ed;
    }

    ListView initListView(int id, int arrayNum)
    {
        ListView lv=findViewById(id);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Results[arrayNum]));
        return  lv;
    }
}
