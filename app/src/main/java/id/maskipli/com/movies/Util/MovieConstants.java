package id.maskipli.com.movies.Util;

/**
 * Created by hidayat on 11/17/16.
 */

public class MovieConstants {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "api_key=d8fcb077e3b62f7d851f8c7833508966";
    public static final String VIDEO = "/videos";
    public static final String UPCOMING = "upcoming";
    public static final String TOP_RATED = "top_rated";
    public static final String POPULAR = "popular";


    public static String getAllItem(String category) {
        return BASE_URL + "movie/" + category + "?" + API_KEY;
    }

    public static String getAllItem(String category, int page) {
        return BASE_URL + "movie/" + category + "?" + API_KEY + "&page=" + page;
    }

    public static String getPopular() {
        return BASE_URL + POPULAR + API_KEY;
    }

    public static String getTopRated() {
        return BASE_URL + TOP_RATED + API_KEY;
    }

    public static String getUpcoming() {
        return BASE_URL + UPCOMING + API_KEY;
    }

    public static String getTrailerYoutube(String id) {
        return BASE_URL + id + VIDEO + API_KEY;
    }

    public static String getPosterUrl(String posterPath) {
        return "https://image.tmdb.org/t/p/w185" + posterPath;
    }

    public static String getSearch(String judul, int page) {
        return BASE_URL + "search/movie?&query=" + judul + "&" + API_KEY + "&page=" + page;
    }
}
