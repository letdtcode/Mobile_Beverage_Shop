package com.iostar.beverageshop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

public class FileConvertUtils {
    private static Context mContext;

    public FileConvertUtils(Context mContext) {
        this.mContext = mContext;
    }

    public static Uri covertToUri(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "val", null);
        return Uri.parse(path);
    }

    public void release() {
        this.mContext = null;
    }
}
