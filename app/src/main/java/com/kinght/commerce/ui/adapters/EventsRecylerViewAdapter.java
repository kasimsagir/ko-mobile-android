
package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.Events;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class EventsRecylerViewAdapter extends RecyclerView.Adapter<EventsRecylerViewAdapter.ViewHolder> {

    private List<Events> myItems;
    private ItemListener myListener;

    public EventsRecylerViewAdapter(List<Events> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }



    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_events, parent, false)); // TODO
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
        void onItemClick(Events item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Events item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            // TODO instantiate/assign view members
        }

        public void setData(Events item) {
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
                                