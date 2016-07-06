package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.Cinema;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface CinemaDao extends BaseDao<Cinema>{
    Cinema findById(String id);
    List<Cinema> findByName(String cinemaName);
}
