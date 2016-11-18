package id.maskipli.com.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.MovieObject;
import id.maskipli.com.movies.R;

/**
 * Created by hidayat on 11/17/16.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleRowHolder> {

    private ArrayList<MovieObject.Result> itemList;
    private Context context;

    public SectionListDataAdapter(Context context, ArrayList<MovieObject.Result> itemlist) {
        this.itemList = itemlist;
        this.context = context;
    }

    @Override
    public SingleRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SingleRowHolder holder, int position) {

        MovieObject.Result movieObject = itemList.get(position);
        holder.textView.setText(movieObject.getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }

    public class SingleRowHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected ImageView imageView;

        public SingleRowHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.tvTitle);
            this.imageView = (ImageView) itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "disiini ntr di ganti kemana ya", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
