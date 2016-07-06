package org.filmticketorderingsystem.bean;

/**
 * Created by 健勤 on 2016/5/6.
 * 用于返回用户信息的bean
 */

public class UserBean {
    private Integer userId;
    private String id;
    private String userName;
    private Double wallet;
    private Integer state;//1为成功,-1为失败

    public UserBean() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
