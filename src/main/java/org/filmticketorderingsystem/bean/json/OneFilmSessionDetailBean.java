package org.filmticketorderingsystem.bean.json;

import org.filmticketorderingsystem.bean.SessionTurnoverRecordBean;

import java.util.List;
import java.util.Map;

/**
 * Created by 健勤 on 2016/7/5.
 */
public class OneFilmSessionDetailBean {
    private Map<String, List<SessionTurnoverRecordBean>> data2SessRecord;
    private Integer state = -1;

    public Map<String, List<SessionTurnoverRecordBean>> getData2SessRecord() {
        return data2SessRecord;
    }

    public void setData2SessRecord(Map<String, List<SessionTurnoverRecordBean>> data2SessRecord) {
        this.data2SessRecord = data2SessRecord;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
