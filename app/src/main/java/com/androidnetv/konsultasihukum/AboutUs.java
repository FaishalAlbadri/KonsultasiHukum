package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about_us);
    setTitle("Tentang Kami");
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }
}
