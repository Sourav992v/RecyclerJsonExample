package com.sourav.developer.recyclerviewjsonexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private List<ExampleItem> mExampleItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    ExampleAdapter(List<ExampleItem> mExampleItems) {
        this.mExampleItems = mExampleItems;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.example_item,parent,false
        );
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem = mExampleItems.get(position);
        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        int likeCount = currentItem.getLikesCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: " + likeCount);

        Picasso.get()
                .load(imageUrl)
                .fit()
                .centerInside()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleItems.size();
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextViewCreator;
        TextView mTextViewLikes;

        ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
