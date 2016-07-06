package org.filmticketorderingsystem.service;

/**
 * Created by 健勤 on 2016/5/13.
 */
public interface UserOrderingService {
    String[] handInOrder(String id, Integer filmSessionId, String[] selectedSeat);
    int pay(String id, String orderNum);
}
