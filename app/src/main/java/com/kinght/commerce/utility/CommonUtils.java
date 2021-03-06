package com.kinght.commerce.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.CommonResponse;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.internal.Utils;
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

    public static void changeActivity(Activity activity, Class goClass) {
        Intent intent = new Intent(activity, goClass);
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        activity.startActivity(intent);
    }

    public static void switchToFragment(AppCompatActivity activity, Fragment targetFragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment, targetFragment).commit();
    }

    public static String getStringNameFromInteger(Context context, int id) {
        return context.getString(id);
    }

    public static void getImage(String imageUrl, final ImageView imageView) {
        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
    }

    public static boolean shareTextWithWhatsapp(Context context, String text) {
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

    public static void shareTextWithTwitter(Context context, String text) {
        String url = "http://www.twitter.com/intent/tweet?url=YOURURL&text=" + text;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    public static void shareTextWithMessagingApps(Context context, String text) {
        String shareBody = "Here isExactTime the share content body";
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

    public static boolean askPermissionForActivity(Activity activity, String permission, int requestCode) {
        List<String> permissionList = new ArrayList<>();
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
            requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), requestCode);
        } else {
            return true;
        }
        return false;
    }

    public static boolean askPermissionForFragment(Activity activity, Fragment fragment, String permission, int requestCode) {
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            fragment.requestPermissions(new String[]{permission}, requestCode);
        } else {
            return true;
        }
        return false;
    }

    public static String getUdid(Context context) {
        String udid = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return udid;
    }

    public static CommonResponse errorHandler(String response) {
        CommonResponse errorResponse = null;
        try {
            Gson gson = new Gson();
            errorResponse = gson.fromJson(response, CommonResponse.class);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return errorResponse;

    }

    public static String regularText(EditText editText) {

        return String.valueOf(editText.getText().toString().trim());
    }

    public static boolean isRegularText(String text){
        text=text.replace("-","").replace("(","").replace(")","").trim();
        if(text.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static String getPnsToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static void getImageWithCache(ImageView imageView, String url) {

        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {

                Picasso.get().load(url).into(imageView);
            }
        });
    }

    public static void getImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);

    }

    public static Bitmap getResizedBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0;
        float pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(resizedBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return resizedBitmap;
    }

    public static String getImageBase64(Bitmap bm) {
        bm = getResizedBitmap(bm, 400, 400);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return resizeBase64Image(encImage);
    }

    public static String resizeBase64Image(String base64image) {
        byte[] encodeByte = Base64.decode(base64image.getBytes(), Base64.NO_WRAP);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);
        if (image.getHeight() < 400 || image.getWidth() < 400) {
            return base64image;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 20, baos);

        byte[] b = baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public static void sendMessageToUserOnWhatsapp(Activity activity, String number,String message) {

        try {
            String text = "Merhaba, "+message+" başlıklı ilan için sizde ulaşıyorum. FLOODY";// Replace with your message.
            number="9"+number;
            String toNumber = number; // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            activity.startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void callPhone(Activity activity, String phoneNumber) {
        Uri call = Uri.parse("tel:" + phoneNumber);
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        activity.startActivity(surf);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}



