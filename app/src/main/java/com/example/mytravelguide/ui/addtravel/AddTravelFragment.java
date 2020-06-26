package com.example.mytravelguide.ui.addtravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mytravelguide.MainActivity;
import com.example.mytravelguide.R;
import com.example.mytravelguide.SQLiteHelper;
import com.google.android.material.textfield.TextInputEditText;

public class AddTravelFragment extends Fragment {

    Button saveButton;
    Spinner countryInput;
    TextInputEditText cityInput;
    TextInputEditText titleInput;
    TextInputEditText descriptionInput;
    SQLiteHelper DB;

    private AddTravelViewModel addTravelViewModel;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add, container, false);

        // Country set in spinner
        countryInput = view.findViewById(R.id.country);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.countryArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryInput.setAdapter(adapter);

        // Save Data
        saveButton = view.findViewById(R.id.save);
        titleInput = view.findViewById(R.id.mainTitle);
        countryInput = view.findViewById(R.id.country);
        cityInput = view.findViewById(R.id.city);
        descriptionInput = view.findViewById(R.id.description);
        DB = new SQLiteHelper(getActivity());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Empty Check
                if (
                        TextUtils.isEmpty(titleInput.getText().toString()) ||
                        TextUtils.isEmpty(cityInput.getText().toString()) ||
                        TextUtils.isEmpty(descriptionInput.getText().toString())
                ) {
                    Toast validationToast = Toast.makeText(getActivity(), "Tüm alanlarım doldurulması zorunludur.", Toast.LENGTH_SHORT);
                    validationToast.setGravity(Gravity.CENTER,0,0);
                    validationToast.show();
                } else {
                    // Save data.
                    DB.AddTravel(
                            titleInput.getText().toString(),
                            countryInput.getSelectedItem().toString(),
                            cityInput.getText().toString(),
                            descriptionInput.getText().toString(),
                            0
                    );
                    Toast validationToast = Toast.makeText(getActivity(),"Kaydedildi.", Toast.LENGTH_SHORT);
                    validationToast.setGravity(Gravity.CENTER,0,0);
                    validationToast.show();

                    Intent launchActivity= new Intent(getActivity(), MainActivity.class);
                    startActivity(launchActivity);
                }
            }
        });

        return view;
    }
}