package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.UserDao;
import org.filmticketorderingsystem.domain.User;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public User findById(String id) {
        List<User> users = query("from User u where u.id = ?0", id);

        if(users.size() != 0){
            return users.get(0);
        }

        return null;
    }
}
