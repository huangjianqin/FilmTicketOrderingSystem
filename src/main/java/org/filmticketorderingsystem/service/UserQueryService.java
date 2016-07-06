package org.filmticketorderingsystem.service;

import org.filmticketorderingsystem.bean.*;

import java.util.List;

/**
 * Created by 健勤 on 2016/5/13.
 */
public interface UserQueryService {
    List<SearchResultBean> search(String keyword);

    List<SimpleFilmBean> getRecentFilms(String dataStr);
    FilmBean getFilmInfo(int filmId);

    /**
     * 获取电影院相关的电影及场次信息
     * @param cinemaId
     * @param dateStr
     * @return
     */
    FilmWithCineAndSessBean getCinemaSessionInfo(Integer cinemaId, String dateStr);

    /**
     * 获取某一电影的场次及影院信息
     * @param filmId
     * @param dateStr
     * @return
     */
    FilmWithSessionBean getOneFilmSessionInfo(Integer filmId, String dateStr);

    /**
     * 获取某一电影的场次详细信息
     * @param sessionId
     * @return
     */
    FilmSeesionDetailBean getOneFilmSessionDetail(Integer sessionId);
}
