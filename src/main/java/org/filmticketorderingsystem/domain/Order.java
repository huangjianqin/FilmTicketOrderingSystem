package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "order_info")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "order_num")
    private String orderNum;
    @Column(name = "state")
    private Integer state;//0未支付 1支付完成 2订单消费完成
    @Column(name = "book_time")
    private String bookTime;
    @Column(name = "finishTime")
    private String finishTime;
    @Column(name = "sum_money")
    private Double sumMoney;
    @Column(name = "verification_code")
    private String verificationCode;

    @OneToMany(targetEntity = FilmTicket.class, mappedBy = "order")
    private Set<FilmTicket> filmTickets;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Order() {
        this.state = 0;
        this.filmTickets = new HashSet<FilmTicket>();
        this.finishTime = "";
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Set<FilmTicket> getFilmTickets() {
        return filmTickets;
    }

    public void setFilmTickets(Set<FilmTicket> filmTickets) {
        this.filmTickets = filmTickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        return !(orderNum != null ? !orderNum.equals(order.orderNum) : order.orderNum != null);

    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        return result;
    }
}
