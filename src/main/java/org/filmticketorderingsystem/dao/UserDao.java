package org.filmticketorderingsystem.dao;

import org.filmticketorderingsystem.domain.User;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface UserDao extends BaseDao<User>{
    User findById(String id);
}
