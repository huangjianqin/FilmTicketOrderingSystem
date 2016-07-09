package org.filmticketorderingsystem.controller.user;

import org.filmticketorderingsystem.bean.*;
import org.filmticketorderingsystem.bean.json.SearchResultBeanList;
import org.filmticketorderingsystem.bean.json.SimpleFilmBeanList;
import org.filmticketorderingsystem.generator.factory.GeneratorFactory;
import org.filmticketorderingsystem.service.UserQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
//      解决js或jquery动态获取url参数乱码
        try {
            keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        List<SearchResultBean> searchResultBeans = userQueryService.search(keyword);

        if(searchResultBeans != null){
            searchResultBeanList.setSearchResultBeans(searchResultBeans);
            searchResultBeanList.setState(1);
        }

        return searchResultBeanList;
    }

    @RequestMapping(value = "/getRecentFilms.do")
    @ResponseBody
    public SimpleFilmBeanList getRecentFilms(){
        //获取格式化后的当前时间
        String dateStr = GeneratorFactory.getDateGenerator().getFormatedNowDate();

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
        FilmWithCineAndSessBean filmWithCineAndSessBean = userQueryService.getCinemaSessionInfo(cinemaId, dateStr);
        filmWithCineAndSessBean.setState(1);

        return filmWithCineAndSessBean;
    }

    @RequestMapping(value = "/getOneFilmSessionInfo.do")
    @ResponseBody
    public FilmWithSessionBean getOneFilmSessionInfo(@RequestParam Integer filmId,
                                                     @RequestParam String dateStr,
                                                     Map<String, Object> model){
        FilmWithSessionBean filmWithSessionBean = userQueryService.getOneFilmSessionInfo(filmId, dateStr);
        filmWithSessionBean.setState(1);

        return filmWithSessionBean;
    }

    @RequestMapping(value = "/getOneFilmSessionDetail.do")
    @ResponseBody
    public FilmSeesionDetailBean getOneFilmSessionDetail(@RequestParam Integer sessionId){
        FilmSeesionDetailBean filmSeesionDetailBean = userQueryService.getOneFilmSessionDetail(sessionId);
        filmSeesionDetailBean.setState(1);

        return filmSeesionDetailBean;
    }

}
