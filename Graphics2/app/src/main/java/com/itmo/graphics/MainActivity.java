package com.itmo.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

class Graphics2D extends View{
    protected Paint p;
    protected float x,y;
    protected int r;
    Resources res = getContext().getResources();
    //BitmapFactory bf =
    //Bitmap bm = new BitmapFactory().decodeFile("C:\\Users\\Crois-San\\AndroidStudioProjects\\Graphics2\\EA.png");
    Bitmap bm = new BitmapFactory().decodeResource(res, R.drawable.ea);
    protected int colorTable[]={0xffff0000,0xff00ff00,0xff0000ff,0xffffff00,0xff00ffff,0xffff00ff};

    public Graphics2D(Context context) {
        super(context);
    }

    public void initPaint(int color,int width){
        p=new Paint();
        p.setColor(color);
        p.setStrokeWidth(width);
        p.setTextSize(50);
    }

    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }

    public void setRadius(int r){
        this.r=r;
    }

    public void setColor(int color){
        p.setColor(color);
    }

    @Override
    protected void onDraw(Canvas c){
            /*if(r<=100)
            {
                c.drawCircle(x, y, r, p);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r++;

            }*/

            c.drawBitmap(bm,x,y,p);
        invalidate();

        }
    }


public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        final Graphics2D G=new Graphics2D(this);
        //G.setRadius(0);
        G.setPosition(100, 100);
        G.initPaint(0xffff0000,10);
        setContentView(G);

        /*G.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x=motionEvent.getX();
                float y=motionEvent.getY();
                G.setPosition(x,y);
                G.initPaint(0xffff0000,10);
                G.setRadius(0);
                return false;
            }
        });*/
    }
}
