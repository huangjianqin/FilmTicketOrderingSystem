package org.filmticketorderingsystem.bean.json;

import org.filmticketorderingsystem.bean.SearchResultBean;

import java.util.List;

/**
 * Created by 健勤 on 2016/7/5.
 */
public class SearchResultBeanList {
    private List<SearchResultBean> searchResultBeans;
    private Integer state = -1;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<SearchResultBean> getSearchResultBeans() {

        return searchResultBeans;
    }

    public void setSearchResultBeans(List<SearchResultBean> searchResultBeans) {
        this.searchResultBeans = searchResultBeans;
    }
}
