package com.xwwx.design.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xwwx.design.R;
import com.xwwx.design.adapter.MsgAdapter;
import com.xwwx.design.bean.Msg;
import com.xwwx.design.common.StringUtils;
import com.xwwx.design.common.UDPSocket;
import com.xwwx.design.common.WifiUtil;
import com.xwwx.design.listener.OnMessageReceiveListener;

import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText et_main_ip;
    private Button btn_main_send;
    private Button btn_main_connect;
    private ListView lv_main_msg;
    private List<String> items = new ArrayList<String>();
    private MsgAdapter adapter;
    private Socket socket;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    private OutputStream os;
    private UDPSocket udpSocket = new UDPSocket(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }
    private void initView(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("UDPSOCKET");
        et_main_ip = findViewById(R.id.et_main_ip);
        btn_main_send = findViewById(R.id.btn_main_send);
        btn_main_connect = findViewById(R.id.btn_main_connect);
        lv_main_msg = findViewById(R.id.lv_main_msg);
        adapter = new MsgAdapter(this);
        lv_main_msg.setAdapter(adapter);
        et_main_ip.setText(WifiUtil.getInstance(this).getServerIPAddress());
    }
    private void initListener(){

        //连接
        btn_main_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                udpSocket.addOnMessageReceiveListener(onMessageReceiveListener);
                udpSocket.startUDPSocket();
            }
        });
        //发送消息
        btn_main_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!"".equals(et_main_ip.getText().toString())){
                    udpSocket.serverIp = et_main_ip.getText().toString();
                    udpSocket.sendMessage("aaaa");
                }
            }
        });
    }
    OnMessageReceiveListener onMessageReceiveListener = new OnMessageReceiveListener() {
        @Override
        public void onMessageReceived(String message) {
            updateList(message);
        }
    };
    /**
     * 更新列表
     */
    private void updateList(String str){
        Msg msg = new Msg();
        msg.setMsg(str);
        msg.setMsgtime(StringUtils.getCurrDateTime());
        adapter.addItem(msg);
        adapter.notifyDataSetChanged();
    }
    /**
     * Handler 操作
     */
    Handler _handler = new Handler() {
        String rstr = "";
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    rstr = (String)msg.obj;
                    updateList(rstr);
                    break;
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        udpSocket.stopUDPSocket();
    }
}
