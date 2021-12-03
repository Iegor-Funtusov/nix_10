package ua.com.alevel.persistence.repository;

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.persistence.type.DepartmentType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface DepartmentRepository extends BaseRepository<Department> {

//    @Query("select d from Department d where d.departmentType = :departmentType order by d.id desc")
//    List<Department> findByDepartmentTypeOrderByIdDesc(@Param("departmentType") DepartmentType departmentType);
//
//    @Query("select d from Department d where d.departmentType in (:departmentTypes) order by d.id desc")
//    List<Department> findByDepartmentTypeOrderByIdDesc(@Param("departmentTypes") List<DepartmentType> departmentTypes);

    List<Department> findByDepartmentTypeOrderByIdDesc(DepartmentType departmentType);
    List<Department> findByDepartmentTypeInOrderByIdDesc(List<DepartmentType> departmentTypes);
    List<Department> findByDepartmentTypeInAndNameEndingWithIgnoreCase(List<DepartmentType> departmentTypes, String name);
    List<Department> findByDepartmentTypeInAndNameEndingWithIgnoreCaseOrNameStartingWithAndCreatedBetween(
            List<DepartmentType> departmentTypes, String start, String end, Date startCreated, Date endCreated);
//    select * from departments where department_type in ('JAVA', 'JS') order by department_type desc;
//    select * from departments where department_type in ('JAVA', 'JS') and name like() order by department_type desc;
//    select * from departments as d join dep_emp as de on d.id = de.dep_id where de.emp_id in (2);
    @Query(value = "select d from Department as d join d.employees as de where de.id in :employeesIds")
    List<Department> findByEmployeesIds(@Param("employeesIds") List<Long> employeesIds);

    @Query(value = "select d from Department as d join d.employees as de where de in :employees")
    List<Department> findByEmployees(@Param("employees") Set<Employee> employees);

}
