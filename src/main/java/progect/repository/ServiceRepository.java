package progect.repository;

import java.util.List;

public interface ServiceRepository<T> {
    public boolean saveService(T nameEntity);

    public void deleteService(T nameEntity);

    public void updateService(T nameEntity);

    public T findIdService(int id);

    public List<T> findAllService();
}
