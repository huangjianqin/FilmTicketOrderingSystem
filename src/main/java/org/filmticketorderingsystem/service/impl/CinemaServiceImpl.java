package org.filmticketorderingsystem.service.impl;

import org.filmticketorderingsystem.bean.SessionTurnoverRecordBean;
import org.filmticketorderingsystem.bean.SimpleFilmBean;
import org.filmticketorderingsystem.dao.CinemaDao;
import org.filmticketorderingsystem.dao.FilmDao;
import org.filmticketorderingsystem.dao.FilmSessionDao;
import org.filmticketorderingsystem.domain.Cinema;
import org.filmticketorderingsystem.domain.Film;
import org.filmticketorderingsystem.domain.FilmSession;
import org.filmticketorderingsystem.service.CinemaService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 健勤 on 2016/5/17.
 */
public class CinemaServiceImpl implements CinemaService {
    private CinemaDao cinemaDao;
    private FilmSessionDao filmSessionDao;
    private FilmDao filmDao;

    public CinemaDao getCinemaDao() {
        return cinemaDao;
    }

    public void setCinemaDao(CinemaDao cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    public FilmSessionDao getFilmSessionDao() {
        return filmSessionDao;
    }

    public void setFilmSessionDao(FilmSessionDao filmSessionDao) {
        this.filmSessionDao = filmSessionDao;
    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public void setFilmDao(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public String login(String id, String password) {
        Cinema cinema = cinemaDao.findById(id);

        if(cinema != null){
            boolean flag = cinema.validate(password);

            if(flag){
                return cinema.getCinemaName();
            }
        }

        return null;
    }

    public int logout(HttpServletRequest request) {
        request.removeAttribute("id");
        request.removeAttribute("cinemaName");
        return 1;
    }

    public int modifyPassword(String id, String oldPassword, String newPassword) {
        Cinema cinema = cinemaDao.findById(id);

        if(cinema != null){
            Boolean flag = cinema.validate(oldPassword);

            if(flag){
                cinema.setPassword(newPassword);
                cinemaDao.update(cinema);
            }
        }

        return -1;
    }

    public List<String> findAllFilmHasSession(int id) {
        List<Film> films = filmDao.findFilmByCinema(id);

        if(films != null){
            List<String> result = new ArrayList<String>();

            for(Film film : films){
                result.add(film.getFilmName());
            }

            return result;
        }

        return null;
    }

    public Map<String, List<SessionTurnoverRecordBean>> findOneFilmSessionDetail(int id, int filmId) {
       //获取当前时间并格式化
        SimpleDateFormat dataFormator = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date now = new Date();
        String nowDataStr = dataFormator.format(now);

        List<FilmSession> sessions = filmSessionDao.findByCinemaAndFIlm(id, filmId, nowDataStr);

        if(sessions != null){
            Map<String, List<SessionTurnoverRecordBean>> data2SessRecord = new HashMap<String, List<SessionTurnoverRecordBean>>();

            for(FilmSession session : sessions){
                String[] datas = session.getStartDate().split(" ");
                String yMD = datas[1].replaceFirst("-", "年").replaceFirst("-", "月") + "日";

                SessionTurnoverRecordBean bean = new SessionTurnoverRecordBean();

                bean.setStartDate(datas[1]);
                bean.setType(session.getPlayLanguage() + " " + session.getPlayType());
                bean.setHallName(session.getCinemaHall().getHallName());
                bean.setPrice(session.getPrice());
                bean.setSoldTicketNum(session.getFilmTickets().size());
                bean.setSumPrice(bean.getPrice() * bean.getSoldTicketNum());

                if(data2SessRecord.get(yMD) != null){
                    data2SessRecord.get(yMD).add(bean);
                }
                else{
                    List<SessionTurnoverRecordBean> beans = new ArrayList<SessionTurnoverRecordBean>();

                    beans.add(bean);

                    data2SessRecord.put(yMD, beans);
                }

            }

            return data2SessRecord;
        }

        return null;
    }
}
