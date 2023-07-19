package com.example.casestudy_movieproject.service.movie.request;

import com.example.casestudy_movieproject.service.SelectOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSaveRequest {
    private String name;
    private String description;
    private String airedYear;
    private String duration;
    private String img_movie;
    private String img_poster;
    private SelectOptionRequest type;
    private SelectOptionRequest status;
    private SelectOptionRequest quality;
    private List<MovieGenreSaveRequest> movieGenres;
    private List<EKipSaveRequest> eKips;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class MovieGenreSaveRequest{
        private SelectOptionRequest genre;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class EKipSaveRequest{
        private String person;
        private SelectOptionRequest role;

    }
}
