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
    private String id;
    private String name;
    private String description;
    private String airedYear;
    private String duration;
    private MultipartFile img_movie;
    private String imgMovie;
    private MultipartFile img_poster;
    private String imgPoster;
    private String scoreIMDb;
    private String totalEp;
    private MultipartFile urlTrailer;
    private String url_trailer;
    private String type;
    private String status;
    private String quality;
    private List<EkipSaveRequest> eKips;
    private List<UrlMovieSaveRequest> epMovies;
    private List<String> movieGenres;

    public List<EkipSaveRequest> geteKips() {
        return eKips;
    }

    public void seteKips(List<EkipSaveRequest> eKips) {
        this.eKips = eKips;
    }

    public List<UrlMovieSaveRequest> getEpMovies() {
        return epMovies;
    }

    public void setEpMovies(List<UrlMovieSaveRequest> epMovies) {
        this.epMovies = epMovies;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UrlMovieSaveRequest {
        private String id;
        private String name;
        private MultipartFile url;
        private String urlChapter;
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
