package id.maskipli.com.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import id.maskipli.com.movies.Models.MovieObject;
import id.maskipli.com.movies.Models.SectionDataModel;
import id.maskipli.com.movies.Util.MovieConstants;
import id.maskipli.com.movies.Util.RequestResult;
import id.maskipli.com.movies.Util.RequestUtil;
import id.maskipli.com.movies.adapters.RecyclerViewDataAdapter;

public class MainActivity extends AppCompatActivity {

    RequestUtil requestUtil;
    MovieObject movieObject;
    private final String TAG = "MainActivity";
    private String category;
    ArrayList<SectionDataModel> sectiondataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();

        sectiondataModel = new ArrayList<SectionDataModel>();

        RecyclerView recycleView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycleView.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, sectiondataModel);
        recycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycleView.setAdapter(adapter);
    }

    private void getData() {
        requestUtil = new RequestUtil();

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                category = MovieConstants.getPopular();
            } else if (i == 1) {
                category = MovieConstants.getTopRated();
            } else if (i == 2) {
                category = MovieConstants.getUpcoming();
            }

            requestUtil.getData(getApplicationContext(), category, new RequestResult() {
                @Override
                public void resultData(String response) {
                    Log.e(TAG, "Response = " + response);
                    GsonBuilder gsonbuilder = new GsonBuilder();
                    Gson gson = gsonbuilder.create();
                    movieObject = gson.fromJson(response, MovieObject.class);

                    SectionDataModel dm = new SectionDataModel();
                    dm.setHeaderTitle(category);

                    ArrayList<MovieObject> mo = new ArrayList<>();

                    mo.add(new MovieObject());
                    dm.setMovieObjectArrayList(mo);

                    sectiondataModel.add(dm);

                }

                @Override
                public void errorResultData(String errorResponse) {

                }
            });
        }
    }
}
