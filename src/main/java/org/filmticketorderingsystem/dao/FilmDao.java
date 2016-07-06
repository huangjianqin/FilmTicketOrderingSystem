package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.Film;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface FilmDao extends BaseDao<Film>{
    List<Film> findByName(String filmName);
    List<Film> findByActorId(int actorId);
    List<Film> findByActorName(String actorName);

    List<Film> findFilmHavingFilmSession(String dataStr);
    List<Film> findFilmByCinema(int cinemaId);

}
