package com.androidnetv.konsultasihukum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.androidnetv.konsultasihukum.Util.SessionManger;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.user.UserItem;
import com.androidnetv.konsultasihukum.data.user.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.edtUsername)
  EditText edtUsername;
  @BindView(R.id.edtPassword)
  EditText edtPassword;
  @BindView(R.id.btnLogin)
  Button btnLogin;
  @BindView(R.id.txtLupaPassword)
  TextView txtLupaPassword;
  @BindView(R.id.txtRegister)
  TextView txtRegister;
  ProgressDialog progressDialog;
  SessionManger sessionManger;

  String id_user, user_name, user_fullname, user_email, user_birthday, user_password, user_gender;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    setTitle("Login");
    setView();
  }

  private void setView() {
    sessionManger = new SessionManger(LoginActivity.this);
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
  }

  @OnClick(R.id.btnLogin)
  public void onBtnLoginClicked() {
    if (Server.isEmpty(edtUsername) || Server.isEmpty(edtPassword)) {
      Toast.makeText(this, "Username/Password Masih Kosong", Toast.LENGTH_SHORT).show();
    } else {
      progressDialog.show();
      getLogin();
    }
  }

  private void getLogin() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<UserResponse> userResponseCall = apiInterface.getLogin(edtUsername.getText()
        .toString(), edtPassword.getText().toString());
    userResponseCall.enqueue(new Callback<UserResponse>() {
      @Override
      public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        try {
          if (response.body().getMsg().equals("login berhasil")){
            UserResponse userResponse = response.body();
            List<UserItem> items = userResponse.getUser();
            for (int a = 0; a < items.size(); a++) {
              id_user = items.get(a).getIdUser();
              user_name = items.get(a).getUserName();
              user_fullname = items.get(a).getUserFullname();
              user_email = items.get(a).getUserMail();
              user_birthday = items.get(a).getUserBirthday();
              user_password = items.get(a).getUserPassword();
              user_gender = items.get(a).getUserGender();
            }
            sessionManger.createSession(id_user,user_name,user_password,user_email,user_fullname,
                user_gender,user_birthday);
            Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
          } else {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT)
                .show();
          }
        } catch (Exception e){
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<UserResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @OnClick(R.id.txtLupaPassword)
  public void onTxtLupaPasswordClicked() {
    startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
    finish();
  }

  @OnClick(R.id.txtRegister)
  public void onTxtRegisterClicked() {
    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    finish();
  }
}
