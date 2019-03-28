package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Gold.Gold;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class GoldRecylerViewAdapters extends RecyclerView.Adapter<GoldRecylerViewAdapters.ViewHolder> {


    private List<Gold> myItems;
    private ItemListener myListener;
    private  BillingProcessor billingProcessor;
    public GoldRecylerViewAdapters(BillingProcessor billingProcessor, List<Gold> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
        this.billingProcessor=billingProcessor;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_gold, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
    }

    public interface ItemListener {
        void onItemClick(Gold item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.row_gold_title_text_view)
        TextView rowGoldTitleTextView;
        @BindView(R.id.row_gold_price_text_view)
        TextView rowGoldPriceTextView;
        // TODO - Your view members
        public Gold item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Gold item) {
            this.item = item;
            rowGoldTitleTextView.setText(billingProcessor.getPurchaseListingDetails(item.getId()).priceText);
            rowGoldPriceTextView.setText(billingProcessor.getPurchaseListingDetails(item.getId()).description);
            // TODO set data to view
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }


}
                                