package id.maskipli.com.movies.Models;

import java.util.List;

/**
 * Created by ptinkosinarmedia on 12/7/16.
 */

public class GetReviewMovies {

    public String getId() {
        return id;
    }

    public String getPage() {
        return page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    private String id;
    private String page;
    public List<Review> results;
    private String total_pages;
    private String total_results;

    /**
     * for detail result of reviews
     * create
     */
    public static class Review {
        private String id;
        private String author;
        private String content;
        private String url;

        public String getId() {
            return id;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getUrl() {
            return url;
        }

        public Review(String id, String author, String content, String url) {
            this.id = id;
            this.author = author;
            this.content = content;
            this.url = url;
        }
    }
}
