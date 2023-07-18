package com.example.casestudy_movieproject.service.movie;

import com.example.casestudy_movieproject.model.EKip;
import com.example.casestudy_movieproject.model.MovieGenre;
import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import com.example.casestudy_movieproject.repository.CommentRepository;
import com.example.casestudy_movieproject.repository.EKipRepository;
import com.example.casestudy_movieproject.repository.MovieGenreRepository;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetail;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final EKipRepository eKipRepository;
    private final CommentRepository commentRepository;

    public ShowMovieDetail showDetail(int id) {
        ShowMovieDetail showMovieDetail = AppUtils.mapper.map(movieRepository.findById(id), ShowMovieDetail.class);
        List<MovieGenre> movieGenres = movieGenreRepository.findAllByMovie_Id(id);
        List<String> ListGenre = movieGenres.stream().map(e -> e.getGenre().getName()).collect(Collectors.toList());
        List<EKip> directors = eKipRepository.findAllByMovie_Id(id);

        showMovieDetail.setMovieGenres(String.join(", ", ListGenre));
        showMovieDetail.setDirectors(convertToString(directors, ERoleEKip.DIRECTOR));
        showMovieDetail.setActors(convertToString(directors, ERoleEKip.ACTOR));
        showMovieDetail.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
        return showMovieDetail;
    }

    private String convertToString(List<EKip> directors, ERoleEKip roleEKip) {
        List<String> listActor  = new ArrayList<>();
        for (EKip eKip : directors) {
            if (eKip.getRole() == roleEKip)
                listActor.add(eKip.getPerson().getName());
        }
        return String.join(", ", listActor);
    }
}
