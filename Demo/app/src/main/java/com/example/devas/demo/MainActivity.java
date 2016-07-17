package com.example.devas.demo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    FrameLayout cameraPreview;
    public android.hardware.Camera mCamera;
    private CameraPreview mPreview;
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
        SurfaceHolder transparentViewHolder = transparentView.getHolder();
        holderTransparent = transparentView.getHolder();
        holderTransparent.setFormat(PixelFormat.TRANSPARENT);

        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        transparentViewHolder.setFormat(PixelFormat.TRANSPARENT);

            this.mPreview = new CameraPreview(this, mCamera);
            cameraPreview.addView(mPreview);
            Log.e("krish", String.valueOf(mPreview));

    }



        //mCamera.setFaceDetectionListener(new MyFaceDetectionListener());


          /*  captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                        //fileUri = getOutputMediaFileUri( MEDIA_TYPE_IMAGE );
                       //intent.putExtra( MediaStore.EXTRA_OUTPUT, fileUri );
                        startActivityForResult( intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE );
                    }
                }

        );

    }
    */


    public static android.hardware.Camera getCameraInstance() {
        android.hardware.Camera c = null;
        try {
            c = android.hardware.Camera.open(0);
            android.hardware.Camera.Parameters params = c.getParameters();
            if(params.getMaxNumMeteringAreas()>0) {
                params.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_AUTO);
                c.setParameters(params);
                c.setDisplayOrientation(90);
                Log.e("Params", String.valueOf(params));
            }
            } catch (Exception e) {
                Log.d("Camera Not Working", "" + e);
            }
        Log.e("Camera", String.valueOf(c));
        return c;
    }
    private static int findFrontFacingCamera(){
        int cameraId = -1;
        int numberOfCameras = android.hardware.Camera.getNumberOfCameras();
        for(int i=0; i< numberOfCameras; i++){
            android.hardware.Camera.CameraInfo cameraInfo = new android.hardware.Camera.CameraInfo();
            android.hardware.Camera.getCameraInfo(i,cameraInfo);
            if(cameraInfo.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK){
                Log.d("TaG","Camera Found");
                cameraId=i;
                break;
            }
        }
        return cameraId;
    }

    /*private boolean checkCameraHardware(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                cameraPreview.setImageBitmap(bp);
                //Toast.makeText(this, "Image Saved to :\n" + data.getData(), Toast.LENGTH_LONG).show();
            }else if(resultCode == RESULT_CANCELED){

            }else{

            }

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Toast.makeText( this, "Image Saved to: \n " + data.getData(), Toast.LENGTH_LONG ).show();
            }
            else if(resultCode == RESULT_CANCELED){

            }else{

            }
        }
        }
        super.onActivityResult( requestCode, resultCode, data );

    }*/

    private boolean checkCameraHardware(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }
        else{
            return false;
        }
    }
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File( Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
    class MyFaceDetectionListener implements android.hardware.Camera.FaceDetectionListener {

        @Override
        public void onFaceDetection(android.hardware.Camera.Face[] faces, android.hardware.Camera camera) {
            if(faces.length>0){
                Log.d("Facedetection", "Face DEtected:" + faces.length + " face1 location X:" + faces[0].rect.centerX() +
                        "Y:" + faces[0].rect.centerY());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.stopFaceDetection();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCamera.release();
        mCamera.stopFaceDetection();
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
