package com.parkchansik.receiverserviceapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static final String TAG = "MyService";
    boolean isRunning = false;
    int mCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while(isRunning) {
                    Log.i(TAG, "count : " + mCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCount++;
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);// 인텐트 필터를 생성 스크린온 액션을 취하는 인텐트를
        filter.addAction(Intent.ACTION_SCREEN_OFF);//필터에 저런 오프를 하는 액션을 더한다.

        registerReceiver(mScreenReceiver, filter);//받을준비를 하는거다 .필터의 기능을 가진 리시버를 받을준비
        //서비스에서 레지스트리시버를 선언하면 서비스를 기반으로 됨
    }

    BroadcastReceiver mScreenReceiver = new BroadcastReceiver() {//이렇게 센드브로드캐스트를 하면서 뿌려진것을 받는거다
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();//리시버에는 토스트가 안되는데 서비스의 컨택스트를 받아서 토스트가 된다.
                //생성되는 위치에 따라서 컨택스트를 받을수 있다 없다가 결정되는것 같다 .
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "Screen off");
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
        if (intent != null) {
            mCount = intent.getIntExtra("count", 0);
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        isRunning = false;
        unregisterReceiver(mScreenReceiver);
    }
}
