package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.CinemaDao;
import org.filmticketorderingsystem.domain.Cinema;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class CinemaDaoImpl extends BaseDaoImpl<Cinema> implements CinemaDao {
    public Cinema findById(String id) {
        return query("from Cinema c where c.id = ?0", id).get(0);
    }

    public List<Cinema> findByName(String cinemaName) {
        return query("from Cinema c where c.cinemaName like '%" + cinemaName + "%'");
    }

}
