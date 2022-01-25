package com.rizkiashari.restoapp2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD:app/src/main/java/com/rizkiashari/restoapp2/Adapter/AdapterData.java
import com.rizkiashari.restoapp2.R;
import com.rizkiashari.restoapp2.model.DataModel;
=======
import com.rizkiashari.restoapp.DetailActivity;
import com.rizkiashari.restoapp.R;
import com.rizkiashari.restoapp.model.DataModel;
>>>>>>> 9aea9a08a8c13283c457c4647b725b6dead236ea:app/src/main/java/com/rizkiashari/restoapp/Adapter/AdapterData.java

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private Context ctx;
    private List<DataModel> listFood;
    private Activity activity;

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

        holder.layoutUtama.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent goDetail = new Intent(activity, DetailActivity.class);
                goDetail.putExtra("Intent name Resto: ", dm.getNamaResto());
                goDetail.putExtra("Intent Open Date: ", dm.getOpenDate());
                goDetail.putExtra("Intent Picture: ", dm.getPicture());
                goDetail.putExtra("Intent Location: ", dm.getLocationResto());
                goDetail.putExtra("Intent Address: ", dm.getAddressResto());

                activity.startActivity(goDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        ImageView picture;
        TextView nameResto, locationResto, idResto;
        LinearLayout layoutUtama;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);
            idResto = itemView.findViewById(R.id.idResto);
            nameResto = itemView.findViewById(R.id.namaResto);
            locationResto = itemView.findViewById(R.id.locationResto);

            layoutUtama = itemView.findViewById(R.id.layoutUtama);
        }
    }

}
