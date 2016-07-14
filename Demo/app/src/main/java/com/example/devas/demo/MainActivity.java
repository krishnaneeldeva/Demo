package com.example.devas.demo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Camera mCamera;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    ImageView cameraPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button captureButton = (Button) findViewById(R.id.button_capture);
        cameraPreview = (ImageView) findViewById( R.id.camera_preview );
        boolean me = checkCameraHardware( this );
        if(me) {
            Toast.makeText( this, " Working", Toast.LENGTH_LONG ).show();
        }
        else{
            Toast.makeText( this, " Not Working", Toast.LENGTH_LONG ).show();
        }

            captureButton.setOnClickListener(
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

    }

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
    class MyFaceDetectionListener implements Camera.FaceDetectionListener{

    }
}
