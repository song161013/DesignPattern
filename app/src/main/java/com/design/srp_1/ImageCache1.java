package com.design.srp_1;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @Description:
 * @Author: sf
 * @CreateDate: 21-4-29 下午4:04
 * @Version: 1.0.0
 */
public class ImageCache1 {
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache1() {
        initCache();
    }

    private void initCache() {
        final int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<>(cacheSize);
    }

    public void putCache(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap getCache(String url) {
        return mImageCache.get(url);
    }
}
