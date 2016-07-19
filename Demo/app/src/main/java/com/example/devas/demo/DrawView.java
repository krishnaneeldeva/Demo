package com.example.devas.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by deva on 15/7/16.
 */
public class DrawView extends View{

    private float x,y;
    private SurfaceHolder holderTransparent;
    public DrawView(Context context) {
        super(context);



    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        SurfaceView transparentView = (SurfaceView) findViewById(R.id.TransparentView);
        transparentView.setZOrderOnTop(true);    // necessary
        holderTransparent = transparentView.getHolder();
        holderTransparent = transparentView.getHolder();
        holderTransparent.setFormat(PixelFormat.TRANSPARENT);

        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MainActivity main = new MainActivity();

        //canvas = holderTransparent.lockCanvas();
        if(canvas != null) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Toast.makeText(getContext(),"Maari",Toast.LENGTH_LONG).show();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            paint.setDither(true);
            paint.setColor(0xFFFFFF00);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
            canvas.drawRect(x - 100, y - 100, x + 100, y + 100, paint);
          //  holderTransparent.unlockCanvasAndPost(canvas);
        }else{
            Log.e("Canvas", "Canvas:" + canvas);
        }
    }
    public void Variables(float x, float y){
        this.x = x;
        this.y = y;
    }
}