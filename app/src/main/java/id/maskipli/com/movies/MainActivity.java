package id.maskipli.com.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.GetMovieList;
import id.maskipli.com.movies.Models.SectionDataModel;
import id.maskipli.com.movies.Models.SingleItemModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.Util.RequestResult;
import id.maskipli.com.movies.Util.RequestUtil;
import id.maskipli.com.movies.adapters.RecyclerViewDataAdapter;


public class MainActivity extends AppCompatActivity {

    RequestUtil requestUtil;
    private static String TAG = "MainActivity";
    ArrayList<SectionDataModel> allSampleData;
    Toolbar toolbar;
    RecyclerView myRecyclerView;
    RecyclerViewDataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getListData();
        allSampleData = new ArrayList<SectionDataModel>();

        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new RecyclerViewDataAdapter(this, allSampleData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    MenuItemCompat.collapseActionView(menuItem);
                    Intent openSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                    openSearchActivity.putExtra(SearchActivity.SEARCH_QUERY, query);
                    startActivity(openSearchActivity);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void getListData() {
        String category = "";
        requestUtil = new RequestUtil();
        for (int i = 1; i <= 4; i++) {
            if (i == 1) {
                category = MovieConstants.POPULAR;
            } else if (i == 2) {
                category = MovieConstants.TOP_RATED;
            } else if (i == 3) {
                category = MovieConstants.UPCOMING;
            } else if (i == 4) {
                category = MovieConstants.NOW_PLAYING;
            }
            getDataMovie(category);
        }

    }

    private void getDataMovie(final String category) {
        requestUtil.getData(getApplicationContext(), MovieConstants.getAllItem(category), new RequestResult() {
            @Override
            public void resultData(String response) {
                Log.e(TAG, "Response = " + response);
                GsonBuilder gsonbuilder = new GsonBuilder();
                Gson gson = gsonbuilder.create();
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
                allSampleData.add(dm);
                dm.setHeaderTitle(category);
                adapter.notifyDataSetChanged();


//                if (category.contains("_")) {
//                    String header = category.replaceAll("[^a-zA-Z]", " ");
//                    dm.setHeaderTitle(header);
//                } else {
//                    dm.setHeaderTitle(category);
//                }
            }

            @Override
            public void errorResultData(String errorResponse) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
