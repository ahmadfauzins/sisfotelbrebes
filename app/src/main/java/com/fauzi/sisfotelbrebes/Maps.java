package com.fauzi.sisfotelbrebes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class Maps extends AppCompatActivity {


    Button pick;
    TextView hasil;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        pick = findViewById(R.id.cari);
        hasil = findViewById(R.id.hasil);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Maps.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (requestCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                StringBuilder stringBuilder = new StringBuilder();
                String a = String.valueOf(place.getLatLng().toString());
                String b = String.valueOf(place.getLatLng().toString());
//
//                String latitude = String.valueOf(place.getLatLng());
//                String longitude = String.valueOf(place.getLatLng());
                stringBuilder.append("Lat : ");
                stringBuilder.append(a);
                stringBuilder.append("\n");
                stringBuilder.append("Ling : ");
                stringBuilder.append(b);
                hasil.setText(stringBuilder.toString());

            }
        }

    }
}

