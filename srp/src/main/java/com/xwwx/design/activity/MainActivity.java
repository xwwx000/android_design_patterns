package com.xwwx.design.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xwwx.design.R;
import com.xwwx.design.common.DoubleCache;
import com.xwwx.design.common.ImageLoader;


public class MainActivity extends AppCompatActivity {

    private ImageView imgv_main_show;
    ImageLoader imageLoader = new ImageLoader();
    DoubleCache doubleCache = new DoubleCache();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgv_main_show = findViewById(R.id.imgv_main_show);
        loadImage(null);
    }

    public void loadImage(View view){
        String url = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2386734369,3655898069&fm=179&app=42&f=PNG?w=121&h=140";

        imageLoader.setmImageCache(doubleCache);
        imageLoader.displayImage(url,imgv_main_show);
    }
}
