package com.example.karim.offer_v3.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.karim.offer_v3.Firebase.Transfer;
import com.example.karim.offer_v3.Model.Company;
import com.example.karim.offer_v3.R;
import com.google.android.gms.maps.model.Circle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.karim.offer_v3.Firebase.GetData.GetAllCategory;


public class AddCompanyActivity extends AppCompatActivity {

    Spinner spinner;
    private void setAdapter(List<String>categoryList){
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categoryList);
        spinner.setAdapter(adapter);
    }
    CircleImageView companyLogo;
    EditText companyName,personalName,email,tel,mobile,desierdUserName;
    Button createBtn,newBtn;
    private  void __int__(){
        companyLogo=findViewById(R.id.companyImage);
        companyName=findViewById(R.id.companyName);
        personalName=findViewById(R.id.personalName);
        email=findViewById(R.id.email);
        tel=findViewById(R.id.tel);
        mobile=findViewById(R.id.mobile);
        desierdUserName=findViewById(R.id.desiredName);
        spinner=findViewById(R.id.spinner);
        newBtn=findViewById(R.id.newBtn);
        createBtn=findViewById(R.id.createBtn);

    }
    public  void GetData(View view){
        if( companyName.getText()!=null&&personalName.getText()!=null&&email.getText()!=null&&tel.getText()!=null&&
                mobile.getText()!=null&&desierdUserName.getText()!=null){
            Company company=new Company();
            company.SetCompanyName(companyName.getText().toString());
            company.SetPersonalName(personalName.getText().toString());
            company.SetEmail(email.getText().toString());
            company.SetTel(tel.getText().toString());
            company.SetMobile(mobile.getText().toString());
            company.SetCategory(spinner.getSelectedItem().toString());
            company.SetDesierdUserName(desierdUserName.getText().toString());
            Transfer transfer=new Transfer(this,"Company");
            transfer.UploadCompany(company,filePath);
        }
    }
    final  int PICK_IMAGE=71;
    Uri filePath;
    public void OpenGallery(View view){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chose Image"),PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE&&data.getData()!=null&&resultCode==RESULT_OK){
            try {
                filePath=data.getData();
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                companyLogo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        __int__();
        FirebaseDatabase.getInstance().getReference().child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String>categoryList=GetAllCategory((Map)dataSnapshot.getValue());
                setAdapter(categoryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
