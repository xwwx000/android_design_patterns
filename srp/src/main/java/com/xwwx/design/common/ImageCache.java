package com.xwwx.design.common;

import android.graphics.Bitmap;

/**
 * @功能 图片缓存接口
 * @作者 Administrator
 * @创建日期 2018/12/5 0005
 */

public interface ImageCache {

    public Bitmap get(String url);
    public void put(String url,Bitmap bmp);
}
