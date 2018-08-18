package com.example.karim.offer_v3.Firebase;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Karim on 8/16/2018.
 */

public class FirebaseInstance extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        FirebaseDatabase.getInstance().getReference().setValue("com.example.karim.offer_v3.Firebase");
        Log.d("Id_Token",refreshToken);
    }

}
