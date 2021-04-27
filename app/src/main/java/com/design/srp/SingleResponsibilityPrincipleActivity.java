package com.design.srp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.design.pattern.R;

public class SingleResponsibilityPrincipleActivity extends AppCompatActivity {

    ImageView mImageView;
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_responsibility_principle);
        showImage();
    }

    private void showImage() {
        myHandler = new MyHandler();
        mImageView = findViewById(R.id.iv_single_resoponsibility);
        ImageLoad imageLoad = new ImageLoad();
        String path = "https://upload-images.jianshu.io/upload_images/7806193-d3612d84a3caa2f8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";

        imageLoad.displayImage(path, mImageView, myHandler);
    }


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            if (bitmap != null) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
