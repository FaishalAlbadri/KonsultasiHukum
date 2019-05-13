package com.androidnetv.konsultasihukum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.Util.SendMail;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.user.UserItem;
import com.androidnetv.konsultasihukum.data.user.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {

  @BindView(R.id.edtMail)
  EditText edtMail;
  @BindView(R.id.btnKirim)
  Button btnKirim;
  ProgressDialog progressDialog;
  String user_name,user_password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forget_password);
    ButterKnife.bind(this);
    setTitle("Lupa Username/Password");
    setView();
  }

  private void setView() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    finish();
  }

  @OnClick(R.id.btnKirim)
  public void onViewClicked() {
    if (Server.isEmpty(edtMail)) {
      Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
    } else {
      progressDialog.show();
      forget();
    }
  }

  private void forget() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<UserResponse> userResponseCall = apiInterface.getFoget(edtMail.getText().toString());
    userResponseCall.enqueue(new Callback<UserResponse>() {
      @Override
      public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        try {
          if (response.body().getMsg().equals("berhasil")){
            UserResponse userResponse = response.body();
            List<UserItem> items = userResponse.getUser();
            for (int a = 0; a < items.size(); a++) {
              user_name = items.get(a).getUserName();
              user_password = items.get(a).getUserPassword();
            }
            sendMail();
          } else {
            progressDialog.dismiss();
            Toast.makeText(ForgetPassword.this, "Email tidak terdaftar", Toast.LENGTH_SHORT)
                .show();
          }
        } catch (Exception e){
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<UserResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(ForgetPassword.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void sendMail() {
    String subject = "Forget Account Konsultasi Hukum";
    String message = "Username : " + user_name + "\n" + "Password : " + user_password;
    SendMail sm = new SendMail(this,edtMail.getText().toString(),subject,message);
    sm.execute();
    progressDialog.dismiss();
    Toast.makeText(ForgetPassword.this, "Berhasil.. Cek Email Anda", Toast.LENGTH_SHORT).show();
    onBackPressed();
  }
}
