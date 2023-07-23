package com.example.casestudy_movieproject.service.ep_movie;

import com.example.casestudy_movieproject.model.EpMovie;
import com.example.casestudy_movieproject.model.Movie;
import com.example.casestudy_movieproject.repository.CommentRepository;
import com.example.casestudy_movieproject.repository.EpMovieRepository;
import com.example.casestudy_movieproject.repository.GenreRepository;
import com.example.casestudy_movieproject.service.ep_movie.reponse.ShowUpdateMovieResponse;
import com.example.casestudy_movieproject.service.genre.response.ShowGenreByMovieResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowListMovieResponse;
import com.example.casestudy_movieproject.service.movie.response.WatchMovieResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EpMovieService {
    private final EpMovieRepository epMovieRepository;
    private final CommentRepository commentRepository;
    private final GenreRepository genreRepository;

    public WatchMovieResponse getMovie(int id){
        return AppUtils.mapper.map(epMovieRepository.findById(id), WatchMovieResponse.class) ;
    }

    public Page<ShowUpdateMovieResponse> showListMovieUpdate (Pageable pageable) {
        List<EpMovie> epMovies = epMovieRepository.findAll();
        List<Movie> movies = epMovies.stream().map(e -> e.getMovie()).collect(Collectors.toList());
        movies = movies.stream().distinct().collect(Collectors.toList());
//        epMoviess.forEach(m -> {
//            ShowListMovieResponse movie = m.getMovie();
//            int id = Integer.parseInt(movie.getId());
//            movie.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
//            movie.setGenres(genreRepository.getGenreByMovie(id)
//                    .stream()
//                    .map(g -> AppUtils.mapper.map(g, ShowGenreByMovieResponse.class))
//                    .collect(Collectors.toList()));
//        });
        return null;
    }
}
