package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsultasiHukum extends AppCompatActivity {

  @BindView(R.id.imgForum)
  ImageView imgForum;
  @BindView(R.id.imgDukung)
  ImageView imgDukung;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_konsultasi_hukum);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.imgForum)
  public void onImgForumClicked() {
    startActivity(new Intent(getApplicationContext(), ForumActivity.class));
  }

  @OnClick(R.id.imgDukung)
  public void onImgDukungClicked() {
    startActivity(new Intent(getApplicationContext(), DukungKami.class));
    finish();
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }
}
