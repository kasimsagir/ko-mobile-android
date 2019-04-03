package com.kinght.commerce.ui.ProductListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Gold.Gold;
import com.kinght.commerce.ui.MainActivity.MainActivity;
import com.kinght.commerce.ui.SplashActivity.SplashActivity;
import com.kinght.commerce.ui.adapters.GoldRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.Constant;
import com.kinght.commerce.utility.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends BaseActivity implements PurchasesUpdatedListener {


    @BindView(R.id.activity_product_list_recylerview)
    RecyclerView activityProductListRecylerview;

    GoldRecylerViewAdapters adapters;
    List<Gold> goldList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BillingClient mBillingClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);








        mBillingClient = BillingClient.newBuilder(this).setListener(this).build(); //BillingClient objemizi oluşturduk
        mBillingClient.startConnection(new BillingClientStateListener() { //satın almaya hazır mı kontrolü
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    // Satın almaya hazır
                    // BUTONLARI AKTIF ET
                    goldList = new ArrayList<>();
                    goldList.add(new Gold(70, "6.99 TL", "delaroy_monthly"));
                    goldList.add(new Gold(150, "12.99 TL", "product_2"));
                    goldList.add(new Gold(220, "19.99 TL", "product_3"));
                    goldList.add(new Gold(300, "26.99 TL", "product_4"));
                    goldList.add(new Gold(400, "34.99 TL", "product_5"));
                    adapters = new GoldRecylerViewAdapters(goldList, new GoldRecylerViewAdapters.ItemListener() {
                        @Override
                        public void onItemClick(Gold item) {
                            buySubscription(item.getId());
                        }
                    });
                    LinearLayoutManager manager = new LinearLayoutManager(ProductListActivity.this, LinearLayoutManager.VERTICAL, false);
                    activityProductListRecylerview.setLayoutManager(manager);
                    activityProductListRecylerview.setAdapter(adapters);

                } else {
                    //TODO Kullanıcıya uyarı ver
                    // Satın almaya hazır değil
                    Toast.makeText(ProductListActivity.this, "Ödeme sistemi için google play hesabını kontrol ediniz", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Servise Bağlanamadı
                //TODO Kullanıcıya uyarı ver
                Toast.makeText(ProductListActivity.this, "Ödeme sistemi şuanda geçerli değil", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void buyProduct(String skuId) {
        //Bir defa satın almak için
        //Buradaki skuId , google playde tanımladığımız id'ler olmalı
        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSku(skuId)
                .setType(BillingClient.SkuType.INAPP)
                .build();
        mBillingClient.launchBillingFlow(this, flowParams);
    }

    private void buySubscription(String skuId) {
        //haftalık,aylık,3 aylık,6 aylık ,yıllık üyelik için
        //Buradaki skuId , google playde tanımladığımız id'ler olmalı
        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSku(skuId)
                .setType(BillingClient.SkuType.SUBS)
                .build();
        mBillingClient.launchBillingFlow(this, flowParams);
    }


    private void billingCanceled() {
        //Kullanıcı iptal ettiğinde yapılacak işlemler,
    }
    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK
                && purchases != null) { //satın alma başarılı
            for (final Purchase purchase : purchases) {
                mBillingClient.consumeAsync(purchase.getPurchaseToken(), new ConsumeResponseListener() {
                    @Override
                    public void onConsumeResponse(int responseCode, String purchaseToken) {
                        if (responseCode == BillingClient.BillingResponse.OK) {
                            //satın alma tamamlandı yapacağınız işlemler
                        }
                    }
                });
            }
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {//kullanıcı iptal etti
            // Handle an error caused by a user canceling the purchase flow.
            billingCanceled(); //kullanıcı iptal etti

        } else {
            billingCanceled(); //bir sorun var
        }
    }
}
