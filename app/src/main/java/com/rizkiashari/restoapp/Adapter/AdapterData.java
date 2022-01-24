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

import com.rizkiashari.restoapp.R;
import com.rizkiashari.restoapp.model.DataModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private Context ctx;
    private List<DataModel> listFood;

    public AdapterData(Context ctx, List<DataModel> listFood) {
        this.ctx = ctx;
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listFood.get(position);

        holder.idResto.setText(String.valueOf(dm.getId()));
        holder.picture.setImageURI(Uri.parse(dm.getPicture()));
        holder.nameResto.setText(dm.getNamaResto());
        holder.locationResto.setText(dm.getLocationResto());
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        ImageView picture;
        TextView nameResto, locationResto, idResto;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);
            idResto = itemView.findViewById(R.id.idResto);
            nameResto = itemView.findViewById(R.id.namaResto);
            locationResto = itemView.findViewById(R.id.locationResto);
        }
    }

}
