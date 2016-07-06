package org.filmticketorderingsystem.service;

import org.filmticketorderingsystem.bean.SessionTurnoverRecordBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/5/16.
 */
public interface CinemaService {
    String login(String id, String password);
    int logout(HttpServletRequest request);
    int modifyPassword(String id, String oldPassword, String newPassword);

    List<String> findAllFilmHasSession(int id);
    Map<String, List<SessionTurnoverRecordBean>> findOneFilmSessionDetail(int id, int filmId);
}
