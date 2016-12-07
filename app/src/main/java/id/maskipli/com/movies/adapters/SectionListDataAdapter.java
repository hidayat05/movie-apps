package id.maskipli.com.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import id.maskipli.com.movies.DetailActivity;
import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.R;
import id.maskipli.com.movies.Util.MovieConstants;

/**
 * Created by hidayat on 11/17/16.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private List<SingleItemModel> itemsList;
    private Context mContext;
    private final Object object = new Object();

    public SectionListDataAdapter(Context context, List<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int i) {

        SingleItemModel singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getOriginal_title());
        holder.id = singleItem.getId();

        Glide.with(holder.itemImage.getContext())
                .load(MovieConstants.getPosterSmall(singleItem.getPoster_path()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.itemImage);
        holder.mainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(DetailActivity.DETAIL, itemsList.get(i));
                v.getContext().startActivity(intent);
            }
        });

    }

    public SectionListDataAdapter(List<SingleItemModel> singleItemModels) {
        itemsList = singleItemModels;
    }

    public void append(List<SingleItemModel> singleItemModels) {
        synchronized (object) {
            int posisi = itemsList.size();
            itemsList.addAll(singleItemModels);
            notifyItemChanged(posisi, singleItemModels);
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;

        protected String id;

        protected View mainContent;


        public SingleItemRowHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
            this.mainContent = view.findViewById(R.id.main_content);
        }

    }
}
