package com.supermeetup.supermeetup.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.supermeetup.supermeetup.R;
import com.supermeetup.supermeetup.model.Event;
import com.supermeetup.supermeetup.model.Group;
import com.supermeetup.supermeetup.model.Photo;
import com.supermeetup.supermeetup.model.Venue;

/**
 * Created by Irene on 10/15/17.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView mName;
    private TextView mGroup;
    private TextView mAddress;
    private ImageView mImage;

    public EventViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.event_name);
        mGroup = (TextView) itemView.findViewById(R.id.event_group);
        mAddress = (TextView) itemView.findViewById(R.id.event_address);
        mImage = (ImageView) itemView.findViewById(R.id.event_image);
    }

    public void bind(Event event){
        mName.setText(event.getName());
        Group group = event.getGroup();
        if(group != null) {
            mGroup.setText(group.getName());
            Photo photo = group.getPhoto();
            if(photo != null) {
                String photoLink = photo.getPhotoLink();
                if (!TextUtils.isEmpty(photoLink)) {
                    Picasso.with(mName.getContext())
                            .load(photoLink)
                            .placeholder(R.drawable.logo)
                            .into(mImage);
                }
            }
        }
        Venue venue = event.getVenue();
        if(venue!=null && venue.isVisible()){
            mAddress.setText(venue.getFullAddress());
        }

    }


}
