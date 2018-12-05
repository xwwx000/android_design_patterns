package com.xwwx.design.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @功能
 * @作者 Administrator
 * @创建日期 2018/12/4
 */

public class ImageLoader {
    LruCache<String,Bitmap> mImageCache;
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    Handler mUihandler = new Handler(Looper.getMainLooper());
    String mTag = "srp";
    public ImageLoader(){
        initImageCache();
    }

    private void initImageCache() {
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        mImageCache = new LruCache<String,Bitmap>(maxMemory/4){
            @Override
            protected int sizeOf(String key,Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void displayImage(final String url,final ImageView imageView){
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = mImageCache.get(url);
                if(bitmap == null){
                    bitmap = downloadImage(url);
                    if(bitmap == null){
                        return;
                    }else {
                        Log.v(mTag,"图片来自于下载!");
                        mImageCache.put(url,bitmap);
                        Map<String,Bitmap> map =  mImageCache.snapshot();
                        for(Map.Entry<String,Bitmap> entry : map.entrySet()){
                            Log.v(mTag,"key:"+entry.getKey());
                        }
                    }
                }else{
                    Log.v(mTag,"图片自于缓存!");
                }
                updateImageView(imageView,bitmap);
            }
        });
    }

    private void updateImageView(final ImageView imageView,final Bitmap bmp){
        mUihandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bmp);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(imageUrl);
            conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            return bitmap;
        }
    }
}
