package com.androidnetv.konsultasihukum.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.androidnetv.konsultasihukum.LoginActivity;
import com.androidnetv.konsultasihukum.MainActivity;
import java.util.HashMap;

public class SessionManger {

  Context context;
  int mode = 0;

  public static final String key_id_user = "key_id_user";
  public static final String key_user_name = "key_user_name";
  public static final String key_user_mail = "key_user_mail";
  public static final String key_user_fullname = "key_user_fullname";
  public static final String key_user_password = "key_user_password";
  public static final String key_user_birthday = "key_user_birthday";
  public static final String key_user_gender = "key_user_gender";
  public static final String pref_id_user = "pref_id_user";
  public static final String pref_user_name = "pref_user_name";
  public static final String pref_user_mail = "pref_user_mail";
  public static final String pref_user_fullname = "pref_user_fullname";
  public static final String pref_user_password = "pref_user_password";
  public static final String pref_user_birthday = "pref_user_birthday";
  public static final String pref_user_gender = "pref_user_gender";

  private SharedPreferences id_userPref, user_namePref, user_fullnamePref, user_mailPref,
      user_passwordPref, user_genderPref, user_birthdayPref;

  private SharedPreferences.Editor id_userEditor, user_nameEditor, user_fullnameEditor,
      user_mailEditor, user_passwordEditor, user_genderEditor, user_birthdayEditor;

  private static final String is_login = "islogin";

  public SessionManger(Context context) {
    this.context = context;

    id_userPref = context.getSharedPreferences(pref_id_user, mode);
    user_namePref = context.getSharedPreferences(pref_user_name, mode);
    user_mailPref = context.getSharedPreferences(pref_user_mail, mode);
    user_fullnamePref = context.getSharedPreferences(pref_user_fullname, mode);
    user_passwordPref = context.getSharedPreferences(pref_user_password, mode);
    user_genderPref = context.getSharedPreferences(pref_user_gender, mode);
    user_birthdayPref = context.getSharedPreferences(pref_user_birthday, mode);

    id_userEditor = id_userPref.edit();
    user_nameEditor = user_namePref.edit();
    user_mailEditor = user_mailPref.edit();
    user_fullnameEditor = user_fullnamePref.edit();
    user_passwordEditor = user_passwordPref.edit();
    user_genderEditor = user_genderPref.edit();
    user_birthdayEditor = user_birthdayPref.edit();

  }

  public void createSession(String id_user, String username, String password, String mail, String
      fullname, String gender, String birthday) {

    id_userEditor.putBoolean(is_login, true);
    id_userEditor.putString(key_id_user, id_user);
    user_nameEditor.putString(key_user_name, username);
    user_passwordEditor.putString(key_user_password, password);
    user_mailEditor.putString(key_user_mail, mail);
    user_fullnameEditor.putString(key_user_fullname, fullname);
    user_genderEditor.putString(key_user_gender, gender);
    user_birthdayEditor.putString(key_user_birthday, birthday);

    id_userEditor.commit();
    user_nameEditor.commit();
    user_passwordEditor.commit();
    user_fullnameEditor.commit();
    user_mailEditor.commit();
    user_genderEditor.commit();
    user_birthdayEditor.commit();
  }

  public HashMap<String, String> getUser() {
    HashMap<String, String> user = new HashMap<>();
    user.put(key_id_user, id_userPref.getString(key_id_user, null));
    user.put(key_user_name, user_namePref.getString(key_user_name, null));
    user.put(key_user_fullname, user_fullnamePref.getString(key_user_fullname, null));
    user.put(key_user_mail, user_mailPref.getString(key_user_mail, null));
    user.put(key_user_password, user_passwordPref.getString(key_user_password, null));
    user.put(key_user_gender, user_genderPref.getString(key_user_gender, null));
    user.put(key_user_birthday, user_birthdayPref.getString(key_user_birthday, null));
    return user;
  }

  public void checkLogin() {
    if (!this.is_login()) {
      Intent i = new Intent(context, LoginActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(i);
      ((Activity) context).finish();
    } else {
      Intent i = new Intent(context, MainActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(i);
      ((Activity) context).finish();
    }
  }

  private boolean is_login() {
    return id_userPref.getBoolean(is_login, false);
  }

  public void logout() {
    this.clear();
    Intent i = new Intent(context, LoginActivity.class);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);
    ((Activity) context).finish();
  }

  public void clear() {
    id_userEditor.clear();
    user_nameEditor.clear();
    user_passwordEditor.clear();
    user_fullnameEditor.clear();
    user_mailEditor.clear();
    user_genderEditor.clear();
    user_birthdayEditor.clear();

    id_userEditor.commit();
    user_nameEditor.commit();
    user_passwordEditor.commit();
    user_fullnameEditor.commit();
    user_mailEditor.commit();
    user_genderEditor.commit();
    user_birthdayEditor.commit();
  }
}
