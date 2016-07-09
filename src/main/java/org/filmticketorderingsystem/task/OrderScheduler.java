package org.filmticketorderingsystem.task;

import org.filmticketorderingsystem.dao.FilmTicketDao;
import org.filmticketorderingsystem.dao.OrderDao;
import org.filmticketorderingsystem.domain.FilmTicket;
import org.filmticketorderingsystem.generator.DateGenerator;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by 健勤 on 2016/7/9.
 */
public class OrderScheduler {
    @Resource(name = "orderDao")
    private OrderDao orderDao;
    @Resource(name = "dateGenerator")
    private DateGenerator dateGenerator;
    @Resource(name = "filmTicketDao")
    private FilmTicketDao filmTicketDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public DateGenerator getDateGenerator() {
        return dateGenerator;
    }

    public void setDateGenerator(DateGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }

    /**
     * 每一分钟运行一次,用于更新订单的有效标志
     * 因为订单默认是15分钟未支付则取消,同时解锁与该订单关联的所有电影票
     */
    @Scheduled(cron = "* */1 * * * *")
    public void updateOrderFlag(){
        String hql1 = "update Order o set state = -1 where o.bookTime < ?0";
        orderDao.update(hql1, dateGenerator.getFormatedNowFullDateBefore15m());

        String hql2 = "update FilmTicket ft set ft.flag = -1 where ft.order.state = -1";
        filmTicketDao.update(hql2, null);

    }
}
