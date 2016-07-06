package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "cinema_info")
public class Cinema implements Serializable {
    @Id
    @Column(name = "cinema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemaId;
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "cinema_name")
    private String cinemaName;
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "wallet")
    private Double wallet;

    @OneToMany(targetEntity = FilmSession.class, mappedBy = "cinema")
    private Set<FilmSession> filmSessions;

    @OneToMany(targetEntity = CinemaHall.class, mappedBy = "cinema")
    private Set<CinemaHall> cinemaHalls;

    public Cinema() {
        filmSessions = new HashSet<FilmSession>();
        cinemaHalls = new HashSet<CinemaHall>();
    }

    public boolean validate(String password){
        if(this.password.equals(password)){
            return true;
        }

        return false;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<FilmSession> getFilmSessions() {
        return filmSessions;
    }

    public void setFilmSessions(Set<FilmSession> filmSessions) {
        this.filmSessions = filmSessions;
    }

    public Set<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setCinemaHalls(Set<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema)) return false;

        Cinema cinema = (Cinema) o;

        if (cinemaId != null ? !cinemaId.equals(cinema.cinemaId) : cinema.cinemaId != null) return false;
        if (id != null ? !id.equals(cinema.id) : cinema.id != null) return false;
        if (cinemaName != null ? !cinemaName.equals(cinema.cinemaName) : cinema.cinemaName != null) return false;
        return !(address != null ? !address.equals(cinema.address) : cinema.address != null);

    }

    @Override
    public int hashCode() {
        int result = cinemaId != null ? cinemaId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (cinemaName != null ? cinemaName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
