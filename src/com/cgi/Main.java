package com.cgi;

import com.cgi.dao.EmployeeDAO;
import com.cgi.model.Employee;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {

        // DAO.findAll()
        EmployeeDAO eDAO = new EmployeeDAO();
        for (Object o : eDAO.findAll()) {
            System.out.println(o);
        }

        // DAO.findById()
        //System.out.println("Employee d'id 7 : " + eDAO.findById(7));

        // DAO.insert()
        Employee emp1 = new Employee(1235, "Sam", "Aide au porteur", 1234, new java.util.Date(), 50000, 500, 10);
        //eDAO.insert(emp1);

        // DAO.update()
        //eDAO.update(emp1);

        // DAO.deleteById()
        //eDAO.deleteById(7);

        // DAO.delete()
        //eDAO.delete(emp1);

    }

}
