package com.kinght.commerce.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.kinght.commerce.R;
import com.kinght.commerce.utility.CommonUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public  class BaseActivity extends AppCompatActivity implements MvpView,BaseFragment.Callback {

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialogBuilder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }


    @Override
    public void showLoading() {
        progressDialog = CommonUtils.showLoadingDialog(BaseActivity.this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();

            }
        }
    }

    @Override
    public Activity getActivity() {
        return BaseActivity.this;
    }

    @Override
    public void showDialogWithChoose(String title, String description, String possitiveButtonText, String negativeButtonText, final DialogCallback dialogCallback) {
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(title).setMessage(description).setPositiveButton(possitiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogCallback.pressedPossitiveButton();
            }
        }).setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogCallback.pressedNegativeButton();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));

            }
        });

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    @Override
    public void showDialogWithOutChoose(String title, String description, String buttonText,DialogCallback dialogCallback) {
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(title).setMessage(description).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogCallback.pressedPossitiveButton();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));

            }
        });

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }

    @Override
    public void showInformation(String text) {
        Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public Typeface getBoldMyriadFont() {
        return Typeface.createFromAsset(getAssets(), "myriad_pro_bold.otf");
    }

    @Override
    public Typeface getRegularMyriadFont() {
        return Typeface.createFromAsset(getAssets(), "MYRIADPRO-REGULAR.OTF");
    }

    @Override
    public String getStringFromResourceId(int resourceId) {
        return getString(resourceId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.slide_from_left, R.anim.slide_to_right);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    @Override
    public void showListDialog(List<String> itemList, String title, ListSelectItem<Integer> listSelectItem) {
        if(itemList != null){
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(BaseActivity.this);
            builder.setTitle(title);


            String[] stockArr = new String[itemList.size()];
            stockArr = itemList.toArray(stockArr);



            builder.setItems(stockArr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    listSelectItem.selectedItem(which);
                }
            });

            androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    @Override
    public void showInputDialog(String title, String hint, DialogStringCallback dialogStringCallback) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton(getStringFromResourceId(R.string.button_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogStringCallback.inputText(input.getText().toString());
            }
        });
        builder.setNegativeButton(getStringFromResourceId(R.string.button_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogStringCallback.clickCancelButton();
            }
        });




        builder.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
