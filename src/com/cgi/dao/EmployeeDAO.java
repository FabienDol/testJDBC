package com.cgi.dao;

import com.cgi.model.Employee;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeDAO implements DAO<Integer, Employee> {

    @Override
    public ArrayList findAll() {

        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM emp;");

            while (result.next()) {
                employees.add(new Employee(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        new Date(result.getDate(5).getTime()), // cast de java.sql.Date vers java.util.Date
                        result.getInt(6),
                        result.getInt(7),
                        result.getInt(8)
                ));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return employees;
    }

    @Override
    public Employee findById(Integer id) {

        Employee employee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("SELECT * FROM emp WHERE empno = ?;");
            st.setInt(1, id);
            ResultSet result = st.executeQuery();

            result.next();

            employee = new Employee(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    new Date(result.getDate(5).getTime()), // cast de java.sql.Date vers java.util.Date
                    result.getInt(6),
                    result.getInt(7),
                    result.getInt(8)
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return employee;
    }

    @Override
    public void insert(Employee obj) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("INSERT INTO emp VALUES(?,?,?,?,?,?,?,?)");
            st.setInt(1, obj.getEmpno());
            st.setString(2, obj.getEname());
            st.setString(3, obj.getJob());
            st.setInt(4, obj.getMgr());
            st.setDate(5, new java.sql.Date(obj.getHiredate().getTime()));
            st.setInt(6, obj.getSal());
            st.setInt(7, obj.getComm());
            st.setInt(8, obj.getDeptno());

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Employee obj) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("UPDATE emp SET " +
                    "ename = ?," +
                    "job = ?," +
                    "mgr = ?," +
                    "hiredate = ?," +
                    "sal = ?," +
                    "comm = ?," +
                    "deptno = ? " +
                    "WHERE empno = ?;"
            );

            st.setInt(8, obj.getEmpno());
            st.setString(1, obj.getEname());
            st.setString(2, obj.getJob());
            st.setInt(3, obj.getMgr());
            st.setDate(4, new java.sql.Date(obj.getHiredate().getTime()));
            st.setInt(5, obj.getSal());
            st.setInt(6, obj.getComm());
            st.setInt(7, obj.getDeptno());

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("DELETE FROM emp WHERE empno = ?;");
            st.setInt(1, id);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(Employee obj) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("DELETE FROM emp WHERE " +
                    "ename = ? AND " +
                    "job = ? AND " +
                    "mgr = ? AND " +
                    "hiredate = ? AND " +
                    "sal = ? AND " +
                    "comm = ? AND " +
                    "deptno = ? AND " +
                    "empno = ?;"
            );

            st.setInt(8, obj.getEmpno());
            st.setString(1, obj.getEname());
            st.setString(2, obj.getJob());
            st.setInt(3, obj.getMgr());
            st.setDate(4, new java.sql.Date(obj.getHiredate().getTime()));
            st.setInt(5, obj.getSal());
            st.setInt(6, obj.getComm());
            st.setInt(7, obj.getDeptno());

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
