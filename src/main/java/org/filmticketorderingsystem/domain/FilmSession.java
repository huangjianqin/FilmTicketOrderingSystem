package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "film_session")
public class FilmSession implements Serializable {
    @Id
    @Column(name = "film_session_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmSessionId;
    @Column(name = "start_date")
    private String startDate;//2016-05-16 10:10
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "play_language")
    private String playLanguage;//播放语言
    @Column(name = "play_type")
    private String playType;//播放类型
    @Column(name = "price")
    private Double price;
    @Column(name = "flag")
    private Integer flag;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    @ManyToOne(targetEntity = Cinema.class)
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema_id")
    private Cinema cinema;

    @ManyToOne(targetEntity = CinemaHall.class)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "cinema_hall_id")
    private CinemaHall cinemaHall;

    @OneToMany(targetEntity = FilmTicket.class, mappedBy = "filmSession")
    private Set<FilmTicket> filmTickets;

    public FilmSession() {
        this.flag = 1;
        filmTickets = new HashSet<FilmTicket>();
    }

    public Integer getFilmSessionId() {
        return filmSessionId;
    }

    public void setFilmSessionId(Integer filmSessionId) {
        this.filmSessionId = filmSessionId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlayLanguage() {
        return playLanguage;
    }

    public void setPlayLanguage(String playLanguage) {
        this.playLanguage = playLanguage;
    }

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public Set<FilmTicket> getFilmTickets() {
        return filmTickets;
    }

    public void setFilmTickets(Set<FilmTicket> filmTickets) {
        this.filmTickets = filmTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmSession)) return false;

        FilmSession that = (FilmSession) o;

        if (filmSessionId != null ? !filmSessionId.equals(that.filmSessionId) : that.filmSessionId != null)
            return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (film != null ? !film.equals(that.film) : that.film != null) return false;
        if (cinema != null ? !cinema.equals(that.cinema) : that.cinema != null) return false;
        return !(cinemaHall != null ? !cinemaHall.equals(that.cinemaHall) : that.cinemaHall != null);

    }

    @Override
    public int hashCode() {
        int result = filmSessionId != null ? filmSessionId.hashCode() : 0;
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        result = 31 * result + (cinemaHall != null ? cinemaHall.hashCode() : 0);
        return result;
    }
}
