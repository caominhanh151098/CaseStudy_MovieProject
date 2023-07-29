package com.example.casestudy_movieproject.service.movie.request;

import com.example.casestudy_movieproject.service.SelectOptionRequest;
import com.example.casestudy_movieproject.validation.valiEkip.ValidEkip;
import com.example.casestudy_movieproject.validation.valiGenre.ValidGenre;
import com.example.casestudy_movieproject.validation.valiQuality.ValidQuality;
import com.example.casestudy_movieproject.validation.valiRole.ValidRole;
import com.example.casestudy_movieproject.validation.valiStatus.ValidStatus;
import com.example.casestudy_movieproject.validation.valiType.ValidType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSaveRequest {
    private String id;
    @NotBlank(message = "Lỗi hệ thống")
    @Pattern(regexp = "^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$",message = "Lỗi hệ thống")
    @Size(max = 50,message = "Lỗi hệ thống")
    private String name;
    private String description;
    @NotBlank(message = "Lỗi hệ thống")
    @Pattern(regexp = "^[-+]?\\d+$",message = "Lỗi hệ thống")
    @Min(value = 0,message = "Lỗi hệ thống")
    private String airedYear;
    @NotBlank(message = "Lỗi hệ thống")
    @Pattern(regexp = "^[-+]?\\d+$",message = "Lỗi hệ thống")
    @Min(value = 0,message = "Lỗi hệ thống")
    private String duration;
    private MultipartFile img_movie;
    private String imgMovie;
    private MultipartFile img_poster;
    private String imgPoster;
    @Min(value = 0,message = "Lỗi hệ thống")
    @Pattern(regexp = "^[-+]?\\d+(\\.\\d+)?$",message = "Lỗi hệ thống")
    private String scoreIMDb;
    @NotBlank(message = "Lỗi hệ thống")
    @Pattern(regexp = "^[-+]?\\d+$",message = "Lỗi hệ thống")
    @Min(value = 0,message = "Lỗi hệ thống")
    private String totalEp;
    private MultipartFile urlTrailer;
    private String url_trailer;
    @ValidType
    private String type;
    @ValidStatus
    private String status;
    @ValidQuality
    private String quality;
    private List<EkipSaveRequest> eKips = new ArrayList<>();
    private List<UrlMovieSaveRequest> epMovies = new ArrayList<>();

    private List<String> movieGenres = new ArrayList<>();

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
        @NotBlank(message = "Lỗi hệ thống")
        @Pattern(regexp = "^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$",message = "Lỗi hệ thống")
        @Size(max = 50,message = "Lỗi hệ thống")
        private String name;
        private MultipartFile url;
        private String urlChapter;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EkipSaveRequest {

        private String id;
        @Size(max = 50,message = "Lỗi hệ thống")
        @Pattern(regexp = "^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$",message = "Lỗi hệ thống")
        private String name;
        @ValidEkip
        private String role;
    }
}
