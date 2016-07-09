package org.filmticketorderingsystem.bean;

import org.filmticketorderingsystem.domain.Actor;

import java.util.Set;


/**
 * Created by 健勤 on 2016/5/5.
 */
public class FilmBean {
    protected Integer filmId;
    private String filmName;
    private String kind;
    private String releaseDate;
    private String description;
    private String duration;
    private Integer rating;
    private String pricturePath;
    private Set<ActorBean> actors;

    private Integer state = -1;

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

    public Set<ActorBean> getActors() {
        return actors;
    }

    public void setActors(Set<ActorBean> actors) {
        this.actors = actors;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
