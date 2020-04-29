package com.example.jamru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jamru.helpers.AppPreferenceManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookedDetailsActivity extends AppCompatActivity {

    AppPreferenceManager preferenceManager;
    Button deleteBooking;
    FirebaseDatabase database;
    DatabaseReference modelDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark_NoActionBar);
        }else{
            setTheme(R.style.AppTheme_NoActionBar);
        }




        setContentView(R.layout.activity_booked_details);

        Toolbar toolbar = findViewById(R.id.bookeddetails_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));



        deleteBooking = findViewById(R.id.delBook);

        deleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                modelDb = database.getReference("ModelBooking");
                if(getIntent().hasExtra("key")) {
                    String key = getIntent().getStringExtra("key");
                    deleteBooking(key);
                    Intent intBackToBooked = new Intent(BookedDetailsActivity.this,BookedActivity.class);
                    startActivity(intBackToBooked);
                    finish();
                }
            }
        });




        if(getIntent().hasExtra("room")) {

            final String room = getIntent().getStringExtra("room");
            final String name = getIntent().getStringExtra("name");
            final String keperluan = getIntent().getStringExtra("keperluan");
            final String start = getIntent().getStringExtra("start");
            final String duration = getIntent().getStringExtra("duration");

            setTv(name, room, keperluan, start, duration);

        } else {
            Toast.makeText(this, "Intent not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTv(String room, String name, String keperluan, String start, String duration) {
        TextView tvRoom = findViewById(R.id.tvroom);
        TextView tvName = findViewById(R.id.tvname);
        TextView tvKeperluan = findViewById(R.id.tvkeperluan);
        TextView tvStart = findViewById(R.id.tvstart);
        TextView tvDuration = findViewById(R.id.tvduration);

        tvRoom.setText("Nama Pemesan : " + room);
        tvName.setText("Ruangan : " + name);
        tvKeperluan.setText("Keperluan : " + keperluan);
        tvStart.setText("Start time : " +start);
        tvDuration.setText("Durasi Pinjam : " + duration +" jam");

    }



    private void deleteBooking(String key) {
        modelDb.child(key).removeValue();

    }


}
