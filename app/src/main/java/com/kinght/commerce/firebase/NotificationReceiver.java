package com.kinght.commerce.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"TEST",Toast.LENGTH_SHORT).show();
        Log.d("veri","Broad çalıştı");

    }

}