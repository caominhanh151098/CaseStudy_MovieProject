package com.example.casestudy_movieproject.service.movie;

import com.example.casestudy_movieproject.config.AppConfig;
import com.example.casestudy_movieproject.model.*;

import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import com.example.casestudy_movieproject.repository.*;

import com.example.casestudy_movieproject.service.genre.response.ShowGenreByMovieResponse;

import com.example.casestudy_movieproject.service.movie.request.MovieSaveRequest;

import com.example.casestudy_movieproject.service.movie.response.MovieListResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowListMovieResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieDetailResponse;
import com.example.casestudy_movieproject.service.movie.response.ShowMovieResponse;
import com.example.casestudy_movieproject.service.ep_movie.reponse.ShowListEpisodeResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final EKipRepository eKipRepository;
    private final CommentRepository commentRepository;
    private final EpMovieRepository epMovieRepository;
    private final PersonRepository personRepository;
    private final AppConfig appConfig;


    public ShowMovieDetailResponse showDetail(int id) {
        ShowMovieDetailResponse showMovieDetail = AppUtils.mapper.map(movieRepository.findById(id), ShowMovieDetailResponse.class);
        List<MovieGenre> movieGenres = movieGenreRepository.findAllByMovie_Id(id);
        List<String> ListGenre = movieGenres.stream().map(e -> e.getGenre().getName()).collect(Collectors.toList());
        List<EKip> eKips = eKipRepository.findAllByMovie_Id(id);
        List<ShowListEpisodeResponse> Eplist = epMovieRepository.findAllByMovie_Id(id).stream()
                .map(e -> AppUtils.mapper.map(e, ShowListEpisodeResponse.class))
                .collect(Collectors.toList());

        showMovieDetail.setSeriesMovie(Eplist);
        showMovieDetail.setTopGenre(AppUtils.mapper.map(movieGenres.get(0).getGenre(), ShowGenreByMovieResponse.class));
        showMovieDetail.setMovieGenres(String.join(", ", ListGenre));
        showMovieDetail.setDirectors(convertToString(eKips, ERoleEKip.DIRECTOR));
        showMovieDetail.setActors(convertToString(eKips, ERoleEKip.ACTOR));
        showMovieDetail.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
        return showMovieDetail;
    }

    private String convertToString(List<EKip> directors, ERoleEKip roleEKip) {
        List<String> listActor = new ArrayList<>();
        for (EKip eKip : directors) {
            if (eKip.getRole() == roleEKip)
                listActor.add(eKip.getPerson().getName());
        }
        return String.join(", ", listActor);
    }

    public ShowMovieResponse showMovie(int id) {
        ShowMovieResponse movie = AppUtils.mapper.map(movieRepository.findById(id), ShowMovieResponse.class);
        movie.setGenre(AppUtils
                .mapper
                .map(movieGenreRepository.findTopByMovie_Id(id).getGenre(), ShowGenreByMovieResponse.class));
        List<ShowListEpisodeResponse> Eplist = epMovieRepository.findAllByMovie_Id(id).stream()
                .map(e -> AppUtils.mapper.map(e, ShowListEpisodeResponse.class))
                .collect(Collectors.toList());
        movie.setSeriesMovie(Eplist);
        return movie;
    }

    public Page<MovieListResponse> findAllWithSearchAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        Page<Movie> movies = movieRepository.searchAll(search,pageable);

        Page<MovieListResponse> responses = movieRepository.searchAll(search,pageable)
                .map(e -> AppUtils.mapper.map(e , MovieListResponse.class));
        responses.forEach(m -> {
            m.setMovieGenres(String.join(",", movieGenreRepository.findAllByMovie_Id(m.getId())
                    .stream().map(e -> e.getGenre().getName()).collect(Collectors.toList())));
        });
        return responses;
    }

    public List<MovieListResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieListResponse> responses = new ArrayList<>();

        for (var movie : movies) {
            MovieListResponse movieListResponse = AppUtils.mapper.map(movie, MovieListResponse.class);
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

    public Page<ShowListMovieResponse> showListMovie(Pageable pageable) {
        Page<Movie> movieessadas = movieRepository.findAll(pageable);
        Page<ShowListMovieResponse> movies = movieRepository.findAll(pageable)
                .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            m.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
            m.setGenres(genreRepository.getGenreByMovie(id)
                    .stream()
                    .map(g -> AppUtils.mapper.map(g, ShowGenreByMovieResponse.class))
                    .collect(Collectors.toList()));
        });
        return movies;
    }

    public Page<ShowListMovieResponse> showListMovieByGenre(String idGenre, Pageable pageable) {
        Page<ShowListMovieResponse> movies = movieRepository.getMovieByGenre(idGenre, pageable).map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            m.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
            m.setGenres(genreRepository.getGenreByMovie(id)
                    .stream()
                    .map(g -> AppUtils.mapper.map(g, ShowGenreByMovieResponse.class))
                    .collect(Collectors.toList()));
        });
        return movies;
    }

    public Page<ShowListMovieResponse> showListEpMovie(Pageable pageable) {
        Page<ShowListMovieResponse> movies = movieRepository.getMovieSeries(pageable)
                .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            m.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
            m.setGenres(genreRepository.getGenreByMovie(id)
                    .stream()
                    .map(g -> AppUtils.mapper.map(g, ShowGenreByMovieResponse.class))
                    .collect(Collectors.toList()));
        });
        return movies;
    }

    public void create(MovieSaveRequest movieSave) {

        saveFile(movieSave.getImg_movie(), appConfig.getImgStoragePathMovie());
        saveFile(movieSave.getImg_poster(), appConfig.getImgStoragePoster());
        saveFile(movieSave.getUrlTrailer(), appConfig.getUrlStorageTrailer());
        for (var chapter : movieSave.getEpMovies()) {
            saveFile(chapter.getUrl(), appConfig.getUrlStorageChapter());
        }


        for (var person : movieSave.geteKips()) {
            if (!personRepository.existsByNameIgnoreCase(person.getName())) {
                Person newPerson = new Person();
                newPerson.setName(person.getName());
                personRepository.save(newPerson);

            } else {
                Person person1 = personRepository.findPersonByNameContaining(person.getName());
            }


        }
        Movie movie = AppUtils.mapper.map(movieSave, Movie.class);
        System.out.println(movie);
        String setLink = "../assets/client";

        movie.setImg_movie(setLink + "/img/movie/" + movieSave.getImg_movie().getOriginalFilename());
        movie.setImg_poster(setLink + "/img/poster/" + movieSave.getImg_poster().getOriginalFilename());
        movie.setUrlTrailer(setLink + "/videos/trailer/" + movieSave.getUrlTrailer().getOriginalFilename());

        movieRepository.save(movie);

        for (var chapter:movie.getEpMovies()) {
            for (var chapterSave:movieSave.getEpMovies()) {
                chapter.setUrl(setLink + "/videos/" + chapterSave.getUrl().getOriginalFilename());
                Movie movie1 = movieRepository.findById(movie.getId());
                EpMovie epMovie = new EpMovie(chapterSave.getName(),chapter.getUrl(),movie1);
                epMovieRepository.save(epMovie);
            }
        }


        for (String genre : movieSave.getMovieGenres()) {
            Genre genre1 = genreRepository.findById(Integer.parseInt(genre));
            MovieGenre newMovieGenre = new MovieGenre(movie, genre1);
            movieGenreRepository.save(newMovieGenre);
        }

        for (var ekip:movieSave.geteKips()) {
            Person person = personRepository.findPersonByNameContaining(ekip.getName());

            EKip eKip = new EKip(person,movie,ERoleEKip.valueOf(ekip.getRole()));
            eKipRepository.save(eKip);
        }


    }

    private static String saveFile(MultipartFile file, String storagePath) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            String originFileName = StringUtils.cleanPath(file.getOriginalFilename());

            String filePath = storagePath + originFileName;

            FileCopyUtils.copy(file.getBytes(), new File(filePath));

            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
