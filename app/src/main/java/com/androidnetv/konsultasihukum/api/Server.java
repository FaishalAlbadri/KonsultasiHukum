package com.androidnetv.konsultasihukum.api;

import android.widget.EditText;

public class Server {

//  public final static String BASE_URL = "http://api.santriprogrammer.com/konsultasi_hukum/api/";
//  public final static String BASE_URL_IMG_GALERY = "http://api.santriprogrammer"
//      + ".com/konsultasi_hukum/imgGalery/";
//  public final static String BASE_URL_IMG_BLOG = "http://api.santriprogrammer"
//      + ".com/konsultasi_hukum/imgBlog/";

  public final static String BASE = "http://192.168.43.5/konsultasi_hukum/";
  public final static String BASE_URL = BASE + "api/";
  public final static String BASE_URL_IMG_GALERY = BASE + "imgGalery/";
  public final static String BASE_URL_IMG_BLOG = BASE + "imgBlog/";

  public static final String EMAIL = "konsultasihukumofficial@gmail.com";
  public static final String PASSWORD = "2019LBHMapp";


  public static boolean isEmpty(EditText editText) {

    return editText.getText().toString().trim().length() <= 0;
  }
}