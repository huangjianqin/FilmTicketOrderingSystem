package org.filmticketorderingsystem.controller.user;

import org.filmticketorderingsystem.bean.*;
import org.filmticketorderingsystem.bean.json.SearchResultBeanList;
import org.filmticketorderingsystem.bean.json.SimpleFilmBeanList;
import org.filmticketorderingsystem.service.UserQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 健勤 on 2016/7/5.
 */

@Controller
@RequestMapping(value = "/user/query")
public class UserQueryController {

    @Resource(name = "userQueryService")
    private UserQueryService userQueryService;

    public void setUserQueryService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @RequestMapping(value = "/search.do")
    @ResponseBody
    public SearchResultBeanList search(@RequestParam String keyword){
        SearchResultBeanList searchResultBeanList = new SearchResultBeanList();

        List<SearchResultBean> searchResultBeans = userQueryService.search(keyword);

        if(searchResultBeans != null){
            searchResultBeanList.setSearchResultBeans(searchResultBeans);
            searchResultBeanList.setState(1);
        }

        return searchResultBeanList;
    }

    @RequestMapping(value = "/getRecentFilms.do")
    @ResponseBody
    public SimpleFilmBeanList getRecentFilms(@RequestParam String dateStr){
        SimpleFilmBeanList simpleFilmBeanList = new SimpleFilmBeanList();

        List<SimpleFilmBean> simpleFilmBeans = userQueryService.getRecentFilms(dateStr);

        if(simpleFilmBeans != null){
            simpleFilmBeanList.setSimpleFilmBeans(simpleFilmBeans);
            simpleFilmBeanList.setState(1);
        }

        return simpleFilmBeanList;
    }

    @RequestMapping(value = "/getFilmInfo.do")
    @ResponseBody
    public FilmBean getFilmInfo(@RequestParam int filmId){
        FilmBean filmBean = userQueryService.getFilmInfo(filmId);

        return filmBean;
    }

    @RequestMapping(value = "/getCinemaSessionInfo.do")
    @ResponseBody
    public FilmWithCineAndSessBean getCinemaSessionInfo(@RequestParam Integer cinemaId,
                                                        @RequestParam String dateStr){

        FilmWithCineAndSessBean result = userQueryService.getCinemaSessionInfo(cinemaId, dateStr);
        result.setState(1);

        return result;
    }

    @RequestMapping(value = "/getOneFilmSessionInfo.do")
    @ResponseBody
    public FilmWithSessionBean getOneFilmSessionInfo(@RequestParam Integer filmId,
                                                     @RequestParam String dateStr){
        FilmWithSessionBean result = userQueryService.getOneFilmSessionInfo(filmId, dateStr);
        result.setState(1);

        return result;
    }

    @RequestMapping(value = "/getOneFilmSessionDetail.do")
    @ResponseBody
    public FilmSeesionDetailBean getOneFilmSessionDetail(@RequestParam Integer sessionId){
        FilmSeesionDetailBean result = userQueryService.getOneFilmSessionDetail(sessionId);
        result.setState(1);

        return result;
    }

}
