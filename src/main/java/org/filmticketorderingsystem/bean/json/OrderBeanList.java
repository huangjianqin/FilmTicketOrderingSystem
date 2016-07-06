package org.filmticketorderingsystem.bean.json;

import org.filmticketorderingsystem.bean.OrderBean;

import java.util.List;

/**
 * Created by 健勤 on 2016/7/4.
 */
public class OrderBeanList {
    private List<OrderBean> orderBeans;
    private int state;

    public List<OrderBean> getOrderBeans() {
        return orderBeans;
    }

    public void setOrderBeans(List<OrderBean> orderBeans) {
        this.orderBeans = orderBeans;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
