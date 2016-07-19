package com.example.devas.demo;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout cameraPreview;
    public static android.hardware.Camera mCamera ;
    public static android.hardware.Camera.Parameters params;
    public CameraPreview mPreview;
    public SurfaceView transparentView;
    public SurfaceHolder holderTransparent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCamera = getCameraInstance();
        //Button captureButton = (Button) findViewById(R.id.button_capture);
        cameraPreview = (FrameLayout) findViewById(R.id.camera_preview);
        transparentView = (SurfaceView)findViewById(R.id.TransparentView);
        transparentView.setZOrderOnTop(true);    // necessary
        //SurfaceHolder transparentViewHolder = transparentView.getHolder();
        holderTransparent = transparentView.getHolder();
        holderTransparent.setFormat(PixelFormat.TRANSPARENT);

        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //transparentViewHolder.setFormat(PixelFormat.TRANSPARENT);
            Log.e("Main Activity", String.valueOf(mCamera));
            this.mPreview = new CameraPreview(this, mCamera);
            //mPreview = new DrawView("",mCamera,"");
            cameraPreview.addView(mPreview);
            Log.e("krish", String.valueOf(mPreview));
        DrawView drawView = new DrawView(this);
        //drawView.setBackground(null);
        //setContentView(drawView);

    }



    public static android.hardware.Camera getCameraInstance() {
        android.hardware.Camera c = null;
        try {
            c = android.hardware.Camera.open();
            params = c.getParameters();
            Log.e("Params", String.valueOf(params));
            /*if(params.getMaxNumMeteringAreas()>0) {
                params.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_AUTO);
                c.setParameters(params);
                c.setDisplayOrientation(90);
                Log.e("Params", String.valueOf(params));
            }*/
            } catch (Exception e) {
                Log.d("Camera Not Working", "" + e);
            }
        Log.e("ME", String.valueOf(c));
        if(c !=null) {
            Log.e("Camera", String.valueOf(c));
            return c;
        }else {
            return null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopFaceDetection();
        mCamera.stopPreview();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mCamera.stopFaceDetection();
        mCamera.release();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mPreview = new CameraPreview(this, mCamera);
        cameraPreview.addView(mPreview);
        Log.e("krish", String.valueOf(mPreview));

    }
    public void drawCanvaws(float x, float y){
        //transparentView = (SurfaceView)findViewById(R.id.TransparentView);


        Canvas canvas = new Canvas();
        //canvas = holderTransparent.lockCanvas();
        if(canvas != null) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            paint.setDither(true);
            paint.setColor(0xFFFFFF00);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
            canvas.drawRect(x - 100, y - 100, x + 100, y + 100, paint);
//            holderTransparent.unlockCanvasAndPost(canvas);
        }else{
            Log.e("Canvas", "Canvas:" + canvas);
        }
    }

}
