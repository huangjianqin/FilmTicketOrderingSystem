package org.filmticketorderingsystem.dao;


import org.filmticketorderingsystem.domain.CinemaHall;
import org.filmticketorderingsystem.domain.Film;
import org.filmticketorderingsystem.domain.FilmSession;
import org.filmticketorderingsystem.domain.HallsRow;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface FilmSessionDao extends BaseDao<FilmSession>{
    /**
     * 找电影场次
     * @param filmId
     * @param dateStr
     * @return
     */
    List<FilmSession> findByFilm(int filmId, String dateStr);

    /**
     * 找电影院场次
     * @param cinemaId
     * @param dateStr
     * @return
     */
    List<FilmSession> findByCinema(int cinemaId, String dateStr);

    /**
     * 找具体某一电影院及具体某一电影的场次
     * @param cinemaId
     * @param filmId
     * @return
     */
    List<FilmSession> findByCinemaAndFIlm(int cinemaId, int filmId, String nowDataStr);
}
