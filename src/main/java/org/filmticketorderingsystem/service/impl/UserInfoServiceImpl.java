package org.filmticketorderingsystem.service.impl;

import org.filmticketorderingsystem.bean.OrderBean;
import org.filmticketorderingsystem.bean.OrderDetailBean;
import org.filmticketorderingsystem.bean.UserBean;
import org.filmticketorderingsystem.dao.OrderDao;
import org.filmticketorderingsystem.dao.UserDao;
import org.filmticketorderingsystem.domain.FilmSession;
import org.filmticketorderingsystem.domain.FilmTicket;
import org.filmticketorderingsystem.domain.Order;
import org.filmticketorderingsystem.domain.User;
import org.filmticketorderingsystem.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by 健勤 on 2016/5/13.
 */
public class UserInfoServiceImpl implements UserInfoService {
    private OrderDao orderDao;
    private UserDao userDao;

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

    public int regiser(String id, String userName, String password) {
        User user = new User();

        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);

        if(userDao.save(user) != null){
            return 1;
        }

        return -1;
    }

    public String login(String id, String password) {
        User user = userDao.findById(id);

        if(user != null){
            boolean flag = user.validate(password);

            if(flag){
                return user.getUserName();
            }
        }

        return null;
    }

    public int logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userName");
        return 1;
    }

    public UserBean getUserInfo(String id) {
        User user = userDao.findById(id);

        if(user != null){
            UserBean result = new UserBean();

            result.setUserId(user.getUserId());
            result.setId(user.getId());
            result.setUserName(user.getUserName());
            result.setWallet(user.getWallet());

            return result;
        }

        return null;
    }

    public int modifyUserInfo(String id, String userName) {
        User user = userDao.findById(id);

        if(user != null){
            user.setUserName(userName);

            userDao.update(user);

            return 1;
        }

        return -1;
    }

    public int modifyPassword(String id, String oldPassword, String newPassword) {
        User user = userDao.findById(id);

        if(user != null){
            if(user.validate(oldPassword)){
                user.setPassword(newPassword);

                userDao.update(user);

                return 1;
            }
        }

        return -1;
    }

    /**
     *
     * @param id
     * @param money
     * @return [状态,当前金额]
     */
    public double[] recharge(String id, double money) {
        User user = userDao.findById(id);

        double[] result = new double[2];
        //默认不成功
        result[0] = -1;

        if(user != null){
            user.setWallet(user.getWallet() + money);

            userDao.update(user);

            result[0] = 1;
            result[1] = user.getWallet();

            return result;
        }

        return result;
    }

    public List<OrderBean> getOrders(String id) {
        List<Order> orders = orderDao.findByUser(id);

        if(orders != null){
            List<OrderBean> result = new ArrayList<OrderBean>();

            for(Order order : orders){
                Set<FilmTicket> filmTickets = order.getFilmTickets();
                Iterator<FilmTicket> iterator = filmTickets.iterator();
                //获取其中一张电影票,进而获取部分电影信息
                FilmTicket oneTicket = null;
                if(iterator.hasNext()){
                    oneTicket = iterator.next();
                }

                if(oneTicket == null){
                    return null;
                }

                OrderBean orderBean = new OrderBean();

                orderBean.setFilmName(oneTicket.getFilmSession().getFilm().getFilmName());
                orderBean.setCinemaName(oneTicket.getFilmSession().getCinema().getCinemaName());
                orderBean.setStartDate(oneTicket.getFilmSession().getStartDate());
                orderBean.setTicketNum(order.getFilmTickets().size());
                orderBean.setOrderState(order.getState());
                orderBean.setSumMoney(order.getSumMoney());

                result.add(orderBean);
            }

            return result;
        }

        return null;
    }

    public OrderDetailBean getOrderDetail(String orderNum) {
        Order order = orderDao.findByOrderNum(orderNum);

        if(order != null){
            Set<FilmTicket> filmTickets = order.getFilmTickets();
            Iterator<FilmTicket> iterator = filmTickets.iterator();

            //获取其中一张电影票,进而获取部分电影信息
            FilmTicket oneTicket = null;
            if(iterator.hasNext()){
                oneTicket = iterator.next();
            }

            if(oneTicket == null){
                return null;
            }

            //记录所有电影所选的座位信息
            List<String > selectedSeats = new ArrayList<String>();

            selectedSeats.add(oneTicket.getSelectedSeat());

            while(iterator.hasNext()){
                selectedSeats.add(iterator.next().getSelectedSeat());
            }

            //获取电影票对应的场次信息
            FilmSession filmSession = oneTicket.getFilmSession();

            OrderDetailBean result = new OrderDetailBean();

            result.setPricturePath(filmSession.getFilm().getPricturePath());
            result.setFilmName(filmSession.getFilm().getFilmName());
            result.setStartDate(filmSession.getStartDate());
            result.setEndDate(filmSession.getEndDate());
            result.setPlayLanguage(filmSession.getPlayLanguage());
            result.setPlayType(filmSession.getPlayType());
            result.setCinemaName(filmSession.getCinema().getCinemaName());
            result.setHallName(filmSession.getCinemaHall().getHallName());
            result.setSelectedSeats(selectedSeats);
            result.setVerificationCode(order.getVerificationCode());
            result.setOrderState(order.getState());

            result.setAddress(filmSession.getCinema().getAddress());
            result.setTelephone(filmSession.getCinema().getTelephone());

            result.setSumMoney(order.getSumMoney());
            result.setOrderNum(order.getOrderNum());
            result.setBookTime(order.getBookTime());
            result.setFinishTime(order.getFinishTime());

            return result;
        }

        return null;
    }
}
