package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.Film;
import org.filmticketorderingsystem.domain.FilmTicket;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface FilmTicketDao extends BaseDao<FilmTicket>{
    List<FilmTicket> findByOrderNum(String orderNum);
    List<String> findSelectedSeatBySession(int filmSessionId);

    long countAllByCinema(int cinemaId);
}
