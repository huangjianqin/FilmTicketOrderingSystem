package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.ActorDao;
import org.filmticketorderingsystem.domain.Actor;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class ActorDaoImpl extends BaseDaoImpl<Actor> implements ActorDao{
    public List<Actor> findByName(String actorName) {
        return query("from Actor a where a.actorName = ?0", actorName);
    }
}
