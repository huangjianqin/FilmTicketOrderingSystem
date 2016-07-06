package org.filmticketorderingsystem.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 健勤 on 2016/4/26.
 */
public interface BaseDao<T> {
    T get(Class<T> claxx, Serializable id);
    Serializable save(T t);
    void delete(T t);
    void delete(Class<T> claxx, Serializable id);
    void update(T t);
    void update(String hql, Object... params);
    List<T> query(String hql);
    List<T> query(String hql, Object... params);
    List<T> query(String hql, String[] paramsName, Object[] params);
    List<T> queryAll(Class<T> claxx);
    long count(Class<T> claxx);
    long count(String hql, Object... params);
    long count(String hql, String[] paramsName, Object[] params);
}
