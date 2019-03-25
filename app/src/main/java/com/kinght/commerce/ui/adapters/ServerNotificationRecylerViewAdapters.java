package com.kinght.commerce.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Settings.Settings;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ServerNotificationRecylerViewAdapters extends RecyclerView.Adapter<ServerNotificationRecylerViewAdapters.ViewHolder> {


    private List<Settings> myItems;
    private ItemListener myListener;

    public ServerNotificationRecylerViewAdapters(ItemListener listener) {
        myListener = listener;
    }

    public void setData(List<Settings> settingsList) {
        this.myItems = settingsList;
        notifyDataSetChanged();
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_server_notification, parent, false)); // TODO
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
        void onItemClick(Settings item, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.row_server_notification_server_name_text_view)
        TextView rowServerNotificationServerNameTextView;
        @BindView(R.id.row_server_notification_select_image_view)
        ImageView rowServerNotificationSelectImageView;
        // TODO - Your view members
        public Settings item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Settings item) {
            this.item = item;
            rowServerNotificationServerNameTextView.setText(item.getName());


            if (item.getIsLiked()) {
                rowServerNotificationSelectImageView.setImageResource(R.mipmap.ic_login_check_on);
            } else {
                rowServerNotificationSelectImageView.setImageResource(R.mipmap.ic_login_check_off);
            }
            rowServerNotificationSelectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getIsLiked()) {
                        rowServerNotificationSelectImageView.setImageResource(R.mipmap.ic_login_check_off);
                        item.setIsLiked(false);
                    } else {
                        rowServerNotificationSelectImageView.setImageResource(R.mipmap.ic_login_check_on);
                        item.setIsLiked(true);
                    }
                    myListener.onItemClick(item,getAdapterPosition());

                }
            });

            // TODO set data to view
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
              //  myListener.onItemClick(item,getAdapterPosition());
            }
        }
    }


}
                                