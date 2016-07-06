package org.filmticketorderingsystem.bean.json;

import java.util.List;

/**
 * Created by 健勤 on 2016/7/5.
 */
public class AllFilmHasSeesionBean {
    private List<String> films;
    private Integer state = -1;

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
