package com.design.srp_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: sf
 * @CreateDate: 21-4-29 下午4:03
 * @Version: 1.0.0
 */
public class ImageLoad {
    private ImageCache mImageCache = new ImageCache();
    private ExecutorService mExecuteService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void showImage(final String url, final Handler handler) {
        final Bitmap bitmap = mImageCache.getCache(url);
        if (bitmap != null) {
            Message msg = handler.obtainMessage();
            msg.obj = bitmap;
            handler.sendMessage(msg);
            return;
        }
        mExecuteService.submit(new Runnable() {
            @Override
            public void run() {
                bitmap = downImage(url);
            }
        });
    }

    private Bitmap downImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
