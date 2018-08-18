package com.example.karim.offer_v3.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.karim.offer_v3.Firebase.Transfer;
import com.example.karim.offer_v3.Model.Category;
import com.example.karim.offer_v3.R;

import java.io.IOException;

public class AddCategory extends AppCompatActivity {

    final int IMAGE_PICK=71;
    boolean cover=false,logo=false;
    ImageView coverImage;
    ImageView logoImage;
    EditText categoryName;
    Boolean coverCheck=false,logoCheck=false;
    Uri logoFilePath,coverFilePath;
    private void SetImage(ImageView imageView,Intent data) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
        imageView.setImageBitmap(bitmap);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(cover){
            if(requestCode==IMAGE_PICK&&data.getData()!=null&&resultCode==RESULT_OK){
                    cover=false;
                    coverCheck=!cover;
                try {
                    SetImage(coverImage,data);
                    coverFilePath=data.getData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        if(logo) {
            if (requestCode == IMAGE_PICK && data.getData() != null && resultCode == RESULT_OK) {
                logo=false;
                logoCheck=!logo;
                try {
                    SetImage(logoImage,data);
                    logoFilePath=data.getData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void OpenGallery(){
        Intent intent=new Intent();
        intent.setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chose Image"),IMAGE_PICK);
    }
    public void ChangePicture(View v){
            switch (v.getId()){
                case R.id.cover:
                    cover=true;
                    OpenGallery();
                    break;
                case R.id.logoImag:
                    logo=true;
                    OpenGallery();
                    break;
            }
    }
    public void Add(View v){
        if(categoryName.getText()==null)
            categoryName.setError("Please enter the category name");
        if(!logoCheck||!coverCheck)
            Toast.makeText(this, "please add the cover and the logo", Toast.LENGTH_SHORT).show();
        else{
            Transfer transfer=new Transfer(this,categoryName.getText().toString());
            transfer.uploadCategory(categoryName.getText().toString(),logoFilePath,coverFilePath);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        coverImage=findViewById(R.id.cover);
        logoImage=findViewById(R.id.logoImag);
        categoryName=findViewById(R.id.CategoryName);
    }
}
