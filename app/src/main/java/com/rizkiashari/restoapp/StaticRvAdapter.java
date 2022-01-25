package com.rizkiashari.restoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rizkiashari.restoapp.model.RvModel;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.RvViewHolder>{

    private ArrayList<RvModel> items;

    public StaticRvAdapter(ArrayList<RvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staticrvlayoutitem,parent,false);
        RvViewHolder staticRvViewHolder = new RvViewHolder(view);
        return staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        RvModel currentitems = items.get(position);
        holder.imagemakanan.setImageResource(currentitems.getImage());
        holder.makanantext.setText(currentitems.getMakanan());
        holder.lokasitext.setText(currentitems.getLocation());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RvViewHolder extends RecyclerView.ViewHolder {

        TextView makanantext;
        TextView lokasitext;
        ImageView imagemakanan;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemakanan = itemView.findViewById(R.id.imagemakanan);
            lokasitext = itemView.findViewById(R.id.textlokasi);
            makanantext = itemView.findViewById(R.id.textmakanan);

        }
    }
}
