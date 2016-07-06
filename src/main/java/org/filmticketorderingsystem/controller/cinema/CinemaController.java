package org.filmticketorderingsystem.controller.cinema;

import org.filmticketorderingsystem.bean.CinemaBean;
import org.filmticketorderingsystem.bean.SessionTurnoverRecordBean;
import org.filmticketorderingsystem.bean.UserBean;
import org.filmticketorderingsystem.bean.json.AllFilmHasSeesionBean;
import org.filmticketorderingsystem.bean.json.OneFilmSessionDetailBean;
import org.filmticketorderingsystem.service.CinemaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/7/5.
 */

@Controller
@RequestMapping(value = "/cinema")
public class CinemaController {
    @Resource(name = "cinemaService")
    private CinemaService cinemaService;

    public void setCinemaService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @RequestMapping(value = "/getNowCinema.do")
    @ResponseBody
    public CinemaBean getNowCinema(HttpServletRequest request){
        //获取当前登录电影院管理员id
        String id = (String)request.getSession().getAttribute("cinema_id");

        CinemaBean cinemaBean = new CinemaBean();
        cinemaBean.setState(-1);

        if(id != null && !id.equals("")){
            String cinemaName = (String)request.getSession().getAttribute("cinemaName");
            cinemaBean.setCinemaName(cinemaName);
            cinemaBean.setState(1);
        }

        return cinemaBean;
    }


    @RequestMapping(value = "/login.do")
    public @ResponseBody
    CinemaBean login(@RequestParam String id,
                   @RequestParam String password, HttpServletRequest request){
        String cinemaName = cinemaService.login(id, password);
        int flag = (cinemaName == null)? -1 : 1;

        //登录成功,session记录用户Id
        if(flag == 1){
            request.getSession().setAttribute("cinema_id", id);
            request.getSession().setAttribute("cinemaName", cinemaName);
        }

        CinemaBean cinemaBean = new CinemaBean();
        //返回用户名用于显示
        cinemaBean.setCinemaName(cinemaName);
        //设置调用服务结果状态
        cinemaBean.setState(flag);

        return cinemaBean;
    }

    @RequestMapping(value = "/logout.do")
    public @ResponseBody CinemaBean logout(HttpServletRequest request){
        int flag = cinemaService.logout(request);

        CinemaBean cinemaBean = new CinemaBean();
        //设置调用服务结果状态
        cinemaBean.setState(flag);
        return cinemaBean;
    }

    @RequestMapping(value = "/modifyPassword.do")
    public @ResponseBody CinemaBean modifyPassword(@RequestParam String oldPassword,
                                                 @RequestParam String newPassword,
                                                 HttpServletRequest request){
        //获取当前登录电影院管理员id
        String id = (String)request.getSession().getAttribute("cinema_id");

        int flag = cinemaService.modifyPassword(id, oldPassword, newPassword);

        CinemaBean cinemaBean = new CinemaBean();
        //设置调用服务结果状态
        cinemaBean.setState(flag);

        return cinemaBean;

    }

    @RequestMapping(value = "/findAllFilmHasSession.do")
    @ResponseBody
    public AllFilmHasSeesionBean findAllFilmHasSession(@RequestParam int cinemaId){
        List<String> films = cinemaService.findAllFilmHasSession(cinemaId);

        AllFilmHasSeesionBean allFilmHasSeesionBean = new AllFilmHasSeesionBean();

        if(films != null){
            allFilmHasSeesionBean.setFilms(films);
            allFilmHasSeesionBean.setState(1);
        }

        return allFilmHasSeesionBean;
    }

    @RequestMapping(value = "/findOneFilmSessionDetail.do")
    @ResponseBody
    public OneFilmSessionDetailBean findOneFilmSessionDetail(@RequestParam int id,
                                                             @RequestParam int filmId){
        OneFilmSessionDetailBean oneFilmSessionDetailBean = new OneFilmSessionDetailBean();

        Map<String, List<SessionTurnoverRecordBean>>
                data2SessRecord = cinemaService.findOneFilmSessionDetail(id, filmId);

        if(data2SessRecord != null){
            oneFilmSessionDetailBean.setData2SessRecord(data2SessRecord);
            oneFilmSessionDetailBean.setState(1);
        }

        return oneFilmSessionDetailBean;
    }
}
