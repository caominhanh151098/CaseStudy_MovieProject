package com.example.casestudy_movieproject.service.movie.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowListMovieResponse {
    private String id;
    private String epPresent;
    private String name;
    private String quality;
    private String img_poster;
    private ShowGenreByMovie movieGenres;
    private String commentNum;
    private String viewNum;
    private String type;
    private String totalEp;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ShowGenreByMovie {
        private String id;
        private String name;
    }
}
