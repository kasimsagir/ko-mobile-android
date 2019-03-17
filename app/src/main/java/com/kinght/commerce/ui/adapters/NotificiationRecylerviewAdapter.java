package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Notification.Notifications;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificiationRecylerviewAdapter extends RecyclerView.Adapter<NotificiationRecylerviewAdapter.ViewHolder> {

    private List<Notifications> myItems;
    private ItemListener myListener;

    public NotificiationRecylerviewAdapter( ItemListener listener) {
        myListener = listener;
    }

    public void setData(List<Notifications> notifications){
        this.myItems=notifications;
        notifyDataSetChanged();
    }
    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notification, parent, false)); // TODO
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
        void onItemClick(Notifications item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Notifications item;

        @BindView(R.id.row_notification_header_text_view)
        TextView rowNotificationHeaderTextView;
        @BindView(R.id.row_notification_message_text_view)
        TextView rowNotificationMessageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Notifications item) {
            this.item = item;
            rowNotificationHeaderTextView.setText(item.getTitle());
            rowNotificationMessageTextView.setText(item.getMessage());
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
                                