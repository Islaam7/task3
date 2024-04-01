package com.sci;

import com.sci.criteria.FilterQuery;
import com.sci.criteria.Operator;
import com.sci.dao.*;
import com.sci.models.Department;
import com.sci.models.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sci.models.Job;
import com.sci.models.Region;
import org.hibernate.Session;

public class EmployeeApp {

  public static void testCache1() {

    System.out.println("Test cache scenario 1");

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      System.out.println(session.get(Employee.class, 101));

      System.out.println("--------------------------------");

      System.out.println(session.get(Employee.class, 102));

      System.out.println("--------------------------------");

      System.out.println(session.get(Employee.class, 101));

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static void testCache2() {

    System.out.println("Test cache scenario 2");

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

      System.out.println(session.get(Employee.class, 103));

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static void main(String[] args) {

//    testCache1();
//
//    System.out.println("*****************************************");
//
//    testCache2();
//
//    System.out.println("*****************************************");
//
//    testCache2();

    DBEmployee dbEmployee = new DBEmployee();
    DBDepartment dbdepartment = new DBDepartment();
    DBRegion dbregion = new DBRegion();

    List<FilterQuery> filterQueries = new ArrayList<>();
    filterQueries.add(new FilterQuery("jobId", "SH_CLERK", Operator.EQ));

    List<FilterQuery> filterQueriesDepart = new ArrayList<>();
    filterQueriesDepart.add(new FilterQuery("departmentId", "20", Operator.EQ));

    List<FilterQuery> filterQueriesRegion = new ArrayList<>();
    filterQueriesRegion.add(new FilterQuery("regionId", "2", Operator.EQ));
//    filterQueries.add(new FilterQuery("salary", 3000, Operator.GT));

    List<Employee> employeeList = dbEmployee.getByFilter(filterQueries);
    System.out.println("the size of the Employee list = " + employeeList.size());
    for(Employee employee : employeeList) {
      System.out.println(employee);
    }

    List<Department> departmentList = dbdepartment.getByFilter(filterQueriesDepart);
    System.out.println("the size of the Department list = " + departmentList.size());
    for(Department department : departmentList) {
      System.out.println(department);
    }

    List<Region> regionList = dbregion.getByFilter(filterQueriesRegion);
    System.out.println("the size of the Region list = " + regionList.size());
    for(Region region : regionList) {
      System.out.println(region);
    }
//
//    List<Employee> employeeList = dbEmployee.get();
//
//    for(Employee e : employeeList) {
//      System.out.println(e);
//    }

//    System.out.println(dbEmployee.get(100));

//    Employee employee = new Employee();
//    employee.setLastName("abdelghany");
//    employee.setEmail("abdelghany@sci.eg");
//    employee.setSalary(151);
//    employee.setCommissionPct(0.5);
//    employee.setDepartmentId(50);
//    employee.setJobId("SH_CLERK");
//    employee.setHireDate(new Date());
//
//    System.out.println(dbEmployee.insert(employee));

//    Employee employee = dbEmployee.get(231);
//
//    employee.setLastName("Mustafa");
//
//    dbEmployee.update(employee);


//    dbEmployee.delete(231);

    DBConfig.shutdown();

  }
}
