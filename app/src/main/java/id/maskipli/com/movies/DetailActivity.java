package id.maskipli.com.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.adapters.ItemPagerAdapter;
import id.maskipli.com.movies.lib.BottomSheetBehaviorGoogleMapsLike;
import id.maskipli.com.movies.lib.MergedAppBarLayoutBehavior;

public class DetailActivity extends AppCompatActivity {


    public static String DETAIL = "detail";
    int[] mDrawables = {
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
    };

    TextView bottomSheetTextView;
    ImageView imageLarge;
    private SingleItemModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(" ");
        }

        imageLarge = (ImageView) findViewById(R.id.imagelarge);
        data = getIntent().getParcelableExtra(DETAIL);
        Glide.with(this)
                .load(MovieConstants.getPosterUrl(data.getPoster_path()))
                .centerCrop()
                .into(imageLarge);
        /**
         * If we want to listen for states callback
         */
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        View bottomSheet = null;
        if (coordinatorLayout != null) {
            bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        }
        final BottomSheetBehaviorGoogleMapsLike behavior = BottomSheetBehaviorGoogleMapsLike.from(bottomSheet);
        behavior.addBottomSheetCallback(new BottomSheetBehaviorGoogleMapsLike.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED:
                        Log.d("bottomsheet-", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehaviorGoogleMapsLike.STATE_DRAGGING:
                        Log.d("bottomsheet-", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehaviorGoogleMapsLike.STATE_EXPANDED:
                        Log.d("bottomsheet-", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehaviorGoogleMapsLike.STATE_ANCHOR_POINT:
                        Log.d("bottomsheet-", "STATE_ANCHOR_POINT");
                        break;
                    case BottomSheetBehaviorGoogleMapsLike.STATE_HIDDEN:
                        Log.d("bottomsheet-", "STATE_HIDDEN");
                        break;
                    default:
                        Log.d("bottomsheet-", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        AppBarLayout mergedAppBarLayout = (AppBarLayout) findViewById(R.id.merged_appbarlayout);
        MergedAppBarLayoutBehavior mergedAppBarLayoutBehavior = MergedAppBarLayoutBehavior.from(mergedAppBarLayout);
        mergedAppBarLayoutBehavior.setToolbarTitle("Title Dummy");
        mergedAppBarLayoutBehavior.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED);
            }
        });

        bottomSheetTextView = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_title);
        ItemPagerAdapter adapter = new ItemPagerAdapter(this, mDrawables);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
    }

}
