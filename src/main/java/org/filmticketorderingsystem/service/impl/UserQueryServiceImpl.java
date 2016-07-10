package org.filmticketorderingsystem.service.impl;

import org.filmticketorderingsystem.bean.*;
import org.filmticketorderingsystem.dao.CinemaDao;
import org.filmticketorderingsystem.dao.FilmDao;
import org.filmticketorderingsystem.dao.FilmSessionDao;
import org.filmticketorderingsystem.domain.*;
import org.filmticketorderingsystem.service.UserQueryService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 健勤 on 2016/5/13.
 */
public class UserQueryServiceImpl implements UserQueryService{
    private static final int SEARCH_RESULT_MAX_NUM = 8;
    private FilmDao filmDao;
    private FilmSessionDao filmSeesionDao;
    private CinemaDao cinemaDao;

    public static int getSearchResultMaxNum() {
        return SEARCH_RESULT_MAX_NUM;
    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public void setFilmDao(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public FilmSessionDao getFilmSeesionDao() {
        return filmSeesionDao;
    }

    public void setFilmSeesionDao(FilmSessionDao filmSeesionDao) {
        this.filmSeesionDao = filmSeesionDao;
    }

    public CinemaDao getCinemaDao() {
        return cinemaDao;
    }

    public void setCinemaDao(CinemaDao cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    public List<SearchResultBean> search(String keyword) {
        List<Film> selectedFilms = filmDao.findByName(keyword);

        List<SearchResultBean> searchResult = new ArrayList<SearchResultBean>();

        int filmsLength = (selectedFilms.size() > SEARCH_RESULT_MAX_NUM)? SEARCH_RESULT_MAX_NUM:selectedFilms.size();

        for(int i = 0; i < filmsLength; i++){
            SearchResultBean bean = new SearchResultBean();
            bean.setId(selectedFilms.get(i).getFilmId());
            bean.setName(selectedFilms.get(i).getFilmName());
            bean.setType("film");

            searchResult.add(bean);
        }

        if(selectedFilms.size() < SEARCH_RESULT_MAX_NUM){
            int rest = SEARCH_RESULT_MAX_NUM - selectedFilms.size();

            List<Cinema> selectedCinemas = cinemaDao.findByName(keyword);

            int cinemasLength = (selectedCinemas.size() > rest)? rest:selectedCinemas.size();

            for(int i = 0; i < cinemasLength; i++){
                SearchResultBean bean = new SearchResultBean();
                bean.setId(selectedCinemas.get(i).getCinemaId());
                bean.setName(selectedCinemas.get(i).getCinemaName());
                bean.setType("cinema");

                searchResult.add(bean);
            }
        }

        return searchResult;
    }

    public List<SimpleFilmBean> getRecentFilms(String dateStr) {
        List<SimpleFilmBean> result = new ArrayList<SimpleFilmBean>();

//        String dataStr = "";
//        Calendar calendar = new GregorianCalendar();
//        //每个场次开场前10分钟就会被取消(即查询不到)
//        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
//        SimpleDateFormat formator = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        dataStr = formator.format(calendar.getTime());

        List<Film> selectedFilms = filmDao.findFilmHavingFilmSession(dateStr);

        if(selectedFilms != null){
            for(int i = 0; i < selectedFilms.size(); i++){
                Film temp = selectedFilms.get(i);

                SimpleFilmBean bean = new SimpleFilmBean();

                bean.setFilmId(temp.getFilmId());
                bean.setFilmName(temp.getFilmName());
                bean.setKind(temp.getKind());
                bean.setReleaseDate(temp.getReleaseDate());
                bean.setDuration(temp.getDuration());
                bean.setRating(temp.getRating());
                bean.setPricturePath(temp.getPricturePath());

                result.add(bean);
            }

            return result;
        }

        return null;
    }

    public FilmBean getFilmInfo(int filmId) {
        FilmBean result = new FilmBean();

        Film temp = filmDao.get(Film.class, filmId);

        if(temp != null){
            result.setFilmId(temp.getFilmId());
            result.setFilmName(temp.getFilmName());
            result.setKind(temp.getKind());
            result.setReleaseDate(temp.getReleaseDate());
            result.setDescription(temp.getDescription());
            result.setDuration(temp.getDuration());
            result.setRating(temp.getRating());
            result.setPricturePath(temp.getPricturePath());
            //将Actor对象转换为bean,即去掉一些因hibernate的一对度==关系而遗留的代码
            Set<ActorBean> actorBeans = new HashSet<ActorBean>();

            for(Actor actor: temp.getActors()){
                ActorBean actorBean = new ActorBean();
                actorBean.setActor_id(actor.getActor_id());
                actorBean.setActorName(actor.getActorName());

                actorBeans.add(actorBean);
            }

            result.setActors(actorBeans);
            result.setState(1);

            return result;
        }

        return  null;
    }

    public FilmWithCineAndSessBean getCinemaSessionInfo(Integer cinemaId, String dateStr) {
        List<FilmSession> filmSessions = filmSeesionDao.findByCinema(cinemaId, dateStr);
        Cinema cinema = cinemaDao.get(Cinema.class, cinemaId);

        if(filmSessions != null && cinema != null){
            Map<String, List<SimpleFilmSessionBean>> film2Session = new HashMap<String, List<SimpleFilmSessionBean>>();
            CinemaBean cinemaBean = new CinemaBean();

            //填充cinemaBean
            cinemaBean.setCinemaName(cinema.getCinemaName());
            cinemaBean.setAddress(cinema.getAddress());
            cinemaBean.setTelephone(cinema.getTelephone());

            //填充film2Session
            for(FilmSession filmSession : filmSessions){
                Film film = filmSession.getFilm();
                CinemaHall cinemaHall = filmSession.getCinemaHall();

                //内容有问题
                if(film == null || cinemaHall == null){
                    return null;
                }

                //key为电影ID,电影名,电影海报url,电影评分,电影种类,电影时长
                String head = film.getFilmId() + "," + film.getFilmName() + "," + film.getPricturePath()
                                + "," + film.getRating() + "," + film.getKind() + film.getDuration();

                SimpleFilmSessionBean simpleFilmSessionBean = new SimpleFilmSessionBean();

                simpleFilmSessionBean.setFilmSessionId(filmSession.getFilmSessionId());
                simpleFilmSessionBean.setStartDate(filmSession.getStartDate());
                simpleFilmSessionBean.setEndDate(filmSession.getEndDate());
                simpleFilmSessionBean.setHallName(cinemaHall.getHallName());
                simpleFilmSessionBean.setType(filmSession.getPlayLanguage() + " " + filmSession.getPlayType());
                simpleFilmSessionBean.setPrice(filmSession.getPrice());

                if(film2Session.get(head) != null){
                    film2Session.get(head).add(simpleFilmSessionBean);
                }
                else{
                    List<SimpleFilmSessionBean> simpleFilmSessionBeans = new ArrayList<SimpleFilmSessionBean>();
                    simpleFilmSessionBeans.add(simpleFilmSessionBean);
                    film2Session.put(head, simpleFilmSessionBeans);
                }

            }

            FilmWithCineAndSessBean filmWithCineAndSessBean = new FilmWithCineAndSessBean();
            filmWithCineAndSessBean.setCinemaBean(cinemaBean);
            filmWithCineAndSessBean.setFilm2Session(film2Session);

            return filmWithCineAndSessBean;
        }


        return null;
    }

    public FilmWithSessionBean getOneFilmSessionInfo(Integer filmId, String dateStr) {
        List<FilmSession> filmSessions = filmSeesionDao.findByFilm(filmId, dateStr);
        Film film = filmDao.get(Film.class, filmId);

        if(filmSessions != null && film != null){
            SimpleFilmBean simpleFilmBean = new SimpleFilmBean();

            simpleFilmBean.setFilmId(film.getFilmId());
            simpleFilmBean.setFilmName(film.getFilmName());
            simpleFilmBean.setPricturePath(film.getPricturePath());
            simpleFilmBean.setRating(film.getRating());
            simpleFilmBean.setReleaseDate(film.getReleaseDate()
                    .replaceFirst("-", "年").replaceFirst("-", "月").replaceFirst(" ", "日 "));
            simpleFilmBean.setDuration(film.getDuration());
            simpleFilmBean.setKind(film.getKind());

            //暂时存储电影与其对应的场次信息, key为电影院名,电影院地址,电影院ID
            Map<String, List<SimpleFilmSessionBean>> tempFilm2Session = new HashMap<String, List<SimpleFilmSessionBean>>();
            //统计每个电影院的最低价格
            Map<String, Double> cinema2Price = new HashMap<String, Double>();

            for(FilmSession session : filmSessions){
                Cinema cinema = session.getCinema();

                if(cinema == null){
                    return  null;
                }

                String head = cinema.getCinemaName() + "," +
                                cinema.getAddress() + "," +
                                cinema.getCinemaId();

                SimpleFilmSessionBean simpleFilmSessionBean = new SimpleFilmSessionBean();

                simpleFilmSessionBean.setFilmSessionId(session.getFilmSessionId());
                simpleFilmSessionBean.setStartDate(session.getStartDate().split(" ")[1]);
                simpleFilmSessionBean.setType(session.getPlayType() + " " + session.getPlayLanguage());
                simpleFilmSessionBean.setPrice(session.getPrice());

                //没处理过这个影院
                if(tempFilm2Session.get(head) == null){
                    List<SimpleFilmSessionBean> simpleFilmSessionBeans = new ArrayList<SimpleFilmSessionBean>();
                    simpleFilmSessionBeans.add(simpleFilmSessionBean);
                    tempFilm2Session.put(head, simpleFilmSessionBeans);
                }
                else{
                    tempFilm2Session.get(head).add(simpleFilmSessionBean);
                }

                //没处理过这个影院
                if(cinema2Price.get(cinema.getCinemaName()) == null){
                    cinema2Price.put(cinema.getCinemaName(), session.getPrice());
                }
                else{
                    double temp = cinema2Price.get(cinema.getCinemaName());
                    if(session.getPrice() < temp){
                        cinema2Price.put(cinema.getCinemaName(), session.getPrice());
                    }
                }

            }

            //key为电影院名,电影院地址,电影院ID,对应电影院的某电影的场次的最低价格
            Map<String, List<SimpleFilmSessionBean>> film2Session = new HashMap<String, List<SimpleFilmSessionBean>>();

            //把最低价格整合到tempFilm2Session的key中
            for(String key : tempFilm2Session.keySet()){
                String cinemaName = key.split(",", 2)[0];

                //获取最低价格,为null则证明系统有问题
                if(cinema2Price.get(cinemaName) == null){
                    return null;
                }

                String newKey = key + "," + cinema2Price.get(cinemaName);

                film2Session.put(newKey, tempFilm2Session.get(key));
            }

            FilmWithSessionBean filmWithSessionBean = new FilmWithSessionBean();
            filmWithSessionBean.setSimpleFilmBean(simpleFilmBean);
            filmWithSessionBean.setFilm2Session(film2Session);

            return filmWithSessionBean;
        }

        return null;
    }

    public FilmSeesionDetailBean getOneFilmSessionDetail(Integer sessionId) {
        FilmSession session = filmSeesionDao.get(FilmSession.class, sessionId);

        if(session != null){
            FilmSeesionDetailBean bean = new FilmSeesionDetailBean();
            Film film = session.getFilm();
            Cinema cinema = session.getCinema();
            CinemaHall cinemaHall = session.getCinemaHall();

            if(film == null || cinema == null || cinemaHall == null){
                return null;
            }

            bean.setFilmSeesionId(sessionId);
            bean.setPricturePath(film.getPricturePath());
            bean.setFilmId(film.getFilmId());
            bean.setFilmName(film.getFilmName());
            bean.setKind(film.getKind());
            bean.setDuration(film.getDuration());

            bean.setCinemaName(cinema.getCinemaName());
            bean.setType(session.getPlayLanguage() + " " + session.getPlayType());
            bean.setStartDate(session.getStartDate());
            bean.setPrice(session.getPrice());
            bean.setCinemaHallName(cinemaHall.getHallName());

            Integer restSeatNum = -1;
            //key为排号,value为对应排的座位
            Map<String, List<String>> hall2Seat = new TreeMap<String, List<String>>();

            Set<HallsRow> rows = cinemaHall.getHallsRows();
            Integer sum = 0;

            for(HallsRow row : rows){
                String rowName = row.getRowName();
                Integer colLength = row.getColLength();
                //记录总座位数
                sum += colLength;

                //存储座位详细信息
                List<String> seatDetailInfos = new ArrayList<String>();

                for(int i = 1; i <= colLength; i++){
                    String detail = "";
                    if(i < 10){
                        detail = rowName + "  0" + i + "座";
                    }
                    else{
                        detail = rowName + "  " + i + "座";
                    }

                    if(!detail.equals("")){
                        seatDetailInfos.add(detail);
                    }
                    else {
                        //座位详细信息,说明系统出问题
                        return null;
                    }

                }

                hall2Seat.put(rowName, seatDetailInfos);

            }
            //存储已选择座位
            List<String> selectedSeats = new ArrayList<String>();

            for(FilmTicket ticket: session.getFilmTickets()){
                if(ticket.getFlag() != -1){
                    selectedSeats.add(ticket.getSelectedSeat());
                }
            }

            bean.setRestSeatNum(sum - session.getFilmTickets().size());
            bean.setHall2Seat(hall2Seat);
            bean.setSelectedSeats(selectedSeats);

            return  bean;
        }

        return null;
    }


}
