package id.maskipli.com.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.maskipli.com.movies.Models.GetReviewMovies;
import id.maskipli.com.movies.R;

/**
 * Created by ptinkosinarmedia on 12/7/16.
 */

public class RecyclerReviewAdapter extends RecyclerView.Adapter<RecyclerReviewAdapter.SingleRowHolder> {


    private Context context;
    private List<GetReviewMovies.Review> data;

    public RecyclerReviewAdapter(Context context, List<GetReviewMovies.Review> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public SingleRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_single_review, parent, false);
        SingleRowHolder singleRowHolder = new SingleRowHolder(view);
        return singleRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleRowHolder holder, int position) {
        holder.review.setText(data.get(position).getContent());
        holder.author.setText(data.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    public class SingleRowHolder extends RecyclerView.ViewHolder {

        protected TextView author;
        protected TextView review;

        public SingleRowHolder(View itemView) {
            super(itemView);
            this.author = (TextView) itemView.findViewById(R.id.tv_author);
            this.review = (TextView) itemView.findViewById(R.id.tv_review);
        }
    }
}
