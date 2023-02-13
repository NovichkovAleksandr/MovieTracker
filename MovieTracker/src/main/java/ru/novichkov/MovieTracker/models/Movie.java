package ru.novichkov.MovieTracker.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "moviename")
    @NotEmpty(message = "Moviename should not be empty")
    private String movieName;

    @Column(name = "genre")
    @NotEmpty(message = "Genre should not be empty")
    private String genre;

    @Column(name = "duration")
    @Min(value = 0, message = "Duration should be greater then 0")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person user;


    public Movie() {}

    public Movie(String movieName, String genre, int duration) {
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Person getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                '}';
    }
}
