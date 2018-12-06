package com.xwwx.design.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @功能
 * @作者 Administrator
 * @创建日期 2018/12/5
 */

public class DiskCache implements ImageCache{
    public static String cacheDir = "sdcard/cache/";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
