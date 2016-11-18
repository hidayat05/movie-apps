package id.maskipli.com.movies.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.SectionDataModel;
import id.maskipli.com.movies.R;

import static java.lang.Boolean.FALSE;

/**
 * Created by hidayat on 11/17/16.
 */

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemHolder> {
    private ArrayList<SectionDataModel> dataList;
    private Context context;


    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final String originalTitle = dataList.get(position).getHeaderTitle();
        ArrayList arrayList = dataList.get(position).getMovieObjectArrayList();
        holder.itemTitle.setText(originalTitle);

        SectionListDataAdapter sectionListDataAdapter = new SectionListDataAdapter(context, arrayList);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, FALSE));
        holder.recyclerView.setAdapter(sectionListDataAdapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "disini untuk more", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        protected Button button;

        public ItemHolder(View itemView) {

            super(itemView);
            this.itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
            this.button = (Button) itemView.findViewById(R.id.btnMore);
        }
    }
}
