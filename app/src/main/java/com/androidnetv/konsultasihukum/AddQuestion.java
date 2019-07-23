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
import com.androidnetv.konsultasihukum.Util.SessionManger;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.Response;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;

public class AddQuestion extends AppCompatActivity {

  @BindView(R.id.edtAsk)
  EditText edtAsk;
  @BindView(R.id.btnSend)
  Button btnSend;
  ProgressDialog progressDialog;
  SessionManger sessionManger;
  String user_name;
  HashMap<String, String> user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_question);
    ButterKnife.bind(this);

    setView();
  }

  private void setView() {
    setTitle("Ajukan Pertanyaan");
    sessionManger = new SessionManger(AddQuestion.this);
    user = sessionManger.getUser();
    user_name = user.get(SessionManger.key_user_name);
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
  }

  @OnClick(R.id.btnSend)
  public void onViewClicked() {
    if (Server.isEmpty(edtAsk)) {
      Toast.makeText(this, "Jangan Kosong", Toast.LENGTH_SHORT).show();
    } else {
      progressDialog.show();
      addQuestion();
    }
  }

  private void addQuestion() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<Response> responseCall = apiInterface
        .addQuestion(user_name, edtAsk.getText().toString());
    responseCall.enqueue(new Callback<Response>() {
      @Override
      public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.body().getMsg().equals("Berhasil")) {
          progressDialog.dismiss();
          Toast.makeText(AddQuestion.this, "Berhasil", Toast.LENGTH_SHORT).show();
          onBackPressed();
        } else {
          progressDialog.dismiss();
          Toast.makeText(AddQuestion.this, "Gagal", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Response> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(AddQuestion.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), ForumActivity.class));
    finish();
  }
}
