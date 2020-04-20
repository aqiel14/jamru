package com.example.jamru.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamru.R;

public class ModelViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    public TextView textName;
    public TextView textAlamat;
    public TextView textContact;
    public TextView textPrice;
    public TextView textDesc;

    public ModelViewHolder(final View itemView) {
        super(itemView);


        textName=itemView.findViewById(R.id.txt_Name);
        textAlamat=itemView.findViewById(R.id.txt_Alamat);
        textContact=itemView.findViewById(R.id.txt_Contact);
        textPrice = itemView.findViewById(R.id.txt_Price);
        textDesc= itemView.findViewById(R.id.txt_Desc);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemView.showContextMenu();
            }
        });

        itemView.setOnCreateContextMenuListener(this);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle("Select Menu");
                menu.add(0,0,getAdapterPosition(),"Update");
                menu.add(0,1,getAdapterPosition(),"Delete");



    }
}
