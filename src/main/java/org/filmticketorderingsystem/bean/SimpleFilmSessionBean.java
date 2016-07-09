package org.filmticketorderingsystem.bean;

/**
 * Created by 健勤 on 2016/5/14.
 */
public class SimpleFilmSessionBean {
    private Integer filmSessionId;
    private String startDate;
    private String type;//播放类型+播放语言
    private Double price;

    private String endDate;
    private String hallName;

    public Integer getFilmSessionId() {
        return filmSessionId;
    }

    public void setFilmSessionId(Integer filmSessionId) {
        this.filmSessionId = filmSessionId;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
