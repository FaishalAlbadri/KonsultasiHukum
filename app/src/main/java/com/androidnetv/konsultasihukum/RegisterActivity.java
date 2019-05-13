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
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.user.UserItem;
import com.androidnetv.konsultasihukum.data.user.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

  @BindView(R.id.edtNama)
  EditText edtNama;
  @BindView(R.id.edtLahir)
  EditText edtLahir;
  @BindView(R.id.edtGender)
  EditText edtGender;
  @BindView(R.id.edtMail)
  EditText edtMail;
  @BindView(R.id.edtUsername)
  EditText edtUsername;
  @BindView(R.id.edtPassword)
  EditText edtPassword;
  @BindView(R.id.edtPassword2)
  EditText edtPassword2;
  @BindView(R.id.btnRegister)
  Button btnRegister;
  ProgressDialog progressDialog;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);
    setTitle("Register");
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

  @OnClick(R.id.btnRegister)
  public void onViewClicked() {
    if (Server.isEmpty(edtUsername) ||
        Server.isEmpty(edtPassword) ||
        Server.isEmpty(edtNama) ||
        Server.isEmpty(edtMail) ||
        Server.isEmpty(edtPassword2) ||
        Server.isEmpty(edtGender) ||
        Server.isEmpty(edtLahir)) {
      Toast.makeText(this, "Periksa Kembali\nJangan Ada Yang Kosong", Toast.LENGTH_SHORT).show();
    } else {
      if (edtPassword2.getText().toString().equals(edtPassword.getText().toString())) {
        progressDialog.show();
        getRegister();
      } else {
        Toast.makeText(this, "Konfirmasi password berbeda", Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void getRegister() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<com.androidnetv.konsultasihukum.data.Response> responseCall = apiInterface
        .getRegister
            (edtUsername.getText().toString(),
                edtNama.getText().toString(),
                edtPassword.getText().toString(),
                edtMail.getText().toString(),
                edtLahir.getText().toString(),
                edtGender.getText().toString());
    responseCall.enqueue(new Callback<com.androidnetv.konsultasihukum.data.Response>() {
      @Override
      public void onResponse(Call<com.androidnetv.konsultasihukum.data.Response> call,
          Response<com.androidnetv.konsultasihukum.data.Response> response) {
        if (response.body().getMsg().equals("Register Berhasil")) {
          progressDialog.dismiss();
          Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
          onBackPressed();
        } else {
          progressDialog.dismiss();
          Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<com.androidnetv.konsultasihukum.data.Response> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
