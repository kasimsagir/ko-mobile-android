package com.kinght.commerce.ui.adapters;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Entries.Entry;
import com.kinght.commerce.data.network.entities.Servers.Servers;
import com.kinght.commerce.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class EntryRecylerViewAdapters extends RecyclerView.Adapter<EntryRecylerViewAdapters.ViewHolder> {

    private List<Entry> myItems;
    private ItemListener myListener;

    public EntryRecylerViewAdapters(ItemListener listener) {
        myListener = listener;
    }

    public void setData(List<Entry> items) {
        this.myItems = items;
        notifyDataSetChanged();
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_entry, parent, false)); // TODO
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
        void onItemClick(Entry item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Entry item;


        @BindView(R.id.row_entry_thumbnail_image_view)
        ImageView rowEntryThumbnailImageView;
        @BindView(R.id.row_entry_header_text_view)
        TextView rowEntryHeaderTextView;
        @BindView(R.id.row_entry_description_text_view)
        TextView rowEntryDescriptionTextView;
        @BindView(R.id.row_entry_price_text_view)
        TextView rowEntryPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);

            // TODO instantiate/assign view members
        }

        public void setData(Entry item) {
            this.item = item;
            CommonUtils.getImageWithCache(rowEntryThumbnailImageView,item.getEntryImageUrl());
            rowEntryDescriptionTextView.setText(item.getMessage());
            rowEntryPriceTextView.setText(String.valueOf(item.getPrice())+" ₺");

            SpannableString content = new SpannableString(item.getHeader());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            rowEntryHeaderTextView.setText(content);

            // TODO set data to view
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }

    public void filter(List<Entry> entryList) {
        myItems = new ArrayList<>();
        myItems.addAll(entryList);
        notifyDataSetChanged();
    }

}
                                