package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "cinema_hall_info")
public class CinemaHall implements Serializable {
    @Id
    @Column(name = "cinema_hall_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemaHallId;
    @Column(name = "hall_name")
    private String hallName;

    @OneToMany(targetEntity = FilmSession.class, mappedBy = "cinemaHall")
    private Set<FilmSession> filmSessions;

    @OneToMany(targetEntity = HallsRow.class, mappedBy = "cinemaHall")
    private Set<HallsRow> hallsRows;

    @ManyToOne(targetEntity = Cinema.class)
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema_id")
    private Cinema cinema;

    public CinemaHall() {
        filmSessions = new HashSet<FilmSession>();
        hallsRows = new HashSet<HallsRow>();
    }

    public Integer getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Integer cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Set<FilmSession> getFilmSessions() {
        return filmSessions;
    }

    public void setFilmSessions(Set<FilmSession> filmSessions) {
        this.filmSessions = filmSessions;
    }

    public Set<HallsRow> getHallsRows() {
        return hallsRows;
    }

    public void setHallsRows(Set<HallsRow> hallsRows) {
        this.hallsRows = hallsRows;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaHall)) return false;

        CinemaHall that = (CinemaHall) o;

        if (cinemaHallId != null ? !cinemaHallId.equals(that.cinemaHallId) : that.cinemaHallId != null) return false;
        if (hallName != null ? !hallName.equals(that.hallName) : that.hallName != null) return false;
        return !(cinema != null ? !cinema.equals(that.cinema) : that.cinema != null);

    }

    @Override
    public int hashCode() {
        int result = cinemaHallId != null ? cinemaHallId.hashCode() : 0;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        return result;
    }
}
