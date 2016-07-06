package org.filmticketorderingsystem.service.impl;

import org.filmticketorderingsystem.dao.FilmSessionDao;
import org.filmticketorderingsystem.dao.FilmTicketDao;
import org.filmticketorderingsystem.dao.OrderDao;
import org.filmticketorderingsystem.dao.UserDao;
import org.filmticketorderingsystem.domain.*;
import org.filmticketorderingsystem.enums.OrderState;
import org.filmticketorderingsystem.generator.OrderNumGenerator;
import org.filmticketorderingsystem.generator.OrderVerifiCodeGenerator;
import org.filmticketorderingsystem.service.UserOrderingService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 健勤 on 2016/5/13.
 */
public class UserOrderingServiceImpl implements UserOrderingService {
    private OrderDao orderDao;
    private UserDao userDao;
    private FilmSessionDao filmSeesionDao;
    private FilmTicketDao filmTicketDao;
    private OrderNumGenerator orderNumGenerator;
    private OrderVerifiCodeGenerator orderVerifiCodeGenerator;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public FilmSessionDao getFilmSeesionDao() {
        return filmSeesionDao;
    }

    public void setFilmSeesionDao(FilmSessionDao filmSeesionDao) {
        this.filmSeesionDao = filmSeesionDao;
    }

    public FilmTicketDao getFilmTicketDao() {
        return filmTicketDao;
    }

    public void setFilmTicketDao(FilmTicketDao filmTicketDao) {
        this.filmTicketDao = filmTicketDao;
    }

    public OrderNumGenerator getOrderNumGenerator() {
        return orderNumGenerator;
    }

    public void setOrderNumGenerator(OrderNumGenerator orderNumGenerator) {
        this.orderNumGenerator = orderNumGenerator;
    }

    public OrderVerifiCodeGenerator getOrderVerifiCodeGenerator() {
        return orderVerifiCodeGenerator;
    }

    public void setOrderVerifiCodeGenerator(OrderVerifiCodeGenerator orderVerifiCodeGenerator) {
        this.orderVerifiCodeGenerator = orderVerifiCodeGenerator;
    }

    /**
     *提交订单
     * @param id
     * @param filmSessionId
     * @param selectedSeat
     * @return {-2失败(服务器问题) -1场次失效 1提交订单成功 返回状态码+订单号}
     */
    public String[] handInOrder(String id, Integer filmSessionId, String[] selectedSeat) {
        String[] result = new String[]{"", ""};

        FilmSession filmSession = filmSeesionDao.get(FilmSession.class, filmSessionId);
        User user = userDao.findById(id);

        if(filmSession == null || user == null){
            result[0] = "-2";
            return result;
        }

        //判断场次是否失效
        if(filmSession.getFlag() == -1){
            result[0] = "-1";
            return result;
        }

        Date now = new Date();

        String orderNum = orderNumGenerator.generate(now);
        String orderVerifiCode = orderVerifiCodeGenerator.generate(now);

        Order order = new Order();

        order.setOrderNum(orderNum);//generator订单号
        order.setBookTime(orderNumGenerator.dateFormat(now));
        order.setState(OrderState.ORDER_NOT_PAID.getState());
        order.setUser(user);
        order.setSumMoney(selectedSeat.length * filmSession.getPrice());
        order.setVerificationCode(orderVerifiCode);//generator订单验证码

        for(String seat : selectedSeat){
            FilmTicket ticket = new FilmTicket();

            //电影票ID=场次ID+行号+列号,
            String sessionId = String.valueOf(filmSession.getFilmSessionId());
            String row = seat.split(" ")[0];
            String col = seat.split(" ")[1];

            //若行号的长度是2,则是1排XX座,需要转换为01的数字形式
            if(row.length() == 2){
                row += "0" + row;
            }

            ticket.setFilmTicketId(Integer.valueOf(sessionId
                    + row.substring(0, row.length() - 1) + col.substring(0, row.length() - 1)));
            ticket.setFilmSession(filmSession);
            ticket.setSelectedSeat(seat);
            ticket.setOrder(order);
            filmTicketDao.save(ticket);
        }

        orderDao.save(order);

        result[0] = "1";
        result[1] = orderNum;

        return result;
    }

    /**
     *
     * @param id
     * @param orderNum
     * @return -3用户余额不足 -2订单超过15分钟未支付自动失效 -1支付失败 0订单已支付 1成功
     */
    public int pay(String id, String orderNum) {
        int flag = -1;

        User user = userDao.findById(id);
        Order order = orderDao.findByOrderNum(orderNum);

        //判断订单是否已支付
        if(order.getState() == OrderState.ORDER_PAID.getState()){
            return 0;
        }
        //判断订单是否超过15分钟未支付自动失效
        if(order.getState() == OrderState.ORDER_UNAVAILABLE.getState()){
            return -2;
        }

        Iterator<FilmTicket> iterator = order.getFilmTickets().iterator();
        Cinema cinema = null;

        if(iterator.hasNext()){
           cinema = iterator.next().getFilmSession().getCinema();
        }

        if(cinema == null){
            return -1;
        }
        //支付订单,需从用户赚钱至电影院指定的支付账号
        if (user.getWallet() < order.getSumMoney()) {
            flag = -3;
        } else {
            user.setWallet(user.getWallet() - order.getSumMoney());
            flag = 1;
        }

        cinema.setWallet(cinema.getWallet() + order.getSumMoney());

        return flag;
    }
}
