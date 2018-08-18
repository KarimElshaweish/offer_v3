package com.example.karim.offer_v3.Firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.karim.offer_v3.Model.Category;
import com.example.karim.offer_v3.Model.Company;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

import static com.example.karim.offer_v3.Helper.Helper.getExt;

/**
 * Created by Karim on 8/17/2018.
 */

public class Transfer {
   private FirebaseStorage firebaseStorage;
   private StorageReference storageReference;
   private Context _ctx;
   private String Key;
    private String Url;

    public Transfer(Context _ctx,String Key){
    firebaseStorage= FirebaseStorage.getInstance();
    storageReference=firebaseStorage.getReference();
    this._ctx=_ctx;
    this.Key=Key;
}

    public void uploadCategory(String Name, Uri logo, final Uri cover){
    final Category category=new Category();
    category.SetCategoryName(Name);
    String img1Url = Key+""+"logo"+ Calendar.getInstance().getTime().toString()
            + "." + getExt(logo,_ctx);
    final StorageReference ref = storageReference.child("Category/" + img1Url);
    ref.putFile(logo).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            category.SetLogoImage(taskSnapshot.getDownloadUrl().toString());
            ref.putFile(cover).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   category.SetCoverImage(taskSnapshot.getDownloadUrl().toString());
                   UploadAllData(category);
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(_ctx, "failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}
    private void UploadAllData(final Object model) {
            ShowDialog();
            FirebaseDatabase.getInstance().getReference().child("Category").child(Key).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Toast.makeText(_ctx, "Done", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(_ctx, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
    }
    ProgressDialog progressDialog;
    private void ShowDialog(){
        progressDialog=new ProgressDialog(_ctx);
        progressDialog.setTitle("adding....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public  void UploadCompany(final Company company, Uri filepath){
        ShowDialog();
        String img1Url = Key+""+"logo"+ Calendar.getInstance().getTime().toString()
                + "." + getExt(filepath,_ctx);
        final StorageReference ref=storageReference.child("Company/"+img1Url);
        ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                company.SetCompanyLogo(taskSnapshot.getDownloadUrl().toString());
                FirebaseDatabase.getInstance().getReference().child("Company").setValue(company).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(_ctx, "Done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
