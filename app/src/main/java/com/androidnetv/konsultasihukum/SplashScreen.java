package com.androidnetv.konsultasihukum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.androidnetv.konsultasihukum.Util.SessionManger;
import java.util.HashMap;

public class SplashScreen extends AppCompatActivity {

  SessionManger sessionManger;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN,
        LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splash_screen);

    sessionManger = new SessionManger(SplashScreen.this);


    Handler handler = new Handler();

    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        sessionManger.checkLogin();
      }
    }, 3000);
  }
}
