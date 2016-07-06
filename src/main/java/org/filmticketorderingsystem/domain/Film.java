package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "film_info")
public class Film implements Serializable {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer filmId;
    @Column(name = "film_name")
    private String filmName;
    @Column(name = "kind")
    private String kind;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private String duration;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "picture_path")
    private String pricturePath;
    @ManyToMany(targetEntity = Actor.class, fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor_info",
                joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
                inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
            )
    private Set<Actor> actors;

    @OneToMany(targetEntity = FilmSession.class, mappedBy = "film")
    private Set<FilmSession> filmSessions;

    public Film(){
        actors = new HashSet<Actor>();
        filmSessions = new HashSet<FilmSession>();
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPricturePath() {
        return pricturePath;
    }

    public void setPricturePath(String pricturePath) {
        this.pricturePath = pricturePath;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<FilmSession> getFilmSessions() {
        return filmSessions;
    }

    public void setFilmSessions(Set<FilmSession> filmSessions) {
        this.filmSessions = filmSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;

        Film film = (Film) o;

        if (filmId != null ? !filmId.equals(film.filmId) : film.filmId != null) return false;
        return !(filmName != null ? !filmName.equals(film.filmName) : film.filmName != null);

    }

    @Override
    public int hashCode() {
        int result = filmId != null ? filmId.hashCode() : 0;
        result = 31 * result + (filmName != null ? filmName.hashCode() : 0);
        return result;
    }
}
