package id.maskipli.com.movies.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hidayat on 11/22/16.
 */

public class SingleItemModel implements Parcelable {

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("id")
    private String id;

    @SerializedName("genre_ids")
    private int[] genre_ids;

    protected SingleItemModel(Parcel in) {
        poster_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        backdrop_path = in.readString();
        vote_average = in.readFloat();
        id = in.readString();
        genre_ids = in.createIntArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(backdrop_path);
        dest.writeFloat(vote_average);
        dest.writeString(id);
        dest.writeIntArray(genre_ids);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SingleItemModel> CREATOR = new Creator<SingleItemModel>() {
        @Override
        public SingleItemModel createFromParcel(Parcel in) {
            return new SingleItemModel(in);
        }

        @Override
        public SingleItemModel[] newArray(int size) {
            return new SingleItemModel[size];
        }
    };

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public SingleItemModel(String original_title, String poster_path, String overview, String release_date, String backdrop_path, String id, int[] genre_id) {
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.genre_ids = genre_id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
