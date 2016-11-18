package id.maskipli.com.movies.Models;

import java.util.List;

/**
 * Created by hidayat on 11/17/16.
 */

public class MovieObject {
    List<Result> results;

    public class Result{
        public String poster_path;
        public String overview;
        public String release_date;
        public String id;
        public String original_title;
        public String backdrop_path;
        public String original_language;
        public String popularity;
        public String vote_count;
        public String genre_ids;

        public String getPoster_path() {
            return poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getId() {
            return id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public String getPopularity() {
            return popularity;
        }

        public String getVote_count() {
            return vote_count;
        }

        public String getGenre_ids() {
            return genre_ids;
        }
    }
}
