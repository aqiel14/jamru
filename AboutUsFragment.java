package com.example.jamru;

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
     Button btnRate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_aboutus,container,false);

        View i = inflater.inflate(R.layout.fragment_aboutus,container,false);

        ratingBar = i.findViewById(R.id.ratingBar);
        btnRate = i.findViewById(R.id.btn_Rate);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), s+"Star", Toast.LENGTH_SHORT).show();

            }
        });

        return i;
    }
}
