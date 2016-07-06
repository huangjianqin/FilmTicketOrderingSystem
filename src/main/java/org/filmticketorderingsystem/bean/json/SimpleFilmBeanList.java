package org.filmticketorderingsystem.bean.json;

import org.filmticketorderingsystem.bean.SimpleFilmBean;

import java.util.List;

/**
 * Created by 健勤 on 2016/7/5.
 */
public class SimpleFilmBeanList {
    private List<SimpleFilmBean> simpleFilmBeans;
    private Integer state = -1;

    public List<SimpleFilmBean> getSimpleFilmBeans() {
        return simpleFilmBeans;
    }

    public void setSimpleFilmBeans(List<SimpleFilmBean> simpleFilmBeans) {
        this.simpleFilmBeans = simpleFilmBeans;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
