package id.maskipli.com.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import id.maskipli.com.movies.Models.GetMovieList;
import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.Util.RequestResult;
import id.maskipli.com.movies.Util.RequestUtil;
import id.maskipli.com.movies.adapters.SectionListDataAdapter;

public class SearchActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private static final int COUNT_COLOUM = 3;
    public static final String SEARCH_QUERY = "SEARCH_QUERY";
    public static final String CATEGORY = "category";
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    RecyclerView recyclerView;
    Toolbar toolbar;
    FloatingActionButton fab;
    TextView empty;
    private Boolean requestData = false;
    private GetMovieList movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        empty = (TextView) findViewById(R.id.tv_empty);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Search Result");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
        setRecyclerView();
        requestData(1);
    }

    private String getURI(int page) {
        String uri = "";
        Intent intent = getIntent();
        String cari = intent.getStringExtra(SEARCH_QUERY);
        String category = intent.getStringExtra(CATEGORY);

        if (!TextUtils.isEmpty(cari)) {
            uri = MovieConstants.getSearch(cari, page);
        } else if (!TextUtils.isEmpty(category)) {
            uri = MovieConstants.getAllItem(category, page);
        } else {
            Log.e(TAG, "intent is NULL");
        }
        Log.e(TAG, "url = " + uri);
        return uri;
    }

    private void requestData(final int page) {
        if (!requestData) {
            String requestURL = getURI(page);
            if (!TextUtils.isEmpty(requestURL)) {
                requestData = true;
                RequestUtil requestUtil = new RequestUtil();
                requestUtil.getData(getApplicationContext(), requestURL, new RequestResult() {
                    @Override
                    public void resultData(String response) {
                        Log.e(TAG, "response = " + response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        movieList = gson.fromJson(response, GetMovieList.class);
                        List<SingleItemModel> singleItemModels = movieList.getResults();
                        if (singleItemModels.isEmpty()) {
                            empty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            fab.setVisibility(View.GONE);
                        } else {
                            if (page == 1) {
                                recyclerView.setAdapter(new SectionListDataAdapter(movieList.getResults()));
                            } else {
                                ((SectionListDataAdapter) recyclerView.getAdapter()).append(movieList.getResults());
                            }
                            empty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            fab.setVisibility(View.VISIBLE);
                        }
                        requestData = false;
                    }

                    @Override
                    public void errorResultData(String errorResponse) {
                        Log.e(TAG, "Response = " + errorResponse);
                    }
                });
            } else {
                Log.e(TAG, "URL = " + requestURL);
            }
        }
    }


    // set untuk auto request page
    private void setRecyclerView() {
        int spanCount = COUNT_COLOUM;
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.hide();
                } else if (dy < 10) {
                    fab.show();
                }

                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager.findFirstCompletelyVisibleItemPosition();

                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    if (movieList.getPage() < movieList.getTotal_pages()) {
                        requestData(movieList.getPage() + 1);
                    }
                }
            }
        });
    }

}
