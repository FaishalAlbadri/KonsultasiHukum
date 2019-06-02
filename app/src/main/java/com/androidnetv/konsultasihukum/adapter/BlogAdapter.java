package com.androidnetv.konsultasihukum.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.BlogActivity;
import com.androidnetv.konsultasihukum.DetailBlog;
import com.androidnetv.konsultasihukum.R;
import com.androidnetv.konsultasihukum.adapter.BlogAdapter.ViewHolder;
import com.androidnetv.konsultasihukum.data.blog.BlogItem;
import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends Adapter<ViewHolder> {

  private Context context;
  private List<BlogItem> items;

  public BlogAdapter(BlogActivity blogActivity, ArrayList<BlogItem> resultItem) {
    context = blogActivity;
    items = resultItem;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.blogitem, viewGroup, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    final BlogItem blogItem = items.get(position);
    viewHolder.txtIsi.setText(blogItem.getBlogNews());
    viewHolder.txtTitle.setText(blogItem.getBlogTitle());
    viewHolder.btnDetail.setOnClickListener(v -> {
      Intent i = new Intent(v.getContext(), DetailBlog.class);
      i.putExtra("title", blogItem.getBlogTitle());
      i.putExtra("isi", blogItem.getBlogNews());
      i.putExtra("img", blogItem.getBlogImg());
      v.getContext().startActivity(i);
    });
    ((BlogActivity) context).cekUser(viewHolder.btnDelete);
    viewHolder.btnDelete.setOnClickListener(v -> {
      ((BlogActivity) context).deleteBlog(blogItem.getIdBlog());
    });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtIsi)
    TextView txtIsi;
    @BindView(R.id.btnDetail)
    Button btnDetail;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

    }
  }

  public Drawable getSelectedItemDrawable() {
    int[] attrs = new int[]{R.attr.selectableItemBackground};
    TypedArray ta = context.obtainStyledAttributes(attrs);
    Drawable selectedItemDrawable = ta.getDrawable(0);
    ta.recycle();
    return selectedItemDrawable;
  }
}
