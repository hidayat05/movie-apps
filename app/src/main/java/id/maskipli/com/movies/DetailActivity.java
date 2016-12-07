package id.maskipli.com.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.GetMovieList;
import id.maskipli.com.movies.Models.GetPosterAndBackdrop;
import id.maskipli.com.movies.Models.GetReviewMovies;
import id.maskipli.com.movies.Models.GetVideoTrailer;
import id.maskipli.com.movies.Models.SectionDataModel;
import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.Util.RequestResult;
import id.maskipli.com.movies.Util.RequestUtil;
import id.maskipli.com.movies.adapters.ItemPagerAdapter;
import id.maskipli.com.movies.adapters.RecyclerReviewAdapter;
import id.maskipli.com.movies.adapters.RecyclerViewDataAdapter;
import id.maskipli.com.movies.adapters.YotubeRecyclerAdapter;
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

    RecyclerView recyclerView, recycleReview, recyclerViewVideo;

    RecyclerViewDataAdapter recyclerViewDataAdapter;
    RecyclerReviewAdapter recyclerReviewAdapter;
    YotubeRecyclerAdapter yotubeRecyclerAdapter;

    ArrayList<SectionDataModel> sectionDataModels;
    ArrayList<GetReviewMovies.Review> reviews;
    ArrayList<GetVideoTrailer.Videos> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        requestUtil = new RequestUtil();


        imageLarge = (ImageView) findViewById(R.id.imagelarge);
        data = getIntent().getParcelableExtra(DETAIL);
        String uriImage = MovieConstants.getPosterHight(data.getPoster_path());
        Log.e(this.getClass().getSimpleName(), "url Image = " + uriImage);
        Glide.with(this)
                .load(uriImage)
                .centerCrop()
                .into(imageLarge);


        /**
         * for recomendations list
         */
        getDataRecomendationList();
        sectionDataModels = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_recomedation);
        recyclerViewDataAdapter = new RecyclerViewDataAdapter(this, sectionDataModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerViewDataAdapter);


        /**
         * for review List
         */
        getMovieReviews();
        reviews = new ArrayList<>();
        recycleReview = (RecyclerView) findViewById(R.id.rv_review);
        recyclerReviewAdapter = new RecyclerReviewAdapter(this, reviews);
        recycleReview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleReview.setAdapter(recyclerReviewAdapter);


        /**
         *  for video trailer
         *
         */

        getVideoSource();
        videos = new ArrayList<>();
        recyclerViewVideo = (RecyclerView) findViewById(R.id.list_video);
        recyclerViewVideo.setHasFixedSize(true);
        yotubeRecyclerAdapter = new YotubeRecyclerAdapter(this, videos);
        recyclerViewVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewVideo.setAdapter(yotubeRecyclerAdapter);

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

    private void getDataRecomendationList() {
        requestUtil.getData(this, MovieConstants.getRecomendation(data.getId()), new RequestResult() {
            @Override
            public void resultData(String response) {
                GsonBuilder buiild = new GsonBuilder();
                Gson gson = buiild.create();
                GetMovieList list = gson.fromJson(response, GetMovieList.class);

                ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
                for (SingleItemModel item : list.getResults()) {
                    singleItem.add(new SingleItemModel(item.getOriginal_title()
                            , item.getPoster_path()
                            , item.getOverview()
                            , item.getRelease_date()
                            , item.getBackdrop_path()
                            , item.getId()
                            , item.getGenre_ids()));
                }
                SectionDataModel dm = new SectionDataModel();
                dm.setAllItemsInSection(singleItem);
                sectionDataModels.add(dm);
                dm.setHeaderTitle(SearchActivity.RECOMENDATIONS);
                recyclerViewDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void errorResultData(String errorResponse) {

            }
        });
    }


    private void getMovieReviews() {
        requestUtil.getData(this, MovieConstants.getReview(data.getId()), new RequestResult() {
            @Override
            public void resultData(String response) {
                GsonBuilder build = new GsonBuilder();
                Gson gson = build.create();
                GetReviewMovies review = gson.fromJson(response, GetReviewMovies.class);
                Log.e(this.getClass().getSimpleName(), "response review = " + response);

                if (review.getTotal_results() != null) {
                    for (GetReviewMovies.Review item : review.results) {
                        reviews.add(new GetReviewMovies.Review(item.getId(), item.getAuthor(), item.getContent(), item.getUrl()));
                    }
                    recyclerReviewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void errorResultData(String errorResponse) {

            }
        });
    }


    private void getVideoSource() {
        requestUtil.getData(this, MovieConstants.getYoutube(data.getId()), new RequestResult() {
            @Override
            public void resultData(String response) {
                GsonBuilder build = new GsonBuilder();
                Gson gson = build.create();
                GetVideoTrailer video = gson.fromJson(response, GetVideoTrailer.class);
                Log.e(this.getClass().getSimpleName(), "response video = " + response);

                if (video.getResults() != null) {
                    for (GetVideoTrailer.Videos item : video.getResults()) {
                        if (item.getSite().contains("YouTube")) {
                            videos.add(new GetVideoTrailer.Videos(item.getId()
                                    , item.getIso_639_1()
                                    , item.getIso_3166_1()
                                    , item.getKey()
                                    , item.getName()
                                    , item.getSite()
                                    , item.getSize()
                                    , item.getType()));
                        }
                    }
                    yotubeRecyclerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void errorResultData(String errorResponse) {

            }
        });
    }
}
