package com.androidnetv.konsultasihukum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.androidnetv.konsultasihukum.Util.SessionManger;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.imgUsers)
  ImageView imgUsers;
  @BindView(R.id.imgGalery)
  ImageView imgGalery;
  @BindView(R.id.imgForum)
  ImageView imgForum;
  @BindView(R.id.imgLogout)
  ImageView imgLogout;
  @BindView(R.id.imgHelp)
  ImageView imgHelp;
  @BindView(R.id.imgBlog)
  ImageView imgBlog;
  SessionManger sessionManger;
  ProgressDialog progressDialog;
  Handler handler;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    sessionManger = new SessionManger(MainActivity.this);
    progressDialog = new ProgressDialog(this);
    handler = new Handler();
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
  }

  @OnClick(R.id.imgUsers)
  public void onImgUsersClicked() {
    startActivity(new Intent(getApplicationContext(), AboutUs.class));
    finish();
  }

  @OnClick(R.id.imgGalery)
  public void onImgGaleryClicked() {
    startActivity(new Intent(getApplicationContext(), GaleryActivity.class));
    finish();
  }

  @OnClick(R.id.imgForum)
  public void onImgForumClicked() {
    startActivity(new Intent(getApplicationContext(), KonsultasiHukum.class));
    finish();
  }

  @OnClick(R.id.imgLogout)
  public void onImgLogoutClicked() {
    progressDialog.show();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        progressDialog.dismiss();
        sessionManger.logout();
      }
    }, 3000);
  }

  @OnClick(R.id.imgHelp)
  public void onImgHelpClicked() {
    startActivity(new Intent(getApplicationContext(), BantuanHukum.class));
    finish();
  }

  @OnClick(R.id.imgBlog)
  public void onImgBlogClicked() {
    startActivity(new Intent(getApplicationContext(), BlogActivity.class));
    finish();
  }
}
