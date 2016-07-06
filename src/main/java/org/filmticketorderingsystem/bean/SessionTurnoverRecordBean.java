package org.filmticketorderingsystem.bean;

/**
 * Created by 健勤 on 2016/5/16.
 */
public class SessionTurnoverRecordBean {
    private String startDate;//时分
    private String type;//播放语言+播放类型
    private String hallName;
    private Double price;
    private Integer soldTicketNum;
    private Double sumPrice;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSoldTicketNum() {
        return soldTicketNum;
    }

    public void setSoldTicketNum(Integer soldTicketNum) {
        this.soldTicketNum = soldTicketNum;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
