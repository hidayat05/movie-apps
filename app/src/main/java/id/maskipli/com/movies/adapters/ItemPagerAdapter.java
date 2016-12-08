package id.maskipli.com.movies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import id.maskipli.com.movies.Models.GetPosterAndBackdrop;
import id.maskipli.com.movies.R;
import id.maskipli.com.movies.Util.MovieConstants;

public class ItemPagerAdapter extends android.support.v4.view.PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    private List<GetPosterAndBackdrop.Backdrops> data;

    public ItemPagerAdapter(Context context, List<GetPosterAndBackdrop.Backdrops> data) {
        this.data = data;
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String getImage = data.get(position).getFile_path();
        Log.e(getClass().getSimpleName(), "uri image = " + MovieConstants.getPosterHight(getImage));

        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Glide.with(mContext)
                .load(MovieConstants.getPosterHight(getImage))
                .error(R.drawable.no_image)
                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
