package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.api.Server;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

public class DetailBlog extends AppCompatActivity {

  String titlle, isi, img;
  @BindView(R.id.txtIsiDetail)
  TextView txtIsiDetail;
  @BindView(R.id.imgBlog)
  ImageView imgBlog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_blog);
    ButterKnife.bind(this);
    titlle = getIntent().getStringExtra("title");
    isi = getIntent().getStringExtra("isi");
    img = getIntent().getStringExtra("img");
    setTitle(titlle);
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888);
    Glide.with(this)
        .load(Server.BASE_URL_IMG_BLOG + img)
        .apply(options)
        .into(imgBlog);
    txtIsiDetail.setText(isi);
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), BlogActivity.class));
    finish();
  }
}
