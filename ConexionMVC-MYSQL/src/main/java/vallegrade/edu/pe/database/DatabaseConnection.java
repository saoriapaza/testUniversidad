package vallegrade.edu.pe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 private static final String URL = "jdbc:mysql://localhost:33060/universidad";
 private static final String USER = "root";  // Ajusta tu usuario y contraseña
 private static final String PASSWORD = "entrecodigosycafe";  // Ajusta tu contraseña

 public static Connection getConnection() throws SQLException {
  return DriverManager.getConnection(URL, USER, PASSWORD);
 }
}
