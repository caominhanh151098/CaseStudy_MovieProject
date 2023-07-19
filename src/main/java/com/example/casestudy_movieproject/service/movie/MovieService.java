package com.example.casestudy_movieproject.service.movie;

import com.example.casestudy_movieproject.model.EKip;
import com.example.casestudy_movieproject.model.Movie;

import com.example.casestudy_movieproject.model.MovieGenre;
import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import com.example.casestudy_movieproject.repository.CommentRepository;
import com.example.casestudy_movieproject.repository.EKipRepository;
import com.example.casestudy_movieproject.repository.MovieGenreRepository;
import com.example.casestudy_movieproject.repository.MovieRepository;
import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetail;
import com.example.casestudy_movieproject.service.movie.response.ShowUrlMovieResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        List<EKip> eKips = eKipRepository.findAllByMovie_Id(id);

        showMovieDetail.setMovieGenres(String.join(", ", ListGenre));
        showMovieDetail.setDirectors(convertToString(eKips, ERoleEKip.DIRECTOR));
        showMovieDetail.setActors(convertToString(eKips, ERoleEKip.ACTOR));
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

    public ShowUrlMovieResponse watchMovie(int id) {
        ShowUrlMovieResponse movie = AppUtils.mapper.map(movieRepository.findById(id), ShowUrlMovieResponse.class);
        movie.setGenre(movieGenreRepository.findTopByMovie_Id(id).getGenre().getName());
        return movie;
    }

    public Page<MovieListResponse> findAllWithSearchAndPaging(String search, Pageable pageable){
        List<Movie> movieList = movieRepository.findAll();
//        List<String> genres

        search = "%" + search + "%";

        return movieRepository.searchAll(search,pageable).map(movie -> {
            var response = AppUtils.mapper.map(movie,MovieListResponse.class);
            List<String> listGenre = new ArrayList<>();
//            for (var genre: ) {
//
//            }
//            response.setMovieGenres();
            return response;
        });
    }

    public List<MovieListResponse> findAll(){
        List<Movie> movies = movieRepository.findAll();

        List<MovieListResponse> responses = new ArrayList<>();

        for (var movie:movies) {
            MovieListResponse movieListResponse = AppUtils.mapper.map(movie,MovieListResponse.class);
            List<MovieGenre> movieGenres = movieGenreRepository.findAllByMovie_Id(movie.getId());
            List<String> ListGenre = movieGenres.stream().map(e -> e.getGenre().getName()).collect(Collectors.toList());
            List<EKip> ekips = eKipRepository.findAllByMovie_Id(movie.getId());
            List<String> listEkip = ekips.stream().map(e -> e.getPerson().getName()).collect(Collectors.toList());



            movieListResponse.setDirectors(convertToString(ekips, ERoleEKip.DIRECTOR));
            movieListResponse.setActors(convertToString(ekips, ERoleEKip.ACTOR));

            movieListResponse.setMovieGenres(String.join(", ", ListGenre));
            movieListResponse.setEKips(String.join(", ", listEkip));

            responses.add(movieListResponse);
        }

        return responses;
    }
}
