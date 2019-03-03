package com.kinght.commerce.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;


import com.google.gson.Gson;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import okhttp3.ResponseBody;

import static androidx.core.app.ActivityCompat.requestPermissions;


public class CommonUtils {

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (!((Activity) context).isFinishing()) {
            progressDialog.show();
        }
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void changeActivity(Activity activity, Class goClass ){
        Intent intent=new Intent(activity,goClass);
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        activity.startActivity(intent);
    }

    public static void switchToFragment(AppCompatActivity activity, Fragment targetFragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment, targetFragment).commit();
    }

    public static String getStringNameFromInteger(Context context,int id){
        return context.getString(id);
    }

    public static void getImage(String imageUrl, final ImageView imageView){
        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
    }

    public static boolean shareTextWithWhatsapp(Context context,String text){
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            context.startActivity(whatsappIntent);
            return true;
        } catch (android.content.ActivityNotFoundException ex) {
            return false;
        }
    }

    public static void shareTextWithTwitter(Context context,String text){
        String url = "http://www.twitter.com/intent/tweet?url=YOURURL&text="+text;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void shareTextWithMessagingApps(Context context,String text){
        String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, text);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Paylaş"));
    }

    public static String longToddMMMyyyyHHMMss(long date) {
        String dateString = new SimpleDateFormat("dd.MM.yyyy HH:MM").format(new Date(date));
        return dateString;
    }

    public static boolean askPermissionForActivity(Activity activity, String permission, int requestCode){
        List<String> permissionList = new ArrayList<>();
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
            requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), requestCode);
        }else {
            return true;
        }
        return false;
    }

    public static boolean askPermissionForFragment(Activity activity, Fragment fragment, String permission, int requestCode){
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            fragment.requestPermissions(new String[]{permission},requestCode);
        }else {
            return true;
        }
        return false;
    }
    public static String getUdid(Context context) {
        String udid = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return udid;
    }

    public static CommonResponse errorHandler(String response){
        CommonResponse errorResponse = null;
        try {
            Gson gson = new Gson();
            errorResponse= gson.fromJson(response, CommonResponse.class);
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
        return errorResponse;

    }
}



