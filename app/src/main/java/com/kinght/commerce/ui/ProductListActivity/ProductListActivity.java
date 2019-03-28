package com.kinght.commerce.ui.ProductListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Gold.Gold;
import com.kinght.commerce.ui.adapters.GoldRecylerViewAdapters;
import com.kinght.commerce.ui.base.BaseActivity;
import com.kinght.commerce.utility.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends BaseActivity implements BillingProcessor.IBillingHandler {


    BillingProcessor bp;
    @BindView(R.id.activity_product_list_recylerview)
    RecyclerView activityProductListRecylerview;

    GoldRecylerViewAdapters adapters;
    List<Gold> goldList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        bp = new BillingProcessor(this, Constant.base64, this);
        bp.initialize();
        goldList = new ArrayList<>();
        goldList.add(new Gold(70, "6.99 TL", "product_1"));
        goldList.add(new Gold(150, "12.99 TL", "product_2"));
        goldList.add(new Gold(220, "19.99 TL", "product_3"));
        goldList.add(new Gold(300, "26.99 TL", "product_4"));
        goldList.add(new Gold(400, "34.99 TL", "product_5"));
        adapters = new GoldRecylerViewAdapters(bp,goldList, new GoldRecylerViewAdapters.ItemListener() {
            @Override
            public void onItemClick(Gold item) {
                bp.purchase(ProductListActivity.this, item.getId());

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(ProductListActivity.this, LinearLayoutManager.VERTICAL, false);
        activityProductListRecylerview.setLayoutManager(manager);
        activityProductListRecylerview.setAdapter(adapters);


    }

    @Override
    public void onBillingInitialized() {
        /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        /*
         * Called when requested PRODUCT ID was successfully purchased
         */

        Log.d("veri","veri");
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */
        Log.d("veri","veri");

    }

    @Override
    public void onPurchaseHistoryRestored() {
        /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
        Log.d("veri","veri");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
