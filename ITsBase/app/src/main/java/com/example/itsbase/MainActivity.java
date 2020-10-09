package com.example.itsbase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=this.openOrCreateDatabase("data",MODE_PRIVATE,null);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("CREATE TABLE IF NOT EXISTS student(fio TEXT,money INTEGER);");
                db.execSQL("INSERT INTO student VALUES('Ivanov A.S.', 2000);");
                db.execSQL("INSERT INTO student VALUES('MIKHAILOV S.S.', 2500);");
                db.execSQL("INSERT INTO student VALUES('VOITEKHOVSKY W.Z.', 5500);");
            }
        });

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.isOpen()==true){
                    Cursor c=db.rawQuery("SELECT * FROM student;",null);
                    if(c.moveToFirst())
                        do Log.d("################ ",c.getString(0)+" "+c.getInt(1));while(c.moveToNext());

                }
            }
        });
    }
}
