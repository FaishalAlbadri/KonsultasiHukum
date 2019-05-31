package com.androidnetv.konsultasihukum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.androidnetv.konsultasihukum.Util.SessionManger;
import com.androidnetv.konsultasihukum.adapter.GaleryAdapter;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.galery.GaleryItem;
import com.androidnetv.konsultasihukum.data.galery.GaleryResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaleryActivity extends AppCompatActivity {

  @BindView(R.id.rvGalery)
  RecyclerView rvGalery;
  @BindView(R.id.swipeGalery)
  SwipeRefreshLayout swipeGalery;
  ProgressDialog progressDialog;
  GaleryAdapter galeryAdapter;
  ArrayList<GaleryItem> arrayList;
  @BindView(R.id.add_galery)
  FloatingActionButton addGalery;
  SessionManger sessionManger;
  HashMap<String, String> user;
  String user_name;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_galery);
    ButterKnife.bind(this);
    setView();
    getGaleri();
  }

  private void setView() {
    setTitle("Galeri");
    sessionManger = new SessionManger(GaleryActivity.this);
    user = sessionManger.getUser();
    user_name = user.get(SessionManger.key_user_name);
    if (user_name.equals("admin")) {
      addGalery.setVisibility(View.VISIBLE);
    }
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    arrayList = new ArrayList<>();
    galeryAdapter = new GaleryAdapter(this, arrayList);
    rvGalery.setLayoutManager(new LinearLayoutManager(this));
    rvGalery.setAdapter(galeryAdapter);
    swipeGalery.setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    swipeGalery.setOnRefreshListener(() -> {
      swipeGalery.setRefreshing(false);
      getGaleri();
    });
    progressDialog.show();
  }

  private void getGaleri() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<GaleryResponse> galeryResponseCall = apiInterface
        .getGalery(Server.BASE_URL + "getGalery.php");
    galeryResponseCall.enqueue(new Callback<GaleryResponse>() {
      @Override
      public void onResponse(Call<GaleryResponse> call, Response<GaleryResponse> response) {
        try {
          if (response.body().getMsg().equals("Data Semua Galery")) {
            GaleryResponse galeryResponse = response.body();
            List<GaleryItem> items = galeryResponse.getGalery();
            arrayList.clear();
            arrayList.addAll(items);
            galeryAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
          } else {
            progressDialog.dismiss();
            Toast.makeText(GaleryActivity.this, "Error Database", Toast.LENGTH_SHORT).show();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<GaleryResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(GaleryActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }

  @OnClick(R.id.add_galery)
  public void onViewClicked() {
    startActivity(new Intent(getApplicationContext(), AddGalery.class));
    finish();
  }
}
