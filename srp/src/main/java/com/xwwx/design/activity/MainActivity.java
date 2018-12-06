package com.xwwx.design.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xwwx.design.R;
import com.xwwx.design.common.DoubleCache;
import com.xwwx.design.common.ImageLoader;
import com.xwwx.design.common.MemoryCache;


public class MainActivity extends AppCompatActivity {

    private ImageView imgv_main_show;
    ImageLoader imageLoader = new ImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgv_main_show = findViewById(R.id.imgv_main_show);
        loadImage(null);
    }

    public void loadImage(View view){
        String url = "https://avatar.csdn.net/E/5/4/3_wuqilianga.jpg";
        imageLoader.setmImageCache(new DoubleCache());
        imageLoader.displayImage(url,imgv_main_show);
    }
}
