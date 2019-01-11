
package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinght.commerce.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ShowCaseRecylerViewAdapter extends RecyclerView.Adapter<ShowCaseRecylerViewAdapter.ViewHolder> {

    private List<String> myItems;
    private ItemListener myListener;

    public ShowCaseRecylerViewAdapter(List<String> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_show_case, parent, false)); // TODO
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
        void onItemClick(String item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public String item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            // TODO instantiate/assign view members
        }

        public void setData(String item) {
            this.item = item;
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
                                