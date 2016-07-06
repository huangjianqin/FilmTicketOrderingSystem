package org.filmticketorderingsystem.dao.impl;

import org.hibernate.Query;
import org.filmticketorderingsystem.dao.HallsRowDao;
import org.filmticketorderingsystem.domain.HallsRow;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class HallsRowDaoImpl extends BaseDaoImpl<HallsRow> implements HallsRowDao {

    public List<HallsRow> findbyHallsRow(int filmSeesionId) {
        return query("select hr " +
                "from HallsRow hr inner join FilmSession fs where fs.filmSeesionId = ?0", filmSeesionId);
    }

    public List<HallsRow> findDeactivedHallsRow(int filmSeesionId) {
        Query query = getFactory().getCurrentSession()
                .createQuery("select hr " +
                        "from ((FilmTicket ft inner join ft.selectedSeat hr) " +
                        "inner join ft.filmSeesion fs" +
                        " where fs.filmSeesionId = ?0");
        query.setParameter("" + 0, filmSeesionId);

        return (List<HallsRow>) query.list();
    }
}
