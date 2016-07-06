package org.filmticketorderingsystem.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/5/13.
 */
public class FilmWithSessionBean {
    private SimpleFilmBean simpleFilmBean;

    //key为电影院名,电影院地址,电影院ID,对应电影院的某电影的场次的最低价格
    private Map<String, List<SimpleFilmSessionBean>> film2Session;

    private Integer state = -1;

    public SimpleFilmBean getSimpleFilmBean() {
        return simpleFilmBean;
    }

    public void setSimpleFilmBean(SimpleFilmBean simpleFilmBean) {
        this.simpleFilmBean = simpleFilmBean;
    }

    public Map<String, List<SimpleFilmSessionBean>> getFilm2Session() {
        return film2Session;
    }

    public void setFilm2Session(Map<String, List<SimpleFilmSessionBean>> film2Session) {
        this.film2Session = film2Session;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
