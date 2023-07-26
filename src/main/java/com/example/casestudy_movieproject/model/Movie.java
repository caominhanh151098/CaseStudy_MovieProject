package com.example.casestudy_movieproject.model;

import com.example.casestudy_movieproject.model.enums.EQuality;
import com.example.casestudy_movieproject.model.enums.EStatus;
import com.example.casestudy_movieproject.model.enums.EType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "status != '1'")
@SQLDelete(sql = "UPDATE movies  SET status = '1' WHERE (`id` = ?);")
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "movie")
    private Set<EpMovie> epMovies;
    private int airedYear;
    private int duration;
    @Column(nullable = true)
    private double scoreIMDb;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EQuality quality;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String urlTrailer;
    private String img_poster;
    private String img_movie;

    @OneToMany(mappedBy = "movie")
    private Set<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "movie")
    private Set<Follow> follows;

    @OneToMany(mappedBy = "movie")
    private Set<View> views;

    @OneToMany(mappedBy = "movie")
    private Set<EKip> eKips;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EType type;
    @Column(nullable = true)
    private int totalEp;
}