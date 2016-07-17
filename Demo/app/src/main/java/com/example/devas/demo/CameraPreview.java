package com.example.devas.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by ankit on 7/15/2016.
 */
public class CameraPreview extends SurfaceView implements android.view.SurfaceHolder.Callback{
    private  SurfaceHolder mHolder,holderTransparent;
    private android.hardware.Camera mCamera;
    public SurfaceView transparentView;

    public CameraPreview(Context context, android.hardware.Camera camera){
        super(context);
        this.mCamera = camera;


        this.mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            Log.e("krish", String.valueOf(holder));
            mCamera.stopFaceDetection();
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();

            startFaceDetection();
            mCamera.setFaceDetectionListener(new MyFaceDetectionListener());
        }catch (IOException e){
            Log.d("TaG","Error setting Camera Preview:" +e.getMessage());
            mCamera.stopFaceDetection();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mHolder.getSurface() == null){
            return;
        }
        try{
            mCamera.stopPreview();
            mCamera.stopFaceDetection();
        }catch (Exception e){
            Log.d("TaG", "Error Stopping Camera Preview" + e.getMessage());
        }
        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

            startFaceDetection();
        }catch (Exception e){
            Log.d("TaG","Error Setting Camera Preview" + e.getMessage());
            mCamera.stopFaceDetection();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    public void startFaceDetection(){

        Camera.Parameters params = mCamera.getParameters();
        if(params.getMaxNumDetectedFaces() > 0 ){
            Log.e("Faces", String.valueOf(params.getMaxNumDetectedFaces()));
            mCamera.startFaceDetection();
        }
    }
    class MyFaceDetectionListener implements android.hardware.Camera.FaceDetectionListener {

        @Override
        public void onFaceDetection(android.hardware.Camera.Face[] faces, android.hardware.Camera camera) {
            if(faces.length>0){
                Log.d("Facedetection", "Face DEtected:" + faces.length + " face1 location X:" + faces[0].rect.centerX() +
                        "Y:" + faces[0].rect.centerY());

                //int vWidth = getWidth();
                //int vHeight = getHeight();
                for(int i=0; i<=faces.length;i++){
                 //   int l = faces[i].rect.left;
                 //   int t = faces[i].rect.top;
                 //   int r = faces[i].rect.right;
                 //   int b = faces[i].rect.bottom;
                 //   int left = (l+1000) * vWidth/2000;
                 //   int top  = (t+1000) * vHeight/2000;
                 //   int right = (r+1000) * vWidth/2000;
                 //   int bottom = (b+1000) * vHeight/2000;
                    MainActivity main = new MainActivity();
                    main.drawCanvaws(faces[i].rect.centerX(),faces[i].rect.centerY());
                }
            }
        }
    }


}
