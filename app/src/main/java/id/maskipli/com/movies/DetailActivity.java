package id.maskipli.com.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.GetPosterAndBackdrop;
import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.Util.RequestResult;
import id.maskipli.com.movies.Util.RequestUtil;
import id.maskipli.com.movies.adapters.ItemPagerAdapter;
import id.maskipli.com.movies.lib.BottomSheetBehaviorGoogleMapsLike;
import id.maskipli.com.movies.lib.MergedAppBarLayoutBehavior;

public class DetailActivity extends AppCompatActivity {


    public static String DETAIL = "detail";

    TextView bottomSheetTextView, years, contentDetail;

    FloatingActionButton fab;
    ImageView imageLarge;

    private SingleItemModel data;

    RequestUtil requestUtil;

    private GetPosterAndBackdrop posterAndBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle("back");
//        }


        imageLarge = (ImageView) findViewById(R.id.imagelarge);
        data = getIntent().getParcelableExtra(DETAIL);
        String uriImage = MovieConstants.getPosterHight(data.getPoster_path());
        Log.e(this.getClass().getSimpleName(), "url Image = " + uriImage);
        Glide.with(this)
                .load(uriImage)
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

        /**
         * for action FAB
         */
        fab = (FloatingActionButton) findViewById(R.id.fab_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehaviorGoogleMapsLike.STATE_ANCHOR_POINT);
            }
        });

        /**
         * for action back button in state detail to state collapsed
         */
        AppBarLayout mergedAppBarLayout = (AppBarLayout) findViewById(R.id.merged_appbarlayout);
        MergedAppBarLayoutBehavior mergedAppBarLayoutBehavior = MergedAppBarLayoutBehavior.from(mergedAppBarLayout);
        mergedAppBarLayoutBehavior.setToolbarTitle(data.getOriginal_title());
        mergedAppBarLayoutBehavior.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED);
            }
        });

        bottomSheetTextView = (TextView) bottomSheet.findViewById(R.id.bottom_sheet_title);
        bottomSheetTextView.setText(data.getOriginal_title());

        years = (TextView) bottomSheet.findViewById(R.id.tv_years);
        years.setText(data.getRelease_date());

        contentDetail = (TextView) findViewById(R.id.tv_detailContent);
        contentDetail.setText(data.getOverview());
        getImages();

    }


    private void getImages() {
        requestUtil = new RequestUtil();

        requestUtil.getData(this, MovieConstants.getAllOfPoster(data.getId()), new RequestResult() {
            @Override
            public void resultData(String response) {
                GsonBuilder build = new GsonBuilder();
                Gson gson = build.create();
                posterAndBackdrop = gson.fromJson(response, GetPosterAndBackdrop.class);

                ArrayList<GetPosterAndBackdrop.Backdrops> backdropses = new ArrayList<GetPosterAndBackdrop.Backdrops>();
                for (GetPosterAndBackdrop.Backdrops item : posterAndBackdrop.getBackdrops()) {
                    GetPosterAndBackdrop.Backdrops misal = new GetPosterAndBackdrop.Backdrops(item.getFile_path(), item.getHeight(), item.getWidth());
                    backdropses.add(misal);
                }
                ItemPagerAdapter adapter = new ItemPagerAdapter(DetailActivity.this, backdropses);
                ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                viewPager.setClipToPadding(false);
                viewPager.setPadding(50, 50, 50, 0);
                viewPager.setPageMargin(20);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void errorResultData(String errorResponse) {

            }
        });
    }
}
