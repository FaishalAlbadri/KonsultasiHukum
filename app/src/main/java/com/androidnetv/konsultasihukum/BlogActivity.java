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
import com.androidnetv.konsultasihukum.adapter.BlogAdapter;
import com.androidnetv.konsultasihukum.api.APIClient;
import com.androidnetv.konsultasihukum.api.APIInterface;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.blog.BlogItem;
import com.androidnetv.konsultasihukum.data.blog.BlogResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {

  @BindView(R.id.rvBlog)
  RecyclerView rvBlog;
  @BindView(R.id.swipeBlog)
  SwipeRefreshLayout swipeBlog;
  ProgressDialog progressDialog;
  BlogAdapter blogAdapter;
  ArrayList<BlogItem> arrayList;
  @BindView(R.id.add_blog)
  FloatingActionButton addBlog;
  SessionManger sessionManger;
  HashMap<String, String> user;
  String user_name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blog);
    ButterKnife.bind(this);
    setTitle("Blog");
    setView();
    getBlog();
  }

  private void setView() {
    sessionManger = new SessionManger(BlogActivity.this);
    progressDialog = new ProgressDialog(this);
    user = sessionManger.getUser();
    user_name = user.get(SessionManger.key_user_name);
    if (user_name.equals("admin")) {
      addBlog.setVisibility(View.VISIBLE);
    }
    progressDialog.setMessage("Loading");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    arrayList = new ArrayList<>();
    blogAdapter = new BlogAdapter(this, arrayList);
    rvBlog.setLayoutManager(new LinearLayoutManager(this));
    rvBlog.setAdapter(blogAdapter);
    swipeBlog.setColorSchemeResources(
        android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    swipeBlog.setOnRefreshListener(() -> {
      swipeBlog.setRefreshing(false);
      getBlog();
    });
    progressDialog.show();
  }

  private void getBlog() {
    APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
    final Call<BlogResponse> blogResponseCall = apiInterface
        .getBlog(Server.BASE_URL + "getBlog.php");
    blogResponseCall.enqueue(new Callback<BlogResponse>() {
      @Override
      public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
        try {
          if (response.body().getMsg().equals("Data Semua Blog")) {
            BlogResponse blogResponse = response.body();
            List<BlogItem> items = blogResponse.getBlog();
            arrayList.clear();
            arrayList.addAll(items);
            blogAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
          } else {
            progressDialog.dismiss();
            Toast.makeText(BlogActivity.this, "Error Database", Toast.LENGTH_SHORT).show();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onFailure(Call<BlogResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(BlogActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }

  @OnClick(R.id.add_blog)
  public void onViewClicked() {
    startActivity(new Intent(getApplicationContext(), AddBlog.class));
    finish();
  }
}
