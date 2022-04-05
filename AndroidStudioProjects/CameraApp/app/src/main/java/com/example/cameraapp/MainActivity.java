package com.example.cameraapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Camera;
    LinearLayout imageshow;
    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Camera=findViewById(R.id.cam);
        img1=findViewById(R.id.img);
        imageshow=findViewById(R.id.layout2);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //camera is clicked
                AskCameraPermission();
            }
        });

    }

    private void AskCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},101);
        }
        else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else{
                Toast.makeText(MainActivity.this, "Permission required",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent openCam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCam,102);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==102)
        {
            Bitmap image= (Bitmap) data.getExtras().get("data");
            ImageView newImage=new ImageView(this);
//            newImage.getLayoutParams().width=img1.getWidth();
//            newImage.getLayoutParams().height=img1.getHeight();
            newImage.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(682,780 );
            newImage.setLayoutParams(params);
            newImage.setImageBitmap(image);

            imageshow.addView(newImage);
        }
    }
}