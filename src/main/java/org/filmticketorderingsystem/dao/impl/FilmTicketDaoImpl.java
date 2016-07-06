package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.FilmTicketDao;
import org.filmticketorderingsystem.domain.FilmTicket;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class FilmTicketDaoImpl extends BaseDaoImpl<FilmTicket> implements FilmTicketDao {
    public long countAllByCinema(int cinemaId) {
        return count("select count(*) " +
                "from FilmTicket ft inner join ft.cinema c " +
                "where c.cinemaId = ?0 and ft.flag = 1", cinemaId);
    }

    public List<FilmTicket> findByOrderNum(String orderNum) {
        return query("select ft " +
                "from FilmTicket f innert join ft.order o " +
                "where order.orderNum = ?0 and ft.flag = 1 and o.state != -1", orderNum);
    }

    public List<String> findSelectedSeatBySession(int filmSessionId) {
        Query query = getFactory().getCurrentSession()
                .createQuery("select ft.selectedSeat from FilmTicket ft inner join ft.filmSeesion fs " +
                        "where fs.filmSessionId = ?0 and ft.flag = 1 and fs.flag = 1")
                .setParameter("0", filmSessionId);

        return (List<String>)query.list();
    }
}
