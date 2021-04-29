package com.design.srp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.collection.LruCache;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoad {
    private LruCache<String, Bitmap> mImageCache;
    //线程池，线程数为CPU数
    private ExecutorService mExecutroService = Executors.newFixedThreadPool(Runtime.
            getRuntime().availableProcessors());

    public ImageLoad() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可用的最大内存
        final long maxMemory = (Runtime.getRuntime().maxMemory()) / 1024;
        final int cacheSize = (int) (maxMemory / 4);
        mImageCache = new LruCache<>(cacheSize);
    }

    public void displayImage(final String url, final Handler handler) {
//        imageView.setTag(url);
        mExecutroService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null)
                    return;
                Message msg = handler.obtainMessage();
                msg.obj = bitmap;
                handler.sendMessage(msg);
                mImageCache.put(url, bitmap);

            }
        });
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("imageLoad", "imageLoad io ex=" + e);
        }
        return bitmap;
    }
}
