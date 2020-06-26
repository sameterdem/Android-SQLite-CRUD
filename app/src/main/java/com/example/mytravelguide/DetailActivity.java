package com.example.mytravelguide;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Samet ERDEM on 26.06.2020.
 */
public class DetailActivity extends AppCompatActivity {

    Spinner detailCountryInput;
    SQLiteHelper DB;
    TravelModel selectedTravelData;
    Boolean isVisited;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Set spinner data

        DB = new SQLiteHelper(this);

        Intent intent =  getIntent();
        final int id = intent.getIntExtra("ID", -1);
        selectedTravelData = DB.GetTravelDetail(id);

        // Get elements.
        final TextView  detailTitle = findViewById(R.id.detailTitle);
        final TextView  detailCity = findViewById(R.id.detailCity);
        final TextView  detailDescription = findViewById(R.id.detailDescription);
        final Switch detailVisible = findViewById(R.id.detailVisited);
        detailCountryInput = findViewById(R.id.detailCountry);

        // Set spinner data
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.countryArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailCountryInput.setAdapter(adapter);

        // Set spinner default value
        int spinnerPosition = adapter.getPosition(selectedTravelData.getCountry());
        detailCountryInput.setSelection(spinnerPosition);

        // Set switch default value


        detailVisible.setChecked(Boolean.valueOf(selectedTravelData.getVisited() == 1 ? true : false));

        // Set data
        detailTitle.setText(selectedTravelData.getTitle());
        detailCity.setText(selectedTravelData.getCity());
        detailDescription.setText(selectedTravelData.getDescription());

        // Set activity title
        setTitle(selectedTravelData.getTitle());

        // Update travel
        Button updateTravel = findViewById(R.id.updateTravelButton);

        updateTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        TextUtils.isEmpty(detailTitle.getText().toString()) ||
                        TextUtils.isEmpty(detailCity.getText().toString()) ||
                        TextUtils.isEmpty(detailDescription.getText().toString())
                ) {
                    Toast validationToast = Toast.makeText(DetailActivity.this, "Tüm alanlarım doldurulması zorunludur.", Toast.LENGTH_SHORT);
                    validationToast.setGravity(Gravity.CENTER,0,0);
                    validationToast.show();
                } else {
                    DB.UpdateTravel(
                            id,
                            detailTitle.getText().toString(),
                            detailCountryInput.getSelectedItem().toString(),
                            detailCity.getText().toString(),
                            detailDescription.getText().toString(),
                            detailVisible.isChecked() == true ? 1 : 0
                    );

                    Toast validationToast = Toast.makeText(DetailActivity.this,"Güncellendi.", Toast.LENGTH_SHORT);
                    validationToast.setGravity(Gravity.CENTER,0,0);
                    validationToast.show();
                    finish();
                }
            }
        });

        // Delete travel
        Button deleteTravel = findViewById(R.id.deleteTravelButton);

        deleteTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.DeleteTravel(id);
                finish();

                Toast validationToast = Toast.makeText(DetailActivity.this,"Silindi.", Toast.LENGTH_SHORT);
                validationToast.setGravity(Gravity.CENTER,0,0);
                validationToast.show();
            }
        });


    }
}
