package com.cgi.dao;

import java.util.ArrayList;

public interface DAO<K, T> {

    public ArrayList<T> findAll();

    public T findById(K id);

    public void insert (T obj);

    public void update (T obj);

    public void deleteById (K id);

    public void delete (T obj);

}
