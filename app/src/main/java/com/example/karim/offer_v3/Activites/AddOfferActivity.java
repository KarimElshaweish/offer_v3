package com.example.karim.offer_v3.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karim.offer_v3.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


import java.io.IOException;

public class AddOfferActivity extends AppCompatActivity {

    ImageView offerImage,imgPin;
    final int PICK_IMAGE=71;
    Uri imagePath;
    int PLACE_PIKCER_REQUEST=1;
    final int PICKIMAGE_REQUEST = 71;
    boolean gps=false;
    TextView placeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        placeText=findViewById(R.id.place);
        offerImage=findViewById(R.id.offer_image);
        offerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImage();
            }
        });
        imgPin=findViewById(R.id.img_pin);
        imgPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
                try {
                    Intent intent=builder.build(AddOfferActivity.this);
                    startActivityForResult(intent,PLACE_PIKCER_REQUEST);
                    gps=!gps;
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(!gps) {
            if (requestCode == PICKIMAGE_REQUEST && data.getData() != null && resultCode == RESULT_OK) {
                try {
                    imagePath = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagePath);
                    offerImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            gps=!gps;
            if(requestCode==PLACE_PIKCER_REQUEST&&resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                String address=String.format(place.getAddress().toString());
                placeText.setText(address);
            }
        }
    }
    private void PickImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chose Image"),PICK_IMAGE);
    }
}
