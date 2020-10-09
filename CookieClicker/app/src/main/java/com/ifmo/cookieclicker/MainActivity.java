package com.ifmo.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int cookie;
    int[] cursor =new int[2];
    int[] granny =new int[2];
    int[] factory = new int[2];
    int[] cat = new int[2];
    int[] time_machine= new int[2];
    int[] black_hole= new int[2];
    boolean goal=false;
    final int fsize=22;
    int page;
    String textData[][]={
            {"Количество курсоров: ","Купить курсор за "},
            {"Количество бабуль: ","Купить бабулю за "},
            {"Количество фабрик: ","Купить фабрику за "},
            {"Количество котов: ","Купить кота за "},
            {"Количество машин времени: ","Купить машину времени за "},
            {"Количество черную дыру: ","Купить черную дыру за "},


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView(R.id.label,"Набери миллиард печенек",fsize);
        final TextView m= initTextView(R.id.counter,"Всего печенек: "+cookie,fsize);
        final TextView curs = initTextView(R.id.cursors,textData[0][0]+cursor[0],fsize);
        final TextView gran= initTextView(R.id.grannys,textData[1][0]+ granny[0],fsize);
        final  TextView factor= initTextView(R.id.factorys,textData[2][0] + factory[0],fsize);
        initBtn(R.id.up,"вверх",fsize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page<1)
                    page++;
                else
                    page=0;
            }
        });
        initBtn(R.id.down,"вниз",fsize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>0)
                    page--;
                else
                    page=1;
            }
        });
        ImageView btn= findViewById(R.id.cookie);
        cursor[1]=25;
        granny[1]=1000;
        factory[1]=2500;
        //btn.setBackgroundResource(R.drawable.cookie);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookie++;
                m.setText("Всего печенек: "+cookie);
            }
        });

        final Button buy_curs = initBtn(R.id.buy_curs,textData[0][1]+ cursor[1] +" печенек",fsize);
        buy_curs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product Cursor = new Product(cookie,cursor[0],cursor[1]);
                Cursor.paymentProcessing(curs,buy_curs,textData[0][0],textData[0][1]);
                cookie=Cursor.getCookie();
                cursor[0]=Cursor.getProduct();
                cursor[1]=Cursor.getProdPrice();
            }
        });

        final Button buy_granny = initBtn(R.id.buy_granny,textData[1][1]+granny[1]+ " печенек",fsize);
        buy_granny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product Granny = new Product(cookie,granny[0],granny[1]);
                Granny.paymentProcessing(gran,buy_granny,textData[1][0],textData[1][1]);
                cookie=Granny.getCookie();
                granny[0]=Granny.getProduct();
                granny[1]=Granny.getProdPrice();
            }
        });
        final Button buy_factory =initBtn(R.id.buy_factory,textData[2][1]+factory[1]+" печенек",fsize);
        buy_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product Factory = new Product(cookie,factory[0],factory[1]);
                Factory.paymentProcessing(factor,buy_factory,textData[2][0],textData[2][1]);
                cookie=Factory.getCookie();
                factory[0]=Factory.getProduct();
                factory[1]=Factory.getProdPrice();
            }
        });

        final Button buy_cat= initBtn(R.id.buy_cat,textData[3][1]+cat[1],fsize);
        buy_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product Cat = new Product(cookie,factory[0],factory[1]);
                Cat.paymentProcessing(factor,buy_factory,textData[2][0],textData[2][1]);
                cookie=Cat.getCookie();
                factory[0]=Cat.getProduct();
                factory[1]=Cat.getProdPrice();
            }
        });


                Timer tm=new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                if(cursor[0]>0)
                {
                    cookie+=cursor[0];
                }
                if(granny[0]>0)
                {
                    cookie+=granny[0]*50;
                }
                if(factory[0]>0)
                {
                    cookie+=factory[0]*1000;
                }

                if(cookie>1000000000)
                {
                    goal=true;
                }

                switch (page)
                {
                    case 0:

                        buy_curs.setVisibility(View.VISIBLE);
                        buy_factory.setVisibility(View.VISIBLE);
                        buy_granny.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        buy_curs.setVisibility(View.VISIBLE);
                        buy_factory.setVisibility(View.VISIBLE);
                        buy_granny.setVisibility(View.VISIBLE);
                        break;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m.setText("Всего печенек: "+cookie);
                    }
                });

            }
        },500,500);
        if(goal)
        {
            tm.cancel();
        }
    }
    TextView initTextView (int id,String text, int textsize)
    {
        TextView m=findViewById(id);
        m.setTextSize(textsize);
        m.setText(text);
        return m;
    }

    Button initBtn (int id,String text, int textsize)
    {
        Button m=findViewById(id);
        m.setTextSize(textsize);
        m.setText(text);
        m.setGravity(Gravity.CENTER);
        m.setBackgroundResource(R.drawable.back);
        return m;
    }





}
class Product
{
        public
        int cookie, product, prodPrice;
        Product(int cookie, int product,int prodPrice)
        {
            this.cookie=cookie;
            this.product=product;
            this.prodPrice=prodPrice;
        }
        public void paymentProcessing(TextView pr, Button prpay, String paymentText,String countText)
        {
            if(cookie>=prodPrice)
            {
            product++;
            cookie -= prodPrice;
            prodPrice*=2;
            pr.setText(paymentText + product);
            prpay.setText(countText +prodPrice+ " печенек");
            }
        }
        public  int getCookie()
        {
            return  cookie;
        }
    public  int getProduct()
    {
        return  product;
    }
    public  int getProdPrice()
    {
        return  prodPrice;
    }
}
