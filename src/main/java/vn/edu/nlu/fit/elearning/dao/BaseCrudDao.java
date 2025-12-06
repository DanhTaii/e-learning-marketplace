package vn.edu.nlu.fit.elearning.dao;

import java.util.List;

public interface BaseCrudDao<T, ID> {

    public void create(T entity);

    public T findById(ID id);

    public List<T> findAll();

    public int update(T entity);

    public int delete(ID id);
}
