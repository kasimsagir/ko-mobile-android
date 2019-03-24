package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.Events;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class EventsRecylerViewAdapter extends RecyclerView.Adapter<EventsRecylerViewAdapter.ViewHolder> {


    private List<Events> myItems;
    private ItemListener myListener;

    public EventsRecylerViewAdapter(ItemListener listener) {
        myListener = listener;
    }

    public void setData(List<Events> eventsList) {
        this.myItems = eventsList;
        notifyDataSetChanged();
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

        @BindView(R.id.row_events_title_text_view)
        TextView rowEventsTitleTextView;
        @BindView(R.id.row_events_check_image_view)
        ImageView rowEventsCheckImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Events item) {
            this.item = item;
            rowEventsTitleTextView.setText(item.getEventName());

            if(item.isSelected()){
                rowEventsCheckImageView.setImageResource(R.mipmap.ic_login_check_on);
            }else {
                rowEventsCheckImageView.setImageResource(R.mipmap.ic_login_check_off);
            }
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
                                