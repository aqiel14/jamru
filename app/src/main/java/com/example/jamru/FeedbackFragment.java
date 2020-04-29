package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jamru.Model.FeedBack;
import com.example.jamru.Model.ModelBooking;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackFragment extends Fragment {
    private EditText txtNama;
    private EditText txtEmail;
    private Spinner FeedbackType;
    private EditText txtDetails;
    private Button btnSubmit;

    FirebaseDatabase database;
    DatabaseReference modelFeedbackDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        txtNama = v.findViewById(R.id.et_name);
        txtEmail = v.findViewById(R.id.et_email);
        FeedbackType = v.findViewById(R.id.spinner_feedback);
        txtDetails = v.findViewById(R.id.feedback_details);
        btnSubmit = v.findViewById(R.id.submitbutton);

        database = FirebaseDatabase.getInstance();
        modelFeedbackDb = database.getReference("FeedBack");



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirebase();
            }
        });
        return v;
    }

    private void saveToFirebase() {
        String nama = txtNama.getText().toString();
        String email = txtEmail.getText().toString();
        String tipefeedback = FeedbackType.getSelectedItem().toString();
        String details = txtDetails.getText().toString();

        if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(tipefeedback) && !TextUtils.isEmpty(details)) {
           FeedBack model = new FeedBack(nama, email, tipefeedback, details);

            modelFeedbackDb.push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getActivity(), "Feedback has been sent", Toast.LENGTH_SHORT).show();
                    txtNama.setText("");
                    txtEmail.setText("");
                    txtDetails.setText("");
                    FeedbackType.setSelection(0);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(getActivity(), "Isi semua field.", Toast.LENGTH_SHORT).show();
        }
    }

}








