package com.rizkiashari.restoapp.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizkiashari.restoapp.R;
import com.rizkiashari.restoapp.databinding.ActivityDetailBinding;
import com.rizkiashari.restoapp.databinding.ActivityDetailItemBinding;
import com.rizkiashari.restoapp.model.DataItem;
import com.rizkiashari.restoapp.model.DataModel;
import com.rizkiashari.restoapp.model.MenuModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {

    private ArrayList<DataItem> itemArrayList;

    public AdapterMenu(ArrayList<DataItem> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataItem item = itemArrayList.get(position);
        holder.binding.tvNameMenu.setText(item.getNameFood());
        holder.binding.tvPriceMenu.setText(item.getPrice());
        Glide.with(holder.itemView.getContext())
                .load(item.getPictureFood())
                .into(holder.binding.ivPictureMenu);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ActivityDetailItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ActivityDetailItemBinding.bind(itemView);
        }
    }

}
