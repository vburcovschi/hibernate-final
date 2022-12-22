package dao;

import domain.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public List<City> getItems(int offset, int limit){
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c From City c", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount(){
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) From City c",Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public City getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
}
