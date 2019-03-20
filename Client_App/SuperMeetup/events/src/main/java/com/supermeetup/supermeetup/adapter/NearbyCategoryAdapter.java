package com.supermeetup.supermeetup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.supermeetup.supermeetup.R;
import com.supermeetup.supermeetup.model.Category;
import com.supermeetup.supermeetup.viewholder.NearbyCategoryViewHolder;

import java.util.ArrayList;

/**
 * Created by yuxin on 10/14/17.
 */

public class NearbyCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEWTYPE_ITEM = 0;
    private static final int VIEWTYPE_END = 1;

    private Context mContext;
    private ArrayList<Category> mCategories = new ArrayList<>();

    public NearbyCategoryAdapter(Context context, ArrayList<Category> categories){
        mContext = context;
        mCategories = categories;
    }

    @Override
    public int getItemViewType(int position){
        if(position < 7 ){
            return VIEWTYPE_ITEM;
        }else{
            return VIEWTYPE_END;
        }
    }

    @Override
    public int getItemCount() {
        if(mCategories == null || mCategories.size() == 0){
            return 0;
        }else if(mCategories.size() > 7){
            return 8;
        }else{
            return mCategories.size() + 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEWTYPE_END:
                return new EndViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false)));
            case VIEWTYPE_ITEM:
                return new NearbyCategoryViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false)));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType){
            case VIEWTYPE_ITEM:
                ((NearbyCategoryViewHolder) holder).bind(mCategories.get(position));
                break;
            case VIEWTYPE_END:
                ((EndViewHolder) holder).bind();
                break;

        }
    }

    class EndViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mTitle;

        public EndViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.category_image);
            mTitle = (TextView) itemView.findViewById(R.id.category_title);
        }

        public void bind(){
            mImage.setImageResource(R.mipmap.ic_more);
            mTitle.setText(R.string.more);
        }
    }
}
