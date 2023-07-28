package com.example.casestudy_movieproject.service.movie;

import com.example.casestudy_movieproject.config.AppConfig;
import com.example.casestudy_movieproject.model.*;

import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import com.example.casestudy_movieproject.repository.*;

import com.example.casestudy_movieproject.service.genre.response.ShowGenreByMovieResponse;

import com.example.casestudy_movieproject.service.movie.request.MovieSaveRequest;

import com.example.casestudy_movieproject.service.movie.response.*;
import com.example.casestudy_movieproject.service.ep_movie.reponse.ShowListEpisodeResponse;
import com.example.casestudy_movieproject.util.AppUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;


import java.io.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
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
    private final ViewRepository viewRepository;
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
        showMovieDetail.setViewNum(String.valueOf(viewRepository.countAllByMovie_Id(id)));
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

        Page<Movie> movies = movieRepository.searchAllWithPage(search, pageable);

        Page<MovieListResponse> responses = movieRepository.searchAllWithPage(search, pageable)
                .map(e -> AppUtils.mapper.map(e, MovieListResponse.class));

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

    public Page<ShowListMovieResponse> showListMovie(Pageable pageable, int type) {
        Page<ShowListMovieResponse> movies = movieRepository.getMovieUpdate(pageable)
                .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        switch (type){
            case 1:
                movies = movieRepository.getMovieByViewsDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 2:
                movies = movieRepository.getMovieByVotesDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 3:
                movies = movieRepository.getMovieByCommentsDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
        }
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            setInfoMovie(m, id);
        });
        return movies;
    }



    public Page<ShowListMovieResponse> showListMovieByGenre(String idGenre, Pageable pageable, int type) {
        Page<ShowListMovieResponse> movies = movieRepository.getMovieByGenre(idGenre, pageable)
                .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        switch (type){
            case 1:
                movies = movieRepository.getMovieGenreByViewsDESC(idGenre, pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 2:
                movies = movieRepository.getMovieGenreByVotesDESC(idGenre, pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 3:
                movies = movieRepository.getMovieGenreByCommentsDESC(idGenre, pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
        }
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            setInfoMovie(m, id);
        });
        return movies;
    }

    public Page<ShowListMovieResponse> showListEpMovie(Pageable pageable, int type) {
        Page<ShowListMovieResponse> movies = movieRepository.getMovieSeries(pageable)
                .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
        switch (type){
            case 1:
                movies = movieRepository.getMovieSeriesByViewsDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 2:
                movies = movieRepository.getMovieSeriesByVotesDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));
                break;
            case 3:
                movies = movieRepository.getMovieSeriesByCommentsDESC(pageable)
                        .map(e -> AppUtils.mapper.map(e, ShowListMovieResponse.class));

                break;
        }
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            setInfoMovie(m, id);
        });
        return movies;
    }

    public Page<ShowUpComingMovieResponse> showUpComingMovie(Pageable pageable) {
        Page<ShowUpComingMovieResponse> movies = movieRepository.getMovieComingSoon(pageable)
                .map(e -> AppUtils.mapper.map(e, ShowUpComingMovieResponse.class));
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

    private void setInfoMovie(ShowListMovieResponse m, int id) {
        m.setEpPresent(String.valueOf(epMovieRepository.countAllByMovie_Id(id)));
        m.setCommentNum(String.valueOf(commentRepository.countAllByMovie_Id(id)));
        m.setViewNum(String.valueOf(viewRepository.countAllByMovie_Id(id)));
        m.setGenres(genreRepository.getGenreByMovie(id)
                .stream()
                .map(g -> AppUtils.mapper.map(g, ShowGenreByMovieResponse.class))
                .collect(Collectors.toList()));
    }

    public List<ShowListRandomMovieResponse> showRandomWithoutMovie(int idMovie) {
        List<ShowListRandomMovieResponse> movies = movieRepository.getRandomWithoutMovie_Id(idMovie)
                .stream()
                .map(e -> AppUtils.mapper.map(e, ShowListRandomMovieResponse.class))
                .collect(Collectors.toList());
        movies.forEach(m -> {
            int id = Integer.parseInt(m.getId());
            m.setGender(genreRepository.getGenreByMovie(id).get(0).getName());
            m.setViewNum(String.valueOf(viewRepository.countAllByMovie_Id(id)));
            m.setEpPresent(String.valueOf(epMovieRepository.countAllByMovie_Id(id)));
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

        movie.setImg_movie(setLink + "/img/movie/" + movieSave.getImgMovie());
        movie.setImg_poster(setLink + "/img/poster/" + movieSave.getImgPoster());
        movie.setUrlTrailer(setLink + "/videos/trailer/" + movieSave.getUrl_trailer());

        movieRepository.save(movie);

        for (var chapter : movie.getEpMovies()) {
            for (var chapterSave : movieSave.getEpMovies()) {
                chapter.setUrl(setLink + "/videos/" + chapterSave.getUrl().getOriginalFilename());
                Movie movie1 = movieRepository.findById(movie.getId());
                EpMovie epMovie = new EpMovie(chapterSave.getName(), chapter.getUrl(), movie1);
                epMovieRepository.save(epMovie);
            }
        }


        for (String genre : movieSave.getMovieGenres()) {
            Genre genre1 = genreRepository.findById(Integer.parseInt(genre));
            MovieGenre newMovieGenre = new MovieGenre(movie, genre1);
            movieGenreRepository.save(newMovieGenre);
        }

        for (var ekip : movieSave.geteKips()) {
            Person person = personRepository.findPersonByNameContaining(ekip.getName());

            EKip eKip = new EKip(person, movie, ERoleEKip.valueOf(ekip.getRole()));
            eKipRepository.save(eKip);
        }

    }

    @Transactional
    public void edit(MovieSaveRequest movieSave) {
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
        for (var person:movie.getEKips()) {
            person.setId(0);
        }
        movie.setMovieGenres(new HashSet<>());
        movie.setEpMovies(new HashSet<>());
        System.out.println(movie);
        String setLink = "../assets/client";

        movie.setImg_movie(setLink + "/img/movie/" + movieSave.getImgMovie());
        movie.setImg_poster(setLink + "/img/poster/" + movieSave.getImgPoster());
        movie.setUrlTrailer(setLink + "/videos/trailer/" + movieSave.getUrl_trailer());
        movieRepository.save(movie);

        for (var chapter:movieSave.getEpMovies()) {
            if(Integer.parseInt(chapter.getId()) != 0){
                EpMovie epMovie = epMovieRepository.findById(Integer.parseInt(chapter.getId()));
                epMovie.setUrl(setLink + "/videos/" + chapter.getUrlChapter());
                epMovie.setName(chapter.getName());

            }
        }





        for (String genre : movieSave.getMovieGenres()) {
            Genre genre1 = genreRepository.findById(Integer.parseInt(genre));
            MovieGenre newMovieGenre = new MovieGenre(movie, genre1);
            movieGenreRepository.save(newMovieGenre);
        }


        for (var ekip : movieSave.geteKips()) {

            Person person = personRepository.findPersonByNameContaining(ekip.getName());

            EKip eKip = new EKip(person, movie, ERoleEKip.valueOf(ekip.getRole()));
            eKipRepository.save(eKip);
        }



    }

    @Transactional
    public void delete(int id) {
        List<EpMovie> epMovies = epMovieRepository.findAllByMovie_Id(id);
        Movie movie = movieRepository.findById(id);

        File fileImg = new File(appConfig.getImgStoragePathMovie(), Paths.get(movie.getImg_movie()).getFileName().toString());
        File filePoster = new File(appConfig.getImgStoragePoster(), Paths.get(movie.getImg_poster()).getFileName().toString());
        File urlTrailer = new File(appConfig.getUrlStorageTrailer(), Paths.get(movie.getUrlTrailer()).getFileName().toString());
        if (fileImg.exists() || filePoster.exists() || urlTrailer.exists()) {
            try {
                fileImg.delete();
                filePoster.delete();
                urlTrailer.delete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        for (var chapter : epMovies) {

            File file = new File(appConfig.getUrlStorageChapter(), Paths.get(chapter.getUrl()).getFileName().toString());

            File absolutePath = new File(file.getAbsolutePath());

            if (absolutePath.exists()) {
                try {
                    absolutePath.delete();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                break;
            }

        }
        epMovieRepository.deleteEpMovieByMovie_Id(id);
        eKipRepository.deleteEKipByMovie_Id(id);
        movieGenreRepository.deleteMovieGenreByMovie_Id(id);
        movieRepository.deleteById(id);


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


    public MovieSaveRequest showEdit(int id) {
        Movie movie1 = movieRepository.findById(id);


        MovieSaveRequest movie = AppUtils.mapper.map(movie1, MovieSaveRequest.class);
        movie.setImgMovie(movie1.getImg_movie().replace("../assets/client/img/movie/", ""));
        movie.setImgPoster(movie1.getImg_poster().replace("../assets/client/img/poster/", ""));
        movie.setUrl_trailer(movie1.getUrlTrailer().replace("../assets/client/videos/trailer/", ""));
        List<MovieSaveRequest.UrlMovieSaveRequest> epMovies = epMovieRepository.findAllByMovie_Id(movie1.getId())
                .stream().map(e -> AppUtils.mapper.map(e, MovieSaveRequest.UrlMovieSaveRequest.class)).collect(Collectors.toList());
        epMovies.forEach(e -> {
            EpMovie epMovie = epMovieRepository.findById(Integer.parseInt(e.getId()));
            e.setUrlChapter(epMovie.getUrl().replace("../assets/client/videos/", ""));
        });

        for (var genre : movieGenreRepository.findAllByMovie_Id(movie1.getId())) {
            movie.getMovieGenres().add(String.valueOf(genre.getGenre().getId()));
        }

        List<EKip> eKips = eKipRepository.findAllByMovie_Id(movie1.getId());

        List<MovieSaveRequest.EkipSaveRequest> eKipList = eKipRepository.findAllByMovie_Id(movie1.getId())
                .stream().map(e -> AppUtils.mapper.map(e, MovieSaveRequest.EkipSaveRequest.class)).collect(Collectors.toList());
        eKipList.forEach(e -> {
            for (var person : eKips) {
                Person person1 = personRepository.findById(person.getPerson().getId());
                e.setId(String.valueOf(person.getPerson().getId()));
                e.setName(person1.getName());
            }

        });
        movie.setScoreIMDb(movie.getScoreIMDb().replace(".0", ""));
        movie.seteKips(eKipList);
        movie.setEpMovies(epMovies);
        return movie;

    }

    List<Movie> listMovie;
    List<ShowMovieTopViewResponse> listMovieTopView;

    public List<ShowMovieTopViewResponse> getListMovieTopView() {
        listMovie = new ArrayList<>();
        listMovieTopView = new ArrayList<>();
        List<Movie> moviesOfDay = movieRepository.getMovieTopViewOfDay();
        List<Movie> moviesOfWeek = movieRepository.getMovieTopViewOfWeek();
        List<Movie> moviesOfMonth = movieRepository.getMovieTopViewOfMonth();
        List<Movie> moviesOfYear = movieRepository.getMovieTopViewOfYear();

        checkTimeTopView(moviesOfYear, "years");
        checkTimeTopView(moviesOfMonth, "month");
        checkTimeTopView(moviesOfWeek, "week");
        checkTimeTopView(moviesOfDay, "day");

        return listMovieTopView;
    }

    private void checkTimeTopView(List<Movie> list, String time) {
        for (Movie m : list) {
            if (listMovie.indexOf(m) < 0) {
                listMovie.add(m);
                ShowMovieTopViewResponse movie = AppUtils.mapper.map(m, ShowMovieTopViewResponse.class);
                movie.getOfTime().add(time);
                int id = Integer.parseInt(movie.getId());
                movie.setViewNum(String.valueOf(viewRepository.countAllByMovie_Id(id)));
                movie.setEpPresent(String.valueOf(epMovieRepository.countAllByMovie_Id(id)));
                listMovieTopView.add(movie);
            } else {
                ShowMovieTopViewResponse movie = listMovieTopView.get(listMovie.indexOf(m));
                if (movie.getOfTime().indexOf(time) < 0)
                    movie.getOfTime().add(time);
            }
        }
    }
}
