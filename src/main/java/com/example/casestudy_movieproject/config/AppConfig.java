package com.example.casestudy_movieproject.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${file.storage.movie.imageMovie}")
    private String imgStoragePathMovie;

    @Value("${file.storage.movie.poster}")
    private String imgStoragePoster;

    @Value("${file.storage.movie.trailer}")
    private String urlStorageTrailer;

    @Value("${file.storage.movie.chapter}")
    private String urlStorageChapter;

    @Bean
    public String getImgStoragePathMovie(){
        return imgStoragePathMovie;
    }

    @Bean
    public String getImgStoragePoster() {
        return imgStoragePoster;
    }

    @Bean
    public String getUrlStorageTrailer() {
        return urlStorageTrailer;
    }

    @Bean
    public String getUrlStorageChapter() {
        return urlStorageChapter;
    }
}
