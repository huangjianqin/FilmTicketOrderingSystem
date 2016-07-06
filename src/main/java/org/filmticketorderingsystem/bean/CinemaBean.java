package org.filmticketorderingsystem.bean;

/**
 * Created by 健勤 on 2016/5/14.
 */
public class CinemaBean {
    private String cinemaName;
    private String address;
    private String telephone;
    //设置调用服务结果状态
    private Integer state;

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
