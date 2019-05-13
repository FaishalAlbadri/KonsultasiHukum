package com.androidnetv.konsultasihukum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.adapter.AnswerAdapter;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.data.answer.AnswerResponse;
import com.androidnetv.konsultasihukum.data.answer.TheanswerItem;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerActivity extends AppCompatActivity {

  @BindView(R.id.txtUser)
  TextView txtUser;
  @BindView(R.id.txtAsk)
  TextView txtAsk;
  @BindView(R.id.rvAnswer)
  RecyclerView rvAnswer;
  @BindView(R.id.swipeAnswer)
  SwipeRefreshLayout swipeAnswer;
  ProgressDialog progressDialog;
  AnswerAdapter answerAdapter;
  ArrayList<TheanswerItem> arrayList;
  String id_ask, username, ask;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_answer);
    ButterKnife.bind(this);
    setView();
    getAnswer();
  }

  private void setView() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), AddAnswer.class)
            .putExtra("id",id_ask).putExtra("username",username).putExtra("ask", ask));
        finish();
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    id_ask = getIntent().getStringExtra("id");
    username = getIntent().getStringExtra("username");
    ask = getIntent().getStringExtra("ask");

    txtAsk.setText(ask);
    txtUser.setText("@"+username);

    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    arrayList = new ArrayList<>();
    answerAdapter = new AnswerAdapter(this, arrayList);
    rvAnswer.setLayoutManager(new LinearLayoutManager(this));
    rvAnswer.setAdapter(answerAdapter);
    swipeAnswer.setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    swipeAnswer.setOnRefreshListener(() -> {
      swipeAnswer.setRefreshing(false);
      getAnswer();
    });
    progressDialog.show();
  }

  private void getAnswer() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<AnswerResponse> answerResponseCall = apiInterface.getAnswer(id_ask);
    answerResponseCall.enqueue(new Callback<AnswerResponse>() {
      @Override
      public void onResponse(Call<AnswerResponse> call, Response<AnswerResponse> response) {
        try {
          if (response.body().getMsg().equals("Berhasil")) {
            AnswerResponse answerResponse = response.body();
            List<TheanswerItem> items = answerResponse.getTheanswer();
            arrayList.clear();
            arrayList.addAll(items);
            answerAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
          } else {
            progressDialog.dismiss();
            Toast.makeText(AnswerActivity.this, "Belum Ada Jawaban", Toast.LENGTH_SHORT).show();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<AnswerResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(AnswerActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), ForumActivity.class));
    finish();
  }
}
