package id.maskipli.com.movies.Models;

import java.util.ArrayList;

/**
 * Created by hidayat on 11/17/16.
 */

public class SectionDataModel {

    private String headerTitle;
    private ArrayList<MovieObject> movieObjectArrayList;

    public SectionDataModel() {

    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public void setMovieObjectArrayList(ArrayList<MovieObject> movieObjectArrayList) {
        this.movieObjectArrayList = movieObjectArrayList;
    }

    public String getHeaderTitle() {

        return headerTitle;
    }

    public ArrayList<MovieObject> getMovieObjectArrayList() {
        return movieObjectArrayList;
    }
}
