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
<<<<<<< HEAD
import java.nio.file.Files;
import java.nio.file.Path;
=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ResourceLoader {

    /**
<<<<<<< HEAD
=======
     * @param res
     * @throws IOException
     * @throws FileNotFoundException
     * @throws IOException
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
     * Source : https://stackoverflow.com/questions/2364185/android-read-a-gzip-file-in-the-assets-folder
     */

    static void unpackResources(Context mContext) throws FileNotFoundException, IOException {
<<<<<<< HEAD
        unpackResources2(mContext,"Prayer.zip");
        unpackResources2(mContext,"WelBeibleNet.zip");
    }


    static void unpackResources2(Context mContext, String fileName) throws FileNotFoundException, IOException {
=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
        final int BUFFER = 8192;

        //android.content.res.Resources t = mContext.getAssets();

        //InputStream fis = t.openRawResource(R.raw.resources);
<<<<<<< HEAD
        InputStream fis = mContext.getAssets().open(fileName);
        Log.v("NOTAG", "Opening "+fileName);
=======
        InputStream fis = mContext.getAssets().open("Prayer.zip");
        Log.v("NOTAG", "Opening Prayer.zip");
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1

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

<<<<<<< HEAD
        public static void unzip(InputStream is, String theTargetDir) {
            try {
                File inputDir = new File(theTargetDir);
                Path targetPath = inputDir.toPath();
                Path targetDir = targetPath.toAbsolutePath();

                ZipInputStream zipIn = new ZipInputStream(is);

                for(ZipEntry ze; (ze = zipIn.getNextEntry()) != null; ) {
                    Path resolvedPath = targetDir.resolve(ze.getName()).normalize();
                    if(!resolvedPath.startsWith(targetDir)){
                        throw new RuntimeException("Entry with Illegal Path:"+ze.getName());
                    }
                    if(ze.isDirectory()) {
                        Files.createDirectories(resolvedPath);
                    }
                    else {
                        //Log.v("TAG", String.valueOf(resolvedPath) + "-" +ze.getName());
                        File f = new File(String.valueOf(resolvedPath));
                        if(f.exists()) {
                            f.delete();
                            AppDebug.log("TAG", "Deleting "+f.getName());
                        }
                        Files.createDirectories(resolvedPath.getParent());
                        Files.copy(zipIn,resolvedPath);
                    }
                }
            }
            catch(Exception e ) {
                e.printStackTrace();
            }
        }

        public static void unzip2(InputStream stream, String destination) {
=======
        public static void unzip(InputStream stream, String destination) {
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
            dirChecker(destination, "");
            byte[] buffer = new byte[BUFFER_SIZE];
            try {
                ZipInputStream zin = new ZipInputStream(stream);
                ZipEntry ze = null;

                while ((ze = zin.getNextEntry()) != null) {
                    Log.v(TAG, "Unzipping :" +destination+"/"+ ze.getName());

<<<<<<< HEAD

=======
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
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
<<<<<<< HEAD
                Log.e(TAG, "Unzip Error:", e);
=======
                Log.e(TAG, "unzip", e);
>>>>>>> c8d18d3a23865f287581bd62aeea5a38dd359ff1
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
