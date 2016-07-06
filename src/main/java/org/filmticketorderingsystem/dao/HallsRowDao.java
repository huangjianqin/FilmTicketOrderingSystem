package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.HallsRow;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface HallsRowDao extends BaseDao<HallsRow>{
    List<HallsRow> findbyHallsRow(int filmSessionId);

    List<HallsRow> findDeactivedHallsRow(int filmSessionId);
}
