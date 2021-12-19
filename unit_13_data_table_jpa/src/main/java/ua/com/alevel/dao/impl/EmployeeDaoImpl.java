package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Employee e where e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(Employee employee) {
        entityManager.remove(employee);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(e.id) from Employee e where e.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll(int page, int size, String sort, String order) {
        page = (page - 1) * size;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> from = criteriaQuery.from(Employee.class);
        if (order.equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(sort)));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(sort)));
        }

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }
}
