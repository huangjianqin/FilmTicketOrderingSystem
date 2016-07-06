package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.CinemaHallDao;
import org.filmticketorderingsystem.domain.CinemaHall;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class CinemaHallDaoImpl extends BaseDaoImpl<CinemaHall> implements CinemaHallDao {
    public CinemaHall findCinemaHall(int cinemaHallId, int cinemaId) {
        List<CinemaHall> cinemaHalls = query("select ch from CinemaHall ch inner join ch.cinema c where ch.hallId = ?0 and c.cinemaId = ?1", cinemaHallId, cinemaId);

        return cinemaHalls.get(0);
    }
}
