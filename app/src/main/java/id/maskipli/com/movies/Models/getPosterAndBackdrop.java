package id.maskipli.com.movies.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ptinkosinarmedia on 12/6/16.
 */

public class GetPosterAndBackdrop {

    public GetPosterAndBackdrop(){}

    @SerializedName("id")
    private int id;

    @SerializedName("backdrops")
    private List<Backdrops> backdrops;

    @SerializedName("posters")
    private List<Posters> posters;

    public int getId() {
        return id;
    }

    public List<Backdrops> getBackdrops() {
        return backdrops;
    }

    public List<Posters> getPosters() {
        return posters;
    }

    /**
     * for json array Backdrops
     * URL = MovieConstans.getAllOfPoster(id);
     */
    public static class Backdrops {
        @SerializedName("file_path")
        private String file_path;

        @SerializedName("aspect_ratio")
        private String aspect_ratio;

        @SerializedName("height")
        private String height;

        @SerializedName("iso_639_1")
        private String iso_639_1;

        @SerializedName("vote_average")
        private String vote_average;

        @SerializedName("vote_count")
        private String vote_count;

        @SerializedName("width")
        private String width;


        public Backdrops(String file_path, String height, String width) {
            this.file_path = file_path;
            this.height = height;
            this.width = width;
        }

        public String getFile_path() {
            return file_path;
        }

        public String getHeight() {
            return height;
        }

        public String getWidth() {
            return width;
        }

    }


    /**
     * for json array Poster
     * URL = MovieConstans.getAllOfPoster(id);
     */
    public class Posters {

        @SerializedName("file_path")
        private String file_path;

        @SerializedName("aspect_ratio")
        private String aspect_ratio;

        @SerializedName("height")
        private String height;

        @SerializedName("iso_639_1")
        private String iso_639_1;

        @SerializedName("vote_average")
        private String vote_average;

        @SerializedName("vote_count")
        private String vote_count;

        @SerializedName("width")
        private String width;


        public Posters(String file_path, String height, String width) {
            this.file_path = file_path;
            this.height = height;
            this.width = width;
        }

        public String getFile_path() {
            return file_path;
        }

        public String getHeight() {
            return height;
        }

        public String getWidth() {
            return width;
        }

    }
}
