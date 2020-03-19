package com.phong.hocbroadcastreceiver_internet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver internetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null){
                Toast.makeText(context,"Có Internet",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context,"Mất Internet",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //Lắng nghe thêm thì gọi addAction():
        //intentFilter.addAction();
        //Đăng ký Broadcast Receiver:
        registerReceiver(internetReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Huỷ đăng ký Broadcast Receiver:
        unregisterReceiver(internetReceiver);
    }
}
