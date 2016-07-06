package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.domain.CinemaHall;
import org.filmticketorderingsystem.domain.HallsRow;
import org.hibernate.Query;
import org.filmticketorderingsystem.dao.FilmSessionDao;
import org.filmticketorderingsystem.domain.Film;
import org.filmticketorderingsystem.domain.FilmSession;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class FilmSessionImpl extends BaseDaoImpl<FilmSession> implements FilmSessionDao{

    public List<FilmSession> findByFilm(int filmId, String dateStr){
        return query("select fs from FilmSession fs inner join fs.cinema c inner join fs.film f " +
                "where f.filmId= ?0 and fs.flag = 1 and substring(fs.startDate,1,10) = ?1 " +
                "order by c.cinemaName", filmId, dateStr);
    }

    public List<FilmSession> findByCinema(int cinemaId, String dateStr) {
        return query("select fs from FilmSession fs inner join fs.cinema c inner join fs.film f " +
                "where fs.flag = 1 and c.cinemaId = ?0 and substring(fs.startDate,1,10) = ?1", cinemaId, dateStr);
    }

    public List<FilmSession> findByCinemaAndFIlm(int id, int filmId, String nowDataStr) {
        return query("select fs from FilmSession fs inner join fs.cinema c inner join fs.film f " +
                "where f.filmId = ?1 and c.id = ?0 and fs.startDate <= ?2 order by fs.startDate desc", id, filmId, nowDataStr);
    }
}
