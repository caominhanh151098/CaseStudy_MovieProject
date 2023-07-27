package com.example.casestudy_movieproject.service.movie.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowMovieTopViewResponse {
    private String id;
    private String epPresent;
    private String name;
    private String quality;
    private String img_movie;
    private String viewNum;
    private String type;
    private String totalEp;
    private List<String> ofTime = new ArrayList<>();

}
