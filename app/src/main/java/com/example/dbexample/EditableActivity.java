package com.example.dbexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbexample.Data.profileData;
import com.example.dbexample.Fragements.HomeFragment;
import com.example.dbexample.adapters.ProfileWrapper;
import com.example.dbexample.adapters.recyclerAdapter;
import com.example.dbexample.databasePackages.dbProfiles;

public class EditableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable);

        final ProfileWrapper profileWrapper = new ProfileWrapper();
        profileData p = null;
        Button btnBack = findViewById(R.id.btnBack);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        TextView tvId = findViewById(R.id.tvID);
        EditText etName = findViewById(R.id.editTextName);
        EditText etAge = findViewById(R.id.editTextAge);
        EditText etPhone = findViewById(R.id.editTextPhone);
        EditText etJob = findViewById(R.id.editTextJobDesc);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int position = getIntent().getIntExtra("position", -1);
        if (position != -1) {
            String name = getIntent().getStringExtra("Name");
            String Id = getIntent().getStringExtra("ID");
            String Age = getIntent().getStringExtra("Age");
            String Phone = getIntent().getStringExtra("Phone");
            String JobDesc = getIntent().getStringExtra("JobDesc");

            try {
                tvId.setText(Id);
                etName.setText(name);
                etAge.setText(Age);
                etPhone.setText(Phone);
                etJob.setText(JobDesc);

            } catch (NumberFormatException e) {
                Log.e("EditableActivity", "NumberFormatException: " + e.getMessage());
            }
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intId = Integer.parseInt(String.valueOf(tvId.getText()));
                int intAge = Integer.parseInt(String.valueOf(etAge.getText()));
                String etNameValue = String.valueOf(etName.getText());
                String etPhoneValue = String.valueOf(etPhone.getText());
                String etJobValue = String.valueOf(etJob.getText());
                profileData p = new profileData(intId, etNameValue, etPhoneValue, intAge, etJobValue);

                if (p != null) { // Check if p is not null before using it
                    dbProfiles dbobj = new dbProfiles(getApplicationContext());
                    int isUpdated = dbobj.updateData(p);

                    if (isUpdated > 0) {
                        HomeFragment.getNotifiedChanges();
                       finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"Error ",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please Enter A Value",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
