package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Department department) {
        entityManager.persist(department);
    }

    @Override
    public void update(Department department) {
        entityManager.merge(department);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Department d where d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(Department department) {
        entityManager.remove(department);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(d.id) from Department d where d.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Department findById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> findAll(int page, int size, String sort, String order) {
        page = (page - 1) * size;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> from = criteriaQuery.from(Department.class);
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
