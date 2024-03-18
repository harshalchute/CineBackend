package com.Book_My_Show.bookmyshow.Models;

import com.Book_My_Show.bookmyshow.Enums.Genre;
import com.Book_My_Show.bookmyshow.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private double duration;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    private double rating;

    @Enumerated(value = EnumType.STRING)
    private Language language;
}
