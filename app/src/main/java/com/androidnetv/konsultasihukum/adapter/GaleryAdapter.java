package com.androidnetv.konsultasihukum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.GaleryActivity;
import com.androidnetv.konsultasihukum.R;
import com.androidnetv.konsultasihukum.adapter.GaleryAdapter.ViewHolder;
import com.androidnetv.konsultasihukum.api.Server;
import com.androidnetv.konsultasihukum.data.galery.GaleryItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

public class GaleryAdapter extends Adapter<ViewHolder> {

  private Context context;
  private List<GaleryItem> items;

  public GaleryAdapter(GaleryActivity galeryActivity, ArrayList<GaleryItem> resultItem) {
    context = galeryActivity;
    items = resultItem;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.galeryitem, viewGroup, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    final GaleryItem galeryItem = items.get(position);
    RequestOptions options = new RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888);
    Glide.with(context)
        .load(Server.BASE_URL_IMG + galeryItem.getGaleryImg())
        .apply(options)
        .into(viewHolder.imgGaleryItem);
    viewHolder.txtGaleryItem.setText(galeryItem.getGaleryTitle());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgGaleryItem)
    ImageView imgGaleryItem;
    @BindView(R.id.txtGaleryItem)
    TextView txtGaleryItem;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
