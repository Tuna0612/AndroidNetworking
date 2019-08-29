package com.sean.anw;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Objects;

public class ViewImageActivity extends AppCompatActivity {
    private ImageView imgImageDetail;
    String link;
    private ProgressDialog progressBar;
    private FloatingActionMenu fab;
    private FloatingActionButton fabWallpaper;
    private FloatingActionButton fabDownload;
    private FloatingActionButton fabShare;
    private FloatingActionButton fabFavorite;
    boolean showButton = false;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        imgImageDetail = findViewById(R.id.imgImageDetail);
        fab = findViewById(R.id.fab);
        fabWallpaper = findViewById(R.id.fabWallpaper);
        fabDownload = findViewById(R.id.fabDownload);
        fabShare = findViewById(R.id.fabShare);
        fabFavorite = findViewById(R.id.fabFavorite);

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        getData();

        initPermission();

        onClickFloatingButton();

        fab.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showButton) {
                    showFloatingButton();
                    showButton = false;
                } else {
                    hideFloatingButton();
                    showButton = true;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                }
                // Permisson don't granted and dont show dialog again.
                else {

                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }
    }

    public void getData() {
        Intent intent = getIntent();
        final String getSourceUrl = intent.getStringExtra("sourceUrl");
        link = getSourceUrl;
        Glide.with(this).load(getSourceUrl).into(imgImageDetail);
    }



    private void onClickFloatingButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewImageActivity.this, "Chức năng này đang update", Toast.LENGTH_SHORT).show();
                hideFloatingButton();
            }
        });
        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

                new iOSDialogBuilder(ViewImageActivity.this)
                        .setTitle(getString(R.string.example_title))
                        .setSubtitle(getString(R.string.example_subtitle))
                        .setBoldPositiveLabel(true)
                        .setCancelable(false)
                        .setPositiveListener(getString(R.string.ok), new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                saveImage();
                                dialog.dismiss();

                            }
                        })
                        .setNegativeListener(getString(R.string.dismiss), new iOSDialogClickListener() {
                            @Override
                            public void onClick(iOSDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .build().show();
                hideFloatingButton();
            }
        });
        fabWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewImageActivity.this, "Chức năng này đang update", Toast.LENGTH_SHORT).show();
                hideFloatingButton();
            }
        });
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewImageActivity.this, "Chức năng này đang update", Toast.LENGTH_SHORT).show();
                hideFloatingButton();
            }
        });
    }

    private void saveImage() {
        Intent intent = getIntent();
        final String getSourceUrl = intent.getStringExtra("sourceUrl");
        link = getSourceUrl;
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(getSourceUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);
        Picasso.get()
                .load(getSourceUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        try {
                            File mydie = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera");
                            if (!mydie.exists()) {
                                mydie.mkdirs();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(mydie, new Date().toString() + ".jpg"));
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private void hideFloatingButton() {
        fabDownload.hide(true);
        fabWallpaper.hide(true);
        fabShare.hide(true);
        fabFavorite.hide(true);
    }

    private void showFloatingButton() {
        fabDownload.show(true);
        fabWallpaper.show(true);
        fabShare.show(true);
        fabFavorite.show(true);

//    public void ondowload(View view) {
//        new DownloadFileFromURL(ViewImageActivity.this).execute(link);
//    }

        class DownloadFileFromURL extends AsyncTask<String, Integer, String> {
            public Context context;

            public DownloadFileFromURL(Context context) {
                this.context = context;
            }

            /**
             * Before starting background thread
             */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                System.out.println("Starting download");
                progressBar = new ProgressDialog(ViewImageActivity.this);
                progressBar.setMessage("Loading... Please wait...");
                progressBar.setIndeterminate(false);
                progressBar.setCancelable(false);
                progressBar.show();
            }

            /**
             * Downloading file in background thread
             */
            @Override
            protected String doInBackground(String... f_url) {
                int count;
                String param = f_url[0];
                try {
                    String root = Environment.getExternalStorageDirectory().toString();
                    URL url = new URL(param);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    int lenghtOfFile = connection.getContentLength();
                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    // Output stream to write file
                    int start = link.length() - 10;
                    int end = link.length();

                    OutputStream output = new FileOutputStream(root + "/" + link.substring(start, end));

                    byte data[] = new byte[1024];

                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        publishProgress((int) (total * 100 / lenghtOfFile));
                        // writing data to file
                        output.write(data, 0, count);
                    }
                    // flushing output
                    output.flush();
                    // closing streams
                    output.close();
                    input.close();

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }
                return null;
            }

            /**
             * After completing background task
             **/
            @Override
            protected void onPostExecute(String file_url) {
                progressBar.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewImageActivity.this);

                builder.setTitle("Download Success!!!");
                builder.setMessage("Succeed!!!");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        }


    }
}
