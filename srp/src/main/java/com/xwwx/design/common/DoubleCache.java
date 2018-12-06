package com.xwwx.design.common;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * @功能 双缓存
 * @作者 Administrator
 * @创建日期 2018/12/6
 */

public class DoubleCache implements ImageCache{
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if(bitmap == null){
            bitmap = mDiskCache.get(url);
            if(bitmap != null){
                Log.v(Constants.TAG,"图片来自硬盘!");
            }
        }else {
            Log.v(Constants.TAG,"图片来自内存!");
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url,bmp);
        mDiskCache.put(url,bmp);
    }
}
