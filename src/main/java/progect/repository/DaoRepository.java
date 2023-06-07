package progect.repository;

import java.util.List;

public interface DaoRepository<T> {

    public boolean save(T nameEntity);

    public void delete(T nameEntity);

    public void update(T nameEntity);

    public T findId(int id);

    public List<T> findAll();
}
