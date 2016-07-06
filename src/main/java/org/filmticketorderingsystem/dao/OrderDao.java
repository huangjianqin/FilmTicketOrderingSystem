package org.filmticketorderingsystem.dao;


import org.filmticketorderingsystem.domain.Order;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/2.
 */
public interface OrderDao extends BaseDao<Order>{
    List<Order> findByUser(String id);
    Order findByOrderNum(String orderNum);

    long countAllByUser(String id);
}
