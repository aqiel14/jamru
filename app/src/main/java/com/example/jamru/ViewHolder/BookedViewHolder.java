package com.example.jamru.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamru.R;

public class BookedViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    public TextView textBookerName;
    public TextView textRoom;
    public TextView textKeperluan;
    public TextView textStartTime;
    public TextView textEndTime;

    public BookedViewHolder(final View itemView) {
        super(itemView);

        textBookerName = itemView.findViewById(R.id.txt_BookedName);
        textRoom = itemView.findViewById(R.id.txt_Room);
        textKeperluan = itemView.findViewById(R.id.txt_Keperluan);
        textStartTime = itemView.findViewById(R.id.txt_StartTime);
        textEndTime = itemView.findViewById(R.id.txt_EndTime);

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

    }
}
