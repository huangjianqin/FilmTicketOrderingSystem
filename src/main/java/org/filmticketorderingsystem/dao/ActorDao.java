package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.Actor;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface ActorDao extends BaseDao<Actor>{
    List<Actor> findByName(String actorName);
}
