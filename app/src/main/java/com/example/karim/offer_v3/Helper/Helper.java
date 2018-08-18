package com.example.karim.offer_v3.Helper;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Karim on 8/17/2018.
 */

public class Helper {
    public static String getExt(Uri Url, Context _ctx){
        ContentResolver CR=_ctx.getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(CR.getType(Url));
    }

}
