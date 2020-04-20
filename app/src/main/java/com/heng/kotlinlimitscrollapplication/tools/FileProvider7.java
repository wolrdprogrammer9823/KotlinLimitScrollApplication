package com.heng.kotlinlimitscrollapplication.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

public class FileProvider7 {

    public static Uri getFileUri(Context context,File file) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return getFileUri24(context, file);
        } else {

            return Uri.fromFile(file);
        }
    }

    public static Uri getFileUri24(Context context, File file) {

        Uri uri = FileProvider.getUriForFile(context, context.getPackageName()
                                             + ".android7.file_provider", file);
        return uri;
    }

    public static void setDataAndTypeForIntent(Context context, File file, Intent intent,
                                               boolean writeable, String type) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            intent.setDataAndType(getFileUri(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeable) {

                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {

            intent.setDataAndType(getFileUri(context, file), type);
        }
    }
}
