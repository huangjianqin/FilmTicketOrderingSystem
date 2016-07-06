package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.CinemaHall;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface CinemaHallDao extends BaseDao<CinemaHall>{
    CinemaHall findCinemaHall(int cinemaHallId, int cinemaId);
}
