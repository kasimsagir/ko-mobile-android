package com.kinght.commerce.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.kinght.commerce.R;
import com.kinght.commerce.ui.MainActivity.MainActivity;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"TEST",Toast.LENGTH_SHORT).show();

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long[] pattern = {500,500,500,500};//Titreşim ayarı
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        Notification.Builder notificationBuilder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(intent.getStringExtra("key"))
                    .setContentText(intent.getStringExtra("key") +" etkinliği başlayacak")
                    .setContentTitle("New Message")
                    .setChannelId(CHANNEL_ID)
                    .setContentText("You've received new messages.");
        }else {
            notificationBuilder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(intent.getStringExtra("key"))
                    .setContentText(intent.getStringExtra("key") +" etkinliği başlayacak")
                    .setContentTitle("New Message")
                    .setContentText("You've received new messages.");
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }


    }

}