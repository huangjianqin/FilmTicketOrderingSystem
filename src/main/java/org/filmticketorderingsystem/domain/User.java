package org.filmticketorderingsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "id")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "wallet")
    private Double wallet;

    @OneToMany(targetEntity = Order.class, mappedBy = "user")
    private Set<Order> orders;

    public User() {
        wallet = 0D;
        orders = new HashSet<Order>();
    }

    public boolean validate(String password){
        if(this.password.equals(password)){
            return true;
        }

        return false;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Double getWallet() {
        return wallet;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
