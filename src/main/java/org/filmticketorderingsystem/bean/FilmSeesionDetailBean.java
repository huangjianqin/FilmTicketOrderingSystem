package org.filmticketorderingsystem.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/5/5.
 */
public class FilmSeesionDetailBean {
    private Integer filmSeesionId;
    private String pricturePath;
    private String filmName;
    private String kind;

    private String cinemaName;
    private String type;//播放语言+播放类型
    private String startDate;
    private Double price;
    private String cinemaHallName;
    private Integer restSeatNum;
    //key为排号,value为具体座位的信息
    private Map<String, List<String>> hall2Seat;

    private Integer state = -1;

    public Integer getFilmSeesionId() {
        return filmSeesionId;
    }

    public void setFilmSeesionId(Integer filmSeesionId) {
        this.filmSeesionId = filmSeesionId;
    }

    public String getPricturePath() {
        return pricturePath;
    }

    public void setPricturePath(String pricturePath) {
        this.pricturePath = pricturePath;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCinemaHallName() {
        return cinemaHallName;
    }

    public void setCinemaHallName(String cinemaHallName) {
        this.cinemaHallName = cinemaHallName;
    }

    public Integer getRestSeatNum() {
        return restSeatNum;
    }

    public void setRestSeatNum(Integer restSeatNum) {
        this.restSeatNum = restSeatNum;
    }

    public Map<String, List<String>> getHall2Seat() {
        return hall2Seat;
    }

    public void setHall2Seat(Map<String, List<String>> hall2Seat) {
        this.hall2Seat = hall2Seat;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
