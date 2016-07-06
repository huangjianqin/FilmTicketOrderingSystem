package org.filmticketorderingsystem.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/5/14.
 */
public class FilmWithCineAndSessBean {
    private CinemaBean cinemaBean;
    //key为电影ID,电影名,电影海报url,电影评分,电影种类,电影时长,value为场次的简单信息
    private Map<String, List<SimpleFilmSessionBean>> film2Session;

    private Integer state = -1;

    public CinemaBean getCinemaBean() {
        return cinemaBean;
    }

    public void setCinemaBean(CinemaBean cinemaBean) {
        this.cinemaBean = cinemaBean;
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
