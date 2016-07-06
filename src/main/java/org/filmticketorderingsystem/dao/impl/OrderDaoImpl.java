package org.filmticketorderingsystem.dao.impl;

import org.filmticketorderingsystem.dao.OrderDao;
import org.filmticketorderingsystem.domain.Order;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {
    public List<Order> findByUser(String id) {
        return query("select o from Order o inner join o.user u where u.id = ?0 and o.state != -1", id);
    }

    public Order findByOrderNum(String orderNum) {
        return query("select o from Order o  where o.orderNum = ?0 and o.state != -1", orderNum).get(0);
    }

    public long countAllByUser(String id) {
        return count("select count(*) from Order o inner join o.user u where u.id = ?0 and o.state != -1", id);
    }
}
