package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BantuanHukum extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bantuan_hukum);
    setTitle("Bantuan Hukum Kami");

  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }
}
