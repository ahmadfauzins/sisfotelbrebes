package com.fauzi.sisfotelbrebes.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fauzi.sisfotelbrebes.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;


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
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//                try {
//                    startActivityForResult(builder.build(Maps.this), PLACE_PICKER_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == LOC_REQ_CODE) {
//            if (resultCode == RESULT_OK) {
//                showPlacePicker();
//            }
//        }else if(requestCode == PLACE_PICKER_REQ_CODE){
//            if (resultCode == RESULT_OK) {
//                place = PlacePicker.getPlace(this, data);
//                name.setText(place.getName());
//            }
//        }
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (requestCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(data,this);
//                StringBuilder stringBuilder = new StringBuilder();
////                String a = String.valueOf(place.getLatLng().toString());
////                String b = String.valueOf(place.getLatLng().toString());
////
//                String latitude = String.valueOf(place.getLatLng().latitude);
//                String longitude = String.valueOf(place.getLatLng().longitude);
//                stringBuilder.append("Lat : ");
//                stringBuilder.append(latitude);
//                stringBuilder.append("\n");
//                stringBuilder.append("Ling : ");
//                stringBuilder.append(longitude);
//                hasil.setText(stringBuilder.toString());
//
//            }
//
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(data, this);
//                String toastMsg = String.format(
//                        "Place: %s \n" +
//                                "Alamat: %s \n" +
//                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude + " " + place.getLatLng().longitude);
//                hasil.setText(toastMsg);
//            }
//        }
    }
}

