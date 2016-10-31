package com.example.lontongboy.posapp.core;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * This <PosappSP> project in package <com.example.lontongboy.posapp> created by :
 * Name         : syafiq
 * Date / Time  : 30 October 2016, 9:17 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class LruBitMapCache extends LruCache<String, Bitmap> implements
                                                             ImageLoader.ImageCache
{
    public LruBitMapCache()
    {
        this(getDefaultLruCacheSize());
    }

    public LruBitMapCache(int sizeInKiloBytes)
    {
        super(sizeInKiloBytes);
    }

    public static int getDefaultLruCacheSize()
    {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    @Override
    protected int sizeOf(String key, Bitmap value)
    {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        put(url, bitmap);
    }
}