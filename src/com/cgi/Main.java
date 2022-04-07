package com.cgi;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {

        //insertDb();

        updateDb();

        selectDb();

    }

    public static void selectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            // createStatement() : pour une requête sans paramètre
                Statement st1 = c.createStatement();
                ResultSet result = st1.executeQuery("SELECT * FROM emp;");

            // prepareStatement() : requete avec paramètres
                /*PreparedStatement st2 = c.prepareStatement("SELECT * FROM emp WHERE sal > ?");
                st2.setInt(1, 1500);
                ResultSet result = st2.executeQuery();*/

            // récupère tous les employés dont le salaire est inférieur à 2000 et job = SALESMAN ou MANAGER
            /*PreparedStatement st3 = c.prepareStatement("SELECT * FROM emp WHERE sal < ? AND (job = ? OR job = ?)");
            st3.setInt(1, 2000);
            st3.setString(2, "SALESMAN");
            st3.setString(3, "MANAGER");
            ResultSet result = st3.executeQuery();*/

            while (result.next()) {
                System.out.println(result.getString("ename") + " a un emploi de " + result.getString("job") +
                        " et un salaire de " + result.getInt("sal"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertDb() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date d = sdf.parse("07/04/2022");

            PreparedStatement st = c.prepareStatement("INSERT INTO emp VALUES(?,?,?,?,?,?,?,?)");
            st.setInt(1, 0007);
            st.setString(2, "Saruman");
            st.setString(3, "Wizard");
            st.setInt(4, 9999);
            // on ne peut pas importer en même temps java.sql.Date et java.util.Date, donc on fait un appel direct :
            st.setDate(5, new java.sql.Date(d.getTime()));
            st.setInt(6, 100000);
            st.setInt(7, 8888);
            st.setInt(8, 30);

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDb() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            PreparedStatement st = c.prepareStatement("UPDATE emp SET sal = ? WHERE empno = ?");
            st.setInt(1, 300000);
            st.setInt(2, 0007);

            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
