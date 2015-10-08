package com.parkchansik.receiverserviceapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // ....
//        Toast.makeText(context, "Receive SMS", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, MyService.class);
        context.startService(i);
    }
}
