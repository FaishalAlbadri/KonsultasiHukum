package com.androidnetv.konsultasihukum.api;

import android.widget.EditText;

public class Server {

  public final static String BASE_URL = "http://api.santriprogrammer.com/konsultasi_hukum/api/";
  public final static String BASE_URL_IMG = "http://api.santriprogrammer.com/konsultasi_hukum/img/";

  public static final String EMAIL = "konsultasihukumofficial@gmail.com";
  public static final String PASSWORD = "2019LBHMapp";


  public static boolean isEmpty(EditText editText) {

    if (editText.getText().toString().trim().length() > 0) {

      return false;
    } else {

      return true;
    }
  }
}
