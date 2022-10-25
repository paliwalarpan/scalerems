package com.scaler.ems.repository;

import com.scaler.ems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(String employeeId);

    @Query("select e from Employee e where lower( e.firstName) like :searchTerm order by e.firstName asc ")
    List<Employee> searchEmployee(@Param("searchTerm") String searchTerm);
}
