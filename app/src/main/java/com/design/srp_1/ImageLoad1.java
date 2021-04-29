package com.design.srp_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: sf
 * @CreateDate: 21-4-29 下午4:03
 * @Version: 1.0.0
 */
public class ImageLoad1 {
    private ImageCache1 mImageCache = new ImageCache1();
    private ExecutorService mExecuteService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void displayImage(final String url, final Handler handler) {
        final Bitmap bitmap = mImageCache.getCache(url);
        if (bitmap != null) {
            Log.e("AA_imageload", "有缓存了");
            Message msg = handler.obtainMessage();
            msg.obj = bitmap;
            handler.sendMessage(msg);
            return;
        }
        mExecuteService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downImage(url);
                Message msg = handler.obtainMessage();
                msg.obj = bitmap;
                handler.sendMessage(msg);

            }
        });
    }

    private Bitmap downImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            mImageCache.putCache(imageUrl, bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
