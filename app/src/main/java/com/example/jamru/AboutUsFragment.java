package com.example.jamru;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutUsFragment extends Fragment {

    RatingBar ratingBar;
    Button btnRate, btnFollow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_aboutus,container,false);

        View i = inflater.inflate(R.layout.fragment_aboutus,container,false);

        ratingBar = i.findViewById(R.id.ratingBar);
        btnRate = i.findViewById(R.id.btn_Rate);
        btnFollow = i.findViewById(R.id.btnFollow);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), s+"Star", Toast.LENGTH_SHORT).show();

            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(Intent.ACTION_VIEW);
                String url = "https://www.instagram.com/firnaa_a/";
                followIntent.setData(Uri.parse(url));
                startActivity(followIntent);
            }
        });


        return i;
    }
}
