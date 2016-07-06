package org.filmticketorderingsystem.service.impl;

import org.filmticketorderingsystem.bean.*;
import org.filmticketorderingsystem.dao.*;
import org.filmticketorderingsystem.domain.*;
import org.filmticketorderingsystem.enums.OrderState;
import org.filmticketorderingsystem.generator.OrderNumGenerator;
import org.filmticketorderingsystem.generator.OrderVerifiCodeGenerator;
import org.filmticketorderingsystem.service.UserInfoService;
import org.filmticketorderingsystem.service.UserOrderingService;
import org.filmticketorderingsystem.service.UserQueryService;
import org.filmticketorderingsystem.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 健勤 on 2016/5/3.
 */
public class UserServiceImpl implements UserService{
    private UserInfoService infoService;
    private UserQueryService queryService;
    private UserOrderingService orderingService;

    public UserInfoService getInfoService() {
        return infoService;
    }

    public void setInfoService(UserInfoService infoService) {
        this.infoService = infoService;
    }

    public UserQueryService getQueryService() {
        return queryService;
    }

    public void setQueryService(UserQueryService queryService) {
        this.queryService = queryService;
    }

    public UserOrderingService getOrderingService() {
        return orderingService;
    }

    public void setOrderingService(UserOrderingService orderingService) {
        this.orderingService = orderingService;
    }

    public int regiser(String id, String userName, String password) {
        return infoService.regiser(id, userName, password);
    }

    public String login(String id, String password) {
        return infoService.login(id, password);
    }

    public int logout(HttpServletRequest request) {
        return infoService.logout(request);
    }

    public UserBean getUserInfo(String id) {
        return infoService.getUserInfo(id);
    }

    public int modifyUserInfo(String id, String userName) {
        return infoService.modifyUserInfo(id, userName);
    }

    public int modifyPassword(String id, String oldPassword, String newPassword) {
        return infoService.modifyPassword(id, oldPassword, newPassword);
    }

    public double[] recharge(String id, double money) {
        return infoService.recharge(id, money);
    }

    public List<OrderBean> getOrders(String id) {
        return infoService.getOrders(id);
    }

    public OrderDetailBean getOrderDetail(String orderNum) {
        return infoService.getOrderDetail(orderNum);
    }

    public String[] handInOrder(String id, Integer filmSessionId, String[] selectedSeat) {
        return orderingService.handInOrder(id, filmSessionId, selectedSeat);
    }

    public int pay(String id, String orderNum) {
        return orderingService.pay(id, orderNum);
    }

    public List<SearchResultBean> search(String keyword) {
        return queryService.search(keyword);
    }

    public List<SimpleFilmBean> getRecentFilms(String dataStr) {
        return queryService.getRecentFilms(dataStr);
    }

    public FilmBean getFilmInfo(int filmId) {
        return queryService.getFilmInfo(filmId);
    }

    public FilmWithCineAndSessBean getCinemaSessionInfo(Integer cinemaId, String dateStr) {
        return queryService.getCinemaSessionInfo(cinemaId, dateStr);
    }

    public FilmWithSessionBean getOneFilmSessionInfo(Integer filmId, String dateStr) {
        return queryService.getOneFilmSessionInfo(filmId, dateStr);
    }

    public FilmSeesionDetailBean getOneFilmSessionDetail(Integer sessionId) {
        return queryService.getOneFilmSessionDetail(sessionId);
    }
}
