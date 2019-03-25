package com.kinght.commerce.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kinght.commerce.R;
import com.kinght.commerce.data.network.entities.Event.Event;
import com.kinght.commerce.data.network.entities.Event.Events;
import com.kinght.commerce.data.network.entities.Event.Hour;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class EventsRecylerViewAdapter extends RecyclerView.Adapter<EventsRecylerViewAdapter.ViewHolder> {


    private List<Event> myItems;
    private ItemListener myListener;
    private Context context;
    public EventsRecylerViewAdapter(Context context,ItemListener listener) {
        myListener = listener;
        this.context=context;
    }

    public void setData(List<Event> eventsList) {
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
        holder.setData(myItems.get(position),holder);
    }

    public interface ItemListener {
        void onItemClick(Event item, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Event item;

        @BindView(R.id.row_events_title_text_view)
        TextView rowEventsTitleTextView;
        @BindView(R.id.row_events_linear_layout)
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Event item,ViewHolder holder) {
            this.item = item;
            rowEventsTitleTextView.setText(item.getEventName());

            for(int i=0;i<item.getEventHours().size();i++){

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.row_events_child, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.row_events_child_image_view);
                TextView hoursTextView = (TextView) view.findViewById(R.id.row_events_child_hour_text_View);
                hoursTextView.setText(item.getEventHours().get(i).getHour());

                if(item.getEventHours().get(i).isSelected()){
                    imageView.setImageResource(R.mipmap.ic_login_check_on);
                }else {
                    imageView.setImageResource(R.mipmap.ic_login_check_off);
                }

                int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!item.getEventHours().get(finalI).isSelected()){
                            item.getEventHours().get(finalI).setSelected(true);
                            imageView.setImageResource(R.mipmap.ic_login_check_on);
                            myListener.onItemClick(item,getAdapterPosition());
                        }else {
                            item.getEventHours().get(finalI).setSelected(false);
                            imageView.setImageResource(R.mipmap.ic_login_check_off);
                            myListener.onItemClick(item,getAdapterPosition());
                        }

                    }
                });
                linearLayout.addView(view);
            }


            holder.setIsRecyclable(false);


        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item,getAdapterPosition());
            }
        }
    }


}
                                