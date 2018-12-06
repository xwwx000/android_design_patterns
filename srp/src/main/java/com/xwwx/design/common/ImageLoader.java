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
    ImageCache mImageCache = new MemoryCache();
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    Handler mUihandler = new Handler(Looper.getMainLooper());

    public void setmImageCache(ImageCache imageCache){
        mImageCache = imageCache;
    }

    private void updateImageView(final ImageView imageView,final Bitmap bitmap){
        mUihandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void displayImage(final String url,final ImageView imageView){
       Bitmap bitmap = mImageCache.get(url);
       if(bitmap == null){
           //下载图片
           Log.v(Constants.TAG,"图片来自下载!");
           submitLoadRequest(url,imageView);
       }else{
           updateImageView(imageView,bitmap);
       }
    }

    private void submitLoadRequest(final String url,final ImageView imageView){
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap == null){
                    return;
                }
                if(imageView.getTag().equals(url)){
                    updateImageView(imageView,bitmap);
                }
                mImageCache.put(url,bitmap);
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
