package com.example.cw;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Category_Adap extends RecyclerView.Adapter< CategoryViewHolder > {

    private Context mContext;
    private List< Categories_Obj > categoryist;

    Category_Adap(Context mContext, List< Categories_Obj > mFlowerList) {
        this.mContext = mContext;
        this.categoryist = mFlowerList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new CategoryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        holder.mImage.setImageResource(categoryist.get(position).getCategoryImage());
        holder.mTitle.setText(categoryist.get(position).getcategoryName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, About.class);
//                mIntent.putExtra("Title", categoryist.get(holder.getAdapterPosition()).getcategoryName());
//                mIntent.putExtra("Description", categoryist.get(holder.getAdapterPosition()).getcategoryDescription());
                mIntent.putExtra("Title", holder.mTitle.getText());
//                mIntent.putExtra("cost", holder.mTitle.getText());
//                mIntent.putExtra("photo", mImage.get(position));
//                mIntent.putExtra("Image", categoryist.get(holder.getAdapterPosition()).getCategoryImage());
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryist.size();
    }
}
class CategoryViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    CategoryViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}

