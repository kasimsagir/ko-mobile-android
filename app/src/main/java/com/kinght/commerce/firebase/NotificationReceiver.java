package com.kinght.commerce.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.kinght.commerce.MvpApp;
import com.kinght.commerce.R;

import com.kinght.commerce.data.DataManager;
import com.kinght.commerce.data.network.ServiceCallback;
import com.kinght.commerce.utility.Constant;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Inject
    DataManager dataManager;

    int id;
    @Override
    public void onReceive(Context context, Intent intent) {

        ((MvpApp) context.getApplicationContext()).getActivityComponent().injectNotificationReceiver(this);
     /*   id=intent.getIntExtra("id",0);
        Calendar calendar = Calendar.getInstance();

        dataManager.getEvents(new ServiceCallback<List<Event>>() {
            @Override
            public void onSuccess(List<Event> response) {
                Log.d("veri","Broadcast çalıştı 2");
                if(dataManager.getEventHours(id).isSelected() ){
                    if(new Date(System.currentTimeMillis()).getHours() == new Date(dataManager.getEventHours(id).getCurrentMilisTime(calendar.get(Calendar.DAY_OF_WEEK))).getHours()){
                        showNotification(context, intent.getStringExtra("key"), intent.getStringExtra("key")+" etkinliği "+ Constant.ALARM_INCREASE+" dakika sonra başlayacak",intent);
                    }
                }

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });

*/

    }

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder.addNextIntent(intent);
        }
        PendingIntent resultPendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
        }
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }


}