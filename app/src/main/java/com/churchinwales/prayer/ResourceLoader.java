package com.churchinwales.prayer;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ResourceLoader {

    /**
     * @param res
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IOException
     * Source : https://stackoverflow.com/questions/2364185/android-read-a-gzip-file-in-the-assets-folder
     */

    static void unpackResources(Context mContext) throws FileNotFoundException, IOException {
        final int BUFFER = 8192;

        //android.content.res.Resources t = mContext.getAssets();

        //InputStream fis = t.openRawResource(R.raw.resources);
        InputStream fis = mContext.getAssets().open("Prayer.zip");
        Log.v("NOTAG", "Opening Prayer.zip");

        if (fis == null)
            return;

        ZipInputStream zin = new ZipInputStream(new BufferedInputStream(fis,
                BUFFER));
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            int count;

            FileOutputStream fos = mContext.openFileOutput(entry.getName(), 0);
            BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

            byte data[] = new byte[BUFFER];

            while ((count = zin.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, count);
                Log.v("NOTAG", "writing "+count + " to "+entry.getName());
            }
            dest.flush();
            dest.close();
        }
        zin.close();

    }

    //source: https://stackoverflow.com/questions/15628421/unzip-file-in-android-assets
        private static final int BUFFER_SIZE = 1024 * 10;
        private static final String TAG = "Decompress";

        public static void unzipFromAssets(Context context, String zipFile, String destination) {
            try {
                if (destination == null || destination.length() == 0)
                    destination = context.getFilesDir().getAbsolutePath();
                InputStream stream = context.getAssets().open(zipFile);
                unzip(stream, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void unzip(String zipFile, String location) {
            try {
                FileInputStream fin = new FileInputStream(zipFile);
                unzip(fin, location);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        public static void unzip(InputStream stream, String destination) {
            dirChecker(destination, "");
            byte[] buffer = new byte[BUFFER_SIZE];
            try {
                ZipInputStream zin = new ZipInputStream(stream);
                ZipEntry ze = null;

                while ((ze = zin.getNextEntry()) != null) {
                    Log.v(TAG, "Unzipping :" +destination+"/"+ ze.getName());

                    if (ze.isDirectory()) {
                        dirChecker(destination, ze.getName());
                    } else {
                        File f = new File(destination, ze.getName());
                        if(f.exists()) {
                            f.delete();
                            Log.v("TAG", "Deleting "+f.getName());
                        }
                        if (!f.exists()) {
                            boolean success = f.createNewFile();
                            if (!success) {
                                Log.w(TAG, "Failed to create file " + f.getName());
                                continue;
                            }
                            else {
                                Log.v("TAG","Created "+f.getName());
                            }
                            FileOutputStream fout = new FileOutputStream(f);
                            int count;
                            while ((count = zin.read(buffer)) != -1) {
                                fout.write(buffer, 0, count);
                            }
                            zin.closeEntry();
                            fout.close();
                        }
                    }

                }
                zin.close();
            } catch (Exception e) {
                Log.e(TAG, "unzip", e);
            }

        }

        private static void dirChecker(String destination, String dir) {
            File f = new File(destination, dir);

            if (!f.isDirectory()) {
                boolean success = f.mkdirs();
                if (!success) {
                    Log.w(TAG, "Failed to create folder " + f.getName());
                }
            }
        }


}
