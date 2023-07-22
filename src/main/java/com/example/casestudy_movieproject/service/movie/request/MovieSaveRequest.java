package com.example.casestudy_movieproject.service.movie.request;

import com.example.casestudy_movieproject.service.SelectOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSaveRequest {
    private String name;
    private String description;
    private String airedYear;
    private String duration;
    private MultipartFile img_movie;
    private MultipartFile img_poster;
    private String scoreIMDb;
    private String totalEp;
    private MultipartFile urlTrailer;
    private String type;
    private String status;
    private String quality;
    private List<EkipSaveRequest> eKips;
    private List<UrlMovieSaveRequest> seriesMovie;
    private List<String> movieGenres;

    public List<EkipSaveRequest> geteKips() {
        return eKips;
    }

    public void seteKips(List<EkipSaveRequest> eKips) {
        this.eKips = eKips;
    }

    public List<UrlMovieSaveRequest> getSeriesMovie() {
        return seriesMovie;
    }

    public void setSeriesMovie(List<UrlMovieSaveRequest> seriesMovie) {
        this.seriesMovie = seriesMovie;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UrlMovieSaveRequest {
        private String name;
        private MultipartFile url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MovieGenreSaveRequest {
        private String genre;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EkipSaveRequest {

        private String id;
        private String name;
        private String role;
    }
}
