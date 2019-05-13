package com.androidnetv.konsultasihukum.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.androidnetv.konsultasihukum.AnswerActivity;
import com.androidnetv.konsultasihukum.ForumActivity;
import com.androidnetv.konsultasihukum.R;
import com.androidnetv.konsultasihukum.adapter.AskAdapter.ViewHolder;
import com.androidnetv.konsultasihukum.data.ask.QuestionItem;
import java.util.ArrayList;
import java.util.List;

public class AskAdapter extends Adapter<ViewHolder> {

  private Context context;
  private List<QuestionItem> items;

  public AskAdapter(ForumActivity forumActivity, ArrayList<QuestionItem> resultItem) {
    context = forumActivity;
    items = resultItem;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.ask_question_item, viewGroup, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    final QuestionItem questionItem = items.get(position);
    viewHolder.txtAsk.setText(questionItem.getAsk());
    viewHolder.txtUser.setText("@" + questionItem.getUserName());
    viewHolder.cvItem.setForeground(getSelectedItemDrawable());
    viewHolder.cvItem.setClickable(true);
    viewHolder.cvItem.setOnClickListener(v -> {
      Intent i = new Intent(v.getContext(), AnswerActivity.class);
      i.putExtra("username", questionItem.getUserName());
      i.putExtra("ask", questionItem.getAsk());
      i.putExtra("id", questionItem.getIdAsk());
      v.getContext().startActivity(i);
    });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtAsk)
    TextView txtAsk;
    @BindView(R.id.cvItem)
    CardView cvItem;

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
