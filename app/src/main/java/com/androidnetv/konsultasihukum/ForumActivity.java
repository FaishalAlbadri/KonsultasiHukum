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
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.adapter.AskAdapter;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.ask.AskResponse;
import com.androidnetv.konsultasihukum.data.ask.QuestionItem;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumActivity extends AppCompatActivity {

  @BindView(R.id.rvForum)
  RecyclerView rvForum;
  @BindView(R.id.swipeForum)
  SwipeRefreshLayout swipeForum;
  ProgressDialog progressDialog;
  AskAdapter askAdapter;
  ArrayList<QuestionItem> arrayList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forum);
    ButterKnife.bind(this);
    setView();
    getAsk();
  }

  private void setView() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), AddQuestion.class));
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    arrayList = new ArrayList<>();
    askAdapter = new AskAdapter(this, arrayList);
    rvForum.setLayoutManager(new LinearLayoutManager(this));
    rvForum.setAdapter(askAdapter);
    swipeForum.setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    swipeForum.setOnRefreshListener(() -> {
      swipeForum.setRefreshing(false);
      getAsk();
    });
    progressDialog.show();
  }

  private void getAsk() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<AskResponse> askResponseCall = apiInterface.getAsk(Server.BASE_URL + "getAsk.php");
    askResponseCall.enqueue(new Callback<AskResponse>() {
      @Override
      public void onResponse(Call<AskResponse> call, Response<AskResponse> response) {
        try {
          if (response.body().getMsg().equals("Data Semua Question")) {
            AskResponse askResponse = response.body();
            List<QuestionItem> items = askResponse.getQuestion();
            arrayList.clear();
            arrayList.addAll(items);
            askAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
          } else {
            progressDialog.dismiss();
            Toast.makeText(ForumActivity.this, "Belum Ada Pertanyaan", Toast.LENGTH_SHORT).show();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<AskResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(ForumActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), KonsultasiHukum.class));
    finish();
  }

}
