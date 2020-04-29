package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jamru.Model.ModelBooking;
import com.example.jamru.ViewHolder.BookedViewHolder;
//import com.example.jamru.ViewHolder.ModelViewHolder;
import com.example.jamru.helpers.AppPreferenceManager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference modelBookingDb;

    FirebaseRecyclerOptions<ModelBooking> options;
    FirebaseRecyclerAdapter<ModelBooking, BookedViewHolder> adapter;
    AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }

        setContentView(R.layout.activity_booked);

        Toolbar toolbar = findViewById(R.id.booked_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        recyclerView = findViewById(R.id.bookedList);
        unregisterForContextMenu(recyclerView);
        database = FirebaseDatabase.getInstance();
        modelBookingDb = database.getReference("ModelBooking");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        showTask();
    }

    private void showTask() {
        options = new FirebaseRecyclerOptions.Builder<ModelBooking>()
                .setQuery(modelBookingDb, ModelBooking.class).build();

        adapter = new FirebaseRecyclerAdapter<ModelBooking, BookedViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookedViewHolder holder, int position, @NonNull final ModelBooking model) {
                holder.textRoom.setText(model.getRoom());
                holder.textBookerName.setText(model.getName());
                holder.textKeperluan.setText("Keperluan : "+model.getKeperluan());
                holder.textStartTime.setText("Booking Start Time : "+model.getStartTime());
                holder.textEndTime.setText("Durasi : "+model.getEndTime()+" Jam");

                final String post_key = getRef(position).getKey();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = model.getName();
                        String room = model.getRoom();
                        String keperluan = model.getKeperluan();
                        String startTime = model.getStartTime();
                        String duration = model.getEndTime();


                        Intent intBookForm = new Intent(BookedActivity.this,BookedDetailsActivity.class);
                        intBookForm.putExtra("room", room);
                        intBookForm.putExtra("name", name);
                        intBookForm.putExtra("keperluan", keperluan);
                        intBookForm.putExtra("start", startTime);
                        intBookForm.putExtra("duration", duration);
                        intBookForm.putExtra("key",post_key);

                        startActivity(intBookForm);




                    }
                });
            }


            @NonNull
            @Override
            public BookedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.bookedmodel_row, parent, false);
                return new BookedViewHolder(itemView);
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
