package org.filmticketorderingsystem.service;

import org.filmticketorderingsystem.bean.OrderBean;
import org.filmticketorderingsystem.bean.OrderDetailBean;
import org.filmticketorderingsystem.bean.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 健勤 on 2016/5/13.
 */
public interface UserInfoService {
    int regiser(String id, String userName, String password);
    String login(String id, String password);
    int logout(HttpServletRequest request);

    UserBean getUserInfo(String id);
    int modifyUserInfo(String id, String userName);
    int modifyPassword(String id, String oldPassword, String newPassword);
    double[] recharge(String id, double money);

    List<OrderBean> getOrders(String id);
    OrderDetailBean getOrderDetail(String orderNum);
}
