package com.androidnetv.konsultasihukum;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.androidnetv.konsultasihukum.api.Server;
import java.io.IOException;
import java.util.UUID;
import net.gotev.uploadservice.MultipartUploadRequest;

public class AddBlog extends AppCompatActivity {

  private static final int STORAGE_PERMISSION_CODE = 123;
  @BindView(R.id.imgAddBlog)
  ImageView imgAddBlog;
  @BindView(R.id.edtTitleBlog)
  EditText edtTitleBlog;
  @BindView(R.id.edtIsiBlog)
  EditText edtIsiBlog;
  @BindView(R.id.btnAddBlog)
  Button btnAddBlog;
  Handler handler;
  ProgressDialog progressDialog;
  private int PICK_IMAGE_REQUEST = 1;
  private Bitmap bitmap;
  private Uri filePath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_blog);
    ButterKnife.bind(this);

    requestStoragePermission();
    handler = new Handler();
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
  }

  public void uploadMultipart() {
    String name = edtTitleBlog.getText().toString().trim();
    String isi = edtIsiBlog.getText().toString().trim();

    try {
      String uploadId = UUID.randomUUID().toString();

      MultipartUploadRequest request = new MultipartUploadRequest(this, uploadId, Server.BASE_URL
          + "add_blog.php");
      request.addFileToUpload(getPath(filePath), "blog_img");
      request.addParameter("blog_title", name);
      request.addParameter("blog_news", isi);
//      request.setNotificationConfig(new UploadNotificationConfig());
      request.setMaxRetries(2);
      request.startUpload();

    } catch (Exception exc) {
      Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
    }

    handler.postDelayed(() -> {
      progressDialog.dismiss();
      onBackPressed();
    }, 3000);
  }

  private void showFileChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
        && data.getData() != null) {
      filePath = data.getData();
      try {
        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        imgAddBlog.setImageBitmap(bitmap);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  public String getPath(Uri uri) {
    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    cursor.moveToFirst();
    String document_id = cursor.getString(0);
    document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
    cursor.close();

    cursor = getContentResolver().query(
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
    cursor.moveToFirst();
    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
    cursor.close();

    return path;
  }

  private void requestStoragePermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED) {
      return;
    }

    if (ActivityCompat
        .shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

    }
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
        STORAGE_PERMISSION_CODE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {

    if (requestCode == STORAGE_PERMISSION_CODE) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG)
            .show();
      } else {
        Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
      }
    }
  }

  @OnClick(R.id.imgAddBlog)
  public void onImgAddBlogClicked() {
    showFileChooser();
  }

  @OnClick(R.id.btnAddBlog)
  public void onBtnAddBlogClicked() {
    if (Server.isEmpty(edtTitleBlog) || Server.isEmpty(edtIsiBlog)) {
      Toast.makeText(this, "Harap Diisi Semua", Toast.LENGTH_SHORT).show();
    } else {
      progressDialog.show();
      uploadMultipart();
    }
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), BlogActivity.class));
    finish();
  }

}
