package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamru.Model.Model;
import com.example.jamru.ViewHolder.BookModelViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BooknowActivity extends AppCompatActivity {

    private Button btnBooked;
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference modelDb;

    FirebaseRecyclerOptions<Model> options;
    FirebaseRecyclerAdapter<Model, BookModelViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booknow);


        recyclerView = findViewById(R.id.roomListBook);
        unregisterForContextMenu(recyclerView);
        database = FirebaseDatabase.getInstance();
        modelDb = database.getReference("Model");



        btnBooked = findViewById(R.id.btnBooked);

        btnBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intBooked = new Intent(BooknowActivity.this, BookedActivity.class);
                startActivity(intBooked);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        showTask();




    }

    private void showTask() {

        options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(modelDb, Model.class).build();

        adapter = new FirebaseRecyclerAdapter<Model, BookModelViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookModelViewHolder holder, int position, @NonNull final Model model) {
                holder.textName.setText(model.getName());
                holder.textAlamat.setText(model.getAlamat());

                final String post_key = getRef(position).getKey();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = model.getName();
                        String alamat = model.getAlamat();
                        String contact = model.getContact();
                        String price = model.getPricehour();
                        String desc = model.getDeskripsi();


                        Intent intBookForm = new Intent(BooknowActivity.this, RoomDetailsActivity.class);
                        intBookForm.putExtra("name", name);
                        intBookForm.putExtra("alamat", alamat);
                        intBookForm.putExtra("contact", contact);
                        intBookForm.putExtra("price", price);
                        intBookForm.putExtra("desc", desc);

                        startActivity(intBookForm);




                    }
                });
            }

            @NonNull
            @Override
            public BookModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.model_row,parent,false);
                return new BookModelViewHolder(itemView);
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



//        if(item.getTitle().equals("Book Now")){
////           showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
//
//            Intent intBookForm = new Intent(BooknowActivity.this, RoomDetailsActivity.class);
//            startActivity(intBookForm);
//
//        }
//        else if(item.getTitle().equals("Delete")) {
//            deleteRoom(adapter.getRef(item.getOrder()).getKey());
//        }

        return super.onContextItemSelected(item);
    }
}
