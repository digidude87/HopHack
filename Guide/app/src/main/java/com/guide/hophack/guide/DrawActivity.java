package com.guide.hophack.guide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class DrawActivity extends Activity implements View.OnTouchListener{
    ImageView iv;
    Bitmap bmp;
    Canvas canvas;
    Paint paint;
    float downx, downy, movex, movey, upx, upy = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        iv = (ImageView) findViewById(R.id.imageView1);
        Display d = getWindowManager().getDefaultDisplay();
        float dw = d.getWidth();
        float dh = d.getHeight();
        bmp = Bitmap.createBitmap((int)dw, (int) dh, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        //paint.setShadowLayer(5,2,2,Color.RED);
        iv.setImageBitmap(bmp);
        iv.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1){
        switch(arg1.getAction()){
            case MotionEvent.ACTION_DOWN :
                downx = arg1.getX();
                downy = arg1.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                movex = arg1.getX();
                movey = arg1.getY();
                break;
            case MotionEvent.ACTION_UP :
                upx = arg1.getX();
                upy = arg1.getY();
                canvas.drawLine(downx,downy,upx,upy,paint);
                iv.invalidate();
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_draw, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
