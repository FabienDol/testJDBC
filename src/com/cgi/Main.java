package com.cgi;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott", "root", "");

            // createStatement() : pour une requête sans paramètre
            /*Statement st1 = c.createStatement();
            ResultSet result = st1.executeQuery("SELECT * FROM emp;");*/

            // prepareStatement() : requete avec paramètres
            /*PreparedStatement st2 = c.prepareStatement("SELECT * FROM emp WHERE sal > ?");
            st2.setInt(1, 1500);
            ResultSet result = st2.executeQuery();*/

            // récupère tous les employés dont le salaire est inférieur à 2000 et job = SALESMAN ou MANAGER
            PreparedStatement st3 = c.prepareStatement("SELECT * FROM emp WHERE sal < ? AND (job = ? OR job = ?)");
            st3.setInt(1, 2000);
            st3.setString(2, "SALESMAN");
            st3.setString(3, "MANAGER");
            ResultSet result = st3.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("ename") + " a un emploi de " + result.getString("job") +
                        " et un salaire de " + result.getInt("sal"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
