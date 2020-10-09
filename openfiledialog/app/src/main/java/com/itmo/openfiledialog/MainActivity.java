package com.itmo.openfiledialog;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    ListView lv;
    Button back, ret, save ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        ret = findViewById(R.id.ret);
        ret.setVisibility(View.GONE);
        save = findViewById(R.id.save);
        save.setVisibility(View.GONE);
        lv=findViewById(R.id.filelist);
        ed=findViewById(R.id.edit);
        ed.setVisibility(View.GONE);
        File f=new File(Environment.getExternalStorageDirectory()+"/download");
        initFileList(f);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lv.getVisibility()== View.GONE)
                lv.setVisibility(View.VISIBLE);
                ed.setVisibility(View.GONE);
                ret.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);
                save.setVisibility(View.GONE);
            }
        });
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lv.getVisibility()== View.VISIBLE)
                lv.setVisibility(View.GONE);
                ed.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                ret.setVisibility(View.GONE);
            }
        });
        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileWriter fw = new FileWriter(f+"/"+f.list()[lv.getSelectedItemPosition()]);

            }
        });*/
    }

    void initFileList(final File f){
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                f.list()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                char buf[]=new char[(int)f.length()];
                try {
                    FileReader fs=new FileReader(f+"/"+f.list()[position]);
                    fs.read(buf);
                    ed.setText(buf,0,buf.length);
                    fs.close();

                    ed.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                    back.setVisibility(View.VISIBLE);
                    ret.setVisibility(View.GONE);
                    save.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
