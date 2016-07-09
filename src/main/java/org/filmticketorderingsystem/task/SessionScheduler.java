package org.filmticketorderingsystem.task;

import org.filmticketorderingsystem.dao.FilmSessionDao;
import org.filmticketorderingsystem.generator.DateGenerator;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by 健勤 on 2016/7/9.
 */
public class SessionScheduler {
    @Resource(name = "filmSessionDao")
    private FilmSessionDao filmSessionDao;
    @Resource(name = "dateGenerator")
    private DateGenerator dateGenerator;

    public FilmSessionDao getFilmSessionDao() {
        return filmSessionDao;
    }

    public void setFilmSessionDao(FilmSessionDao filmSessionDao) {
        this.filmSessionDao = filmSessionDao;
    }

    public DateGenerator getDateGenerator() {
        return dateGenerator;
    }

    public void setDateGenerator(DateGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }

    /**
     * 每一分钟运行一次,用于更新场次的有效标志
     * 因为场次默认是其开始前的十分钟停止售票
     */
    @Scheduled(cron = "* */1 * * * *")
    public void updateSessionFlag(){
        String hql = "update FilmSession fs set flag = -1 where fs.startDate < ?0";

        filmSessionDao.update(hql, dateGenerator.getFormatedNowDateBefore10m());

    }

}
