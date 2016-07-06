package org.filmticketorderingsystem.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.filmticketorderingsystem.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 健勤 on 2016/4/26.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    private SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public T get(Class<T> claxx, Serializable id) {
        return getFactory().getCurrentSession().get(claxx, id);
    }

    public Serializable save(T t) {
        return getFactory().getCurrentSession().save(t);
    }

    public void delete(T t) {
        getFactory().getCurrentSession().delete(t);
    }

    public void delete(Class<T> claxx, Serializable id) {
        getFactory().getCurrentSession()
                .createQuery("delete from " + claxx.getSimpleName() + " where id = ?0")
                .setParameter("0", id)
                .executeUpdate();
    }

    public void update(T t) {
        getFactory().getCurrentSession().saveOrUpdate(t);
    }

    public void update(String hql, Object... params) {
        Query query = getFactory().getCurrentSession().createQuery(hql);

        for(int i = 0; i < params.length; i++){
            query.setParameter("" + i, params[i]);
        }

        query.executeUpdate();
    }

    public List<T> query(String hql) {
        return (List<T>)getFactory().getCurrentSession().createQuery(hql).list();
    }

    public List<T> query(String hql, Object... params) {
        Query query = getFactory().getCurrentSession().createQuery(hql);

        for(int i = 0; i < params.length; i++){
            query.setParameter("" + i, params[i]);
        }

        return (List<T>)query.list();
    }

    public List<T> query(String hql, String[] paramsName, Object[] params) {
        Query query = getFactory().getCurrentSession().createQuery(hql);

        for(int i = 0; i < paramsName.length; i++){
            query.setParameter(paramsName[i], params[i]);
        }

        return (List<T>)query.list();
    }

    public List<T> queryAll(Class<T> claxx) {
        return query("from " + claxx.getSimpleName());
    }

    public long count(Class<T> claxx) {
        List result = query("select count(*) from " + claxx.getSimpleName());
        if(result != null && result.size() == 1){
            return (Long)result.get(0);
        }
        return -1;
    }

    public long count(String hql, Object... params) {
        Query query = getFactory().getCurrentSession().createQuery(hql);

        for(int i = 0; i < params.length; i++){
            query.setParameter("" + i, params[i]);
        }

        return (Long)query.list().get(0);
    }

    public long count(String hql, String[] paramsName, Object[] params) {
        Query query = getFactory().getCurrentSession().createQuery(hql);

        for(int i = 0; i < paramsName.length; i++){
            query.setParameter(paramsName[i], params[i]);
        }

        return (Long)query.list().get(0);
    }

    public List<T> queryByPage(String hql, int nowPage, int pageSize){
        if(nowPage < 1){
            return null;
        }

        int startPage = (nowPage - 1) * pageSize;

        return (List<T>)getFactory().getCurrentSession()
                .createQuery(hql)
                .setFirstResult(startPage)
                .setMaxResults(pageSize)
                .list();
    }

    public List<T> queryByPage(String hql, int nowPage, int pageSize, Object[] params){

        if(nowPage < 1){
            return null;
        }

        int startPage = (nowPage - 1) * pageSize;

        Query query = getFactory().getCurrentSession()
                .createQuery(hql)
                .setFirstResult(startPage)
                .setMaxResults(pageSize);

        for(int i = 0; i < params.length; i++){
            query.setParameter("" + i, params[i]);
        }

        return (List<T>)query.list();
    }

}
