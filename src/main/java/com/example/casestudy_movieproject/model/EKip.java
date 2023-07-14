package com.example.casestudy_movieproject.model;

import com.example.casestudy_movieproject.model.enums.ERoleEKip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EKip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERoleEKip role;
}
