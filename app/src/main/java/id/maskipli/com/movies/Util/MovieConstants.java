package id.maskipli.com.movies.Util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import id.maskipli.com.movies.R;

/**
 * Created by hidayat on 11/17/16.
 */

public class MovieConstants {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "api_key=d8fcb077e3b62f7d851f8c7833508966";
    public static final String API_YOUTUBE = "AIzaSyCH32vqTa5AaDP4S4pXPiFx8D8fObP1Tak";
    public static final String VIDEO = "/videos";
    public static final String UPCOMING = "upcoming";
    public static final String TOP_RATED = "top_rated";
    public static final String POPULAR = "popular";
    public static final String NOW_PLAYING = "now_playing";
    public static final String LATEST = "latest";


    public static String getAllItem(String category) {
        return BASE_URL + "movie/" + category + "?" + API_KEY;
    }

    public static String getAllItem(String category, int page) {
        return BASE_URL + "movie/" + category + "?" + API_KEY + "&page=" + page;
    }

    public static String getTrailerYoutube(String id) {
        return BASE_URL + id + VIDEO + API_KEY;
    }

    public static String getYoutube(String id) {
        return BASE_URL + "movie/" + id + VIDEO + "?" + API_KEY;
    }

    public static String getKeywords(String id) {
        return BASE_URL + "movie/" + id + "/keywords?" + API_KEY;
    }

    public static String getReview(String id) {
        return BASE_URL + "movie/" + id + "/reviews?" + API_KEY;
    }

    public static String getRecomendation(String id) {
        return BASE_URL + "movie/" + id + "/recommendations?" + API_KEY;
    }

    public static String getAllOfPoster(String id) {
        return BASE_URL + "movie/" + id + "/images?" + API_KEY;
    }

    public static String getPosterSmall(String posterPath) {
        return "https://image.tmdb.org/t/p/w185" + posterPath;
    }

    public static String getPosterHight(String posterPath) {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public static String getSearch(String judul, int page) {
        return BASE_URL + "search/movie?&query=" + judul + "&" + API_KEY + "&page=" + page;
    }

    public static String getGenreMovie() {
        return BASE_URL + "genre/movie/list?" + API_KEY;
    }


    public static void shareText(String text, String extraSubject, Context ctx) {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        else
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, extraSubject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);

        ctx.startActivity(Intent.createChooser(shareIntent, ctx.getString(R.string.share_with)));
    }

}
