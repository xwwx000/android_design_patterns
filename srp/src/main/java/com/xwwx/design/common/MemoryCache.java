package com.xwwx.design.common;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @功能 内存缓存
 * @作者 Administrator
 * @创建日期 2018/12/5 0005
 */

public class MemoryCache implements ImageCache{
    private LruCache<String,Bitmap> mMemoryCache;
    public MemoryCache(){
        initImageCache();
    }
    private void initImageCache() {
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        mMemoryCache = new LruCache<String,Bitmap>(maxMemory/4){
            @Override
            protected int sizeOf(String key,Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }
    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url,bmp);
    }
}
