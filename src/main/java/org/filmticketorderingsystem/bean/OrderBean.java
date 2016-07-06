package org.filmticketorderingsystem.bean;

/**
 * Created by ���� on 2016/5/13.
 */
public class OrderBean {
    private String filmName;
    private Integer ticketNum;
    private String startDate;
    private String cinemaName;
    private Integer orderState;//0未支付 1支付完成 2订单消费完成
    private Double sumMoney;

    private Integer state;//服务器返回json状态

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
