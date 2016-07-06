package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.FilmDao;
import org.filmticketorderingsystem.domain.Film;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class FilmDaoImpl extends BaseDaoImpl<Film> implements FilmDao {
    public List<Film> findByName(String filmName) {
        return query("from Film f where f.filmName like '%" + filmName + "%'");
    }

    public List<Film> findByActorId(int actorId) {
        return query("select f from Film f inner join f.actors a where a.actorId = ?0", actorId);
    }

    public List<Film> findByActorName(String actorName) {
        return query("select f from Film f inner join f.actors a where a.actorName = ?0", actorName);
    }

    public List<Film> findFilmHavingFilmSession(String dateStr) {
        return query("select distinct f from FilmSession fs inner join fs.film f where fs.flag = 1 and substring(fs.startDate,1,10) >= ?0", dateStr);
    }

    public List<Film> findFilmByCinema(int cinemaId) {
        return query("select distinct f from FilmSession fs inner join fs.film f inner join fs.cinema c " +
                "where c.cinemaId = ?0 order by f.releaseDate desc", cinemaId);
    }
}
