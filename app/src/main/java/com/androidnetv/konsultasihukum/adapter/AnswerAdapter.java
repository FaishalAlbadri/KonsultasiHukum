package com.androidnetv.konsultasihukum.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.AnswerActivity;
import com.androidnetv.konsultasihukum.R;
import com.androidnetv.konsultasihukum.adapter.AnswerAdapter.ViewHolder;
import com.androidnetv.konsultasihukum.data.answer.TheanswerItem;
import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends Adapter<ViewHolder> {

  private Context context;
  private List<TheanswerItem> items;

  public AnswerAdapter(AnswerActivity answerActivity, ArrayList<TheanswerItem> resultItem) {
    context = answerActivity;
    items = resultItem;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.answeritem, viewGroup, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    final TheanswerItem theanswerItem = items.get(position);
    viewHolder.txtAnswer.setText(theanswerItem.getAnswer());
    viewHolder.txtUser.setText("@" + theanswerItem.getUserName());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtAnswer)
    TextView txtAnswer;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

    }
  }
}
