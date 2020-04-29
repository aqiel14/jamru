package com.example.jamru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jamru.Model.Model;
import com.example.jamru.ViewHolder.ModelViewHolder;
import com.example.jamru.helpers.AppPreferenceManager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private Button booknow;
    private Button addroom;
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference modelDb;

    FirebaseRecyclerOptions<Model> options;
    FirebaseRecyclerAdapter<Model, ModelViewHolder> adapter;
    AppPreferenceManager preferenceManager;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    return inflater.inflate(R.layout.fragment_dashboard,container,false);
        View v = inflater.inflate(R.layout.fragment_dashboard,container,false);




        recyclerView = v.findViewById(R.id.roomList);
        registerForContextMenu(recyclerView);



        addroom = v.findViewById(R.id.btnAddRoom);
        addroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"OK",Toast.LENGTH_SHORT).show();
                Intent intAddroom = new Intent(getActivity(), AddRoomActivity.class);
                startActivity(intAddroom);
            }
        });

        booknow = v.findViewById(R.id.btnBookNow);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"OK",Toast.LENGTH_SHORT).show();
                Intent intBooknow = new Intent(getActivity(), BooknowActivity.class);
                startActivity(intBooknow);
            }
        });



        database = FirebaseDatabase.getInstance();
        modelDb = database.getReference("Model");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showTask(); 



        return v;
    }

    private void showTask() {

        options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(modelDb, Model.class).build();

        adapter = new FirebaseRecyclerAdapter<Model, ModelViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ModelViewHolder holder, int position, @NonNull Model model) {
                holder.textName.setText(model.getName());
                holder.textAlamat.setText(model.getAlamat());
                holder.textContact.setText(model.getContact());
                holder.textPrice.setText(model.getPricehour());
                holder.textDesc.setText(model.getDeskripsi());
            }

            @NonNull
            @Override
            public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.model_row,parent,false);
                return new ModelViewHolder(itemView);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals("Update")){
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
        } else if(item.getTitle().equals("Delete")) {
            deleteRoom(adapter.getRef(item.getOrder()).getKey());
        }

        return super.onContextItemSelected(item);
    }

    private void deleteRoom(String key) {
        modelDb.child(key).removeValue();

    }

    private void showUpdateDialog(final String key, Model item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Update");
        builder.setMessage("Please update the fields");

        View update_layout = LayoutInflater.from(getActivity()).inflate(R.layout.custom_layout,null);

        final EditText updateName = update_layout.findViewById(R.id.UpdateName);
        final EditText updateAlamat = update_layout.findViewById(R.id.UpdateAlamat);
        final EditText updateContact = update_layout.findViewById(R.id.UpdateContact);
        final EditText updatePricehour = update_layout.findViewById(R.id.UpdatePricehour);
        final EditText updateDesc = update_layout.findViewById(R.id.UpdateDesc);

        updateName.setText(item.getName());
        updateAlamat.setText(item.getAlamat());
        updateContact.setText(item.getContact());
        updatePricehour.setText(item.getPricehour());
        updateDesc.setText(item.getDeskripsi());

        builder.setView(update_layout);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = updateName.getText().toString();
                String alamat = updateAlamat.getText().toString();
                String contact = updateContact.getText().toString();
                String pricehour = updatePricehour.getText().toString();
                String deskripsi = updateDesc.getText().toString();

                Model model = new Model(name,alamat,contact,pricehour,deskripsi);
                modelDb.child(key).setValue(model);

                Toast.makeText(getActivity(), "Room is Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
