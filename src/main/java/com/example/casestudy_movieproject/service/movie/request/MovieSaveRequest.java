package com.example.casestudy_movieproject.service.movie.request;

import com.example.casestudy_movieproject.service.SelectOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String scoreIMDb;
    private String totalEp;
    private String urlTrailer;
    private SelectOptionRequest type;
    private SelectOptionRequest status;
    private SelectOptionRequest quality;
    private List<EkipSaveRequest> eKips;
    private List<UrlMovieSaveRequest> seriesMovie;
    private List<MovieGenreSaveRequest> movieGenres;

    public List<EkipSaveRequest> geteKips() {
        return eKips;
    }

    public void seteKips(List<EkipSaveRequest> eKips) {
        this.eKips = eKips;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UrlMovieSaveRequest {
        private String name;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MovieGenreSaveRequest {
        private SelectOptionRequest genre;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EkipSaveRequest {

        private List<PersonSaveRequest> person;
        private SelectOptionRequest role;

        public List<PersonSaveRequest> getPerson() {
            return person;
        }

        public void setPerson(List<PersonSaveRequest> person) {
            this.person = person;
        }


        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class PersonSaveRequest {
            private String id;
            private String name;
        }
    }
}
