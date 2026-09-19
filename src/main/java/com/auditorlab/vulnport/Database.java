package com.auditorlab.vulnport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class Database {

    private static final String URL = "jdbc:h2:mem:vulnport;DB_CLOSE_DELAY=-1";
    private static boolean initialized = false;

    private Database() {}

    public static synchronized Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        Connection cn = DriverManager.getConnection(URL, "sa", "");
        if (!initialized) {
            initialize(cn);
            initialized = true;
        }
        return cn;
    }

    private static void initialize(Connection cn) throws Exception {
        Statement st = cn.createStatement();

        st.execute("CREATE TABLE IF NOT EXISTS USERS (" +
                "ID INT PRIMARY KEY, USERNAME VARCHAR(60), PASSWORD VARCHAR(120), " +
                "FULL_NAME VARCHAR(120), EMAIL VARCHAR(120), ROLE VARCHAR(30))");

        st.execute("CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY, NAME VARCHAR(120), OWNER VARCHAR(80), PRICE DECIMAL(12,2))");

        st.execute("DELETE FROM USERS");
        st.execute("DELETE FROM PRODUCTS");

        // Contraseñas deliberadamente en texto plano para el laboratorio.
        st.execute("INSERT INTO USERS VALUES " +
                "(1,'admin','admin123','Administrador VulnPort','admin@lab.local','ADMIN')," +
                "(2,'analyst','analyst123','Analista Auditor','analyst@lab.local','ANALYST')," +
                "(3,'user','user123','Usuario Demo','user@lab.local','USER')");

        st.execute("INSERT INTO PRODUCTS VALUES " +
                "(1,'Diesel Demo','ACME',1250.00)," +
                "(2,'Gasolina Demo','PORTLAB',980.00)," +
                "(3,'Lubricante Demo','ACME',330.00)");

        st.close();
    }
}
