package com.supermeetup.supermeetup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.supermeetup.supermeetup.R;
import com.supermeetup.supermeetup.model.Category;
import com.supermeetup.supermeetup.model.Event;
import com.supermeetup.supermeetup.viewholder.NearbyCategoryListViewHolder;
import com.supermeetup.supermeetup.viewholder.EventViewHolder;

import java.util.ArrayList;

/**
 * Created by yuxin on 10/14/17.
 */

public class NearbyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEWTYPE_CATEGORYLIST = 0;
    private static final int VIEWTYPE_EVENTITEM = 1;

    private Context mConext;
    private ArrayList<Event> mEvents = new ArrayList<>();
    private ArrayList<Category> mCategories = new ArrayList<>();

    public NearbyAdapter(Context context){
        mConext = context;
    }

    public void setCategories(ArrayList<Category> categories){
        mCategories = categories;
        notifyDataSetChanged();
    }

    public void setEvents(ArrayList<Event> events){
        mEvents = events;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return VIEWTYPE_CATEGORYLIST;
        }else{
            return VIEWTYPE_EVENTITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEWTYPE_CATEGORYLIST:
                return new NearbyCategoryListViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorylist, parent, false)));
            case VIEWTYPE_EVENTITEM:
                return new EventViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false)));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType){
            case VIEWTYPE_CATEGORYLIST:
                ((NearbyCategoryListViewHolder) holder).bind(mCategories);
                break;
            case VIEWTYPE_EVENTITEM:
                ((EventViewHolder) holder).bind(mEvents.get(position-1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mEvents.size() + 1;
    }
}
