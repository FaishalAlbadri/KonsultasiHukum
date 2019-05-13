package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DukungKami extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dukung_kami);
    setTitle("Dukung Kami");
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), KonsultasiHukum.class));
    finish();
  }
}
